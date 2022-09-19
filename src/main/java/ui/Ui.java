package ui;

import exception.DukeException;

import java.util.Scanner;

public class Ui {
    protected Scanner in;
    protected String nextCommand;

    public Ui() {
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?\n");
        this.in = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke.Duke\nWhat can I do for you?\n");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return this.in.nextLine();
    }

    public void changeCommand() {
        this.nextCommand = this.in.nextLine();
    }

    public void nextCommand() {
        this.in.nextLine();
    }

    public void showLoadingError() throws DukeException {
        try {
            throw new DukeException("The file is empty!");
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLine() {
        System.out.println("---------------------------------");
    }

}
