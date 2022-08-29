package duke;
import duke.Exceptions.NoSuchCommandException;
import duke.Task.Event;
import duke.Task.Task;
import duke.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {

    public Parser() {
    }

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
    public void executeInput(Ui ui, String input, Storage storage, TaskList taskList) {
        String[] inputArr = input.split(" ", 2);
        String commandString = inputArr[0];

        try {
            String param = inputArr[1];
            switch (this.analyzeCommand(commandString)) {
                case BYE:
                    handleByeCommand(ui);
                    break;
                case LIST:
                    handleListComand(ui, taskList);
                    break;
                case TODO:
                    handleTodoCommand(ui, storage, taskList, param);
                    break;
                case EVENT:
                    handleEventCommand(ui, storage, taskList, param);
                    break;
                case DEADLINE:
                    handleDeadlineCommand(ui, storage, taskList, param);
                    break;
                case MARK:
                    handleMarkCommand(ui, storage, taskList, param);
                    break;
                case UNMARK:
                    handleUnmarkCommand(ui, storage, taskList, param);
                    break;
                case DELETE:
                    handleDeleteCommand(ui, storage, taskList, param);
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

    private void handleByeCommand(Ui ui) {
        ui.showBye();
    }

    private void handleTodoCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        Task task = new Task(input);
        taskList.addTask(task);
        ui.showAddTask(taskList, task);
    }
    private void handleEventCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        String[] inputArr = input.split("/at");
        String description = inputArr[0];
        LocalDateTime time = formatTime(inputArr[1].strip());
        Task task = new Event(description, time);
        taskList.addTask(task);
        ui.showAddTask(taskList, task);
    }

    private void handleDeadlineCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        String[] inputArr = input.split("/by");
        String description = inputArr[0];
        LocalDateTime time = formatTime(inputArr[1].strip());
        Task task = new Event(description, time);
        taskList.addTask(task);
        ui.showAddTask(taskList, task);
    }
    private void handleMarkCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        Integer index = Integer.parseInt(input) - 1;
        Task task = taskList.get(index);
        task.toggleStatus();
        ui.showMarkTask(task);
    }
    private void handleUnmarkCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        Integer index = Integer.parseInt(input) - 1;
        Task task = taskList.get(index);
        task.toggleStatus();
        ui.showUnmarkTask(task);
    }
    private void handleDeleteCommand(Ui ui, Storage storage, TaskList taskList, String input) {
        Integer index = Integer.parseInt(input) - 1;
        Task task = taskList.get(index);
        taskList.deleteTask(index);
        ui.showRemoveTask(taskList, task);
    }


}
