package duke.chatbot.commandmanager;

import java.util.HashMap;
import java.util.Map;

import duke.chatbot.ChatBot;
import duke.chatbot.commandmanager.commands.ByeCommandHandler;
import duke.chatbot.commandmanager.commands.Command;
import duke.chatbot.commandmanager.commands.DeadlineTaskCommandHandler;
import duke.chatbot.commandmanager.commands.DeleteTaskCommandHandler;
import duke.chatbot.commandmanager.commands.EventTaskCommandHandler;
import duke.chatbot.commandmanager.commands.FindTaskCommandHandler;
import duke.chatbot.commandmanager.commands.ListTaskCommandHandler;
import duke.chatbot.commandmanager.commands.MarkTaskCommandHandler;
import duke.chatbot.commandmanager.commands.TodoTaskCommandHandler;
import duke.chatbot.commandmanager.commands.UnmarkTaskCommandHandler;
import duke.chatbot.commandmanager.commands.UpdateTaskCommandHandler;
import duke.chatbot.commandmanager.commands.exceptions.InvalidCommandException;
import duke.chatbot.personality.Personality;
import duke.chatbot.taskmanager.TaskManager;

/**
 * CommandManager class manages a list of commands that can be executed.
 */
public class CommandManager {
    private final Map<String, Command> commandTable;
    public CommandManager() {
        this.commandTable = new HashMap<>();
    }

    /**
     * Initializes the command table with all commands that can be executed.
     *
     * @param chatBot a reference to the chatbot
     * @param taskManager a reference to the task manager
     */
    public void initialize(ChatBot chatBot, Personality personality, TaskManager taskManager) {
        this.commandTable.put("bye", new ByeCommandHandler(personality, chatBot));
        this.commandTable.put("list", new ListTaskCommandHandler(personality, taskManager));
        this.commandTable.put("todo", new TodoTaskCommandHandler(personality, taskManager));
        this.commandTable.put("deadline", new DeadlineTaskCommandHandler(personality, taskManager));
        this.commandTable.put("event", new EventTaskCommandHandler(personality, taskManager));
        this.commandTable.put("mark", new MarkTaskCommandHandler(personality, taskManager));
        this.commandTable.put("unmark", new UnmarkTaskCommandHandler(personality, taskManager));
        this.commandTable.put("delete", new DeleteTaskCommandHandler(personality, taskManager));
        this.commandTable.put("find", new FindTaskCommandHandler(personality, taskManager));
        this.commandTable.put("update", new UpdateTaskCommandHandler(personality, taskManager));
    }

    public Command getCommand(Personality personality, String command) throws InvalidCommandException {
        if (!(this.commandTable.containsKey(command))) {
            throw new InvalidCommandException(personality);
        }
        return this.commandTable.get(command);
    }
}
