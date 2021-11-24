package by.it._classwork_.jd01_12;

import java.util.ArrayList;
import java.util.LinkedList;

public class TaskB3 {
    static String process(ArrayList<String> peoples) {
        while (peoples.size() > 1) {
            String first = peoples.remove(0);
            peoples.remove(0);
            peoples.add(first);
        }
        return peoples.get(0);
    }

    static String process(LinkedList<String> peoples) {
        while (peoples.size() > 1) {
            String first = peoples.pollFirst();
            peoples.removeFirst();
            peoples.addLast(first);
        }
        return peoples.get(0);
    }
}
