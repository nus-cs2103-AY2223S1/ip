import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "I'm Duke! \n What can I do for you?");

        // Read user input
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                printWithLineBreak("Goodbye! Hope to see you again!");
                break;
            } else if (input.equals("list")) {
                printWithLineBreak(printArray(list));
            } else {
                // add input to list
                list.add(input);
                printWithLineBreak("added: " + input);
            }
        }

        // Close scanner
        scanner.close();
    }

    public static void printWithLineBreak(String text) {
        System.out.println("\n" + text + "\n");
    }

    public static String printArray(ArrayList<String> list) {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += "\t" + i + ": " + list.get(i) + "\n";
        }
        return result;
    }
}
