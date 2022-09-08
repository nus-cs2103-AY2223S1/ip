package duke;

import duke.task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents duke chatbot Ui that user sees.
 */
public class Ui {

    /**
     * Prints duke chatbot start up message that user sees when he/her opens duke chatbot.
     */
    public java.util.List<String> printStartUpUi() {
        List<String> texts = new ArrayList<>();
        texts.add("Hello! I'm Duke, your personalized chatbot to arrange your tasks!");
        texts.add("Duke allows you to freely add, delete, mark task status, search tasks by time, "
                + "and list all tasks");
        texts.add("Please type 'help' command for more information on Duke task types and command format.");
        return texts;
    }

    /**
     * Prints duke information message that user sees when he/her uses help command to get information
     * about duke chatbot.
     * The information includes task type, command format, and other notes.
     */
    public List<String> printDukeInfo() {
        List<String> texts = new ArrayList<>();
        texts.add("There are 3 types of task implemented:");
        texts.add("1. todo     : tasks without any date/time attached to it\n"
                + "2. deadline : tasks that need to be done before a specific date/time\n"
                + "3. event    : tasks that start at a specific time and ends at a specific time");

        texts.add("Below is all the command you can use: ");
        texts.add("  Command                | Command Format\n"
                + "  1. Add todo            | todo {task description}\n"
                + "  2. Add deadline        | deadline {task description} /by {end date}\n"
                + "  3. Add event           | event {task description} /at {start date} to {end date}\n"
                + "  7. List all tasks      | list\n"
                + "  4. Delete task         | delete {task index in the list}\n"
                + "  5. Mark task as done   | mark {task index in the list}\n"
                + "  6. Mark task as undone | unmark {task index in the list}\n"
                + "  8. Exit chatbot       | bye");

        texts.add("Other Remarks: ");
        texts.add("1. Acceptable date formats include dd/MM/yyyy, yyyy/MM/dd, yyyy-MM-dd, dd-MM-yyyy, "
                + "   dd MM yyyy, yyyy MM dd.\n"
                + "2. Task list will be auto-saved after bye command and auto-loaded when chatbot starts up.");
        return texts;
    }

    /**
     * Prints duke chatbot message after user add, delete task, and mark tasks as done or undone.
     *
     * @param command user command.
     * @param index index of a task that is added / deleted / marked / unmarked.
     * @param taskList task list.
     */
    public List<String> printAddCommandUi(String command, String index, TaskList taskList) {
        List<String> texts = new ArrayList<>();
        // for add, delete, mark / unmark
        assert !command.equals("") : "empty command.";
        assert index.matches("[0-9]+") : "wrong index.";
        assert Integer.parseInt(index) <= taskList.getSize() : "Index out of bound.";
        Task task = taskList.getTask(Integer.parseInt(index) - 1);
        if (command.equals("todo") || command.equals("event") || command.equals("deadline")) { //add
            texts.add("Got it. I've added this task:\n"
                            + String.format("  %s %s", task.getStatusIcon(), task.getDescription()));
            texts.add(String.format("Now you have %d tasks in the list.", taskList.getSize()));
        } else if (command.equals("delete")) { //delete
            taskList.deleteTaskAtIndex(Integer.parseInt(index));
            texts.add("Noted. I've removed this task:\n"
                    + String.format("  %s %s", task.getStatusIcon(), task.getDescription()));
            texts.add(String.format("Now you have %d tasks in the list.", taskList.getSize()));
        } else if (command.equals("mark")) {
            texts.add("Nice! I've marked this task as done:"
                    + String.format("  %s %s", task.getStatusIcon(), task.getDescription()));
        } else { //unmark
            texts.add("OK, I've marked this task as not done yet:"
                    + String.format("  %s %s", task.getStatusIcon(), task.getDescription()));
        }
        return texts;
    }

    /**
     * Prints duke chatbot message after user list tasks and list unfinished tasks at a date.
     *
     * @param command user command.
     * @param str string containing a date.
     * @param taskList task list to be printed.
     */
    public List<String> printListCommandUi(String command, String str, TaskList taskList) {
        List<String> texts = new ArrayList<>();
        assert !command.equals("") : "empty command.";
        // for list, list date, search
        if (str.trim().equals("0")) { //list
            texts.add("Here are the tasks in your list:");
            texts.add(taskList.printTaskList());
        } else if (str.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) { //list date
            texts.add("Here are unfinished tasks on this date in your list:");
            texts.add(taskList.searchByDate(LocalDate.parse(str)).printTaskList());
        } else {
            texts.add("Here are the matching tasks in your list:");
            texts.add(taskList.searchByKeyword(str).printTaskList());
        }
        return texts;
    }

    /**
     * Prints duke chatbot ending message that user sees when he/her stops/exits duke chatbot.
     */
    public List<String> printEndingUi() {
        List<String> texts = new ArrayList<>();
        texts.add("Chatbot stopped, all previous tasks will be auto-saved :D");
        texts.add("Bye. Hope to see you again soon!");
        return texts;
    }

    /**
     * Prints message when task list storage loading occurs.
     */
    public List<String> showLoadingError() {
        List<String> texts = new ArrayList<>();
        texts.add("☹ OOPS!!! Loading error occurred, new text file is created for task list storage.");
        return texts;
    }

    /**
     * Prints message when task list storage saving occurs.
     */
    public List<String> showSavingError() {
        List<String> texts = new ArrayList<>();
        texts.add("☹ OOPS!!! Saving error occurred, task list not properly saved in the text file.");
        return texts;
    }

    /**
     * Prints error message when an exception is raised possibly because of inproper commands.
     *
     * @param e exception.
     */
    public List<String> showException(Exception e) {
        List<String> texts = new ArrayList<>();
        texts.add(e.getMessage());
        return texts;
    }

}
