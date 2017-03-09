package track.lessons.lesson1;

import java.util.Scanner;
import java.io.File;
import java.io.*;

/**
 * Задание 1: Реализовать два метода
 * <p>
 * Формат файла: текстовый, на каждой его строке есть (или/или)
 * - целое число (int)
 * - текстовая строка
 * - пустая строка (пробелы)
 * <p>
 * <p>
 * Пример файла - words.txt в корне проекта
 * <p>
 * ******************************************************************************************
 * Пожалуйста, не меняйте сигнатуры методов! (название, аргументы, возвращаемое значение)
 * <p>
 * Можно дописывать новый код - вспомогательные методы, конструкторы, поля
 * <p>
 * ******************************************************************************************
 */
public class CountWords {

    /**
     * Метод на вход принимает объект File, изначально сумма = 0
     * Нужно пройти по всем строкам файла, и если в строке стоит целое число,
     * то надо добавить это число к сумме
     *
     * @param file - файл с данными
     * @return - целое число - сумма всех чисел из файла
     */
    public long countNumbers(File file) throws Exception {
        Scanner scanIn = new Scanner(file);
        long sumInt = 0;
        String bufString;
        while (scanIn.hasNext()) {

            if (scanIn.hasNextInt()) {
                sumInt += scanIn.nextInt();
            } else if (scanIn.hasNextLine()) {
                bufString = scanIn.nextLine();
            }

        }
        scanIn.close();
        return sumInt;
    }


    /**
     * Метод на вход принимает объект File, изначально результат= ""
     * Нужно пройти по всем строкам файла, и если в строка не пустая и не число
     * то надо присоединить ее к результату через пробел
     *
     * @param file - файл с данными
     * @return - результирующая строка
     */
    public String concatWords(File file) throws Exception {
        Scanner scanIn = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        String bufString;
        int bufNumber;
        while (scanIn.hasNext()) {

            if (scanIn.hasNextInt()) {
                bufNumber = scanIn.nextInt();
            } else {
                bufString = scanIn.nextLine().trim();
                if (!bufString.equals("\n") & !bufString.equals("")) {
                    builder.append(" ");
                    builder.append(bufString);
                }
            }

        }

        String sumString = builder.toString().trim();
        scanIn.close();
        return sumString;
    }
}


