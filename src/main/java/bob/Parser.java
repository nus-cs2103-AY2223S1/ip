package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents Parser object, to read and respond to user input
 */
public class Parser {

    private boolean toExit = false;

    /**
     * Parses through user input and determines response
     *
     * @param reply user input
     * @param tasks current list of tasks
     * @param storage storage to save and read files
     * @param ui ui to determine text responses to user
     */
    //referenced https://github.com/Donovan9617/ip/blob/master/src/main/java/Duke/Parser.java for structure
    public String parse(String reply, TaskList tasks, Storage storage, Ui ui) {
        String[] splitReply = reply.split(" ");
        String command = splitReply[0].toLowerCase();
        String output = "";
        switch(command) {
        case "bye":
            output = ui.sayGoodbye();
            this.toExit = true;
            break;
        case "list":
            output = ui.displayTaskList(tasks, "here are your tasks!");
            break;
        case "mark":
            try {
                int index = Integer.valueOf(splitReply[1]);
                tasks.markTask(index, true);
                output = ui.displayMarked(tasks, index);
                storage.save(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                output = ui.displayError("which task to mark?");
            }
            break;
        case "unmark":
            try {
                int index = Integer.valueOf(splitReply[1]);
                tasks.markTask(index, false);
                output = ui.displayUnmarked(tasks, index);
                storage.save(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                output = ui.displayError("which task to unmark?");
            }
            break;
        case "remove":
            try {
                int index = Integer.valueOf(splitReply[1]);
                Task removedTask = tasks.getTask(index);
                tasks.removeTask(index);
                output = ui.displayRemoved(tasks, removedTask);
                storage.save(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                output = ui.displayError("which task to remove?");
            }
            break;
        case "filter":
            try {
                LocalDate date = LocalDate.parse(splitReply[1]);
                String response = "here are your tasks on " + "'"
                        + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "'";
                output = ui.displayTaskList(tasks.filterTask(splitReply[1]), response);
                storage.save(tasks);
            } catch (IndexOutOfBoundsException e) {
                output = ui.displayError("what date do you want to filter?");
            }
            break;
        case "find":
            try {
                String searchWord = splitReply[1];
                String response = "here are all tasks with" + " '" + searchWord + "'";
                output = ui.displayTaskList(tasks.findTask(searchWord), response);
                storage.save(tasks);
            } catch (IndexOutOfBoundsException e) {
                output = ui.displayError("what word do you want to search for?");
            }
            break;
        case "todo":
            try {
                String taskName = reply.substring(5);
                ToDo newTask = new ToDo(taskName);
                tasks.addTask(newTask);
                output = ui.displayAddedTask(tasks, newTask);
                storage.save(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                output = ui.displayError("Oops! What's the todo again?");
            }
            break;
        case "deadline":
            try {
                String[] temp = reply.split("/");
                String taskName = temp[0].substring(9, temp[0].length() - 1);
                String date = temp[1].substring(3);
                Deadline newTask = new Deadline(taskName, LocalDate.parse(date));
                tasks.addTask(newTask);
                output = ui.displayAddedTask(tasks, newTask);
                storage.save(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                output = ui.displayError("Oops! What's the deadline again?");
            }
            break;
        case "event":
            try {
                String[] temp = reply.split("/");
                String taskName = temp[0].substring(6, temp[0].length() - 1);
                String date = temp[1].substring(3);
                Event newTask = new Event(taskName, LocalDate.parse(date));
                tasks.addTask(newTask);
                output = ui.displayAddedTask(tasks, newTask);
                storage.save(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                output = ui.displayError("Oops! When's your event again?");
            }
            break;
        default:
            output = ui.displayError("Oops! What do you want to do?");
        }
        return output;
    }

    /**
     * Retrieves boolean value toExit
     */
    public boolean getToExit() {
        return this.toExit;
    }
}
