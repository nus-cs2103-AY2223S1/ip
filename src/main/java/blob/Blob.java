package blob;

import blob.commands.Command;
import blob.commands.CommandResult;
import blob.commands.TaskCommand;
import blob.exception.BlobException;
import blob.exception.ErrorLoadingTaskException;
import blob.storage.Storage;
import blob.tasks.TaskList;
import blob.parser.Parser;
import blob.ui.TextUi;
import java.util.Scanner;

public class Blob {
    private final Storage storage;
    private final TaskList taskList;
    private final TextUi ui;
    private final Parser parser;

    Blob(String filePath) {
        TaskList taskList1;
        this.ui = new TextUi();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        try {
            taskList1 = storage.loadTaskList();
        } catch (ErrorLoadingTaskException exception) {
            ui.speakToUser(exception.getBlobMessages());
            taskList1 = new TaskList();
        }
        this.taskList = taskList1;
    }

    /**
     * Start the interaction with an instance of blob.Blob
     */
    public void start() {
        ui.greetUser();
        Scanner sc = new Scanner(System.in);
        while (true) {
            ui.promptUserInput();
            try {
                Command command = parser.parseUserInput(sc.nextLine());
                CommandResult result;

                if (command.isByeCommand()) {
                    end();
                }

                if (command.isTaskCommand()) {
                    TaskCommand taskCommand = (TaskCommand) command;
                    taskCommand.setTaskList(taskList);
                    result = taskCommand.execute();
                    storage.saveTaskList(taskList);
                } else {
                    result = command.execute();
                }

                ui.speakToUser(result.getResultMessages());

            } catch (BlobException exception) {
                ui.speakToUser(exception.getBlobMessages());
            }
        }
    }

    public void end() {
        storage.saveTaskList(taskList);
        ui.sayGoodbyeToUser();
        System.exit(0);
    }

    public static void main(String[] args) {
        Blob blob = new Blob("data/tasks.txt");
        blob.start();
    }
}
