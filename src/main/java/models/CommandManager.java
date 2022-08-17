package models;

import exceptions.DukeException;
import handlers.*;

import java.util.HashMap;

public class CommandManager {
    // List of valid commands for the user
    private static final String LIST_TASKS_COMMAND = "list";
    private static final String MARK_TASK_COMMAND = "mark";
    private static final String UNMARK_TASK_COMMAND = "unmark";
    private static final String TODO_TASK_COMMAND = "todo";
    private static final String DEADLINE_TASK_COMMAND = "deadline";
    private static final String EVENT_TASK_COMMAND = "event";
    private static final String DELETE_TASK_COMMAND = "delete";
    private static final String BYE_COMMAND = "bye";

    private static final String UNKNOWN_COMMAND_ERROR = "I do not understand your command!";

    public final HashMap<String, DukeCommand> commands;

    public CommandManager() {
        this.commands = new HashMap<>(){{
            put(LIST_TASKS_COMMAND, new ListTasksCommand());
            put(MARK_TASK_COMMAND, new MarkTaskCommand());
            put(UNMARK_TASK_COMMAND, new UnmarkTaskCommand());
            put(TODO_TASK_COMMAND, new AddToDoTaskCommand());
            put(DEADLINE_TASK_COMMAND, new AddDeadlineTaskCommand());
            put(EVENT_TASK_COMMAND, new AddEventTaskCommand());
            put(DELETE_TASK_COMMAND, new DeleteTaskCommand());
            put(BYE_COMMAND, new ByeCommand());
        }};
    }

    /**
     * Retrieve the command corresponding to the specified key
     *
     * @param key Command key entered by the user
     * @return Corresponding DukeCommand if the key is valid
     */
    public DukeCommand get(String key) throws DukeException {
        DukeCommand command = this.commands.get(key);
        if (command == null) {
            throw new DukeException(CommandManager.UNKNOWN_COMMAND_ERROR);
        }
        return command;
    }

    public boolean isTerminatingCommand(String key) {
        return key.equals(CommandManager.BYE_COMMAND);
    }
}
