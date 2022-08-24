import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Duke {

    private String input;
    private Scanner scanner = new Scanner(System.in);
    private Storage storage = new Storage();
    protected boolean isRunning = true;
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

        try {
            this.storage.readFileContents("data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            File file = new File("data/duke.txt");
            System.out.println("New file created.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Wrong date format stored.");
        }

        System.out.println("What can I do for you?");

        while (this.isRunning) {
            System.out.println("-------------------");
            this.input = scanner.nextLine();
            try {
                Command command = new Command(input, this.storage);
                this.isRunning = command.execution();
                this.storage.writeToFile("data/duke.txt");
            } catch (DukeException e) {
                // this.isRunning = true;
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Please enter the date as YYYY-MM-DD.");
            }
        }
    }

}
