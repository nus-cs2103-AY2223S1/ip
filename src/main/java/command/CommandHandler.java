package command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import exception.CommandException;
import exception.DeadlineException;
import exception.EventException;
import exception.ToDoException;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

import storage.Config;
import storage.Storage;

import ui.Ui;

public class CommandHandler {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public CommandHandler(Ui ui) {
        this.storage = new Storage(Config.DIRECTORY, Config.NAME);
        this.taskList = this.storage.loadTasksInStorage();
        this.ui = ui;
    }

    public void handleCommand(CommandPair commandPair) throws CommandException {
        Command command = commandPair.getCommand();
        String description = commandPair.getDescription();

        if (command.equals(Command.BYE) || command.equals(Command.LIST)) {
            this.processSingleCommand(command);
        } else if (command.equals(Command.MARK) || command.equals(Command.UNMARK)
                || command.equals(Command.DELETE)) {
            this.editTask(command, description);
        } else {
            this.addTask(command, description);
        }
    }

    private void processSingleCommand(Command command) {
        switch (command) {
        case BYE:
            this.ui.printFarewellMessage();
            break;
        case LIST:
            this.ui.printTaskList(this.taskList);
            break;
        }
    }

    private void editTask(Command command, String description) throws CommandException {
        try {
            int selectedIndex = Integer.parseInt(description) - 1;

            switch (command) {
            case MARK:
                Task markedTask = this.taskList.markTaskWithIndex(selectedIndex);
                this.ui.printTaskMarkSuccessMessage(markedTask);
                break;
            case UNMARK:
                Task unmarkedTask = this.taskList.unmarkTaskWithIndex(selectedIndex);
                this.ui.printTaskUnmarkSuccessMessage(unmarkedTask);
                break;
            case DELETE:
                Task deletedTask = this.taskList.removeTaskWithIndex(selectedIndex);
                this.ui.printTaskDeletionSuccessMessage(deletedTask, this.taskList.getTaskListSize());
                break;
            }

            this.storage.saveTasksInStorage(this.taskList.toStorageRepresentation());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CommandException();
        }
    }

    private void addTask(Command command, String description) throws CommandException {
        Task newTask = null;

        switch(command) {
        case TODO:
            if (description.isEmpty()) {
                throw new ToDoException();
            }
            newTask = new ToDo(description);
            break;
        case DEADLINE:
            List<String> deadlineInfo = Arrays.stream(description.split("/by", 2))
                    .map(String::trim)
                    .filter(info -> !info.isEmpty())
                    .collect(Collectors.toList());

            if (deadlineInfo.size() < 2) {
                throw new DeadlineException();
            }

            newTask = new Deadline(deadlineInfo.get(0), deadlineInfo.get(1));
            break;
        case EVENT:
            List<String> eventInfo = Arrays.stream(description.split("/at", 2))
                    .map(String::trim)
                    .filter(info -> !info.isEmpty())
                    .collect(Collectors.toList());

            if (eventInfo.size() < 2) {
                throw new EventException();
            }
            newTask = new Event(eventInfo.get(0), eventInfo.get(1));
            break;
        }

        this.taskList.addTask(newTask);
        this.ui.printTaskCreationSuccessMessage(newTask, this.taskList.getTaskListSize());
        this.storage.saveTasksInStorage(this.taskList.toStorageRepresentation());
    }
}
