package duke;

import java.util.Scanner;

public class Ui {
    private Scanner in;
    public Ui() {
        in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showError(DukeException e) {
        wrapPrint("☹ OOPS!!! " + e.getLocalizedMessage());
    }

    public void printAddTaskSuccessfully(TaskList tasks) {
        String taskString = "tasks";
        if (tasks.size() == 1) {
            taskString = "task";
        }
        wrapPrint("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1).toString()
                + String.format(
                "\nNow you have %d %s in the list.", tasks.size(), taskString));
    }

    /**
     * 
     */
    public void greet() {
        this.wrapPrint("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * 
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * @param toPrint
     */
    public void wrapPrint(String toPrint) {
        showLine();
        Scanner scanner = new Scanner(toPrint);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(leftPad(line));
        }
        scanner.close();
        showLine();
    }

    /**
     * @param toPrint
     * @return
     */
    private String leftPad(String toPrint) {
        return "     " + toPrint;
    }
}
