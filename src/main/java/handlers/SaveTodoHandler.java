package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import dto.TodoRequestDto;
import service.TodoService;

public class SaveTodoHandler {

    public void handleRequest(TodoRequestDto todoDto, Context context) {
        TodoService todoService = new TodoService();
        todoService.addTodo(todoDto);
        context.getLogger().log("Todo have been successfully added");
    }
}
