import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {

    private String input;
    private Scanner scanner = new Scanner(System.in);
    private Storage storage = new Storage();
    protected boolean keepRunning = true;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.console();
    }

    public void console() {
        System.out.println("Just a moment...\nHello! I am Duke.");
        System.out.println("Just ignore the symbol above. What can I do for you?");

        while(this.keepRunning) {
            System.out.println("-------------------");
            this.input = scanner.nextLine();
            Command command = new Command(input, this.storage);
            try {
                this.keepRunning = command.execution();
            } catch (DukeException e) {
                this.keepRunning = true;
                System.out.println(e.getMessage());
            }
        }
    }

}
