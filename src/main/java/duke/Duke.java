package duke;

import java.util.Scanner;

/**
 * Main object to be created to initialise the chatbot program.
 * 
 * @author Siau Wee
 */
public class Duke {

    private Ui ui;

    private Parser parser;

    private Storage storage;

    private TaskList taskList;

    /**
     * Constructor to initialise a new Duke object with a new instance of
     * Parser, Storage and Ui, and a new taskList with the required instances
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage();
        this.taskList = new TaskList(this.parser, this.storage, this.ui);
    }

    /**
     * Calls the relevant method based on the Command passed as argument.
     * 
     * @param command The Command to be executed.
     * @throws DukeException If there is an invalid command keyword or arguments.
     */
    public void executeCommand(Command command) throws DukeException {
        switch (command.getKeyword()) {
        case BYE:
            System.out.println("Come again soon!");
            System.exit(0);
        case LIST:
            this.taskList.listTask();
            break;
        case MARK:
            this.taskList.markTask(Integer.parseInt(command.getCommandArgs()[1]));
            break;
        case UNMARK:
            this.taskList.unmarkTask(Integer.parseInt(command.getCommandArgs()[1]));
            break;
        case DELETE:
            this.taskList.deleteTask(Integer.parseInt(command.getCommandArgs()[1]));
            break;
        case TODO:
            Todo todo = new Todo(command.getCommandArgs()[1]);
            this.taskList.addTask(todo);
            break;
        case DEADLINE:
            Deadline deadline = new Deadline(command.getCommandArgs()[0],
                    this.parser.parseDateTime(command.getCommandArgs()[1]));
            this.taskList.addTask(deadline);
            break;
        case EVENT:
            Event event = new Event(command.getCommandArgs()[0],
                    this.parser.parseDateTime(command.getCommandArgs()[1]));
            this.taskList.addTask(event);
            break;
        case FIND:
            String chars = command.getCommandArgs()[1];
            this.taskList.findTask(chars);
        default:
            return;
        }
    }

    /**
     * Main driver method of the program. To be called at the start
     * of the life cycle of the program.
     */
    public void run() {
        this.ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String[] commandArgs = command.split(" ", 2);
            try {
                Command commandToExecute = this.parser.parseCommand(commandArgs);
                this.executeCommand(commandToExecute);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
