package duke;

import java.io.IOException;

/**
 * Handles UI aspects of the duke.Duke chatbot.
 */
public class Ui {

    protected Storage storage;

    public Ui(Storage storage) {
        this.storage = storage;
    }

    String divider = "--------------------------------------------------" +
            "--------------------------------------------------------------";

    /**
     * Handles UI output for list keyword.
     */
    public void list() {
        if (storage.taskList.arrayList.size() == 0) {
            System.out.println(divider);
            System.out.println("The list is empty, please add more items to display them here.");
            System.out.println(divider);
        } else {
            System.out.println(divider);
            System.out.println("Here are the tasks in your list : ");
            for (int i = 0; i < storage.taskList.arrayList.size(); i++) {
                System.out.println((i + 1) + ". " + storage.taskList.arrayList.get(i).toString());
            }
            System.out.println(divider);
        }
    }

    /**
     * Handles UI output for bye keyword.
     *
     * @throws IOException
     */
    public void bye() throws IOException {
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
        storage.writeToFile();
        System.exit(0);
    }

    /**
     * Handles UI output for delete keyword with no line number.
     */
    public void deleteNoNumber() {
        System.out.println(divider);
        System.out.println("Please provide a task number to delete");
        System.out.println(divider);
    }

    /**
     * Handles UI output for delete keyword where the task does not exist.
     */
    public void deleteError() {
        System.out.println(divider);
        System.out.println("The task you want to delete does not exist.");
        System.out.println(divider);
    }

    /**
     * Handles the delete keyword and deletes the task.
     *
     * @param deleted duke.Task to delete.
     */
    public void delete(Task deleted) {
        System.out.println(divider);
        System.out.println("Noted. I have removed this task:\n" +
                deleted.toString() +
                "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");
        System.out.println(divider);
    }

    /**
     * Handles invalid delete command.
     */
    public void deleteNumberFormatError() {
        System.out.println(divider);
        System.out.println("Invalid command please add a space between delete and " +
                "the list item you would like to interact with. \n" +
                "Additionally ensure you have entered a number after delete.");
        System.out.println(divider);
    }

    /**
     * Handles todo keyword and adds task to the list.
     *
     * @param item duke.ToDo item to add to the list.
     */
    public void todo(ToDo item) {
        System.out.println(divider);
        System.out.println("Got it. I've added this task. \n" + item.toString()
                + "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");
        System.out.println(divider);
    }

    /**
     * Handles todo error when the todo keyword is used.
     *
     * @param error The error thrown when todo malfunctions.
     */
    public void todoError(DukeException.DukeToDoException error) {
        System.out.println(divider);
        System.out.println(error.getMessage());
        System.out.println(divider);
    }

    /**
     * Handles deadline keyword and adds deadline task to list.
     *
     * @param item The deadline item to add to the list.
     */
    public void deadline(Deadline item) {
        System.out.println(divider);
        System.out.println("Got it. I've added this task. \n" + item.toString()
                + "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");
        System.out.println(divider);
    }

    /**
     * Handles improper date time format for deadline item.
     */
    public void dateTimeParseError() {
        System.out.println(divider);
        System.out.println("Incorrect date time format the format is dd/mm/yyyy hh:mm " +
                "if time is not provided the default is 00:00");
        System.out.println(divider);
    }

    /**
     * Handles improper deadline keyword entries.
     */
    public void dateTimeArrayException() {
        System.out.println(divider);
        System.out.println("Please provide a deadline and a by time e.g. deadline " +
                "<description of the deadline> /by <time of the deadline>");
        System.out.println(divider);
    }

    /**
     * Handles the event keyword and adds an event to the list.
     *
     * @param item The event item to add to the list.
     */
    public void event(Event item) {
        System.out.println(divider);
        System.out.println("Got it. I've added this task. \n" + item.toString()
                + "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");
        System.out.println(divider);
    }

    /**
     * Handles improper event keyword entries.
     */
    public void eventError() {
        System.out.println(divider);
        System.out.println("Please provide a event and an at time " +
                "e.g. event <description of the event> /at <time of the event>");
        System.out.println(divider);
    }

