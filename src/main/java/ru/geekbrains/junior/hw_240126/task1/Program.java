package ru.geekbrains.junior.hw_240126.task1;

// Напишите программу, которая использует Stream API для обработки списка чисел.
// Программа должна вывести на экран среднее значение всех четных чисел в списке.

import java.util.Arrays;
import java.util.List;

public class Program
{
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(4, 3, 1, 2, 7, 5, 6, 9);
        double result = numbers
                .stream()
                .filter(n -> n%2==0)
                .mapToDouble(n -> n)
                .average()
                .orElse(0.0);
        System.out.println(result);
    }
}