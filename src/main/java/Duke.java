import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        hello();

        Scanner sc = new Scanner(System.in);
        DukeControl dc = new DukeControl("../../../data/aRC.txt");

        System.out.print("\n");
        String input = sc.nextLine();

        // Keeps reading user input until the user types "bye"
        while(!input.equals("bye")) {
            try {
                dc.run(input);
            } catch (DukeException e) {
                System.out.println(e);
            }
            System.out.print("\n");
            input = sc.nextLine();
        }

        bye();
        sc.close();
    }

    /**
     * Prints hello message to the screen
     */
    public static void hello() {
        System.out.println("Hello! I'm aRC! (°▽°)/\nWhat can I do for you?");
    }

    /**
     * Print goodbye message to the screen
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon! ʘ ͜ʖ ʘ");
    }
}
