package org.example;

 import java.util.Arrays;
 import java.util.Random;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        int size = 10000000; // 数组大小
        int[] array1 = new int[size]; // 创建一个大小为size的数组

        Random random = new Random(); // 创建一个Random对象

        for (int i = 0; i < size; i++) {
            array1[i] = random.nextInt(10001); // 生成一个随机整数并存入数组中
        }

        int[] array2 = new int[size]; // 创建一个大小为size的数组

         random = new Random(); // 创建一个Random对象

        for (int i = 0; i < size; i++) {
            array2[i] = random.nextInt(10001); // 生成一个随机整数并存入数组中
        }

        int[] array3 = new int[size]; // 创建一个大小为size的数组

         random = new Random(); // 创建一个Random对象

        for (int i = 0; i < size; i++) {
            array3[i] = random.nextInt(10001); // 生成一个随机整数并存入数组中
        }


        long bstart = System.currentTimeMillis();
        Arrays.parallelSort(array1);
        // Arrays.sort(array1);
        // BubbleSort.bubbleSort(array1);
        long bend = System.currentTimeMillis();

        // SelectionSort.selectionSort(array2);
        long send = System.currentTimeMillis();

        QuickSort.quickSort(array3, 0, array3.length - 1);
        long qend = System.currentTimeMillis();

        System.out.println("冒泡: " + (bend - bstart) + " ms " );
        System.out.println("选择: " + (send - bend)  + " ms " );
        System.out.println("快速: " + (qend - send) + " ms " );


    }

}
