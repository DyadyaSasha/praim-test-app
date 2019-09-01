package app;

import java.lang.reflect.Field;
import java.util.Arrays;


/**
 * Класс {@code Task} предоставляет метод {@link app.Task#getSortedArray(java.lang.String)},
 * позволяющий из входной строки целых чисел, разделённых ";",
 * получить отсортированный массив чётных чисел
 *
 * @author Alex Serebryakov
 * @since 1.0
 */
public class Task {

    /**
     * Вывод отсортированного массива чётных чисел из входного значения строки
     * @param input
     *        строка, представляющие целые числа, разделённые ";",
     *        строка должна содержать хотя бы одно целое число,
     *        строка не должна заканчиваться символом разделителя ";"
     * @return отсортированный массив целых чётных чисел
     * @throws NullPointerException
     *         если {@code input} ссылается на null
     * @since 1.0
     */
    public int[] getSortedArray(final String input) throws NullPointerException{

        char[] charArray;
        try {
            charArray = getValueFromString(input);
        } catch (NoSuchFieldException | IllegalAccessException e){
            return null;
        }

        int numbersCount = getNumbersCount(charArray);
        int[] numbers = new int[numbersCount];
        int evenNumbersCount = 0;

        int currentPositionInArray = 0;
        for (int i = 0; i < numbersCount; i++) {

            boolean isNegative = charArray[currentPositionInArray] == '-';
            if (isNegative){
                currentPositionInArray++;
            }

            int number = 0;

            while (currentPositionInArray < charArray.length && charArray[currentPositionInArray] != ';') {

                number = number * 10 + (charArray[currentPositionInArray] - 48);
                currentPositionInArray += 1;
            }
            currentPositionInArray += 1;

            if (isNegative){
                number = -number;
            }

            if ((number & 1) == 0) {
                evenNumbersCount++;
                numbers[i] = number;
            }
        }

        if (evenNumbersCount < numbers.length) {
            if (evenNumbersCount == 0) {
                return null;
            }
            int[] newNumbers = new int[evenNumbersCount];
            currentPositionInArray = 0;
            for (int number : numbers) {
                if (number != 0) {
                    newNumbers[currentPositionInArray] = number;
                    currentPositionInArray++;
                }
            }
            Arrays.sort(newNumbers);
            return newNumbers;
        } else {
            Arrays.sort(numbers);
            return numbers;
        }

    }

    private int getNumbersCount(final char[] charArray) {
        int numbersCount = 1;
        for (char chr : charArray) {
            if (chr == ';') {
                numbersCount++;
            }
        }
        return numbersCount;
    }

    private char[] getValueFromString(final String input) throws NoSuchFieldException, IllegalAccessException {
        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        return (char[]) field.get(input);
    }

}
