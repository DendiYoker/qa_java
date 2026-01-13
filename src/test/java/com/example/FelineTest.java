package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
    @DisplayName("Проверка, что метод getFamily возвращает то что нужно")
    void testGetFamily_ReturnsCorrectValue() {
        Feline feline = new Feline();
        String expectedReturnFamily = "Кошачьи";

        String actualReturnFamily = feline.getFamily();

        assertEquals(expectedReturnFamily, actualReturnFamily, String.format("Метод getFamily() должен возвращать '%s'", expectedReturnFamily));


    }

    @Test
    @DisplayName("Проверка метода getKittens без параметра : убедиться что метод вызывает getKittens с парамтером 1")
    void tesGetKittens_WithoutParam() {

        int actualResult = felineSpy.getKittens();

        Mockito.verify(felineSpy, Mockito.times(1)).getKittens(1);

        assertEquals(1, actualResult);

    }


    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1, 10, Integer.MAX_VALUE})
    @DisplayName("Проверка метода getKittens с параметро: убедиться что метод возвращает параметр")
    void getKittens_WithParameter_ReturnsParameter(int kittensCount) {
        Feline feline = new Feline();

        int actualResult = feline.getKittens(kittensCount);

        assertEquals(kittensCount, actualResult);

    }
}