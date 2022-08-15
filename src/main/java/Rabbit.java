import java.util.Scanner;
import java.util.ArrayList;

/**
 * Rabbit is a short-tempered, annoyed bot that puts in her 30% efforts
 *  to help you solve some simple problems as her part-time jpb.
 *
 * @author Jiang Zhimeng
 */
public class Rabbit {
    private static String greet = "Yo...nice to meet you. This is rabbit...Ughhhhh I hate this job.\n"
            + "You can input stuff that you want me to write on this grandma-aged notebook.\n"
            + "Type 'list' then I'll show all the existing lines to you.\n"
            + "Actually why not just do me a favour? Type 'bye' in the console and free both of us.";

    private static String bye = "Thanks a lot. I'm gonna have some carrot tea later. See you...";

    /**
     * a function that continues to take in a line from the user
     * and makes Rabbit output the same line unless the line
     * is 'bye', in which case the program terminates as Rabbit goes
     * back to enjoy her carrot tea.
     */
    private static void Repeat() {
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
     *  a list with a size no more than 100, and returns
     *  the list as an output when the user type 'list'.
     *  Program terminates when the user types 'bye'.
     */
    private static void AddToList() {
        Scanner sc = new Scanner(System.in);
        // a list that keeps the user's input
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            // program terminates when the user inputs 'bye'.
            if (input.equals("bye")) {
                System.out.println(bye);
                break;
            }

            // Rabbit lists out all the lines in the list.
            if (input.equals("list")) {
                for (int i  = 0; i < list.size(); i++ ) {
                    int index = i +1;
                    System.out.println(index + ". " + list.get(i));
                }
                // skips the part where input line is stored into the list.
                continue;
            }

            if (list.size() < 100) {
                list.add(input);
                System.out.println("Okay...noted.");
            } else {
                // warns the user when there are already 100 lines in
                // the list when the user is trying to input a new line.
                System.out.println("There are too many lines! Don't exceed 100 lines please.\n"
                        + "Type 'list' to list out all the existing lines.");
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(greet);

        AddToList();
    }
}
