package duke;

import duke.TaskList;
import duke.CommandType;
import duke.Event;
import duke.Deadline;
import duke.ToDo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {

    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses a user command and returns its type as a CommandType
     *
     * @param userCommand the command entered by the user.
     * @return            the type of the command, as a CommandType
     */
    public CommandType getCommandType(String userCommand) {
        if (userCommand.startsWith("bye")) {
            return CommandType.EXIT;
        } else if (userCommand.startsWith("todo") ||
                   userCommand.startsWith("event") ||
                   userCommand.startsWith("deadline")) {
            return CommandType.ADD;
        } else if (userCommand.startsWith("remove")) {
            return CommandType.REMOVE;
        } else if (userCommand.startsWith("mark")) {
            return CommandType.MARK_AS_DONE;
        } else if (userCommand.startsWith("unmark")) {
            return CommandType.MARK_AS_UNDONE;
        } else if (userCommand.startsWith("list")) {
            return CommandType.LIST;
        } else if (userCommand.startsWith("sort")) {
            return CommandType.SORT;
        } else if (userCommand.startsWith("search")) {
            return CommandType.SEARCH;
        } else {
            return CommandType.NONSENSE;
        }
    }

    public String execute(String userCommand) {
        switch (getCommandType(userCommand)) {
        case EXIT:
            return Ui.showGoodbyeMessage();
        case ADD:
            return handleAddCommand(userCommand);
        case REMOVE:
            return handleRemoveCommand(userCommand);
        case MARK_AS_DONE:
            return handleMarkAsDoneCommand(userCommand);
        case MARK_AS_UNDONE:
            return handleMarkAsUndoneCommand(userCommand);
        case LIST:
            return handleListCommand();
        case SORT:
            return handleSortCommand();
        case SEARCH:
            return handleSearchCommand(userCommand);
        case NONSENSE:
            return Ui.showInvalidCommandError();
        default:
            return Ui.showInvalidCommandError();
        }
    }

    private String handleAddCommand(String userCommand) {
        // TODO: Ensure that the user does not use the '|' character.
        //       (The '|' character is used as a separator when encoding the tasks as a text file.)
        assert(!userCommand.contains("|"));

        if (userCommand.startsWith("event")) {
            // TODO: Ensure that the string contains a "@" and a time range is specified.
            String eventName = userCommand.substring(6).split("@ ")[0].strip();
            String eventTimeRange = userCommand.split("@ ")[1].strip();
            taskList.add(new Event(eventName, false, eventTimeRange));
            return String.format("Duke: I have added the event %s.\n", eventName);
        } else if (userCommand.startsWith("deadline")) {
            // TODO: Ensure that the string contains a "@" and a due date is specified.
            String deadlineName = userCommand.substring(9).split("@ ")[0].strip();
            String deadlineDueDate = userCommand.split("@ ")[1].strip();
            taskList.add(new Deadline(deadlineName, false, deadlineDueDate));
            return String.format("Duke: I have added the deadline %s.\n", deadlineName);
        } else { // a 'todo'
            assert userCommand.startsWith("todo");
            String toDoName = userCommand.substring(5).strip();
            taskList.add(new ToDo(toDoName, false));
            return String.format("Duke: I have added the to-do %s.\n", toDoName);
        }

    }

    private String handleRemoveCommand(String userCommand) {
        // TODO: Ensure that the index is reasonable.
        int index = Integer.parseInt(userCommand.substring(7)) - 1;
        assert (index >= 0 && index <= taskList.size());
        String stringToReturn = String.format("Duke: I have removed the task %s.\n", taskList.get(index));
        taskList.remove(index);
        return stringToReturn;
    }

    private String handleMarkAsDoneCommand(String userCommand) {
        // TODO: Ensure that the index is reasonable.
        int index = Integer.parseInt(userCommand.substring(5)) - 1;
        assert (index >= 0 && index <= taskList.size());
        taskList.get(index).setComplete(true);

        return ("Task marked as complete.\n" + taskList.get(index).toString());
    }

    private String handleMarkAsUndoneCommand(String userCommand) {
        // TODO: Ensure that the index is reasonable.
        int index = Integer.parseInt(userCommand.substring(7)) - 1;
        assert (index >= 0 && index <= taskList.size());
        taskList.get(index).setComplete(false);

        return ("Task marked as not complete.\n" + taskList.get(index).toString());
    }

    private String handleListCommand() {
        return ("Duke: Here are your tasks.\n" + taskList.toDisplayString());
    }

    private String handleSortCommand() {
        taskList.sortTasksByName();
        return ("Duke: Here are your sorted tasks.\n" + taskList.toDisplayString());
    }

    private String handleSearchCommand(String userCommand) {
        String searchTerm = userCommand.substring(7).strip();

        StringBuilder stringToReturn = new StringBuilder();
        stringToReturn.append("Duke: Here are the matching tasks.");

        Stream<Task> filteredTasksStream = taskList
                .stream()
                .filter(task -> task.getName().contains(searchTerm));

        List<Task> filteredTaskList = filteredTasksStream.collect(Collectors.toList());

        for (int i = 0; i < filteredTaskList.size(); i++) {
            stringToReturn.append(String.format("\n%3d: %s", i + 1, taskList.get(i).toString()));
        }

        return stringToReturn.toString();
    }
}
