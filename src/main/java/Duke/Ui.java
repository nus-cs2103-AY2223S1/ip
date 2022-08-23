package duke;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Don\nHow may I help you?");
    }

    public String readCommand() {
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showTaskList(TaskList listOfTasks) {
        System.out.println("Here are the tasks in your list:");
        for (int index = 0; index < listOfTasks.getSize(); index++) {
            System.out.println(index + 1 + "." + listOfTasks.getTask(index).toString());
        }
    }

    public void showMarkedTask(int taskIndex, TaskList listOfTasks) {
        System.out.println("Nice! I've marked this task as done:\n" + "[" +
                listOfTasks.getTask(taskIndex).getStatusIcon() + "] " + listOfTasks.getTask(taskIndex).getDescription());
    }

    public void showUnmarkedTask(int taskIndex, TaskList listOfTasks) {
        System.out.println("Ok, I've marked this task as not done yet:\n" + "[" +
                listOfTasks.getTask(taskIndex).getStatusIcon() + "] " + listOfTasks.getTask(taskIndex).getDescription());
    }

    public void showToDoTask(Task toDoTask, TaskList listOfTasks) {
        System.out.println("Got it. I've added this task:\n" + toDoTask + "\nNow you have " +
                listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
    }

    public void showDeadlineTask(Task deadlineTask, TaskList listOfTasks) {
        System.out.println("Got it. I've added this task:\n" + deadlineTask + "\nNow you have " +
                listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
    }

    public void showEventTask(Task eventTask, TaskList listOfTasks) {
        System.out.println("Got it. I've added this task:\n" + eventTask + "\nNow you have " +
                listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
    }

    public void showDeletedTask(Task deletedTask, TaskList listOfTasks) {
        System.out.println("Noted. I've removed this task:\n" + deletedTask + "\nNow you have " +
                listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");

    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLoadingError() {
        System.out.println("Unable to load data");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
