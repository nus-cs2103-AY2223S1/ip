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

        } else if (userResponse.startsWith("unmark")) {
            int length = userResponse.length();
            int index = userResponse.charAt(length - 1) - '0' - 1;
            Task.markDone(index, false);

        } else if (userResponse.startsWith("mark")) {
            int length = userResponse.length();
            int index = userResponse.charAt(length - 1) - '0' - 1;
            Task.markDone(index, true);

        } else if (userResponse.startsWith("todo")) {
            ToDo todo = new ToDo(userResponse.substring(5));
            BotResponse.addTaskLog(todo);

        } else if (userResponse.startsWith("deadline")) {
            int timeIndex = userResponse.indexOf("/by ");
            Deadline deadline = new Deadline(userResponse.substring(9, timeIndex - 1), userResponse.substring(timeIndex + 4));
            BotResponse.addTaskLog(deadline);

        } else if (userResponse.startsWith("event")) {
            int timeIndex = userResponse.indexOf("/at ");
            Event event = new Event(userResponse.substring(6, timeIndex - 1), userResponse.substring(timeIndex + 4));
            BotResponse.addTaskLog(event);
            
        } else {
            Task task = new Task(userResponse);
            BotResponse.addTaskLog(task);
        }
        checkResponse(input);
    }

}
