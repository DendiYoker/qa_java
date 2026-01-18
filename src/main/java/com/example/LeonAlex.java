package com.example;

import java.util.List;

public class LeonAlex extends Lion{
    private static final String ALEX_SEX = "Самец";
    private static final String LIVING_PLACE = "Нью-Йоркский зоопарк";
    private static final List<String> FRIENDS = List.of("Марти", "Глория", "Мелман");

    public LeonAlex(Feline feline) throws Exception {
        super(ALEX_SEX, feline);
    }

    public List<String> getFriends(){
        return FRIENDS;

    }

    public String getPlaceOfLiving() {
        return LIVING_PLACE;
    }

    @Override
    public int getKittens(){
        return 0;
    }


}
