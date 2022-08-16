import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";

    private static void printTaskList(ArrayList<String> list) {
        System.out.println(Duke.line);
        for (int i = 0; i < list.size(); i ++) {
            System.out.println((i+ 1) + ". " + list.get(i));
        }
        System.out.println(Duke.line);
    }

    private static void printResponse(String response) {
        System.out.println(Duke.line);
        System.out.println(response);
        System.out.println(Duke.line);
    }
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);

        String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";
        String exitMessage = "Bye. Hope to see you again soon!";
        String exitKeyword = "bye";
        String listKeyword = "list";
        String userInput;

        ArrayList<String> taskList = new ArrayList<String>();

        printResponse(greetingMessage);

        while(true) {
            userInput = scanner.nextLine();
            if (userInput.equals(exitKeyword)) {
                printResponse(exitMessage);
                break;
            } else if (userInput.equals(listKeyword)) {
                printTaskList(taskList);
            } else {
                taskList.add(userInput);
                printResponse("added: " + userInput);
            }
        }
    }
}
