import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BotResponse.welcome();
        checkResponse(input);
    }

    public static void checkResponse(Scanner input) {
        String userResponse = input.nextLine();

        if (userResponse.equalsIgnoreCase("bye")) {
            BotResponse.bye();
            System.exit(0);

        } else if (userResponse.equalsIgnoreCase("list")) {
            Task.printTasks();

        } else if (userResponse.contains("unmark")) {
            int length = userResponse.length();
            int index = userResponse.charAt(length - 1) - '0' - 1;
            Task.markDone(index, false);

        } else if (userResponse.contains("mark")) {
            int length = userResponse.length();
            int index = userResponse.charAt(length - 1) - '0' - 1;
            Task.markDone(index, true);

        } else {
            Task t = new Task(userResponse);
            BotResponse.addTaskLog(userResponse);
        }
        checkResponse(input);
    }

}
