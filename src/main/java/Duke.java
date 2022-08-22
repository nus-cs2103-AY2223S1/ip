import java.util.Scanner;

public class Duke {
    private static String TASKS_STORAGE_PATH = "./data/duke.txt";
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        this.taskList = new TaskList(TASKS_STORAGE_PATH);
        this.ui = new Ui();
    }

    public void run() {
        boolean hasEnded = false;
        Scanner scanner = new Scanner(System.in);
        this.ui.welcomeMessage();

        while (!hasEnded) {
            try {
                String input = scanner.nextLine();
                Command command = new Parser().parseCommand(input, taskList);
                String message = command.action();
                this.ui.printMessage(message);
                if (command instanceof ExitCommand) {
                    scanner.close();
                    hasEnded = true;
                    break;
                }
                taskList.saveTasks();
            } catch (DukeException e){
                this.ui.invalidMessage(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}