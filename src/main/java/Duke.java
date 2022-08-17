import java.util.*;
public class Duke {
    public static void main(String[] args) {

        String dukeGreeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(dukeGreeting);

        Scanner sc = new Scanner(System.in);
        String userResponse = null;
        while (userResponse == null || !userResponse.equals("bye")) {
            userResponse = sc.nextLine();
            String dukeEndOutput = "    " + "Bye. Hope to see you again soon!";
            String dukeContinuationOutput = "    " + userResponse;
            String dukeOutput = userResponse.equals("bye") ? dukeEndOutput : dukeContinuationOutput;
            System.out.println(dukeOutput);
        }

    }
}
