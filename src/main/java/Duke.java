import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printGreeting();
        String exitWord = "bye";
        String inputText = "";

        while (!inputText.equals(exitWord)) {
            Scanner input = new Scanner(System.in);
            inputText = input.nextLine();

            System.out.println("\n  _______________");
            System.out.println("  You entered " + inputText);
            System.out.println("  _______________\n");
        }
        printBye();
    }

    private static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printBye() {
        System.out.println("Goodbye. Hope to see you again soon 😈 \n");
    }
}
