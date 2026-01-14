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

@ExtendWith(MockitoExtension.class)
class CatTest {

    @Spy
    Feline felineSpy;

    @Test
    @DisplayName("Проверка что метод возвращает корректное значение")
    void testGetSound_ReturnCurrenValue() {
        Cat cat = new Cat(felineSpy);
        String expectedValue = "Мяу";

        String actualResult = cat.getSound();

        assertEquals(expectedValue, actualResult);

    }

    @Test
    @DisplayName("Проверка, что getFood вызывает метод eatMeat один раз")
    void testGetFood_DelegatesToPredator() throws Exception {
        Cat cat = new Cat(felineSpy);

        List<String> actualFood = cat.getFood();

        Mockito.verify(felineSpy, Mockito.times(1)).eatMeat();

    }

    @Test
    @DisplayName("Проверка, что getFood возвращает то что получил из метода eatMeat")
    void testGetFood_ReturnsPredatorResult() throws Exception {

        Cat cat = new Cat(felineSpy);

        List<String> mockedEatMeat = List.of("Мокаем что угодно", "лишь бы не null");
        Mockito.when(felineSpy.eatMeat()).thenReturn(mockedEatMeat);

        List<String> actualFood = cat.getFood();

        assertEquals(mockedEatMeat, actualFood);

    }

    @Test
    @DisplayName("Проверка, что метод getFood пробрасывает исключения от метода eatMeat")
    void testGetFood_PropagatesExceptions() throws Exception {

        Cat cat = new Cat(felineSpy);
        Exception expectedException = new Exception("Нет еды");

        Mockito.when(felineSpy.eatMeat()).thenThrow(expectedException);

        // Act & Assert
        Exception actualException = assertThrows(Exception.class, cat::getFood);
        assertEquals(expectedException, actualException);
    }
}