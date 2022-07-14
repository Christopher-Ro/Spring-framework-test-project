package com.homework.allrest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.homework.allrest.thing.Thing;
import com.homework.allrest.thing.ThingServise;
import com.homework.allrest.todo.ToDo;
import com.homework.allrest.todo.ToDoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AllRestController {
    private ThingServise thingServise;
    private ToDoService toDoService;

    @GetMapping("/things")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Thing> getThings() {
        return thingServise.getThings();
    }

    @GetMapping("/things/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Thing getThing(@PathVariable Integer id) {
        return thingServise.getThing(id);
    }

    @PostMapping("/things")
    @ResponseStatus(HttpStatus.CREATED)
    public Thing createThing(@RequestBody Thing thing) {
        thingServise.createThing(thing);
        return thing;
    }

    @PutMapping("/things/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateThing(@RequestBody Thing thing,
                            @PathVariable Integer id) {
        thingServise.updateThing(thing, id);
    }

    @DeleteMapping("/things/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteThing(@PathVariable Integer id) {
        thingServise.deleteThing(id);
    }

    @GetMapping("/todos/cached")
    public Iterable<ToDo> getToDosCached() {
        return toDoService.getCachedToDos();
    }

    @GetMapping("/todos/{id}")
    public ToDo getToDo(@PathVariable Integer id) {
        return toDoService.getToDo(id);
    }
}
