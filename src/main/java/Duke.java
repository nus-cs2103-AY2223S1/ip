import java.util.Scanner;

/**
 * This class represents the chatbot interface that allows
 * user to interact with the todo list.
 */
public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String input = "";
        TaskList list = new TaskList();

        String welcomeMsg = "Hello I'm Duke!\nWhat can I do for you?";
        Duke.printText(welcomeMsg);

        while (input != "bye") {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                Duke.printText(list.toString());
            } else if (input.matches("[Mm]ark \\d+")) {
                int itemIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                try {
                    Duke.printText(list.markItem(itemIndex));
                } catch (ArrayIndexOutOfBoundsException e) {
                    Duke.printText("Uhoh! The item doesn't exist");
                }
            } else if (input.matches("[Uu]nmark \\d+")) {
                int itemIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                try {
                    Duke.printText(list.unmarkItem(itemIndex));
                } catch (ArrayIndexOutOfBoundsException e) {
                    Duke.printText("Uhoh! The item doesn't exist");
                }
            } else if (input.matches("delete \\d+")) {
                int itemIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                try {
                    Duke.printText(list.deleteItem(itemIndex));
                } catch (ArrayIndexOutOfBoundsException e) {
                    Duke.printText("Uhoh! The item doesn't exist");
                }
            } else {
                try {
                    Duke.printText(list.insertItem(input));
                } catch (ArrayIndexOutOfBoundsException e) {
                    Duke.printText("Uhoh! The list is full.");
                }
            }
        }

        sc.close();
        String exitMsgs = "Good bye!\nHope to see you soon!";
        Duke.printText(exitMsgs);
    }

    /**
     * Print the output in customised format.
     * @param text The text to print
     */ 
    public static void printText(String text) {
        System.out.println("\u2015\u2015\u2015");
        System.out.println("" + text);
        System.out.println("\u2015\u2015\u2015");
    }

}