package uwu.command;

import uwu.exception.EmptyInputException;
import uwu.exception.IncorrectFormatException;
import uwu.exception.InvalidDateException;
import uwu.exception.UwuException;

import uwu.Storage;

import uwu.task.Deadline;
import uwu.task.Event;
import uwu.task.Task;
import uwu.task.TaskList;
import uwu.task.ToDos;

import uwu.Ui;

public class AddCommand extends Command {
    String description;
    String taskType;
    String userCommand;

    public AddCommand(String userCommand) {
        this.userCommand = userCommand;
        String[] taskData = userCommand.split(" ", 2);
        this.taskType = taskData[0];
        this.description = taskData[1];
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws UwuException {
        if (userCommand.replaceFirst(taskType, "").isBlank()) {
            throw new EmptyInputException("\tyour task description is empty TT\n\t"
                    + "feed me a task description to get started! <:");
        }

        switch (taskType) {
        case "todo":
                ToDos todo = new ToDos(description);
                tasks.add(todo);
                storage.save(tasks.taskListToStorageString());
                ui.addTask(todo, tasks.size());
                break;
        case "deadline":
        case "event":
            String descriptor = taskType.equals("deadline") ? "/by" : "/at";

            if (!description.contains(descriptor)) {
                throw new IncorrectFormatException("\tplease make sure your task contains the keyword "
                        + descriptor + " ><!");
            }

            if (description.trim().endsWith(descriptor)) {
                throw new InvalidDateException("\tuwu it looks like the date is missing~"
                        + "\n\tplease enter a date after the " + descriptor
                        + " in this format:" + "\n\tyyyy-mm-dd HH:mm"
                        + "\n\tthankiew <:");
                }

            int startIndex = userCommand.indexOf(descriptor + " ");
            int userCmdLen = userCommand.length();
            String description = userCommand.substring(taskType.length(), startIndex).trim();

            if (description.isBlank()) {
                throw new EmptyInputException("\tyour task description is empty TT\n\t"
                        + "feed me a task description to get started! <:");
            }

            String start = userCommand.substring(startIndex + 3, userCmdLen).trim();

            Task task = taskType.equals("deadline") ? new Deadline(description, start) : new Event(description, start);
            tasks.add(task);
            storage.save(tasks.taskListToStorageString());
            ui.addTask(task, tasks.size());
            break;
        }
    }

    public boolean isExit() {
        return false;
    }
}
