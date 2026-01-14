package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LionTest {
    @Spy
    private AnimalBehavior animalBehaviorSpy ;

    @Test
    @DisplayName("getKittens() должен делегировать вызов animalBehavior.getKittens()")
    void getKittens_DelegatesToAnimalBehavior() throws Exception {
        Lion lion = new Lion("Самец", animalBehaviorSpy);

        Mockito.when(animalBehaviorSpy.getKittens()).thenReturn(3);

        int actualKittens = lion.getKittens();

        Mockito.verify(animalBehaviorSpy, Mockito.times(1)).getKittens();
    }

    @Test
    @DisplayName("getKittens() возвращает результат animalBehavior.getKittens()")
    void getKittens_ReturnsResultFromAnimalBehavior() throws Exception {
        Lion lion = new Lion("Самка", animalBehaviorSpy);
        int expectedKittens = 5;

        Mockito.when(animalBehaviorSpy.getKittens()).thenReturn(expectedKittens);

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
        Lion lion = new Lion(sex, animalBehaviorSpy);

        boolean result = lion.doesHaveMane();

        assertEquals(expectedHasMane, result);
    }

    // Тест на исключение для невалидного пола
    @Test
    @DisplayName("Проверка конструктора, при невалидном поле должно выбрасываться исключение")
    void lionConstructor_WithInvalidSex_ThrowsException() throws Exception {

        String expectedMessage = "Используйте допустимые значения пола животного - самец или самка";

        Exception actualException = assertThrows(Exception.class,
                () -> { new Lion("Чупакабра", animalBehaviorSpy);});

        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    @DisplayName("Проверка, что getFood вызывает метод getFood итерфейса один раз, с нужным параметром")
    void testGetFood_DelegatesToPredator() throws Exception {
        Lion lion = new Lion("Самец", animalBehaviorSpy);
        List<String> dummyFood = List.of("мясо", "птица");
        Mockito.when(animalBehaviorSpy.getFood("Хищник")).thenReturn(dummyFood);

        List<String> actualFood = lion.getFood();

        Mockito.verify(animalBehaviorSpy, Mockito.times(1)).getFood("Хищник");

    }

    @Test
    @DisplayName("Проверка, что getFood возвращает то что получил из метода getFood")
    void testGetFood_ReturnsPredatorResult() throws Exception {

        Lion lion = new Lion("Самец", animalBehaviorSpy);

        List<String> mockedGetFood = List.of("Мокаем что угодно", "лишь бы не null");
        Mockito.when(animalBehaviorSpy.getFood("Хищник")).thenReturn(mockedGetFood);

        List<String> actualFood = lion.getFood();

        assertEquals(mockedGetFood, actualFood);

    }

    @Test
    @DisplayName("Проверка, что getFood пробрасывает исключение от animalBehavior.getFood")
    void getFood_ShouldPropagateExceptionFromAnimalBehavior() throws Exception {
        Lion lion = new Lion("Самец", animalBehaviorSpy);
        Exception expectedException = new Exception("Ошибка получения еды");
        Mockito.when(animalBehaviorSpy.getFood("Хищник")).thenThrow(expectedException);


        Exception actualException = assertThrows(Exception.class, lion::getFood);

        assertEquals(expectedException, actualException);
    }
}