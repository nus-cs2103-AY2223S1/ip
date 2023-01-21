package bobby;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bobby.exceptions.DukeException;
import bobby.task.Deadline;
import bobby.task.Event;
import bobby.task.Task;
import bobby.task.Todo;


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
    public Bobby.Commands analyzeCommand(String input) throws DukeException {
        if (input.matches("list(.*)")) {
            return Bobby.Commands.LIST;
        } else if (input.matches("bye(.*)")) {
            return Bobby.Commands.BYE;

        } else if (input.matches("todo(.*)")) {
            return Bobby.Commands.TODO;

        } else if (input.matches("mark(.*)")) {
            return Bobby.Commands.MARK;
        } else if (input.matches("unmark(.*)")) {
            return Bobby.Commands.UNMARK;
        } else if (input.matches("event(.*)")) {
            return Bobby.Commands.EVENT;

        } else if (input.matches("deadline(.*)")) {
            return Bobby.Commands.DEADLINE;
        } else if (input.matches("delete(.*)")) {
            return Bobby.Commands.DELETE;
        } else if (input.matches("find(.*)")) {
            return Bobby.Commands.FIND;
        } else if (input.matches("switch(.*)")) {
            return Bobby.Commands.SWITCH;
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
            case SWITCH:
                return handleSwitchCommand(ui, storage, taskList, inputArr[1]);
            default:
                return null;
            }
        } catch (IndexOutOfBoundsException e) {
            return new DukeException("Description cannot be empty").getMessage();
        } catch (DukeException e) {
            return e.getMessage();
        }

    }

    private String handleListComand(Ui ui, TaskList taskList) throws DukeException {
        if (taskList.length() <= 0) {
            throw new DukeException("There are no task!!");
        }
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
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (!input.contains("/at")) {
            throw new DukeException("Event command must have /at param");
        }
        String[] inputArr = input.split("/at");
        if (inputArr[0].isEmpty()) {
            throw new DukeException("Description cannot be empty");
        }
        String description = inputArr[0];
        try {
            String[] datetimeArr = inputArr[1].strip().split(" ");
            String startTimeString = (datetimeArr[0] + " " + datetimeArr[1]).strip();
            String endTimeString = null;
            if (datetimeArr.length < 5) {
                endTimeString = (datetimeArr[0] + " " + datetimeArr[3]).strip();
            } else {
                endTimeString = (datetimeArr[3] + " " + datetimeArr[4]).strip();
            }
            LocalDateTime startTime = LocalDateTime.parse(startTimeString, dateTimeFormat);
            LocalDateTime endTime = LocalDateTime.parse(endTimeString, dateTimeFormat);
            Task task = new Event(description, startTime, endTime);
            taskList.addTask(task);
            storage.updateFile(taskList.list);
            return ui.showAddTask(taskList, task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Time cannot be empty");
        }

    }

    private String handleDeadlineCommand(Ui ui, Storage storage, TaskList taskList, String input) throws DukeException {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (!input.contains("/by")) {
            throw new DukeException("Deadline command must have /by param");
        }
        String[] inputArr = input.split("/by");
        if (inputArr[0].isEmpty()) {
            throw new DukeException("Description cannot be empty");
        }
        try {
            String description = inputArr[0];
            LocalDateTime time = LocalDateTime.parse(inputArr[1].strip(), dateTimeFormat);
            Task task = new Deadline(description, time);
            taskList.addTask(task);
            storage.updateFile(taskList.list);
            return ui.showAddTask(taskList, task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Time cannot be empty");
        } catch (DateTimeParseException e) {
            throw new DukeException("Time must be in YYYY-MM-DD HH:mm format");
        }
    }

    private String handleMarkCommand(Ui ui, Storage storage, TaskList taskList, String input) throws DukeException {
        try {
            Integer index = Integer.parseInt(input) - 1;

            Task task = taskList.markTask(index);
            storage.updateFile(taskList.list);
            return ui.showMarkTask(task);
        } catch (NumberFormatException e) {
            throw new DukeException("Param can only contain integers");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no such task");
        } catch (DateTimeParseException e) {
            throw new DukeException("Time must be in YYYY-MM-DD HH:mm format");
        }


    }

    private String handleUnmarkCommand(Ui ui, Storage storage, TaskList taskList, String input) throws DukeException {
        try {
            Integer index = Integer.parseInt(input) - 1;
            Task task = taskList.unmarkTask(index);
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
    private String handleSwitchCommand(Ui ui, Storage storage, TaskList taskList, String input) throws DukeException {
        if (!input.matches("(.*).txt")) {
            throw new DukeException("File name must end with .txt");
        }
        storage.changeFile(input);
        taskList.refreshTask(storage.readFile());
        return ui.showSwitchFile(input);
    }

}
