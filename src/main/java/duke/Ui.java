package duke;

import java.util.Scanner;
public class Ui {
    private Scanner sc;
    private static final String INTRO = "Hi! I'm ELON MUSK\nWhat can I do for you?";
    private static final String OUTRO = "Bye, Don't miss me please.";

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints greeting message.
     */
    public static void greet() {
        System.out.println(INTRO);
    }

    /**
     * Prints exit message.
     */
    public static void bye() {
      System.out.println(OUTRO);
    }

    /**
     * Prints the task list.
     *
     * @param tasks
     */
    public static void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0, d = 1; i < tasks.size(); i++, d++) {
            System.out.println(d + ". " + tasks.get(i));
        }
    }

    public static void addedTask(TaskList tasks, Task input) {
        tasks.add(input);
        System.out.println("Got it. I've added this task:");
        System.out.println(input);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }
}
