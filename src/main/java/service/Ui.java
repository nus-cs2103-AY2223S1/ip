package service;

import exceptions.DukeException;

public class Ui {
    public void customPrint(String s) {
        System.out.println("--------------------");
        System.out.println(s);
        System.out.println("--------------------");
    }

    public void customPrintError(DukeException ex) {
        customPrint(" ☹ OOPS!!! " + ex.getMessage());
    }

    public void customPrintError(String s) {
        customPrintError(" ☹ OOPS!!! " + s);
    }
}
