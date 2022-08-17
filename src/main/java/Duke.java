import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        addToList();

        printInStyle("Goodbye! Hope to see you again soon!");
    }

    public static void printInStyle(String stringToPrint) {
        System.out.println("\t" + "-".repeat(25) + "\n\t\t" + stringToPrint);
        System.out.println("\t" + "-".repeat(25));
    }

    public static String askForInput(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);

        String input = askForInput("Type something:");
        while (!input.toLowerCase(Locale.ROOT).equals("bye")) {
            printInStyle(input);
            input = askForInput("Type something:");
        }
    }

    private static void addToList() {
        List<String> toDo = new ArrayList<String>(100);

        String input = askForInput("Add something:");

        while (!input.toLowerCase(Locale.ROOT).equals("bye")) {
            if (input.equals("list")) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < toDo.size(); i++) {
                    builder.append(String.format("%d. %s", i + 1, toDo.get(i)));
                    if (i < toDo.size() - 1) {
                        builder.append("\n\t\t");
                    }
                }
                printInStyle(builder.toString());
            } else {
                toDo.add(input);
                printInStyle(String.format("added: %s", input));
            }
            input = askForInput("Add something:");
        }
    }
}
