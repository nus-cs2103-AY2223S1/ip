package duke;
import duke.Exceptions.NoSuchCommandException;
import duke.Task.Event;
import duke.Task.Task;
import duke.Task.Todo;
import duke.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {

    public Parser() {
    }
    /**
     * Checks if the command inputted is a valid command
     * @param input: input string to check
     * @return enum Duke.Commands value
     * @throws NoSuchCommandException inputted command is not a valid command
     */
    public Duke.Commands analyzeCommand(String input) throws NoSuchCommandException {
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
        }
        throw new NoSuchCommandException();
    }

    private LocalDateTime formatTime(String dateTime) {
        String[] dateTimeArr = dateTime.split(" ");
        LocalDate date = LocalDate.parse(dateTimeArr[0]);
        Integer hour = Integer.parseInt(dateTimeArr[1].substring(0,2));
        Integer minute = Integer.parseInt(dateTimeArr[1].substring(2,4));
        LocalTime time = LocalTime.of(hour,minute,0);

        return LocalDateTime.of(date, time);
    }

    /**
     * Executes the given command
     * @param ui the user interface to display text
     * @param input the command input
     * @param storage the storage system
     * @param taskList the list of task
     */
    public void executeInput(Ui ui, String input, Storage storage, TaskList taskList) {
        String[] inputArr = input.split(" ", 2);
        String commandString = inputArr[0];

        try {

            switch (this.analyzeCommand(commandString)) {
                case LIST:
                    handleListComand(ui, taskList);
                    break;
                case TODO:
                    handleTodoCommand(ui, storage, taskList, inputArr[1]);
                    break;
                case EVENT:
                    handleEventCommand(ui, storage, taskList, inputArr[1]);
                    break;
                case DEADLINE:
                    handleDeadlineCommand(ui, storage, taskList, inputArr[1]);
                    break;
                case MARK:
                    handleMarkCommand(ui, storage, taskList, inputArr[1]);
                    break;
                case UNMARK:
                    handleUnmarkCommand(ui, storage, taskList, inputArr[1]);
                    break;
                case DELETE:
                    handleDeleteCommand(ui, storage, taskList, inputArr[1]);
                    break;

            }
        } catch (NoSuchCommandException e) {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void handleListComand(Ui ui, TaskList taskList) {
        ui.showAllTask(taskList);
    }

    private void handleTodoCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        Task task = new Todo(input);
        taskList.addTask(task);
        ui.showAddTask(taskList, task);
        storage.updateFile(taskList.list);
    }
    private void handleEventCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        String[] inputArr = input.split("/at");
        String description = inputArr[0];
        LocalDateTime time = formatTime(inputArr[1].strip());
        Task task = new Event(description, time);
        taskList.addTask(task);
        ui.showAddTask(taskList, task);
        storage.updateFile(taskList.list);
    }

    private void handleDeadlineCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        String[] inputArr = input.split("/by");
        String description = inputArr[0];
        LocalDateTime time = formatTime(inputArr[1].strip());
        Task task = new Event(description, time);
        taskList.addTask(task);
        ui.showAddTask(taskList, task);
        storage.updateFile(taskList.list);
    }
    private void handleMarkCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        Integer index = Integer.parseInt(input) - 1;
        Task task = taskList.toggleTaskStatus(index);
        task.toggleStatus();
        ui.showMarkTask(task);
        storage.updateFile(taskList.list);
    }
    private void handleUnmarkCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        Integer index = Integer.parseInt(input) - 1;
        Task task = taskList.toggleTaskStatus(index);
        ui.showUnmarkTask(task);
        storage.updateFile(taskList.list);
    }
    private void handleDeleteCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        Integer index = Integer.parseInt(input) - 1;
        Task task = taskList.deleteTask(index);
        ui.showRemoveTask(taskList, task);
        storage.updateFile(taskList.list);
    }


}
