package duke.ui;

import duke.command.TaskList;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interactions with user through command line.
 */
public class Ui {
    private Scanner myObj = new Scanner(System.in);

    public void showLoadingError() {
        System.out.println("There was an error loading tasks from the file");
    }

    public String printWelcomeMessage() {
        return "Hello! I'm Snoopy\nWhat can I do for you?";
    }

    public String collectUserInput() {
        return myObj.nextLine();
    }

    public String printList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int i = tasks.size();
        String output = "Here are the tasks in your list:\n";
        for (int a = 1; a <= i; a++) {
            output += a + "." + tasks.get(a - 1).toString() + "\n";
        }
        return output;
    }

    public String printMatchingList(ArrayList<Task> tasks) {
        int i = tasks.size();
        String output = "Here are the matching tasks in your list:\n";
        for (int a = 1; a <= i; a++) {
            output += a + "." + tasks.get(a - 1).toString() + "\n";
        }
        return output;
    }

    public String printDeleteMessage(TaskList taskList, int taskNumber) {
        String output = "Noted. I've removed this task:\n";
        output += taskList.retrieveTask(taskNumber) + "\n";
        int i = taskList.getListSize() - 1;
        output += "Now you have " + i + " tasks in the list." + "\n";
        return output;
    }

    public String printAddMessage(TaskList taskList) {
        int listSize = taskList.getListSize();
        String output = "Got it. I've added this task:\n";
        output += taskList.retrieveTask(listSize) + "\n";
        output += "Now you have " + listSize + " tasks in the list.\n";
        return output;
    }

    public String printGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }
}
