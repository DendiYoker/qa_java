package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LionTest {
    @Mock
    private Feline felineSpy;

    @Test
    @DisplayName("getKittens() должен делегировать вызов Feline.getKittens()")
    void getKittens_DelegatesToFeline() throws Exception {
        Lion lion = new Lion("Самец", felineSpy);

        Mockito.when(felineSpy.getKittens()).thenReturn(3);

        lion.getKittens();

        Mockito.verify(felineSpy, Mockito.times(1)).getKittens();
    }

    @Test
    @DisplayName("getKittens() возвращает результат Feline.getKittens()")
    void getKittens_ReturnsResultFromFeline() throws Exception {
        Lion lion = new Lion("Самка", felineSpy);
        int expectedKittens = 5;

        Mockito.when(felineSpy.getKittens()).thenReturn(expectedKittens);

        int actualKittens = lion.getKittens();

        assertEquals(expectedKittens, actualKittens);
    }



    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    @DisplayName("Проверка наличия гривы у льва в зависимости от пола + проверка конструктора")
    void testDoesHaveMane_WithDifferentSex_ReturnsExpectedResul(String sex,boolean expectedHasMane ) throws Exception {
        Lion lion = new Lion(sex, felineSpy);

        boolean result = lion.doesHaveMane();

        assertEquals(expectedHasMane, result);
    }

    // Тест на исключение для невалидного пола
    @Test
    @DisplayName("Проверка конструктора, при невалидном поле должно выбрасываться исключение")
    void lionConstructor_WithInvalidSex_ThrowsException() {

        String expectedMessage = "Используйте допустимые значения пола животного - самец или самка";

        Exception actualException = assertThrows(Exception.class,
                () -> new Lion("Чупакабра", felineSpy));

        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    @DisplayName("Проверка, что getFood вызывает метод getFood абстрактного класса Animal один раз, с нужным параметром")
    void testGetFood_DelegatesToPredator() throws Exception {
        Lion lion = new Lion("Самец", felineSpy);
        List<String> dummyFood = List.of("мясо", "птица");

        Mockito.when(felineSpy.getFood("Хищник")).thenReturn(dummyFood);

        lion.getFood();

        Mockito.verify(felineSpy, Mockito.times(1)).getFood("Хищник");

    }

    @Test
    @DisplayName("Проверка, что getFood возвращает то что получил из метода getFood")
    void testGetFood_ReturnsPredatorResult() throws Exception {

        Lion lion = new Lion("Самец", felineSpy);

        List<String> mockedGetFood = List.of("Мокаем что угодно", "лишь бы не null");
        Mockito.when(felineSpy.getFood("Хищник")).thenReturn(mockedGetFood);

        List<String> actualFood = lion.getFood();

        assertEquals(mockedGetFood, actualFood);

    }

    @Test
    @DisplayName("Проверка, что getFood пробрасывает исключение от Feline.getFood")
    void getFood_ShouldPropagateExceptionFromFeline() throws Exception {
        Lion lion = new Lion("Самец", felineSpy);
        Exception expectedException = new Exception("Ошибка получения еды");
        Mockito.when(felineSpy.getFood("Хищник")).thenThrow(expectedException);

        Exception actualException = assertThrows(Exception.class, lion::getFood);

        assertEquals(expectedException, actualException);
    }
}