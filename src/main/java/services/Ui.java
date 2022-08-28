package services;

import exceptions.DukeException;

public class Ui {
    public static void dukePrint(String s) {
        System.out.println("---->".repeat(10));
        System.out.println(s);
        System.out.println("<----".repeat(10));
    }

    public void customPrintError(DukeException ex) {
        dukePrint(" ☹ OOPS!!! " + ex.getMessage());
    }

    public void customPrintError(String s) {
        dukePrint(" ☹ OOPS!!! " + s);
    }
}
