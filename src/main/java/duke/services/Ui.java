package duke.services;

import duke.exceptions.DukeException;

public class Ui {
    public static void dukePrint(String str) {
        System.out.println(str);
    }

    public void printError(DukeException e) {
        dukePrint(" ☹ OOPS!!! " + e.getMessage());
    }

    public void printError(String str) {
        dukePrint((" ☹ OOPS!!! " + str));
    }
}
