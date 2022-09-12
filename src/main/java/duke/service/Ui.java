package duke.service;

import duke.exceptions.DukeException;

/** Handles Ui functionality. */
public class Ui {
    /**
     * Prints a message in a custom format.
     *
     * @param s message to print.
     */
    public void customPrint(String s) {
        System.out.println("--------------------");
        System.out.println(s);
        System.out.println("--------------------");
    }

    /**
     * Prints an error message in a custom format.
     *
     * @param ex DukeException.
     */
    public void customPrintError(DukeException ex) {
        customPrint(" ☹ OOPS!!! " + ex.getMessage());
    }

    /**
     * Prints an error message.
     *
     * @param s Error message.
     */
    public void customPrintError(String s) {
        customPrintError(" ☹ OOPS!!! " + s);
    }
}
