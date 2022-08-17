import java.util.Scanner;

public class Duke {
    String input;
    static final String LINE_BREAK = "-----------------------------";

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Hello! I'm Ee Suan!\nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        duke.input = scan.nextLine();
        while (!duke.input.equals("bye")) {
            System.out.println(LINE_BREAK + "\n\t" + duke.input + "\n" + LINE_BREAK);
            duke.input = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
