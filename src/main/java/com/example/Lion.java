package com.example;

import java.util.List;

public class Lion {

    boolean hasMane;
    private final AnimalBehavior animalBehavior;

    public Lion(String sex, AnimalBehavior animalBehavior) throws Exception {
        this.animalBehavior = animalBehavior;

        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самей или самка");
        }
    }

    Feline feline = new Feline();

    public int getKittens() {
        return animalBehavior.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return animalBehavior.getFood("Хищник");
    }
}
