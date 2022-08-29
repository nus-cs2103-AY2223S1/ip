package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Parser class object.
 */
public class Parser {

    /**
     * Creates a Parser object by parsing the commands from user.
     *
     * @param command     is the response from user.
     * @param listOfTasks is the list of tasks stored.
     * @param ui          is the output text from Duke to console.
     * @param storage     deals with saving and loading tasks in Duke.txt.
     * @throws DukeException if invalid command input.
     * @throws IOException   if file does not open.
     */
    public static void parse(String command, TaskList listOfTasks, Ui ui, Storage storage)
            throws DukeException, IOException {
        String[] response = command.split(" ");
        Response firstWord = Response.valueOf(response[0].toUpperCase());
        switch (firstWord) {
        case BYE:
            ui.showBye();
            break;

        case LIST:
            ui.showTaskList(listOfTasks);
            break;

        case MARK:
            try {
                int taskIndex = Integer.parseInt(response[1]) - 1;
                listOfTasks.markAsDone(taskIndex);
                ui.showMarkedTask(taskIndex, listOfTasks);
                storage.writeToTextFile(listOfTasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! You're missing an index for mark.");
            }
            break;

        case UNMARK:
            try {
                int taskIndex = Integer.parseInt(response[1]) - 1;
                listOfTasks.markAsNotDone(taskIndex);
                ui.showUnmarkedTask(taskIndex, listOfTasks);
                storage.writeToTextFile(listOfTasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! You're missing an index for unmark.");
            }
            break;

        case TODO:
            try {
                String toDoTaskDescription = command.substring(5);
                Todo toDoTask = new Todo(toDoTaskDescription);
                listOfTasks.add(toDoTask);
                ui.showToDoTask(toDoTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;

        case DEADLINE:
            try {
                String deadlineDescriptionWithDate = command.substring(9);
                String deadlineDescription = deadlineDescriptionWithDate.split(" /by ")[0];
                String deadlineDate = deadlineDescriptionWithDate.split(" /by ")[1];
                Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
                listOfTasks.add(deadlineTask);
                ui.showDeadlineTask(deadlineTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! You're missing some descriptions for your deadline.");
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! You need to use yyyy-mm-dd for date format.");
            }
            break;

        case EVENT:
            try {
                String eventDescriptionWithDate = command.substring(6);
                String eventDescription = eventDescriptionWithDate.split(" /at ")[0];
                String eventDate = eventDescriptionWithDate.split(" /at ")[1];
                Event eventTask = new Event(eventDescription, eventDate);
                listOfTasks.add(eventTask);
                ui.showEventTask(eventTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! You're missing some descriptions for your event.");
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! You need to use yyyy-mm-dd for date format.");
            }
            break;

        case DELETE:
            try {
                int deleteIndex = Integer.parseInt(response[1]) - 1;
                Task deletedTask = listOfTasks.getTask(deleteIndex);
                listOfTasks.remove(deleteIndex + 1);
                ui.showDeletedTask(deletedTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! You're missing an index for delete.");
            }
            break;

        case FIND:
            try {
                String keyword = response[1];
                TaskList matchingTasks = new TaskList();
                for (Task task : listOfTasks.getListOfTasks()) {
                    if (task.getDescription().contains(keyword)) {
                        matchingTasks.add(task);
                    }
                }
                ui.showFindTask(matchingTasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a find cannot be empty.");
            }
            break;

        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
