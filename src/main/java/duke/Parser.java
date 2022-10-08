package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class that handles user input.
 */
public class Parser {
    private static final String GREET_COMMAND = "hello";
    private static final String EXIT_COMMAND = "bye";
    private static final String DISPLAY_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String CREATE_TODO_COMMAND = "todo";
    private static final String CREATE_DEADLINE_COMMAND = "deadline";
    private static final String CREATE_EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String SORT_COMMAND = "sort";
    private static final String HELP_COMMAND = "help";

    /**
     * Parses an input string and calls the relevant method (if any).
     *
     * @param input The input string to be parsed.
     */
    public static void parseInput(String input) throws DukeException {
        String[] words = input.toLowerCase().split(" ", 2);
        String command = words[0];
        String args = "";
        if (words.length > 1) {
            args = words[1];
        }

        switch (command) {
        case GREET_COMMAND:
            Ui.displayGreeting();
            break;
        case EXIT_COMMAND:
            Duke.exit();
            break;
        case DISPLAY_COMMAND:
            Ui.displayTasks();
            break;
        case MARK_COMMAND:
        case UNMARK_COMMAND:
            boolean isDone = command.equals("mark");
            Ui.displayMarkTaskMessage(TaskList.markTask(isDone, args), isDone);
            break;
        case CREATE_TODO_COMMAND:
            Ui.displayAddTaskMessage(TaskList.addToDo(args));
            break;
        case CREATE_DEADLINE_COMMAND:
            Ui.displayAddTaskMessage(TaskList.addDeadline(args));
            break;
        case CREATE_EVENT_COMMAND:
            Ui.displayAddTaskMessage(TaskList.addEvent(args));
            break;
        case DELETE_COMMAND:
            Ui.displayDeleteTaskMessage(TaskList.deleteTask(args));
            break;
        case FIND_COMMAND:
            Ui.displaySearchTasksMessage(TaskList.searchTasks(args), args);
            break;
        case SORT_COMMAND:
            Ui.displaySortTasksByNameMessage(TaskList.sortTaskListByName(args));
            break;
        case HELP_COMMAND:
            Ui.displayCommands();
            break;
        default:
            throw new DukeException("Command not recognised");
        }
    }

    /**
     * Gets a list of commands recognized by the Parser.
     *
     * @return An arraylist of strings that are recognized commands.
     */
    public static List<String> getCommandList() {
        return new ArrayList<>(Arrays.asList(
                GREET_COMMAND,
                EXIT_COMMAND,
                DISPLAY_COMMAND,
                MARK_COMMAND,
                UNMARK_COMMAND,
                CREATE_TODO_COMMAND,
                CREATE_DEADLINE_COMMAND,
                CREATE_EVENT_COMMAND,
                DELETE_COMMAND,
                FIND_COMMAND,
                SORT_COMMAND,
                HELP_COMMAND));
    }
}
