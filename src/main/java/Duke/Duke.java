package Duke;

import java.io.IOException;

/**
 * Duke.Duke is a basic chat-bot stores tasks into a list.
 *
 * @author Chi Song Yi Amadeus
 * @version A-MoreOOP
 * @since 17-08-2022
 */
public class Duke {

    /**
     * storage object deals with read/write interactions with file
     */
    private Storage storage;

    /**
     * tasks manages a list of Task objects
     */
    private TaskList tasks;

    /**
     * ui handles user inputs and outputs
     */
    private Ui ui;

    /**
     * parser interpretes user commands
     */
    private Parser parser;

    /**
     * possible user commands.
     */
    enum Commands {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        CREATETASK
    }

    /**
     * Constructor for Duke.
     *
     * @param filePath filepath to data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showErrorReadingMessage();
        }
    }

    /**
     * Main method initializes welcome message, and then calls taskList method.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * TaskList method creates an input loop, creating Duke.Task objects and adding it to the array.
     *
     * @throws NumberFormatException if User inputs a non integer after mark/unmark
     */
    public void run() throws NumberFormatException {
        while (ui.hasInput()) {
            String input = ui.getUserInput().trim();
            Commands command = parser.parseCommand(input);
            if (command == Commands.BYE) {
                break;
            } else {
                executeCommand(command, input);
            }
        }
        ui.showGoodbyeMessage();
    }


    /**
     * Processes and runs user commands.
     *
     * @param command user command
     * @param input   input string
     */
    private void executeCommand(Commands command, String input) {
        if (command == Commands.LIST) {
            System.out.println("    ____________________________________________________________\n");
            for (int i = 1; i <= tasks.getSize(); i++) {
                String index = String.format("%d.", i);
                ui.showEntry(index + tasks.getItem(i - 1).toString());
            }
            System.out.println("    ____________________________________________________________\n");

        } else if (command == Commands.MARK) {
            String number = input.split(" ", 2)[1];
            try {
                int num = Integer.parseInt(number);
                tasks.markTasks("mark", num);
                storage.rewriteFile(tasks.getTaskArray());
            } catch (NumberFormatException e) {
                ui.showInvalidTaskMessage();
            } catch (IOException e) {
                ui.showErrorWritingMessage();
            }
        } else if (command == Commands.UNMARK) {
            try {
                String number = input.split(" ", 2)[1];
                int num = Integer.parseInt(number);
                tasks.markTasks("unmark", num);
                storage.rewriteFile(tasks.getTaskArray());
            } catch (NumberFormatException e) {
                ui.showInvalidTaskMessage();
            } catch (IOException e) {
                ui.showErrorWritingMessage();
            }
        } else if (command == Commands.DELETE) {
            String number = input.split(" ", 2)[1];
            try {
                int num = Integer.parseInt(number);
                Task toDelete = tasks.getTaskArray().get(num - 1);
                tasks.delete(toDelete);
                ui.showDeleteMessage(toDelete, tasks.getSize());
                storage.writeToFile(toDelete);
            } catch (NumberFormatException e) {
                ui.showInvalidTaskMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.showInvalidIndexMessage();
            } catch (IOException e) {
                ui.showErrorWritingMessage();
            }
        } else if (command == Commands.CREATETASK) {
            try {
                Task newTask = tasks.createTask(input, false);
                if (newTask != null) {
                    storage.writeToFile(newTask);
                }
            } catch (DukeException.EmptyTaskException | DukeException.UnkownCommandException | DukeException.InvalidParameterException error) {
                System.out.println(error.getMessage());
            } catch (IOException e) {
                ui.showErrorWritingMessage();
            }
        }
    }
}
