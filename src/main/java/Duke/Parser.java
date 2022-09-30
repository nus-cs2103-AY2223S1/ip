package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Parser class object.
 */
public class Parser {
    private static boolean isExit = false;

    /**
     * Gets whether program should exit
     *
     * @return boolean of whether program should exit
     */
    public static boolean getIsExit() {
        return isExit;
    }

    /**
     * Enables program to exit
     */
    public static void setIsExit() {
        isExit = true;
    }

    /**
     * Creates a Parser object by parsing the commands from user.
     *
     * @param command     is the response from user.
     * @param listOfTasks is the list of tasks stored.
     * @param ui          is the output text from Duke to console.
     * @param storage     deals with saving and loading tasks in Duke.txt.
     * @param undo        deals with undoing the last command from user.
     * @throws IOException   if file does not open.
     * @throws DukeException if command is invalid.
     */
    public static String parse(String command, TaskList listOfTasks, Ui ui, Storage storage, Undo undo)
            throws IOException, DukeException {
        String reply = "";
        String[] response = command.split(" ");
        String firstWord = response[0].toUpperCase();

        switch (firstWord) {
        case "BYE":
            setIsExit();
            reply = ui.showBye();
            break;

        case "LIST":
            if (listOfTasks.getSize() == 0) {
                reply = ui.showError("No tasks.");
                break;
            }
            reply = ui.showTaskList(listOfTasks);
            break;

        case "MARK":
            reply = handleMark(listOfTasks, response, ui, storage, undo);
            break;

        case "UNMARK":
            reply = handleUnmark(listOfTasks, response, ui, storage, undo);
            break;

        case "TODO":
            reply = handleToDo(listOfTasks, command, ui, storage, undo);
            break;

        case "DEADLINE":
            reply = handleDeadline(listOfTasks, command, ui, storage, undo);
            break;

        case "EVENT":
            reply = handleEvent(listOfTasks, command, ui, storage, undo);
            break;

        case "DELETE":
            reply = handleDelete(listOfTasks, response, ui, storage, undo);
            break;

        case "FIND":
            reply = handleFind(listOfTasks, response, ui, storage, undo);
            break;

        case "UNDO":
            reply = handleUndo(listOfTasks, ui, storage, undo);
            break;

        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return reply;
    }

    /**
     * Handles marking task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param response    user message.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @param undo        stack of commands.
     * @return reply from chat bot.
     * @throws DukeException if invalid command.
     */
    public static String handleMark(TaskList listOfTasks, String[] response, Ui ui, Storage storage, Undo undo)
            throws DukeException {
        try {
            int taskIndex = Integer.parseInt(response[1]) - 1;
            if (taskIndex >= listOfTasks.getSize()) {
                throw new DukeException(":( OOPS!!! That index does not exist.");
            }
            listOfTasks.markAsDone(taskIndex);
            String reply = ui.showMarkedTask(taskIndex, listOfTasks);
            storage.writeToTextFile(listOfTasks);
            undo.addLastCommand("MARK");
            undo.addLastIndex(taskIndex);
            return reply;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! You're missing an index for mark.");
        } catch (IOException e) {
            throw new DukeException("Error writing text to file.");
        }
    }

    /**
     * Handles unmarking task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param response    user message.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @param undo        stack of commands.
     * @return reply from chat bot.
     * @throws DukeException if invalid command.
     */
    public static String handleUnmark(TaskList listOfTasks, String[] response, Ui ui, Storage storage, Undo undo)
            throws DukeException {
        try {
            int taskIndex = Integer.parseInt(response[1]) - 1;
            if (taskIndex >= listOfTasks.getSize()) {
                throw new DukeException(":( OOPS!!! That index does not exist.");
            }
            listOfTasks.markAsNotDone(taskIndex);
            String reply = ui.showUnmarkedTask(taskIndex, listOfTasks);
            storage.writeToTextFile(listOfTasks);
            undo.addLastCommand("UNMARK");
            undo.addLastIndex(taskIndex);
            return reply;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! You're missing an index for unmark.");
        } catch (IOException e) {
            throw new DukeException("Error writing text to file");
        }
    }

    /**
     * Handles todo task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param command     user command.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @param undo        stack of commands.
     * @return reply from chat bot.
     * @throws DukeException if invalid command.
     */
    public static String handleToDo(TaskList listOfTasks, String command, Ui ui, Storage storage, Undo undo)
            throws DukeException {
        try {
            String toDoTaskDescription = command.substring(5);
            Todo toDoTask = new Todo(toDoTaskDescription);
            listOfTasks.add(toDoTask);
            String reply = ui.showToDoTask(toDoTask, listOfTasks);
            storage.writeToTextFile(listOfTasks);
            undo.addLastCommand("TODO");
            return reply;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException e) {
            throw new DukeException("Error writing text to file");
        }
    }

    /**
     * Handles deadline task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param command     user command.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @param undo        stack of commands.
     * @return reply from chat bot.
     * @throws DukeException if invalid command.
     */
    public static String handleDeadline(TaskList listOfTasks, String command, Ui ui, Storage storage, Undo undo)
            throws DukeException {
        try {
            String deadlineDescriptionWithDate = command.substring(9);
            String deadlineDescription = deadlineDescriptionWithDate.split(" /by ")[0];
            String deadlineDate = deadlineDescriptionWithDate.split(" /by ")[1];
            Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
            listOfTasks.add(deadlineTask);
            String reply = ui.showDeadlineTask(deadlineTask, listOfTasks);
            storage.writeToTextFile(listOfTasks);
            undo.addLastCommand("DEADLINE");
            return reply;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! The description of a deadline cannot be empty.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! You're missing some descriptions for your deadline.");
        } catch (DateTimeParseException e) {
            throw new DukeException(":( OOPS!!! You need to use yyyy-mm-dd for date format.");
        } catch (IOException e) {
            throw new DukeException("Error writing text to file");
        }
    }

    /**
     * Handles event task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param command     user command.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @param undo        stack of commands.
     * @return reply from chat bot.
     * @throws DukeException if invalid command.
     */
    public static String handleEvent(TaskList listOfTasks, String command, Ui ui, Storage storage, Undo undo)
            throws DukeException {
        try {
            String eventDescriptionWithDate = command.substring(6);
            String eventDescription = eventDescriptionWithDate.split(" /at ")[0];
            String eventDate = eventDescriptionWithDate.split(" /at ")[1];
            Event eventTask = new Event(eventDescription, eventDate);
            listOfTasks.add(eventTask);
            String reply = ui.showEventTask(eventTask, listOfTasks);
            storage.writeToTextFile(listOfTasks);
            undo.addLastCommand("EVENT");
            return reply;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! The description of an event cannot be empty.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! You're missing some descriptions for your event.");
        } catch (DateTimeParseException e) {
            throw new DukeException(":( OOPS!!! You need to use yyyy-mm-dd for date format.");
        } catch (IOException e) {
            throw new DukeException("Error writing text to file");
        }
    }

    /**
     * Handles delete task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param response    user message.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @param undo        stack of commands.
     * @return reply from chat bot.
     * @throws DukeException if invalid command.
     */
    public static String handleDelete(TaskList listOfTasks, String[] response, Ui ui, Storage storage, Undo undo)
            throws DukeException {
        try {
            int deleteIndex = Integer.parseInt(response[1]) - 1;
            if (deleteIndex >= listOfTasks.getSize()) {
                throw new DukeException(":( OOPS!!! That index does not exist.");
            }
            Task deletedTask = listOfTasks.getTask(deleteIndex);
            listOfTasks.remove(deleteIndex + 1);
            String reply = ui.showDeletedTask(deletedTask, listOfTasks);
            storage.writeToTextFile(listOfTasks);
            undo.addLastCommand("DELETE");
            undo.addLastIndex(deleteIndex);
            undo.addLastTask(deletedTask);
            return reply;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! You're missing an index for delete.");
        } catch (IOException e) {
            throw new DukeException("Error writing text to file");
        }
    }

    /**
     * Handles find task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param response    user message.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @param undo        stack of commands.
     * @return reply from chat bot.
     * @throws DukeException if invalid command.
     */
    public static String handleFind(TaskList listOfTasks, String[] response, Ui ui, Storage storage, Undo undo)
            throws DukeException {
        try {
            String keyword = response[1];
            TaskList matchingTasks = new TaskList();
            for (Task task : listOfTasks.getListOfTasks()) {
                if (task.getDescription().contains(keyword)) {
                    matchingTasks.add(task);
                }
            }
            return ui.showFindTask(matchingTasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! The description of a find cannot be empty.");
        }
    }

    /**
     * Handles undo.
     *
     * @param listOfTasks is tasks saved by user.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @param undo        stack of commands.
     * @return repky from chat bot.
     * @throws DukeException if invalid command.
     * @throws IOException   if file does not exist.
     */
    public static String handleUndo(TaskList listOfTasks, Ui ui, Storage storage, Undo undo)
            throws DukeException, IOException {
        if (undo.isCommandStackEmpty()) {
            throw new DukeException("No previous commands.");
        }
        String lastCommand = undo.popLastCommand();
        String reply = "";
        switch (lastCommand) {
        case "MARK":
            reply = handleMarkUndo(listOfTasks, ui, storage, undo);
            break;

        case "UNMARK":
            reply = handleUnmarkUndo(listOfTasks, ui, storage, undo);
            break;

        case "TODO":
            reply = handleTodoUndo(listOfTasks, ui, storage);
            break;

        case "DEADLINE":
            reply = handleDeadlineUndo(listOfTasks, ui, storage);
            break;

        case "EVENT":
            reply = handleEventUndo(listOfTasks, ui, storage);
            break;

        case "DELETE":
            reply = handleDeleteUndo(listOfTasks, ui, storage, undo);
            break;

        default:
            break;
        }
        return reply;
    }

    /**
     * Handles undo marked task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @param undo        stack of commands.
     * @return reply from chat bot.
     * @throws IOException if file does not exist.
     */
    public static String handleMarkUndo(TaskList listOfTasks, Ui ui, Storage storage, Undo undo)
            throws IOException {
        int taskIndex = undo.popLastIndex();
        listOfTasks.markAsNotDone(taskIndex);
        String reply = ui.showUnmarkedTask(taskIndex, listOfTasks);
        storage.writeToTextFile(listOfTasks);
        return reply;
    }

    /**
     * Handles undo unmarked task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @param undo        stack of commands.
     * @return reply from chat bot.
     * @throws IOException if file does not exist.
     */
    public static String handleUnmarkUndo(TaskList listOfTasks, Ui ui, Storage storage, Undo undo)
            throws IOException {
        int taskIndex = undo.popLastIndex();
        listOfTasks.markAsDone(taskIndex);
        String reply = ui.showMarkedTask(taskIndex, listOfTasks);
        storage.writeToTextFile(listOfTasks);
        return reply;
    }

    /**
     * Handles undo todo task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @return reply from chat bot.
     * @throws IOException if file does not exist.
     */
    public static String handleTodoUndo(TaskList listOfTasks, Ui ui, Storage storage)
            throws IOException {
        Todo lastToDoTask = (Todo) listOfTasks.popLastTask();
        String reply = ui.showDeletedTask(lastToDoTask, listOfTasks);
        storage.writeToTextFile(listOfTasks);
        return reply;
    }

    /**
     * Handles undo deadline task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @return reply from chat bot.
     * @throws IOException if file does not exist.
     */
    public static String handleDeadlineUndo(TaskList listOfTasks, Ui ui, Storage storage)
            throws IOException {
        Deadline lastDeadlineTask = (Deadline) listOfTasks.popLastTask();
        String reply = ui.showDeletedTask(lastDeadlineTask, listOfTasks);
        storage.writeToTextFile(listOfTasks);
        return reply;
    }

    /**
     * Handles undo event task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @return reply from chat bot.
     * @throws IOException if file does not exist.
     */
    public static String handleEventUndo(TaskList listOfTasks, Ui ui, Storage storage)
            throws IOException {
        Event lastEventTask = (Event) listOfTasks.popLastTask();
        String reply = ui.showDeletedTask(lastEventTask, listOfTasks);
        storage.writeToTextFile(listOfTasks);
        return reply;
    }

    /**
     * Handles undo delete task.
     *
     * @param listOfTasks is tasks saved by user.
     * @param ui          of chat bot reply.
     * @param storage     of chat bot with saved text.
     * @param undo        stack of commands.
     * @return reply from chat bot.
     * @throws IOException if file does not exist.
     */
    public static String handleDeleteUndo(TaskList listOfTasks, Ui ui, Storage storage, Undo undo)
            throws IOException {
        int taskIndex = undo.popLastIndex();
        Task deletedTask = undo.popLastTask();
        listOfTasks.addTaskToIndex(taskIndex, deletedTask);
        String reply = ui.showUndoDeletedTask(deletedTask, listOfTasks);
        storage.writeToTextFile(listOfTasks);
        return reply;
    }
}
