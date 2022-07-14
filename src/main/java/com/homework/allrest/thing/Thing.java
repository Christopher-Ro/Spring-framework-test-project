package com.homework.allrest.thing;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Thing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private Integer year;

    public Thing update(Thing other) {
        this.title = other.title == null ? this.title : other.title;
        this.year = other.year == null ? this.year : other.year;
        return this;
    }
}
