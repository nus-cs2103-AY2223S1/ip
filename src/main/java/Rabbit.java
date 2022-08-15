import java.util.Scanner;

/**
 * Rabbit is a short-tempered, annoyed bot that puts in her 30% efforts
 *  to help you solve some simple problems as her part-time jpb.
 *
 * @author Jiang Zhimeng
 */
public class Rabbit {

    /**
     * a function that continues to take in a line from the user
     * and makes Rabbit output the same line unless the line
     * is 'bye', in which case the program terminates as Rabbit goes
     * back to enjoy her carrot tea.
     */
    private static void Repeat() {
        Scanner sc = new Scanner(System.in);

        // user's input
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            // Rabbit repeats what the user types in
            System.out.println(input);
            input = sc.nextLine();
        }

        // when the user types 'bye' the program is terminated
        System.out.println("Thanks a lot. I'm gonna have some carrot tea later. See you...");
    }
    public static void main(String[] args) {
        System.out.println("Yo...nice to meet you. This is rabbit...Ughhhhh I hate this job.\n"
                + "Actually why not just do me a favour? Type 'bye' in the console and free both of us.\n");

        Repeat();
    }
}
