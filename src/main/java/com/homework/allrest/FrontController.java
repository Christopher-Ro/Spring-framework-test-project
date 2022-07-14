package com.homework.allrest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.homework.allrest.thing.ThingServise;
import com.homework.allrest.todo.ToDoService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class FrontController {
    private ThingServise thingServise;
    private ToDoService toDoService;

    @GetMapping("/")
    public String getLook(Model model) {
        model.addAttribute("things", thingServise.getThings());
        model.addAttribute("todos", toDoService.getCachedToDos());
        return "look";
    }
}
