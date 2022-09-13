package duke;

import java.util.ArrayList;
import java.util.List;


/**
* UI class to handle all user interactions and system output.
*
* @author Sheryl Kong (A0240686Y)
*/

public class UI {
    private static final String DIVIDER = "–––––––––––––––––––––––\n";

    public static void userInput(String text) {
        System.out.println("You entered: " + text + System.lineSeparator());
    }

    public static void response(String text) {
        System.out.println(text + System.lineSeparator());
    }

    public static String getResponse(String text) { return text; }

    public static void welcome() {
        System.out.println("Hello Spongebob! Anything you want me to do?\n Enter \"help\" for more info.");
    }

    public static String welcomeResponse() {
        return "Hello Spongebob! This is Captain Duke-it! Anything you want me to do?\n Enter \"help\" for more info.";
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String byeResponse() {
        return "Bye. Hope to see you again soon!";
    }

    public static void invalid() {
        System.out.println("OOPS!! Please enter a valid command!");
    }

    public static String invalidResponse() {
        return "OOPS!! Please enter a valid command!";
    }

    public static void help() { System.out.println(
            "I'm here to keep your life in check. \n" +
                    "You may enter the following commands: \n" +
                    "1. todo <task>:\n" +
                    "\t Saves a task\n" +
                    "2. deadline <task> /by <date>: \n" +
                    "\t Saves a task with a deadline\n" +
                    "3. event <task> /at <date>: \n" +
                    "\t Saves an event with a date\n" +
                    "4. list: \n" +
                    "\t Lists out all the tasks you have\n" +
                    "5. mark <task number(s)>: \n" +
                    "\t Marks all the tasks stated as done\n" +
                    "6. unmark <task number(s)>: \n" +
                    "\t Marks all the tasks stated as undone\n" +
                    "7. delete <task number(s)>: \n" +
                    "\t Deletes all the tasks stated\n" +
                    "8. find <keyword>: \n" +
                    "\t Finds all tasks containing the keyword\n" +
                    "9. update description <task number> <new description>: \n" +
                    "\t Updates the task description\n" +
                    "10. bye: \n" +
                    "\t Exits the program\n");}

    public static String helpResponse() {
        return
                "I'm here to keep your life in check. \n" +
                        "You may enter the following commands: \n" +
                        "1. todo <task>:\n" +
                        "\t Saves a task\n" +
                        "2. deadline <task> /by <date>: \n" +
                        "\t Saves a task with a deadline\n" +
                        "3. event <task> /at <date>: \n" +
                        "\t Saves an event with a date\n" +
                        "4. list: \n" +
                        "\t Lists out all the tasks you have\n" +
                        "5. mark <task number(s)>: \n" +
                        "\t Marks all the tasks stated as done\n" +
                        "6. unmark <task number(s)>: \n" +
                        "\t Marks all the tasks stated as undone\n" +
                        "7. delete <task number(s)>: \n" +
                        "\t Deletes all the tasks stated\n" +
                        "8. find <keyword>: \n" +
                        "\t Finds all tasks containing the keyword\n" +
                        "9. update description <task number> <new description>: \n" +
                        "\t Updates the task description\n" +
                        "10. bye: \n" +
                        "\t Exits the program\n";
    }


    public static void added(Task task) {
        System.out.println(DIVIDER);
        System.out.println(task.added());
        System.out.println(DIVIDER);
    }

    public static String addedResponse(Task task) {
        return task.added() + System.lineSeparator();
    }

    public static void delete(int[] taskNos, TaskList taskList) {
        String tasks = "";
        for (int taskNo : taskNos) {
            tasks += taskList.getTask(taskNo).toString() + System.lineSeparator();
        }
        System.out.println(DIVIDER);
        System.out.printf("Noted. I've removed the task(s):\n" +
                "%s" +
                "Now you have %d tasks in the list.\n", tasks, Task.getTaskCount()-taskNos.length);
        System.out.println(DIVIDER);
    }

    public static String deleteResponse(int[] taskNos, TaskList taskList) {
        String tasks = "";
        for (int taskNo : taskNos) {
            tasks += taskList.getTask(taskNo).toString() + System.lineSeparator();
        }
        return "Noted. I've removed this task:\n"
                + tasks
                + String.format("Now you have %d tasks in the list.\n", Task.getTaskCount()-taskNos.length);
    }

    public static void list(TaskList taskList) {
        System.out.println(DIVIDER);
        System.out.print("Here are the tasks in your list:\n");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.printf(" %d. %s\n", i + 1, taskList.getTask(i));
        }
        System.out.println(DIVIDER);
    }

    public static String listResponse(TaskList taskList) {
        String tasks = "";
        for (int i = 0; i < Task.getTaskCount(); i++) {
            tasks += String.format(" %d. %s\n", i + 1, taskList.getTask(i));
        }
        return "Here are the tasks in your list:\n"
                + tasks;
    }

    public static void markAsDone(int[] taskNos, TaskList taskList) {
        String tasks = "";
        for (int taskNo : taskNos) {
            tasks += taskList.getTask(taskNo).toString() + System.lineSeparator();
        }
        System.out.println(DIVIDER);
        System.out.printf("Nice! I've marked the task(s) as done: \n" +
                            "%s", tasks);
        System.out.println(DIVIDER);
    }

    public static String markAsDoneResponse(int[] taskNos, TaskList taskList) {
        String tasks = "";
        for (int taskNo : taskNos) {
            tasks += taskList.getTask(taskNo).toString() + System.lineSeparator();
        }
        return "Nice! I've marked the task(s) as done: \n"
                + tasks;
    }

    public static void markAsUndone(int[] taskNos, TaskList taskList) {
        String tasks = "";
        for (int taskNo : taskNos) {
            tasks += taskList.getTask(taskNo).toString() + System.lineSeparator();
        }
        System.out.println(DIVIDER);
        System.out.printf("OK, I've marked the task(s) as not done yet: \n" +
                            "%s\n", tasks);
        System.out.println(DIVIDER);
    }

    public static String markAsUndoneResponse(int[] taskNos, TaskList taskList) {
        String tasks = "";
        for (int taskNo : taskNos) {
            tasks += taskList.getTask(taskNo).toString() + System.lineSeparator();
        }
        return "OK, I've marked the task(s) as not done yet: \n"
                + tasks + System.lineSeparator();
    }

    public static void find(TaskList tasks, String desc) {
        System.out.println(DIVIDER);
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for(Task task : tasks.getList()) {
            if (task.toString().contains(desc)) {
                System.out.printf(" %d. %s\n", count, task);
                count++;
            }
        }
        System.out.println(DIVIDER);
    }

    public static String findResponse(TaskList tasks, String desc) {
        String matchedTasks = " ";
        int count = 1;
        for(Task task : tasks.getList()) {
            if (task.toString().contains(desc)) {
                matchedTasks += String.format(" %d. %s\n", count, task);
                count++;
            }
        }
        return "Here are the matching tasks in your list: \n"
                + matchedTasks;
    }

    public static void updateTaskDesc(Task task) {
        System.out.println(DIVIDER);
        System.out.println("The following task (" + " has been updated:");
        System.out.println(task);
        System.out.println(DIVIDER);
    }

    public static String updateTaskDescResponse(Task task) {
        return "The following task has been updated: \n"
                + task + System.lineSeparator();
    }
}
