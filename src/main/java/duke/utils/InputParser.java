package duke.utils;

import duke.commands.*;
import duke.tasks.TaskList;
import duke.tasks.TaskType;
import duke.ui.Ui;

public class InputParser {

    public Command parse(String input, TaskList tasks, Storage storage, Ui ui) {
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
            return new PrintTasksCommand(ui, tasks);
        } else if ("mark".equals(command)) {
            return new MarkTaskCommand(storage, ui, tasks, body);
        } else if ("unmark".equals(command)) {
            return new UnmarkTaskCommand(storage, ui, tasks, body);
        } else if ("delete".equals(command)) {
            return new DeleteTaskCommand(storage, ui, tasks, body);
        } else if ("find".equals(command)) {
            return new FindTaskCommand(tasks, ui, body);
        } else if (isTaskType(command)) {
            TaskType type = getTaskType(command);
            return new AddTaskCommand(storage, ui, tasks, type, body);
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
