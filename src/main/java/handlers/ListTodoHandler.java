package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import dto.ListTodoRequestDto;
import dto.TodoResponseDto;
import service.TodoService;

import java.util.List;
import java.util.Optional;

public class ListTodoHandler {

    public List<TodoResponseDto> handleRequest(ListTodoRequestDto listTodoRequestDto, Context context) {
        TodoService todoService = new TodoService();
        List<TodoResponseDto> responseDtos = todoService.getTodoList(Optional.ofNullable(listTodoRequestDto.getExcludeCompleted()));
        context.getLogger().log("Input: " + listTodoRequestDto.getExcludeCompleted());
        return responseDtos;
    }
}
