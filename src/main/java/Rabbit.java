import java.util.Scanner;
import java.util.ArrayList;

/**
 * Rabbit is a short-tempered, annoyed bot that puts in her 30% efforts
 *  to help you solve some simple problems as her part-time jpb.
 *
 * @author Jiang Zhimeng
 */
public class Rabbit {
    private static String greet = "-----------------------------------------------------------------------------\n"
            + "-----------------------------------------------------------------------------\n"
            + "Yo...nice to meet you. This is rabbit...Ughhhhh I hate this job.\n"
            + "You can input stuff that you want me to write on this grandma-aged notebook.\n"
            + "-----------------------------------------------------------------------------\n"
            + "Type the name of a task to add it to the list such as 'Do homework'.\n"
            + "Type 'list' then I'll show all the existing lines to you.\n"
            + "Type 'mark + the index of a existing task' to marks it as done. Like 'mark 1'.\n"
            + "Type 'unmark + the index of a existing task' to unmark a task.\n"
            + "-----------------------------------------------------------------------------\n"
            + "Actually why not just do me a favour? Type 'bye' in the console and free both of us.";

    private static String bye = "Thanks a lot. I'm gonna have some carrot tea later. See you...";
    // initialise the list that stores tasks.
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * a function that continues to take in a line from the user
     * and makes Rabbit output the same line unless the line
     * is 'bye', in which case the program terminates as Rabbit goes
     * back to enjoy her carrot tea.
     */
    private static void repeat() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // user's input
            String input = sc.nextLine();

            if (input.equals("bye")) {
                // terminates the program
                break;
            }

            // Rabbit repeats what the user types in
            System.out.println(input);
        }

        // when the user types 'bye' the program is terminated
        System.out.println(bye);
    }

    /**
     *  Rabbit adds the input lines the user types into
     *  a list with a size no more than 100.
     *
     * @param input the content of the task the user inputs
     */
    private static void addToList(String input) {
        if (list.size() < 100) {
            list.add(new Task(input));
            System.out.println("Okay...noted.");
        } else {
            // warns the user when there are already 100 lines in
            // the list when the user is trying to input a new line.
            System.out.println("There are too many lines! Don't exceed 100 lines please.\n"
                    + "Type 'list' to list out all the existing lines.");
        }
    }

    /**
     * Rabbit prints the list of current tasks
     * when the user inputs "list".
     */
    private static void list() {
        if (list.size() == 0) {
            System.out.println("There is no task in the list.");
        }
        for (int i  = 0; i < list.size(); i++ ) {
            int index = i + 1;
            System.out.println(index + ". " + list.get(i));
        }
    }

    /**
     * marks the task at index i as done
     *
     * @param i the index of the task marked done.
     */
    private static void mark(int i) {
        if (i > list.size() || i <= 0) {
            System.out.println("Hey, be careful.\n"
                    + "The index must be between 0 and the size of the list, alright?");
            return;
        }

        if (list.get(i - 1).isDone()) {
            System.out.println("What do you mean? This task is already marked as done.");
            return;
        }

        System.out.println("Okay...task: " + list.get(i - 1).getContent() + " is marked as done.");
        list.get(i - 1).markDone();
    }

    /**
     * unmarks the task at index i as not done
     *
     * @param i the index of the task unmarked.
     */
    private static void unmark(int i) {
        if (i > list.size() || i <= 0) {
            System.out.println("Hey, be careful.\n"
                    + "The index must be between 0 and the size of the list, alright?");
            return;
        }

        if (!list.get(i - 1).isDone()) {
            System.out.println("What do you mean? This task is not done in the first place.");
            return;
        }

        System.out.println("Okay...task: " + list.get(i - 1).getContent() + " is unmarked.");
        list.get(i - 1).unmark();
    }

    public static void main(String[] args) {
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.length() > 5 && input.substring(0, 5).equals("mark ")) {
                // the input string is parsed to be the integer
                // that the string represents, 0 if it is not an integer
                try {
                    mark(Integer.parseInt(input.substring(5)));
                    continue;
                } catch (NumberFormatException ex) {
                    System.out.println("Is '" + input + "' a task?");
                    if (!sc.nextLine().equals("yes")) {
                        System.out.println("Type 'mark + the index of the task' "
                                + "if that's what you want instead.");
                        continue;
                    }
                }
            }

            if (input.length() > 6 && input.substring(0, 7).equals("unmark ")) {
                // the input string is parsed to be the integer
                // that the string represents, 0 if it is not an integer
                try {
                    unmark(Integer.parseInt(input.substring(7)));
                    continue;
                } catch (NumberFormatException ex) {
                    System.out.println("Is '" + input + "' a task?");
                    if (!sc.nextLine().equals("yes")) {
                        System.out.println("Type 'unmark + the index of the task' "
                                + "if that's what you want instead.");
                        continue;
                    }
                }
            }

            // program terminates when the user inputs 'bye'.
            if (input.equals("bye")) {
                System.out.println(bye);
                break;
            }
                // Rabbit lists out all the lines in the list.
            if (input.equals("list")) {
                // prints out the all the current tasks
                list();
                // skips the part where input line is stored into the list.
                continue;
            }

            addToList(input);

        }
    }
}
