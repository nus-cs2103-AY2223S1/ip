import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Parser {
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
                int deleteIndex = Integer.parseInt(response[1]);
                Task deletedTask = listOfTasks.getTask(deleteIndex);
                listOfTasks.remove(deleteIndex);
                ui.showDeletedTask(deletedTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! You're missing an index for delete.");
            }
            break;

        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
