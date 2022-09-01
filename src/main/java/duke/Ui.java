package duke;

import java.io.IOException;

/**
 * Handles UI aspects of the duke.Duke chatbot.
 *
 * @author Yuvaraj Kumaresan
 */
public class Ui {

    /**
     * The storage used to interact with the user.
     */
    protected Storage storage;

    /**
     * Constructor.
     *
     * @param storage The storage used to get item to interact with user.
     */
    public Ui(Storage storage) {
        this.storage = storage;
    }


    /**
     * Handles UI output for list keyword.
     */
    public String list() {
        if (storage.taskList.arrayList.size() == 0) {
            return ("The list is empty, please add more items to display them here.");

        } else {
            String temp = "";
            for (int i = 0; i < storage.taskList.arrayList.size(); i++) {
                temp = temp + "\n" + ((i + 1) + ". " + storage.taskList.arrayList.get(i).toString());

            }
            return ("Here are the tasks in your list :" + temp);

        }
    }

    /**
     * Handles UI output for bye keyword.
     *
     * @throws IOException
     */
    public String bye() throws IOException {
        storage.writeToFile();
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Handles UI output for delete keyword with no line number.
     */
    public String deleteNoNumber() {

        return ("Please provide a task number to delete");

    }

    /**
     * Handles UI output for delete keyword where the task does not exist.
     */
    public String deleteError() {

        return ("The task you want to delete does not exist.");

    }

    /**
     * Handles the delete keyword and deletes the task.
     *
     * @param deleted duke.Task to delete.
     */
    public String delete(Task deleted) {

        return ("Noted. I have removed this task:\n" +
                deleted.toString() +
                "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");

    }

    /**
     * Handles invalid delete command.
     */
    public String deleteNumberFormatError() {

        return ("Invalid command please add a space between delete and " +
                "the list item you would like to interact with. \n" +
                "Additionally ensure you have entered a number after delete.");

    }

    /**
     * Handles todo keyword and adds task to the list.
     *
     * @param item duke.ToDo item to add to the list.
     */
    public String todo(ToDo item) {

        return ("Got it. I've added this task. \n" + item.toString()
                + "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");

    }

    /**
     * Handles todo error when the todo keyword is used.
     *
     * @param error The error thrown when todo malfunctions.
     */
    public String todoError(DukeException.DukeToDoException error) {

        return (error.getMessage());

    }

    /**
     * Handles deadline keyword and adds deadline task to list.
     *
     * @param item The deadline item to add to the list.
     */
    public String deadline(Deadline item) {

        return ("Got it. I've added this task. \n" + item.toString()
                + "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");

    }

    /**
     * Handles improper date time format for deadline item.
     */
    public String dateTimeParseError() {

        return ("Incorrect date time format the format is dd/mm/yyyy hh:mm " +
                "if time is not provided the default is 00:00");

    }

    /**
     * Handles improper deadline keyword entries.
     */
    public String dateTimeArrayException() {

        return ("Please provide a deadline and a by time e.g. deadline " +
                "<description of the deadline> /by <time of the deadline>");

    }

    /**
     * Handles the event keyword and adds an event to the list.
     *
     * @param item The event item to add to the list.
     */
    public String event(Event item) {

        return ("Got it. I've added this task. \n" + item.toString()
                + "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");

    }

    /**
     * Handles improper event keyword entries.
     */
    public String eventError() {

        return ("Please provide a event and an at time " +
                "e.g. event <description of the event> /at <time of the event>");

    }

    /**
     * Handles mark keyword and marks tasks.
     *
     * @param text String to mark.
     */
    public String mark(String text) {

        return ("Nice! I've marked this task as done \n" +
                storage.taskList.arrayList.get(Integer.parseInt(text.replace("mark ", "")) - 1).toString() +
                "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");

    }

    /**
     * Handles missing task number to mark.
     */
    public String noNumberToMark() {

        return ("Please provide a task number to mark as done");

    }

    /**
     * Handles case where task is already marked.
     */
    public String alreadyMarked() {

        return ("This task is already marked done");

    }

    /**
     * Handles case where the item to mark does not exist.
     */
    public String noItemToMark() {

        return ("Such an item does not exist");

    }

    /**
     * Handles improper mark keyword entries.
     */
    public String markFormatError() {

        return ("Invalid command please add a space between " +
                "mark and the list item you would like to interact with. \n" +
                "Additionally ensure you have entered a number after mark.");

    }

    /**
     * Handles unmark keyword and unmarks tasks.
     *
     * @param text String to unmark
     */
    public String unmark(String text) {

        return ("Ok, I've marked this task as not done yet \n" +
                storage.taskList.arrayList.get(Integer.parseInt(text.replace("unmark ", "")) - 1).toString() +
                "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");

    }

    /**
     * Handles case where unmark number is missing.
     */
    public String noNumberToUnmark() {

        return ("Please provide a task number to mark as not done");

    }

    /**
     * Handles case where task is already unmarked.
     */
    public String alreadyUnmarked() {

        return ("This task is already marked not done");

    }

    /**
     * Handles case where item to unmark does not exist.
     */
    public String noItemToUnmark() {

        return ("Such an item does not exist");

    }

    /**
     * Handles improper unmark keyword entries.
     */
    public String unmarkFormatError() {

        return ("Invalid command please add a space between " +
                "unmark and the list item you would like to interact with. \n" +
                "Additionally ensure you have entered a number after unmark.");

    }

    /**
     * Handles the help keyword and displays the user guide.
     */
    public String help() {

        return ("Welcome to the user guide. This guide has all the" +
                " commands that are necessary to operate duke.Duke\n\n" +
                "Main commands : \n\n" +
                "Todo : adds a todo task to the task list :-> todo <description of the task> \n" +
                "duke.Deadline : adds a deadline task to the task list :->" +
                " deadline <description of the deadline> /by <time of the deadline> \n" +
                "duke.Event : adds an event task to the task list :-> event" +
                " <description of the event> /at <time of the event> \n\n" +
                "Other commands: \n\n" +
                "Mark : marks a task as done :-> mark <task number> \n" +
                "Unmark : marks a task as not done :-> unmark <task number> \n" +
                "Delete : deletes a task :-> delete <task number>\n" +
                "Help : brings up this display :-> help\n" +
                "Bye : closes duke.Duke :-> bye\n" +
                "Find: finds task based on description :-> find <search string>\n\n" +
                "Please enter one of the above to continue.");

    }

    /**
     * Handles general errors
     *
     * @param error The error message thrown.
     */
    public String generalError(DukeException.DukeCommandException error) {

        return (error.getMessage() + "\n" + "Please provide a proper command. Formats are as follows: \n\n" +
                "Main commands : \n\n" +
                "Todo : adds a todo task to the task list :-> todo <description of the task> \n" +
                "duke.Deadline : adds a deadline task to the task list :-> deadline" +
                " <description of the deadline> /by <time of the deadline> \n" +
                "duke.Event : adds an event task to the task list :-> event " +
                "<description of the event> /at <time of the event> \n\n" +
                "Other commands: \n\n" +
                "Mark : marks a task as done :-> mark <task number> \n" +
                "Unmark : marks a task as not done :-> unmark <task number> \n" +
                "Delete : deletes a task :-> delete <task number>\n" +
                "Help : brings up this display :-> help\n" +
                "Bye : closes duke.Duke :-> bye\n" +
                "Find: finds task based on description :-> find <search string>\n\n" +
                "Please enter one of the above to continue.");

    }

    /**
     * Handles the find keyword user input.
     *
     * @param search The string to search for.
     */
    public String find(String search) {
        int counter = 0;

        String temp = "";
        for (int i = 0; i < storage.taskList.arrayList.size(); i++) {
            if (storage.taskList.arrayList.get(i).getDescription().contains(search)) {
                temp = temp + "\n" + ((i + 1) + ". " + storage.taskList.arrayList.get(i).toString());
                counter++;
            }
        }


        if (counter == 0) {
            return ("Here are the matching tasks in your list : \n" + "No tasks on the list match the search criteria.");

        } else {

            return "Here are the matching tasks in your list : \n" + temp;
        }

    }

    /**
     * Handles erroneous find keyword usage.
     */
    public String findError() {
        return ("Please provide a space between your search " +
                "and the find keyword. Format : find <search keyword>");
    }

    /**
     * Handles missing search keywords.
     */
    public String noSearchError() {
        return ("Please provide keywords to search the list with.");
    }
}
