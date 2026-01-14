package com.example;

import java.util.List;

public class Cat {

    Predator predator;

    // ToDo помоему тут лучше принимать параметр Predator predator
    public Cat(Feline feline) {
        this.predator = feline;
    }

    public String getSound() {
        return "Мяу";
    }

    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }

}
