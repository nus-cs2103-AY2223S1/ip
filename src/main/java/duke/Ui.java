package duke;

import duke.models.Task;

import java.util.List;
import java.util.ListIterator;

public class Ui {
    public Ui() {

    }

    public void showWelcome() {
        this.showIndentedDottedLines();
        System.out.println(Constants.INDENTED_DOTTED_LINE);
        System.out.println(Constants.WELCOME_MESSAGE);
        System.out.println(Constants.INDENTED_DOTTED_LINE);
        this.showIndentedDottedLines();
    }

    public void showIndentedDottedLines() {
        System.out.println(Constants.INDENTED_DOTTED_LINE);
    }

    public void showByeMessage() {
        this.showIndentedDottedLines();
        System.out.println(Constants.INDENTED_DOTTED_LINE);
        System.out.println(Constants.indent + "Bye! Hope to see you again soon!");
        this.showIndentedDottedLines();
    }

    public void newItemAdded(Task t, int size) {
        this.showIndentedDottedLines();
        System.out.println(Constants.indent + "Got it. I've added this task:");
        System.out.println(Constants.indent + Constants.indent + t);
        System.out.println(Constants.indent +"Now you have " + size + " tasks in the list.");
        this.showIndentedDottedLines();
    }

    public void showTaskMarkMessage(Task t) {
        this.showIndentedDottedLines();
        System.out.println(Constants.indent + "Nice! I've marked this task as done");
        System.out.println(Constants.indent+ Constants.indent + t);
        this.showIndentedDottedLines();
    }

    public void showTaskUnmarkMessage(Task t) {
        this.showIndentedDottedLines();
        System.out.println(Constants.indent + "OK, I've marked this task as not done yet");
        System.out.println(Constants.indent + Constants.indent + t);
        this.showIndentedDottedLines();

    }

    public void showTaskDeletedMessage(Task t, int size) {
        this.showIndentedDottedLines();
        System.out.println(Constants.indent +"Noted. I've removed this task:");
        System.out.println(Constants.indent + Constants.indent + t);
        System.out.println(Constants.indent +"Now you have " + size + " tasks in the list.");
        this.showIndentedDottedLines();
    }

    public void listAllTasks(List<Task> taskList) {
        ListIterator<Task> listIterator = taskList.listIterator();
        this.showIndentedDottedLines();
        while (listIterator.hasNext()) {
            Task t = listIterator.next();
            System.out.println(Constants.indent + listIterator.nextIndex() +
                    ". " + t);
        }
        this.showIndentedDottedLines();
    }
}
