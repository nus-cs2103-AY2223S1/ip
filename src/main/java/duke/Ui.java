package duke;
import duke.task.Task;

import java.time.LocalDate;
/**
 * Represents duke chatbot Ui that user sees.
 */
public class Ui {
    /**
     * Prints duke chatbot start up message that user sees when he/her opens duke chatbot.
     */
    public void printStartUpUi() {
        System.out.println("========================================================================================");
        System.out.println("Hello! I'm Duke, your personalized chatbot to arrange your tasks!");
        System.out.println("Duke allows you to freely add, delete, mark task status, search tasks by time, \nand list all tasks");
        System.out.println("________________________________________________________________________________________");
        System.out.println("Please type 'help' command for more information on Duke task types and command format.");
        System.out.println("========================================================================================");
    }

    /**
     * Prints duke information message that user sees when he/her uses help command to get information about duke chatbot.
     * The information includes task type, command format, and other notes.
     */
    public void printDukeInfo() {
        System.out.println("________________________________________________________________________________________");
        System.out.println("There are 3 types of task implemented: " +
                "\n1. todo     : tasks without any date/time attached to it" +
                "\n2. deadline : tasks that need to be done before a specific date/time" +
                "\n3. event    : tasks that start at a specific time and ends at a specific time");
        System.out.println("________________________________________________________________________________________");
        System.out.println("Below is all the command you can use: ");
        System.out.println("  Command                | Command Format");
        System.out.println("  1. Add todo            | todo {task description}");
        System.out.println("  2. Add deadline        | deadline {task description} /by {end date}");
        System.out.println("  3. Add event           | event {task description} /at {start date} to {end date}");
        System.out.println("  7. List all tasks      | list");
        System.out.println("  4. Delete task         | delete {task index in the list}");
        System.out.println("  5. Mark task as done   | mark {task index in the list}");
        System.out.println("  6. Mark task as undone | unmark {task index in the list}");
        System.out.println("  8. Leave chatbot       | bye");
        System.out.println("________________________________________________________________________________________");
        System.out.println("Remarks:  ");
        System.out.println("1. Acceptable date formats include dd/MM/yyyy, yyyy/MM/dd, yyyy-MM-dd, dd-MM-yyyy, ");
        System.out.println("   dd MM yyyy, yyyy MM dd.");
        System.out.println("2. Task list will be auto-saved after bye command and auto-loaded when chatbot starts up.");
        System.out.println("________________________________________________________________________________________");
    }

    /**
     * Prints duke chatbot message after user add, delete task, and mark tasks as done or undone.
     *
     * @param command user command.
     * @param index index of a task that is added / deleted / marked / unmarked.
     * @param taskList task list.
     */
    public void printAddCommandUi(String command, String index, TaskList taskList) {
        // for add, delete, mark/unmark
        System.out.println("________________________________________________________________________________________");
        Task task = taskList.getTask(Integer.parseInt(index) - 1);
        if (command.equals("todo") || command.equals("event") || command.equals("deadline")) { //add
            System.out.println("Got it. I've added this task:");
            System.out.println(String.format("  %s %s", task.getStatusIcon(), task.getDescription()));
            System.out.println(String.format("Now you have %d tasks in the list.", taskList.getSize()));
        } else if (command.equals("delete")) { //delete
            System.out.println("Noted. I've removed this task:");
            taskList.deleteTaskAtIndex(Integer.parseInt(index));
            System.out.println(String.format("  %s %s", task.getStatusIcon(), task.getDescription()));
            System.out.println(String.format("Now you have %d tasks in the list.", taskList.getSize()));
        } else if (command.equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(String.format("  %s %s", task.getStatusIcon(), task.getDescription()));
        } else { //unmark
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(String.format("  %s %s", task.getStatusIcon(), task.getDescription()));
        }
        System.out.println("________________________________________________________________________________________");
    }

    /**
     * Prints duke chatbot message after user list tasks and list unfinished tasks at a date.
     *
     * @param command user command.
     * @param str string containing a date.
     * @param taskList task list to be printed.
     */
    public void printListCommandUi(String command, String str, TaskList taskList) {
        // for list, list date, search
        if (str.trim().equals("0")) { //list
            System.out.println("________________________________________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            taskList.printTaskList();
            System.out.println("________________________________________________________________________________________");
        } else if (str.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) { //list date
            System.out.println("________________________________________________________________________________________");
            System.out.println("Here are unfinished tasks on this date in your list:");
            taskList.searchByDate(LocalDate.parse(str)).printTaskList();
            System.out.println("________________________________________________________________________________________");
        } else {
            System.out.println("________________________________________________________________________________________");
            System.out.println("Here are the matching tasks in your list:");
            taskList.searchByKeyword(str).printTaskList();
            System.out.println("________________________________________________________________________________________");
        }
    }

    /**
     * Prints duke chatbot ending message that user sees when he/her stops/exits duke chatbot.
     */
    public void printEndingUi() {
        System.out.println("________________________________________________________________________________________");
        System.out.println("Chatbot stopped, all previous tasks will be auto-saved :D");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________________________________________________________");
    }

    /**
     * Prints message when task list storage loading occurs.
     */
    public void showLoadingError() {
        System.out.println("________________________________________________________________________________________");
        System.out.println("☹ OOPS!!! Loading error occurred, new text file is created for task list storage.");
        System.out.println("________________________________________________________________________________________");
    }

    /**
     * Prints message when task list storage saving occurs.
     */
    public void showSavingError() {
        System.out.println("________________________________________________________________________________________");
        System.out.println("☹ OOPS!!! Saving error occurred, task list not properly saved in the text file.");
        System.out.println("________________________________________________________________________________________");
    }

    /**
     * Prints error message when an exception is raised possibly because of inproper commands.
     *
     * @param e exception.
     */
    public void printException(Exception e) {
        System.out.println("________________________________________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println("________________________________________________________________________________________");
    }

}
