package utils;

import commands.*;
import tasks.TaskList;
import tasks.TaskType;
import ui.Ui;

public class Parser {

    public Command parse(String input, TaskList taskList, Storage storage, Ui ui) {
        int spaceIndex = input.indexOf(' ');
        String command, body;
        if (spaceIndex == -1) {
            command = input;
            body = "";
        } else {
            command = input.substring(0, spaceIndex);
            body = input.substring(spaceIndex + 1);
        }

        if ("bye".equals(command)) {
            return new ExitCommand(ui);
        } else if ("list".equals(command)) {
            return new PrintTasksCommand(ui, taskList);
        } else if ("mark".equals(command)) {
            int index = Integer.parseInt(body) - 1;
            return new MarkTaskCommand(storage, ui, taskList, index);
        } else if ("unmark".equals(command)) {
            int index = Integer.parseInt(body) - 1;
            return new UnmarkTaskCommand(storage, ui, taskList, index);
        } else if ("delete".equals(command)) {
            int index = Integer.parseInt(body) - 1;
            return new DeleteTaskCommand(storage, ui, taskList, index);
        } else if (isTaskType(command)) {
            TaskType type = getTaskType(command);
            return new AddTaskCommand(storage, ui, taskList, type, body);
        } else {
            return new UnrecognisedCommand(ui);
        }
    }

    private boolean isTaskType(String s) {
        return "todo".equals(s) ||
                "event".equals(s) ||
                "deadline".equals(s);
    }

    private TaskType getTaskType(String type) {
        switch(type) {
            case "todo":
                return TaskType.TODO;
            case "event":
                return TaskType.EVENT;
            case "deadline":
                return TaskType.DEADLINE;
            default:
                return null;
        }
    }

}
