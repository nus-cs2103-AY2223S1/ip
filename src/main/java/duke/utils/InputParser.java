package duke.utils;

import duke.commands.*;
import duke.tasks.TaskList;
import duke.tasks.TaskType;
import duke.ui.Ui;

public class InputParser {

    /**
     * Parses the input string.
     * @param input Input string.
     * @param tasks TaskList in use by the app.
     * @param storage Storage in use by the app.
     * @param ui Ui in use by the app.
     * @return Command to be executed.
     */
    public Command parse(String input, TaskList tasks, Storage storage, Ui ui) {
        String[] splitStr = splitInput(input);
        String command = splitStr[0];
        String body = splitStr[1];
        return parseCommand(command, body, tasks, storage, ui);
    }

    private Command parseCommand(String command, String body, TaskList tasks, Storage storage, Ui ui) {
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

    private String[] splitInput(String input) {
        String[] ret = new String[2];

        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            ret[0] = input;
            ret[1] = "";
        } else {
            ret[0] = input.substring(0, spaceIndex);
            ret[1] = input.substring(spaceIndex + 1);
        }

        return ret;
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
