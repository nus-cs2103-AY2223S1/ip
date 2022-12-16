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
import duke.chatbot.commandmanager.commands.InvalidCommand;
import duke.chatbot.commandmanager.commands.ListTaskCommandHandler;
import duke.chatbot.commandmanager.commands.MarkTaskCommandHandler;
import duke.chatbot.commandmanager.commands.TodoTaskCommandHandler;
import duke.chatbot.commandmanager.commands.UnmarkTaskCommandHandler;
import duke.chatbot.commandmanager.commands.UpdateTaskCommandHandler;

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
     */
    public void initialize(ChatBot chatBot) {
        this.commandTable.put("bye", new ByeCommandHandler(chatBot));
        this.commandTable.put("list", new ListTaskCommandHandler(chatBot));
        this.commandTable.put("todo", new TodoTaskCommandHandler(chatBot));
        this.commandTable.put("deadline", new DeadlineTaskCommandHandler(chatBot));
        this.commandTable.put("event", new EventTaskCommandHandler(chatBot));
        this.commandTable.put("mark", new MarkTaskCommandHandler(chatBot));
        this.commandTable.put("unmark", new UnmarkTaskCommandHandler(chatBot));
        this.commandTable.put("delete", new DeleteTaskCommandHandler(chatBot));
        this.commandTable.put("find", new FindTaskCommandHandler(chatBot));
        this.commandTable.put("update", new UpdateTaskCommandHandler(chatBot));
    }

    public Command getCommand(String command) {
        if (!(this.commandTable.containsKey(command))) {
            return new InvalidCommand();
        }
        return this.commandTable.get(command);
    }
}
