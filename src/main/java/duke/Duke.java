package duke;

import duke.command.Command;
import duke.command.CommandException;
import duke.command.CommandFactory;
import duke.command.handler.CommandHandler;
import duke.command.response.CommandResponse;

import duke.data.TaskList;
import duke.data.storage.Storage;
import duke.data.storage.StorageException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class Duke {

    private static final String DEFAULT_CHAT_BOT_NAME = "Duke";
    private static final Path DEFAULT_CACHE_PATH = Paths.get(".duke.cache");

    private final Ui ui;
    private final Storage<TaskList> taskListStorage;

    private final TaskList taskList;

    /**
     * Constructor for Duke class
     *
     * @param chatBotName name of the chat-bot
     * @param cachePath path to cache file
     */
    public Duke(String chatBotName, Path cachePath) {
        this.ui = new Ui(chatBotName);
        this.taskListStorage = new Storage<>(cachePath);

        TaskList tempTaskList;
        try {
            tempTaskList = this.taskListStorage.load(new TaskList());
        } catch (StorageException storageException) {
            this.ui.raiseError(storageException.getMessage());
            tempTaskList = new TaskList();
        }
        this.taskList = tempTaskList;
    }

    public static void main(String[] args) {
        new Duke(DEFAULT_CHAT_BOT_NAME, DEFAULT_CACHE_PATH).run();
    }

    public void run() {
        ui.welcomeUser();

        Scanner input = new Scanner(System.in);
        CommandFactory commandFactory = new CommandFactory();

        boolean terminate = false;
        while (!terminate && input.hasNextLine()) {
            String commandStr = input.nextLine();
            try {
                Command command = commandFactory.parseCommand(commandStr);
                CommandHandler commandHandler = commandFactory.getCommandHandler(command,
                    commandStr);
                CommandResponse commandResponse = commandHandler.run(taskList);

                ui.replyUser(commandResponse.responseStr);
                terminate = commandResponse.triggerTerminate;

                if (commandResponse.triggerSave) {
                    taskListStorage.save(taskList);
                }
            } catch (CommandException | StorageException error) {
                ui.raiseError(error.getMessage());
            }
        }
    }
}
