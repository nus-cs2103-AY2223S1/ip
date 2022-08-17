import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BotResponse.welcome();
        String userResponse = input.nextLine();
        while (!userResponse.equalsIgnoreCase("bye")) {
            try {
                checkResponse(userResponse);
            } catch (InvalidInputException e) {
                BotResponse.separationLine();
                System.out.println(e.getMessage());
                BotResponse.separationLine();
            }
            userResponse = input.nextLine();
        }

        BotResponse.bye();
        System.exit(0);
    }

    public static void checkResponse(String userResponse) throws InvalidInputException {

        if (userResponse.equalsIgnoreCase("list")) {
            Task.printTasks();

        } else if (userResponse.startsWith("unmark")) {
            int index = Integer.parseInt(userResponse.substring(6).trim()) - 1;
            Task.markDone(index, false);

        } else if (userResponse.startsWith("mark")) {
            int index = Integer.parseInt(userResponse.substring(4).trim()) - 1;
            Task.markDone(index, true);

        } else if (userResponse.startsWith("todo")) {
            try {
                String description = userResponse.substring(4).trim();
                if (description.isEmpty()) {
                    throw new InvalidDescriptionException();
                }
                ToDo todo = new ToDo(description);
                BotResponse.addTaskLog(todo);
            } catch (InvalidDescriptionException e) {
                BotResponse.separationLine();
                System.out.println(e.getMessage());
                BotResponse.separationLine();
            }

        } else if (userResponse.startsWith("deadline")) {
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
                BotResponse.separationLine();
                System.out.println(e.getMessage());
                BotResponse.separationLine();
            }

        } else if (userResponse.startsWith("event")) {
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
                BotResponse.separationLine();
            }

        } else if (userResponse.startsWith("delete")) {
            int index = Integer.parseInt(userResponse.substring(6).trim()) - 1;
            Task.removeTask(index);

        } else {
            throw new InvalidInputException();
        }
    }

}
