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
        texts.add("To display full text in a dialog box, please use the scroll bar, "
                + "or click on the dialog and scroll up.");
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
        texts.add("There are 3 types of task implemented:\n"
                + "  1. todo : tasks without any date/time attached to it\n"
                + "  2. deadline : tasks that need to be done before a specific date/time\n"
                + "  3. event : tasks that start at a specific time and ends at a specific time");
        texts.add("Below is all the command you can use with the command format specified: \n"
                + "  1.  Add todo : todo {task description}\n"
                + "  2.  Add deadline : deadline {task description} /by {end date}\n"
                + "  3.  Add event : event {task description} /at {start date} to {end date}\n"
                + "  4.  Delete task : delete {task index}\n"
                + "  5.  Mark task as done : mark {task index}\n"
                + "  6.  Mark task as undone : unmark {task index}\n"
                + "  7.  Tag task : tag {task index} /with {tag}\n"
                + "  8.  Untag task : untag {task index} /with {tag}\n"
                + "  9.  List all tasks : list\n"
                + "  10. List unfinished tasks by date : list {date}\n"
                + "  11. List tasks by description keyword : list {keyword}\n"
                + "  12. List tasks by tag : list #{tag}\n"
                + "  13. Exit duke chatbot : bye");
        texts.add("Other Remarks: \n"
                + "  1. Acceptable date formats include dd/MM/yyyy, yyyy/MM/dd, yyyy-MM-dd, dd-MM-yyyy, "
                + "dd MM yyyy, yyyy MM dd.\n"
                + "  2. A tag / keyword must be a case-sensitive word containing only alphabets.\n"
                + "  3. A task can contain at most 3 tags, repeated tags are not allowed in a task.\n"
                + "  4. Task list will be auto-saved after bye command and auto-loaded when chatbot starts up.");
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
        if (command.equals("delete")) {  // special case: index represents a task string instead of a task index
            texts.add("Noted. I've removed this task:\n" + String.format("  %s", index));
            texts.add(String.format("Now you have %d tasks in the list.", taskList.getSize()));
            return texts;
        }
        assert index.matches("[0-9]+") : "wrong index.";
        assert Integer.parseInt(index) <= taskList.getSize() : "Index out of bound.";
        Task task = taskList.getTask(Integer.parseInt(index) - 1);
        if (command.equals("todo") || command.equals("event") || command.equals("deadline")) { //add
            texts.add("Got it. I've added this task:\n" + String.format("  %s", task.printTask()));
            texts.add(String.format("Now you have %d tasks in the list.", taskList.getSize()));
        } else if (command.equals("mark")) {
            texts.add("Nice! I've marked this task as done:\n" + String.format("  %s", task.printTask()));
        } else { //unmark
            texts.add("OK, I've marked this task as not done yet:\n" + String.format("  %s", task.printTask()));
        }
        return texts;
    }

    public List<String> printTagCommandUi(String command, String index, String tag, TaskList taskList) {
        List<String> texts = new ArrayList<>();
        Task task = taskList.getTask(Integer.parseInt(index) - 1);
        if (command.equals("tag")) {
            texts.add(String.format("OK, I've added this tag %s for the task:  %s", tag, task.printTask()));
            texts.add(String.format("Now the task have %d tags: %s", task.noOfTags(), task.printTags()));
        } else { //untag
            texts.add(String.format("OK, I've deleted this tag %s for the task:  %s", tag, task.printTask()));
            texts.add(String.format("Now the task have %d tags: %s", task.noOfTags(), task.printTags()));
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
        } else if (!str.trim().contains("#")) {
            texts.add("Here are the matching tasks in your list:");
            texts.add(taskList.searchByKeyword(str).printTaskList());
        } else {
            String tag = str.substring(1);
            texts.add(String.format("Here are the tasks tagged %s:", tag));
            texts.add(taskList.searchByTag(tag).printTaskList());
        }
        return texts;
    }


    /**
     * Prints duke chatbot ending message that user sees when he/her stops/exits duke chatbot.
     */
    public List<String> printEndingUi() {
        List<String> texts = new ArrayList<>();
        texts.add("Duke Chatbot will stop, all previous tasks will be auto-saved :D \n"
                + "Bye, Hope to see you again soon!");
        texts.add("Please type 'yes' to confirm exit or close the window.");
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
