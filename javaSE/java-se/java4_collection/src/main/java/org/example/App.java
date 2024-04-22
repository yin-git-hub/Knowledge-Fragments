package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        List<String> list = new ArrayList<String>();
        list.add("123");
        list.add("456");
        Object[] array =   list.toArray();
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].getClass());
        }
    }
}
