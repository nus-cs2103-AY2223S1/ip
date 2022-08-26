package duke.utils;

import duke.exception.DukeException;
import java.util.Scanner;

public class UI {
    Scanner reader;

    public UI() {
        reader = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        return reader.nextLine();
    }

    public void showError(DukeException e) {
        System.out.println(e);
    }

    public void showTasks() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showMatchingTasks() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
