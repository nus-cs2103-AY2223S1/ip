import java.util.Scanner;
public class Duke {

    String input;
    Storage storage = new Storage();
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.welcomeMessage();
    }

    public void welcomeMessage() {
        System.out.println("Just a moment...\nHello! I am Cortana.");
        System.out.println("Just ignore the symbol above. What can I do for you?");

        while(true) {
            System.out.println("-------------------");
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Please don't leave me >_<\nSee you soon");
                break;
            } else if (input.equals("list")) {
                storage.iterate();
            } else {
                storage.add(input);
            }

        }
    }
}
