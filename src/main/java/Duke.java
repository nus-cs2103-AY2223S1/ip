import java.util.*;
public class Duke {
    public static void main(String[] args) {

        String dukeGreeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(dukeGreeting);

        Scanner sc = new Scanner(System.in);
        String userResponse = null;
        ArrayList<String> userInputs = new ArrayList<>();
        while (userResponse == null || !userResponse.equals("bye")) {
            userResponse = sc.nextLine();
            String dukeOutput = "";

            switch(userResponse) {
                case "bye":
                    dukeOutput = "    " + "Bye. Hope to see you again soon!";
                    break;
                case "list":
                    for (int i = 0; i < userInputs.size(); i++) {
                        dukeOutput += "    " + (i + 1) + ". " + userInputs.get(i) + "\n";
                    }
                    break;
                default:
                    userInputs.add(userResponse);
                    dukeOutput = "    " + "added: " + userResponse;
            }
            System.out.println(dukeOutput);
        }

    }
}
