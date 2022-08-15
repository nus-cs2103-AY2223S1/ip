import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> inputs = new ArrayList<>();

    /**
     * The main program loop.
     *
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n"
                           + logo
                           + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String inputText = sc.nextLine();
            if (inputText.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inputText.equals("list")){
                Duke.printItems();
            } else {
                Duke.addItem(inputText);
                System.out.println("added: " + inputText);
            }
        }
    }

    /**
     * Add an item to the items list.
     *
     * @param input A String to be added to the list.
     */
    public static void addItem(String input) {
        Duke.inputs.add(input);
    }


    /**
     * Prints all items in the items list.
     */
    public static void printItems() {
        for (int i = 0; i < Duke.inputs.size(); i++) {
            String output = String.format("%s. %s", i + 1, Duke.inputs.get(i));
            System.out.println(output);
        }

    }
}
