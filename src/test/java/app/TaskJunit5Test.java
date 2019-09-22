package app;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

public class TaskJunit5Test {

    private Task task;
    private String input;
    private int[] expected;

    @BeforeEach
    void prepareTestFixture() {
        task = new Task();
    }

    @AfterEach
    void clearTestFixture() {
        task = null;
    }

    @Test
    void testGetSortedArrayThrowsNPE() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            task.getSortedArray(null);
        });
    }

    /**
     * Данный тест тестирует ту же функциональность, что и тест testGetSortedArrayThrowsNPE, но с использованием
     * аннотации Junit5 {@link NullSource} - вместо аргумента {@code input} встанет значение null
     * @param input
     */
    @ParameterizedTest
    @NullSource
    void testGetSortedArrayThrowsNPEViaJunitSource(String input){
        Assertions.assertThrows(NullPointerException.class, () -> {
            task.getSortedArray(input);
        });
    }

    @ParameterizedTest
    @EmptySource
    void testGetSortedArrayWithEmptyString(String input){
        Assertions.assertThrows(EmptyStringException.class, () -> {
           task.getSortedArray(input);
        });
    }

    private static Stream<Arguments> provideTestData(){
        return Stream.of(
                Arguments.of("5", null),
                Arguments.of("4", new int[]{4}),
                Arguments.of("1;4;2;6;7;3;2;155", new int[]{2, 2, 4, 6}),
                Arguments.of("23;44;458887;0;99991;-16;555521;999999994;-123;-24;1231;22;135;22;1;-6", new int[]{-24, -16, -6, 0, 22, 22, 44, 999999994}),
                Arguments.of("5;7;9;11", null),
                Arguments.of("2;6;2;4;-4;-18", new int[]{-18, -4, 2, 2, 4, 6})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "provideTestData")
    void testGetSortedArray(String input, int[] expected){
        Assertions.assertArrayEquals(expected, task.getSortedArray(input));
    }




}
