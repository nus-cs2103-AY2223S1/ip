import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner scanner;
    private final TaskList taskList;

    public Duke() {
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        System.out.println(LOGO);
        ui.printMessages(new String[]{"Hello! I'm Duke", "What can I do for you?"});

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = this.scanner.nextLine();
                Command c = Parser.parse(input);
                c.execute(taskList);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }


}
