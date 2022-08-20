import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {

    public static void main(String[] args) {
        Task.loadTasks();
        Scanner input = new Scanner(System.in);
        BotResponse.welcome();
        String userResponse = input.nextLine();
        while (!userResponse.equalsIgnoreCase("bye")) {
            try {
                checkResponse(userResponse);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
            userResponse = input.nextLine();
        }

        BotResponse.bye();
        System.exit(0);
    }

    public static void checkResponse(String userResponse) throws InvalidInputException {
        userResponse += " ";
        String command = userResponse.substring(0, userResponse.indexOf(' ')).trim();

        switch (command) {
        case "list":
            Task.printTasks();
            break;

        case "unmark":
            try {
                int index = Integer.parseInt(userResponse.substring(6).trim()) - 1;
                if (index < 0 || index > Task.length() - 1) {
                    throw new InvalidIndexException();
                }
                Task.markDone(index, false);
            } catch (InvalidIndexException e) {
                System.out.println(e.getMessage());
            }
            break;

        case "mark":
            try {
                int index = Integer.parseInt(userResponse.substring(4).trim()) - 1;
                if (index < 0 || index > Task.length() - 1) {
                    throw new InvalidIndexException();
                }
                Task.markDone(index, true);
            } catch (InvalidIndexException e) {
                System.out.println(e.getMessage());
            }
            break;

        case "todo":
            try {
                String description = userResponse.substring(4).trim();
                if (description.isEmpty()) {
                    throw new InvalidDescriptionException();
                }
                ToDo todo = new ToDo(description);
                BotResponse.addTaskLog(todo);
            } catch (InvalidDescriptionException e) {
                System.out.println(e.getMessage());
            }
            break;

        case "deadline":
            try {
                int timeIndex = userResponse.indexOf("/by");
                if (timeIndex == -1) {
                    throw new InvalidTimeException();
                }
                String description = userResponse.substring(8, timeIndex - 1).trim();
                String time = userResponse.substring(timeIndex + 3).trim();
                if (description.isEmpty()) {
                    throw new InvalidDescriptionException();
                }
                if (time.isEmpty()) {
                    throw new InvalidTimeException();
                }
                Deadline deadline = new Deadline(description, time);
                BotResponse.addTaskLog(deadline);
            } catch (InvalidDescriptionException | InvalidTimeException e) {
                System.out.println(e.getMessage());
            }
            break;

        case "event":
            try {
                int timeIndex = userResponse.indexOf("/at");
                if (timeIndex == -1) {
                    throw new InvalidTimeException();
                }
                String description = userResponse.substring(5, timeIndex - 1).trim();
                String time = userResponse.substring(timeIndex + 3).trim();
                if (description.isEmpty()) {
                    throw new InvalidDescriptionException();
                }
                if (time.isEmpty()) {
                    throw new InvalidTimeException();
                }
                Event event = new Event(description, time);
                BotResponse.addTaskLog(event);
            } catch (InvalidDescriptionException | InvalidTimeException e) {
                System.out.println(e.getMessage());
            }
            break;

        case "delete":
            try {
                int index = Integer.parseInt(userResponse.substring(6).trim()) - 1;
                if (index < 0 || index > Task.length() - 1) {
                    throw new InvalidIndexException();
                }
                Task.removeTask(index);
            } catch (InvalidIndexException e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            throw new InvalidInputException();
        }
        Task.saveTasks();
    }

}
