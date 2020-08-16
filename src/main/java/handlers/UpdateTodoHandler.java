package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import dto.TodoRequestDto;
import service.TodoService;

public class UpdateTodoHandler {

    public void handleRequest(TodoRequestDto todoDto, Context context) {
        TodoService todoService = new TodoService();
        todoService.updateTodo(todoDto);
        context.getLogger().log("Todo have been successfully updated");
    }
}
