package chatbot.ui;

import chatbot.exceptions.DukeException;
import chatbot.tasks.Task;

import java.util.List;
import java.util.Scanner;

public class UI {
//    private static final String LOGO = " ____        _        \n"
//            + "|  _ \\ _   _| | _____ \n"
//            + "| | | | | | | |/ / _ \\\n"
//            + "| |_| | |_| |   <  __/\n"
//            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String EMOJI = "<_>";
    private final Scanner reader;

    public UI() {
        this.reader = new Scanner(System.in);
    }

    public String getUserInput() {
        return reader.nextLine();
    }
    /**
     * The method allows the chatbot to greet the user upon initialization.
     */
    public void greet() {
        System.out.print("Yes? I'm Zlimez~~ \nWhat can I possibly do for you?\n >>>^<<<\n\n");
    }

    public void listAll(List<Task> tasks) {
        System.out.println("\tReally? If you are so forgetful...");

        list(tasks);
    }

    public void listTaskOn(List<Task> tasks) {
        if (tasks != null) {
            System.out.println("\tThese are your tasks for that day");
            list(tasks);
        } else {
            System.out.println("\tWell you are a lazy bum, you have nothing on the day");
        }
    }

    public void listFound(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            System.out.println("\tThese are the tasks that match your description");
            list(tasks);
        } else {
            System.out.println("\t You like to search for nothing and waste time huh?");
        }
    }

    private void list(List<Task> tasks) {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("\t" + i + ". " + tasks.get(i - 1));
        }
    }

    public void add(Task target, int numberOfTasks) {
        System.out.println("\tLazily added this task for you " + EMOJI);
        System.out.println("\t\t" + target);
        System.out.println("\tWala now you have " + numberOfTasks + " tasks in the list.");
    }

    public void delete(Task target, int numberOfTasks) {
        System.out.println("\tYES, I've removed this task for YOU:");
        System.out.println("\t\t" + target);
        System.out.println("\tWala now you have " + numberOfTasks + " tasks in the list.");
    }

    public void unmark(Task target) {
        System.out.println("\t-_-, I've unmarked this task for YOU AGAIN:");
        System.out.println("\t\t" + target);
    }

    public void mark(Task target) {
        System.out.println("\tWellz, I've marked this task for YOU:");
        System.out.println("\t\t" + target);
    }

    public void reprimand(DukeException e) {
        System.out.println("\t" + e.getMessage());
    }

    public void bye() {
        System.out.println("\tBye. zzz FINALLY~~" + " " + EMOJI);
        reader.close();
    }
}
