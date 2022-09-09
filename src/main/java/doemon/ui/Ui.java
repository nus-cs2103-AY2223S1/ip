package doemon.ui;

import doemon.exception.DoemonException;
import doemon.task.Task;
import doemon.task.TaskList;

import java.util.Scanner;

public class Ui {
    /**
     * Picture of Doemon made using text
     */
    private static final String logo =
            "                       _______________\n" +
            "                      /  --. --.      \\ \n" +
            "                     /  | '| ' |   \\   \\ \n" +
            "                    / /  `-O--'     \\   \\ \n" +
            "                   |.  --  |  --     |   |\n" +
            "                   |  --   |  --     |   |\n" +
            "                    \\  (___|_______) /  /\n" +
            "                     \\              /  /\n" +
            "                       |== (t) ===|____";

    /**
     * Introduction string that is printed when Doemon is started.
     */
    private static final String introStr = "Hello I'm\n" + logo + "\t\t\tDoemon!";

    /**
     * The string that is printed when Doemon is exited.
     */
    private static final String exitStr = "I'm going to sleep now...See you again soon!";

    private Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println(output(this.introStr));
    }

    public void showTask(Task task) {
        System.out.println(output(task.toString()));
    }

    public void showTaskList(TaskList tasks) {
        StringBuilder listStringBuilder =
                new StringBuilder("Here is what's on my bread:\n\t");
        if (tasks.getSize() == 0) {
            System.out.println(output("You have no tasks!"));
        } else {
            for (int i = 1; i <= tasks.getSize(); i++) {
                listStringBuilder.append(i)
                        .append(".")
                        .append(tasks.getTask(i - 1))
                        .append("\n\t");
            }
            System.out.println(output(listStringBuilder.toString().trim()));
        }
    }

    public void showAddTask(Task task, int numTasks) {
        System.out.println(
                output(String.format(
                        "Alright! I have recorded this task on my bread:\n\t" +
                                "  %s\n\tYou now have %d task(s) recorded on my bread.",
                        task.toString(),
                        numTasks)));
    }

    public void showMarkTask(Task task) {
        System.out.println(output(
                String.format("Yay! This task is now marked as done:\n\t  %s", task)));
    }

    public void showUnmarkTask(Task task) {
        System.out.println(output(
                String.format("I guess you weren't done with that one:\n\t  %s", task)));
    }

    public void showDeleteTask(Task task, int numTasks) {
        System.out.println(output(
                String.format("I used a knife to slice off this task from my bread:\n\t  %s"
                + "\n\tThere are %d items left on my bread.", task, numTasks)));
    }

    public void showError(DoemonException e) {
        System.out.println(output(e.toString()));
    }

    public void showExit() {
        System.out.println(output(this.exitStr));
    }

    /**
     * Returns a formatted string to display the given text.
     * @param text the text to be formatted
     * @return the formatted string
     */
    private static String output(String text) {
        String line = "____________________________________________________________";
        return String.format("\t%s\n\t%s\n\t%s", line, text, line);
    }
}
