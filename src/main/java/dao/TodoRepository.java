package dao;

import model.TodoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository {

    //region queries
    private static final String FIND_ALL_QUERY = "select * from todo";
    private static final String FIND_ALL_BY_COMPLETED_QUERY = "select * from todo where completed = ?";
    private static final String ADD_TODO_QUERY = "insert into todo(title, completed) values (?,?)";
    private static final String UPDATE_TODO_QUERY = "update todo set completed = ? where id = ?";
    //endregion

    public static List<TodoModel> findAll(){
        List<TodoModel> todos = new ArrayList<>();
        Connection con = getRemoteConnection();
        try (PreparedStatement prSt = con.prepareStatement(FIND_ALL_QUERY)){
            try(ResultSet rs = prSt.executeQuery()){
                while(rs.next()){
                    todos.add(getTodoModel(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong during db call");
        }
        return todos;
    }

    public static List<TodoModel> findAllByCompleted(boolean completed){
        List<TodoModel> todos = new ArrayList<>();
        Connection con = getRemoteConnection();
        try (PreparedStatement prSt = con.prepareStatement(FIND_ALL_BY_COMPLETED_QUERY)){
            prSt.setBoolean(1, completed);
            try(ResultSet rs = prSt.executeQuery()){
                while(rs.next()){
                    todos.add(getTodoModel(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong during db call");
        }
        return todos;
    }

    public static void addTodo(TodoModel todo){
        Connection con = getRemoteConnection();
        try (PreparedStatement prSt = con.prepareStatement(ADD_TODO_QUERY)){
            prSt.setString(1, todo.getTitle());
            prSt.setBoolean(2, todo.isCompleted());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong during db call");
        }
    }

    public static void updateTodo(TodoModel todo){
        Connection con = getRemoteConnection();
        try (PreparedStatement prSt = con.prepareStatement(UPDATE_TODO_QUERY)){
            prSt.setBoolean(1, todo.isCompleted());
            prSt.setInt(2, todo.getId());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong during db call");
        }
    }

    //region private utility methods
    private static Connection getRemoteConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String jdbcUrl = "jdbc:postgresql://test.chqrsln9e0ai.us-east-2.rds.amazonaws.com:5432/test?user=root&password=Admin1234";
            Connection con = DriverManager.getConnection(jdbcUrl);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static TodoModel getTodoModel(ResultSet rs) throws SQLException {
        TodoModel todo = new TodoModel();
        todo.setId(rs.getInt("id"));
        todo.setTitle(rs.getString("title"));
        todo.setCompleted(rs.getBoolean("completed"));
        return todo;
    }
    //endregion
}
