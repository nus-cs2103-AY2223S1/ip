import java.util.Scanner;
import java.nio.file.Path;

public class Duke {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        DukeHandler handler = new DukeHandler("data/Tasks.txt");

        while (userInput.hasNextLine()) {
            String input = userInput.nextLine();
            handler.handleResponse(input);
        }
    }
}
