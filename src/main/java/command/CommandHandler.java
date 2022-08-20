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

import printer.Printer;

public class CommandHandler {
    private final Storage storage;
    private final TaskList taskList;

    public CommandHandler() {
        this.storage = new Storage(Config.DIRECTORY, Config.NAME);
        this.taskList = this.storage.loadTasksInStorage();
    }

    public void processSingleCommand(Command command) {
        switch (command) {
        case BYE:
            Printer.print("Bye. See you later master!");
            break;
        case LIST:
            Printer.print(this.taskList.toString());
            break;
        }
    }

    public void editTask(Command command, String description) throws CommandException {
        try {
            int selectedIndex = Integer.parseInt(description) - 1;

            switch (command) {
            case MARK:
                this.taskList.markTaskWithIndex(selectedIndex);
                break;
            case UNMARK:
                this.taskList.unmarkTaskWithIndex(selectedIndex);
                break;
            case DELETE:
                this.taskList.removeTaskWithIndex(selectedIndex);
                break;
            }

            this.storage.saveTasksInStorage(this.taskList.toStorageRepresentation());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CommandException();
        }
    }

    public void addTask(Command command, String description) throws CommandException {
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
        this.storage.saveTasksInStorage(this.taskList.toStorageRepresentation());
    }
}
