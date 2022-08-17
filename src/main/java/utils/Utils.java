package utils;

import entities.Task;

import java.util.List;

public class Utils {

    public static void customPrint(String s) {
        System.out.println("--------------------");
        System.out.println(s);
        System.out.println("--------------------");
    }

    public static void addToList(List list, Task t) {
        list.add(t);
        int size = list.size();
        if (size == 1) {
            customPrint(String.format("Got it. I've added this task:\n  " +
                    t +
                    "\nNow you have %d task in the list.", size));
            return;
        }
        customPrint(String.format("Got it. I've added this task:\n  " +
                t +
                "\nNow you have %d tasks in the list.", size));
    }
}
