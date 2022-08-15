import java.util.Scanner;

public class Duke {
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke. How may I assist you?");
    }

    public void sayBye() {
        String message = "Bye! Hope to see you soon!";
        System.out.println(message);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        duke.greetUser();

        while (true) {
            System.out.print(">>> ");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                duke.sayBye();
                break;
            }
            System.out.println(input);
        }
    }
}