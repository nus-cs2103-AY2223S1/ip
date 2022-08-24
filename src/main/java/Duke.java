import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage();
        this.taskList = new TaskList(this.parser, this.storage, this.ui);
    }
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
            default:
                return;
        }
    }

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
