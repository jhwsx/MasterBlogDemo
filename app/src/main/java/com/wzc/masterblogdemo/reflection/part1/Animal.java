package com.wzc.masterblogdemo.reflection.part1;

import java.io.Serializable;

public class Animal implements IAnimal, Serializable {
    private String name;

    @Override
    public String getName() {
        return name == null ? "" : name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}