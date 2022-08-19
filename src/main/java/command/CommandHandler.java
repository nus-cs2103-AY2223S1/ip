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
import task.ToDo;

import storage.Config;
import storage.Storage;

import printer.Printer;

public class CommandHandler {
    private final Storage storage;

    public CommandHandler() {
        this.storage = new Storage(Config.DIRECTORY, Config.NAME);
    }

    public void processSingleCommand(Command command) {
        switch (command) {
        case BYE:
            Printer.print("Bye. See you later master!");
            break;
        case LIST:
            Printer.print(this.storage.toString());
            break;
        }
    }

    public void editTask(Command command, String description) throws CommandException {
        int selectedIndex;

        try {
            selectedIndex = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            throw new CommandException();
        }

        if (!this.storage.checkIndex(selectedIndex)) {
            throw new CommandException();
        }

        switch (command) {
        case MARK:
            this.storage.markTaskWithIndex(selectedIndex);
            break;
        case UNMARK:
            this.storage.unmarkTaskWithIndex(selectedIndex);
            break;
        case DELETE:
            this.storage.removeTaskWithIndex(selectedIndex);
            break;
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

        this.storage.addTask(newTask);
    }
}
