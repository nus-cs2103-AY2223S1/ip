import java.util.Scanner;

public class Duke {

    public static void dukeGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello there! I'm Duke and I am you personal task tracking assistant!\nWhat can I do for you today?\n");

    }

    public static void dukeExit() {
       System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String userCommand = scanner.next();

        while (!"bye".equals(userCommand)) {
            System.out.println(userCommand + "\n");
            userCommand = scanner.next();
        }
        dukeExit();
    }

    public static void main(String[] args) {
        dukeGreet();
        echo();
    }
}
