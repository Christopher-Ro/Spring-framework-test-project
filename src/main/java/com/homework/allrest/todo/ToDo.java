package com.homework.allrest.todo;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToDo {
    @Id
    private Integer id;
    private Integer userId;
    private String title;
    private boolean completed;
}
