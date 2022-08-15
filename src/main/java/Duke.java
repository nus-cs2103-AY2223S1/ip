import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> inputs = new ArrayList<>();

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

        boolean flag = true; // flag indicating if the loop should continue
        while (flag) {
            // Read input from console
            String inputText = sc.nextLine();

            // Get first word
            int firstSpaceIdx = inputText.indexOf(" ");
            String command = firstSpaceIdx == -1
                             ? inputText
                             : inputText.substring(0, firstSpaceIdx);

            // Determine the action to perform based on the first, "command" word
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    flag = false;
                    break;
                case "list":
                    Duke.printItems();
                    break;
                case "mark":
                    int markindex = Integer.parseInt(inputText.substring(firstSpaceIdx + 1));
                    Duke.mark(markindex);
                    break;
                case "unmark":
                    int unmarkindex = Integer.parseInt(inputText.substring(firstSpaceIdx + 1));
                    Duke.unmark(unmarkindex);
                    break;
                default:
                    Duke.addItem(inputText);
            }
        }
    }

    /**
     * Add an item to the items list, then prints it out.
     *
     * @param input A String to be added to the list.
     */
    public static void addItem(String input) {
        // Instantiate task object
        Task newTask = new Task(input);

        // Add to list
        Duke.inputs.add(newTask);

        // Print message
        System.out.println("Added: " + input);
    }


    /**
     * Prints all items in the items list.
     */
    public static void printItems() {
        System.out.println("Here are the tasks in your list:");

        // Print every task in the list
        for (int i = 0; i < Duke.inputs.size(); i++) {
            String output = String.format("%s. %s", i + 1, Duke.inputs.get(i));
            System.out.println(output);
        }

    }

    /**
     * Marks a task at a certain index as done.
     *
     * @param index the index of the task to be marked as done
     */
    public static void mark(int index) {
        // Get the task to be marked
        Task selectedTask = Duke.inputs.get(index - 1);

        // Mark it as done
        selectedTask.mark();

        // Print message
        System.out.println("Nice! I've marked this task as done:\n"+
                           selectedTask);
    }

    /**
     * Marks a task at a certain index as done.
     *
     * @param index the index of the task to be marked as done
     */
    public static void unmark(int index) {
        // Get the task to be marked
        Task selectedTask = Duke.inputs.get(index - 1);

        // Mark it as not done
        selectedTask.unmark();

        // Print message
        System.out.println("OK, I've marked this task as not done yet:\n"+
                           selectedTask);
    }
}
