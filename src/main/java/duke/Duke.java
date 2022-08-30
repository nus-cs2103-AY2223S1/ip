package duke;

import duke.ui.Ui;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandException;
import duke.command.CommandFactory;
import duke.command.handler.CommandHandler;
import duke.command.response.CommandResponse;
import duke.data.TaskList;
import duke.data.storage.Storage;
import duke.data.storage.StorageException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Duke extends Application {

    private Ui ui;
    private Storage<TaskList> taskListStorage;

    private TaskList taskList;

    @Override
    public void init() {
        String chatBotName = getParameters().getUnnamed().get(0);
        String cachePath = getParameters().getUnnamed().get(1);

        this.ui = new Ui(chatBotName);
        this.taskListStorage = new Storage<>(Paths.get(cachePath));
        TaskList tempTaskList;
        try {
            tempTaskList = this.taskListStorage.load(new TaskList());
        } catch (StorageException storageException) {
            this.ui.raiseError(storageException.getMessage());
            tempTaskList = new TaskList();
        }
        this.taskList = tempTaskList;
    }

    /**
     * @deprecated
     */
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

                ui.replyUser(commandResponse.getResponseStr());
                terminate = commandResponse.isTriggerTerminate();

                if (commandResponse.isTriggerSave()) {
                    taskListStorage.save(taskList);
                }
            } catch (CommandException | StorageException error) {
                ui.raiseError(error.getMessage());
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        ui.createUi(primaryStage);
        primaryStage.show();
    }
}
