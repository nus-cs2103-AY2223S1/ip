import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello from\n" + LOGO);

        while (true) {
            System.out.println("------------------------------");
            System.out.println("What can I do for you?");
            System.out.print(": ");
            String userInput = scanner.nextLine();
            System.out.println("------------------------------");
            System.out.println(userInput + "\n");
        }
    }
}
