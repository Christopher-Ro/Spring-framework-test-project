package com.homework.allrest.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ToDoService {
    private static final String TODO_API = """
            https://jsonplaceholder.typicode.com/todos/""";
    @Autowired
    private ToDoRepository toDoRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Iterable<ToDo> getCachedToDos() {
        return toDoRepository.findAll();
    }

    public ToDo getToDo(Integer id) {
        return toDoRepository.findById(id)
                .orElseGet(() -> {
                    ToDo todo = restTemplate.getForObject(TODO_API + id,
                            ToDo.class);
                    toDoRepository.save(todo);
                    return todo;
                });
    }
}
