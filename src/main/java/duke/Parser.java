package duke;

import duke.TaskList;
import duke.CommandType;
import duke.Event;
import duke.Deadline;
import duke.ToDo;

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
        } else if (userCommand.startsWith("search")) {
            return CommandType.SEARCH;
        } else {
            return CommandType.NONSENSE;
        }
    }

    public void execute(String userCommand) {
        switch (getCommandType(userCommand)) {
        case ADD:
            handleAddCommand(userCommand);
            break;
        case REMOVE:
            handleRemoveCommand(userCommand);
            break;
        case MARK_AS_DONE:
            handleMarkAsDoneCommand(userCommand);
            break;
        case MARK_AS_UNDONE:
            handleMarkAsUndoneCommand(userCommand);
            break;
        case LIST:
            handleListCommand();
            break;
        case SEARCH:
            handleSearchCommand(userCommand);
            break;
        }
    }

    private void handleAddCommand(String userCommand) {
        if (userCommand.startsWith("event")) {
            // TODO: Ensure that the string contains a "@" and a time range is specified.
            String eventName = userCommand.substring(6).split("@ ")[0].strip();
            String eventTimeRange = userCommand.split("@ ")[1].strip();
            taskList.add(new Event(eventName, false, eventTimeRange));
            System.out.printf("Duke: I have added the event %s.\n", eventName);
        } else if (userCommand.startsWith("deadline")) {
            // TODO: Ensure that the string contains a "@" and a due date is specified.
            String deadlineName = userCommand.substring(9).split("@ ")[0].strip();
            String deadlineDueDate = userCommand.split("@ ")[1].strip();
            taskList.add(new Deadline(deadlineName, false, deadlineDueDate));
            System.out.printf("Duke: I have added the deadline %s.\n", deadlineName);
        } else { // a 'todo'
            String toDoName = userCommand.substring(5).strip();
            taskList.add(new ToDo(toDoName, false));
            System.out.printf("Duke: I have added the to-do %s.\n", toDoName);

        }
    }

    private void handleRemoveCommand(String userCommand) {
        // TODO: Ensure that the index is reasonable.
        int index = Integer.parseInt(userCommand.substring(7)) - 1;
        System.out.printf("Duke: I have removed the task %s.\n", taskList.get(index));
        taskList.remove(index);
    }

    private void handleMarkAsDoneCommand(String userCommand) {
        // TODO: Ensure that the index is reasonable.
        int index = Integer.parseInt(userCommand.substring(5)) - 1;
        taskList.get(index).setComplete(true);
        System.out.println("Task marked as complete.");
        System.out.println(taskList.get(index).toString());
    }

    private void handleMarkAsUndoneCommand(String userCommand) {
        // TODO: Ensure that the index is reasonable.
        int index = Integer.parseInt(userCommand.substring(7)) - 1;
        taskList.get(index).setComplete(false);
        System.out.println("Task marked as not complete.");
        System.out.println(taskList.get(index).toString());
    }

    private void handleListCommand() {
        System.out.println("Duke: Here are your tasks.");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%3d: %s\n", i + 1, taskList.get(i).toString());
        }
    }

    private void handleSearchCommand(String userCommand) {
        String searchTerm = userCommand.substring(7).strip();
        System.out.println("Duke: Here are your tasks that match the search term '" + searchTerm + "'.");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getName().contains(searchTerm)) {
                System.out.printf("%3d: %s\n", i + 1, taskList.get(i).toString());
            }
        }
    }
}
