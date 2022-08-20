package ui;

import exception.CommandException;

import task.Task;
import task.TaskList;

public class Ui {
    private final String DASH = "----------------------------------------";

    private void printTemplate(String message) {
        System.out.println(String.format("%s\n%s\n%s", DASH, message, DASH));
    }

    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello from\n" + logo + "\nWait, I'm not Duke\n"
                + "I'm Yem, your personal assistant\nWhat can I do for you master?";
        printTemplate(welcomeMessage);
    }

    public void printFarewellMessage() {
        printTemplate("Bye. See you later master!");
    }

    public void printError(CommandException error) {
        printTemplate(error.getMessage());
    }

    public void printTaskCreationSuccessMessage(Task newTask, int currentNumberOfTasks) {
        String successMessage = "This task is successfully added:\n " + newTask.toString()
                + "\n Now you have " + currentNumberOfTasks + " task(s) in the list";
        printTemplate(successMessage);
    }

    public void printTaskDeletionSuccessMessage(Task deletedTask, int currentNumberOfTasks) {
        String successMessage = "Noted. I've removed this task:\n " + deletedTask.toString()
                + "\n Now you have " + currentNumberOfTasks + " task(s) in the list";
        printTemplate(successMessage);
    }

    public void printTaskMarkSuccessMessage(Task markedTask) {
        String successMessage = String.format("This task has been marked as done:\n %s",
                markedTask);
        printTemplate(successMessage);
    }

    public void printTaskUnmarkSuccessMessage(Task unmarkedTask) {
        String successMessage = String.format("This task has been marked as not done yet:\n %s",
                unmarkedTask);
        printTemplate(successMessage);
    }

    public void printTaskList(TaskList taskList) {
        printTemplate(taskList.toString());
    }
}
