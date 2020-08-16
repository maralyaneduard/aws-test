package service;

import dao.TodoRepository;
import dto.TodoRequestDto;
import dto.TodoResponseDto;
import model.TodoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoService {

    public List<TodoResponseDto> getTodoList(Optional<Integer> completed){
        List<TodoModel> models = new ArrayList<>();
        if(completed.isPresent()){
            models.addAll(TodoRepository.findAllByCompleted(completed.get() == 1));
        } else {
            models.addAll(TodoRepository.findAll());
        }
        return models.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    public void addTodo(TodoRequestDto requestDto){
        TodoRepository.addTodo(convertToModel(requestDto));
    }

    public void updateTodo(TodoRequestDto requestDto){
        TodoRepository.updateTodo(convertToModel(requestDto));
    }

    // region private utility method
    private TodoResponseDto convertToResponseDto(TodoModel model){
        TodoResponseDto dto = new TodoResponseDto();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setCompleted(model.isCompleted());
        return dto;
    }

    private TodoModel convertToModel(TodoRequestDto dto){
        TodoModel model = new TodoModel();
        model.setId(dto.getId());
        model.setTitle(dto.getTitle());
        model.setCompleted(dto.isCompleted());
        return model;
    }

    //endregion
}
