package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.exceptions.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 *
 */
public class Parser {

    public Parser() {
    }

    /**
     * Checks if the command inputted is a valid command
     *
     * @param input comannd input
     * @return enum Duke.Commands value
     * @throws DukeException
     */
    public Duke.Commands analyzeCommand(String input) throws DukeException {
        if (input.matches("list(.*)")) {
            return Duke.Commands.LIST;
        } else if (input.matches("bye(.*)")) {
            return Duke.Commands.BYE;

        } else if (input.matches("todo(.*)")) {
            return Duke.Commands.TODO;

        } else if (input.matches("mark(.*)")) {
            return Duke.Commands.MARK;
        } else if (input.matches("unmark(.*)")) {
            return Duke.Commands.UNMARK;
        } else if (input.matches("event(.*)")) {
            return Duke.Commands.EVENT;

        } else if (input.matches("deadline(.*)")) {
            return Duke.Commands.DEADLINE;
        } else if (input.matches("delete(.*)")) {
            return Duke.Commands.DELETE;
        } else if (input.matches("find(.*)")) {
            return Duke.Commands.FIND;
        }
        throw new DukeException("I dont understand this command booo");
    }

    private LocalDateTime formatTime(String dateTime) {
        String[] dateTimeArr = dateTime.split(" ");
        LocalDate date = LocalDate.parse(dateTimeArr[0]);
        Integer hour = Integer.parseInt(dateTimeArr[1].substring(0, 2));
        Integer minute = Integer.parseInt(dateTimeArr[1].substring(2, 4));
        LocalTime time = LocalTime.of(hour, minute, 0);

        return LocalDateTime.of(date, time);
    }

    /**
     * Executes the given command
     *
     * @param ui       the user interface to display text
     * @param input    the command input
     * @param storage  the storage system
     * @param taskList the list of task
     */
    public String executeInput(Ui ui, String input, Storage storage, TaskList taskList) {
        String[] inputArr = input.split(" ", 2);
        try {
            switch (this.analyzeCommand(input)) {
            case BYE:
                return ui.showBye();
            case LIST:
                return handleListComand(ui, taskList);
            case TODO:
                return handleTodoCommand(ui, storage, taskList, inputArr[1]);
            case EVENT:
                return handleEventCommand(ui, storage, taskList, inputArr[1]);
            case DEADLINE:
                return handleDeadlineCommand(ui, storage, taskList, inputArr[1]);
            case MARK:
                return handleMarkCommand(ui, storage, taskList, inputArr[1]);
            case UNMARK:
                return handleUnmarkCommand(ui, storage, taskList, inputArr[1]);
            case DELETE:
                return handleDeleteCommand(ui, storage, taskList, inputArr[1]);
            case FIND:
                return handleFindCommand(ui, taskList, inputArr[1]);
            default:
                return null;
            }
        } catch (IndexOutOfBoundsException e) {
            return new DukeException("Description cannot be empty").getMessage();
        } catch (DukeException e) {
            return e.getMessage();
        }

    }

    private String handleListComand(Ui ui, TaskList taskList) {
        return ui.showAllTask(taskList);
    }

    private String handleTodoCommand(Ui ui, Storage storage, TaskList taskList, String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("Description cannot be empty");
        }
        Task task = new Todo(input);
        taskList.addTask(task);
        storage.updateFile(taskList.list);
        return ui.showAddTask(taskList, task);

    }

    private String handleEventCommand(Ui ui, Storage storage, TaskList taskList, String input) throws DukeException {
        if (!input.contains("/at")) {
            throw new DukeException("Event command must have /at param");
        }
        String[] inputArr = input.split("/at");
        if (inputArr[0].isEmpty()) {
            throw new DukeException("Description cannot be empty");
        }
        String description = inputArr[0];
        try {
            LocalDateTime time = formatTime(inputArr[1].strip());
            Task task = new Event(description, time);
            taskList.addTask(task);
            storage.updateFile(taskList.list);
            return ui.showAddTask(taskList, task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Time cannot be empty");
        }

    }

    private String handleDeadlineCommand(Ui ui, Storage storage, TaskList taskList, String input) throws DukeException {
        if (!input.contains("/by")) {
            throw new DukeException("Deadline command must have /by param");
        }
        String[] inputArr = input.split("/by");
        if (inputArr[0].isEmpty()) {
            throw new DukeException("Description cannot be empty");
        }
        try {
            String description = inputArr[0];
            LocalDateTime time = formatTime(inputArr[1].strip());
            Task task = new Event(description, time);
            taskList.addTask(task);
            storage.updateFile(taskList.list);
            return ui.showAddTask(taskList, task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Time cannot be empty");
        }
    }

    private String handleMarkCommand(Ui ui, Storage storage, TaskList taskList, String input) throws DukeException {
        try {
            Integer index = Integer.parseInt(input) - 1;
            Task task = taskList.toggleTaskStatus(index);
            storage.updateFile(taskList.list);
            return ui.showMarkTask(task);
        } catch (NumberFormatException e) {
            throw new DukeException("Param can only contain integers");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no such task");
        }


    }

    private String handleUnmarkCommand(Ui ui, Storage storage, TaskList taskList, String input) throws DukeException {
        try {
            Integer index = Integer.parseInt(input) - 1;
            Task task = taskList.toggleTaskStatus(index);
            storage.updateFile(taskList.list);

            return ui.showUnmarkTask(task);
        } catch (NumberFormatException e) {
            throw new DukeException("Param can only contain integers");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no such task");
        }

    }

    private String handleDeleteCommand(Ui ui, Storage storage, TaskList taskList, String input) throws DukeException {
        try {
            Integer index = Integer.parseInt(input) - 1;
            Task task = taskList.deleteTask(index);
            storage.updateFile(taskList.list);

            return ui.showRemoveTask(taskList, task);
        } catch (NumberFormatException e) {
            throw new DukeException("Param can only contain integers");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no such task");
        }

    }
    private String handleFindCommand(Ui ui, TaskList taskList, String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("Param cannot be empty");

        }
        return ui.showFindTask(taskList.filterTask(input));
    }


}
