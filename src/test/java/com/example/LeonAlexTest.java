package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LeonAlexTest {
    @Spy
    Feline feline;

    @Test
    @DisplayName("Проверка что метод возвращает корректное значение")
    void testGetFriends_ReturnsCorrectValue() throws Exception {
        LeonAlex leonAlex = new LeonAlex(feline);
        List<String> expectedResult = List.of("Марти", "Глория", "Мелман");

        List<String> actualResult = leonAlex.getFriends();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Проверка что метод возвращает корректное значение")
    void testGetPlaceOfLiving_ReturnsCorrectValue() throws Exception {
        LeonAlex leonAlex = new LeonAlex(feline);
        String expectedResult = "Нью-Йоркский зоопарк";

        String actualResult = leonAlex.getPlaceOfLiving();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Проверка что метод возвращает корректное значение")
    void tesGetKittens_ReturnsCorrectValue() throws Exception {
        LeonAlex leonAlex = new LeonAlex(feline);
        int expectedResult = 0;

        int actualResult = leonAlex.getKittens();

        assertEquals(expectedResult, actualResult);


    }
}