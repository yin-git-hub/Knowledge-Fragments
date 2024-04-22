package org.example;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Hashtable<Object, Object> object1 = new Hashtable<Object, Object>();
        ConcurrentHashMap<Object, Object> object2 =
                new ConcurrentHashMap<Object, Object>();
        object2.put("k1", "v1");
    }
}
