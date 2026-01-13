package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FelineTest {

    // Spy для частичного мока Feline
    @Spy
    private Feline felineSpy;

    // Mock для Animal (родительский класс)
    @Mock
    private Animal animalMock;

    @Test
    @DisplayName("Проверка, что eatMeat() вызывет getFood() 1 раз и с правильным параметром, а также возвращает то же, что и getFood()")
    void testEatMeatCallsGetFoodWithPredator() throws Exception {
        // Arrange
        // т.к нам не нужно проверять класс Animal, то мокируем ответ от этого класса
        List<String> mockedGetFood = List.of("Мокаем что угодно", "лишь бы не null");
        Mockito.when(felineSpy.getFood("Хищник")).thenReturn(mockedGetFood);

        // Act
        List<String> actualFood = felineSpy.eatMeat();

        // Assert
        // 1. Проверяем, что getFood вызван 1 раз с параметром "Хищник"
        Mockito.verify(felineSpy, Mockito.times(1)).getFood("Хищник");

        // 2. Проверяем, что eatMeat() возвращает то же, что и getFood()
        assertEquals(mockedGetFood, actualFood,
                "eatMeat() должен возвращать результат getFood()");

    }

    @Test
    @DisplayName("Проверяем, что исключения из getFood() пробрасываются в/из eatMeat()")
    void testEatMeat_PropagatesException() throws Exception {
        // Arrange
        Exception expectedException = new Exception("Ошибка из getFood");
        Mockito.when(felineSpy.getFood("Хищник")).thenThrow(expectedException);

        // Act & Assert
        Exception actualException = assertThrows(Exception.class,
                () -> felineSpy.eatMeat());

        assertEquals(expectedException, actualException);
    }

    @Test
    void getFamily() {
    }

    @Test
    void getKittens() {
    }

    @Test
    void testGetKittens() {
    }
}