    /**
     * Handles mark keyword and marks tasks.
     *
     * @param text String to mark.
     */
    public void mark(String text) {
        System.out.println(divider);
        System.out.println("Nice! I've marked this task as done \n" +
                storage.taskList.arrayList.get(Integer.parseInt(text.replace("mark ", "")) - 1).toString() +
                "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");
        System.out.println(divider);
    }

    /**
     * Handles missing task number to mark.
     */
    public void noNumberToMark() {
        System.out.println(divider);
        System.out.println("Please provide a task number to mark as done");
        System.out.println(divider);
    }

    /**
     * Handles case where task is already marked.
     */
    public void alreadyMarked() {
        System.out.println(divider);
        System.out.println("This task is already marked done");
        System.out.println(divider);
    }

    /**
     * Handles case where the item to mark does not exist.
     */
    public void noItemToMark() {
        System.out.println(divider);
        System.out.println("Such an item does not exist");
        System.out.println(divider);
    }

    /**
     * Handles improper mark keyword entries.
     */
    public void markFormatError() {
        System.out.println(divider);
        System.out.println("Invalid command please add a space between " +
                "mark and the list item you would like to interact with. \n" +
                "Additionally ensure you have entered a number after mark.");
        System.out.println(divider);
    }

    /**
     * Handles unmark keyword and unmarks tasks.
     *
     * @param text String to unmark
     */
    public void unmark(String text) {
        System.out.println(divider);
        System.out.println("Ok, I've marked this task as not done yet \n" +
                storage.taskList.arrayList.get(Integer.parseInt(text.replace("unmark ", "")) - 1).toString() +
                "\nNow you have " + storage.taskList.arrayList.size() + " tasks in the list");
        System.out.println(divider);
    }

    /**
     * Handles case where unmark number is missing.
     */
    public void noNumberToUnmark() {
        System.out.println(divider);
        System.out.println("Please provide a task number to mark as not done");
        System.out.println(divider);
    }

    /**
     * Handles case where task is already unmarked.
     */
    public void alreadyUnmarked() {
        System.out.println(divider);
        System.out.println("This task is already marked not done");
        System.out.println(divider);
    }

    /**
     * Handles case where item to unmark does not exist.
     */
    public void noItemToUnmark() {
        System.out.println(divider);
        System.out.println("Such an item does not exist");
        System.out.println(divider);
    }

    /**
     * Handles improper unmark keyword entries.
     */
    public void unmarkFormatError() {
        System.out.println(divider);
        System.out.println("Invalid command please add a space between " +
                "unmark and the list item you would like to interact with. \n" +
                "Additionally ensure you have entered a number after unmark.");
        System.out.println(divider);
    }

    /**
     * Handles the help keyword and displays the user guide.
     */
    public void help() {
        System.out.println(divider);
        System.out.println("Welcome to the user guide. This guide has all the" +
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
        System.out.println(divider);
    }

    /**
     * Handles general errors
     *
     * @param error The error message thrown.
     */
    public void generalError(DukeException.DukeCommandException error) {
        System.out.println(divider);
        System.out.println(error.getMessage());
        System.out.println("Please provide a proper command. Formats are as follows: \n\n" +
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
        System.out.println(divider);
    }

    /**
     * Handles the find keyword user input.
     *
     * @param search The string to search for.
     */
    public void find(String search) {
        int counter = 0;
        System.out.println(divider);
        System.out.println("Here are the matching tasks in your list :");
        for (int i = 0; i < storage.taskList.arrayList.size(); i++) {
            if (storage.taskList.arrayList.get(i).getDescription().contains(search)) {
                System.out.println((i + 1) + ". " + storage.taskList.arrayList.get(i).toString());
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("No tasks on the list match the search criteria.");
        }
        System.out.println(divider);
    }

    /**
     * Handles erroneous find keyword usage.
     */
    public void findError() {
        System.out.println("Please provide a space between your search " +
                "and the find keyword. Format : find <search keyword>");
    }

    /**
     * Handles missing search keywords.
     */
    public void noSearchError() {
        System.out.println("Please provide keywords to search the list with.");
    }
}
