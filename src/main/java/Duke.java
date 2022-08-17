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
        } else {
            Task t = new Task(userResponse);
            BotResponse.addTaskLog(userResponse);
        }
        checkResponse(input);
    }

}
