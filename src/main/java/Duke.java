import java.util.Scanner;

public class Duke {
    private final static String horLine = "--------------------------------------";


    private static void userInput() {
        Scanner userCommand = new Scanner(System.in);

        while (!userCommand.hasNext("bye")) {
            String text = userCommand.nextLine();

            System.out.println(horLine + "\n\t" + text + "\n" + horLine);
        }
        System.out.println(horLine + "\n\tBye. Hope to see you again soon!\n" + horLine);

    }


    public static void main(String[] args) {
        System.out.println(horLine + "\nHello! I'm Duke\nWhat can I do for you?\n" + horLine);
        userInput();
    }
}
