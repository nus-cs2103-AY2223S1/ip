import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        String nextInstruction;
        String introduction = "Hello! I'm Jarvis \n"
                            + "What can I do for you?";
        String farewell = "Bye. Hope to see you again soon!";

        System.out.println(introduction);

        while (true) {
            nextInstruction = inputScanner.nextLine();
            if (nextInstruction.equals("bye")) {
                break;
            }
            System.out.println(nextInstruction);
        }

        System.out.println(farewell);


    }
}
