package com.example;

import java.util.List;

public interface AnimalBehavior {
    int getKittens();
    List<String> getFood(String type) throws Exception;
}
