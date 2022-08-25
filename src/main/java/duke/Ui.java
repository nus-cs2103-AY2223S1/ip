package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "```````````````````````````````````````````````````````````````````";
    private static final String GREET_WELCOME= "Hello there! I am\n" + LOGO
            + "\nyour personal task tracking assistant!\nWhat can I do for you today?\n";
    private static final String GREET_EXIT = "Bye. Hope to see you again soon!\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void showWelcome() {
        System.out.println(GREET_WELCOME + LINE);
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    public static void showError(String error) {
        System.out.println(error);
    }

    public static void printTaskList(TaskList taskList) {
        String header = "";
        if (taskList.isEmpty()) {
            header = "You have no tasks in your list.";
        } else {
            header = "My List Of Tasks :D";
        }
        System.out.println(header + "\n" + taskList);
    }

    public static void printTaskCreationMessage(Task newTask,int noOfTasks) {
        System.out.println("Got it. I've added this task:\n "
                + newTask + "\nNow you have " + noOfTasks + " tasks in the list.");
    }

    public void exit() {
        this.scanner.close();
        System.out.println(GREET_EXIT + LINE);
    }

    public String readUserInput() {
        return scanner.nextLine();
    }
}
