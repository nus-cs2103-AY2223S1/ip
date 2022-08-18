import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        DukeResponse.intro();

        run();

        DukeResponse.outro();
    }

    private static void run() {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        DukeList list = new DukeList();

        while (isRunning) {
            String input = scanner.nextLine();

            try {
                DukeCommand command = getCommand(input);
                String data = getData(input);

                switch (command) {
                case EXIT:
                    // Exit Duke
                    scanner.close();
                    isRunning = false;
                    break;
                case LIST:
                    // Print list
                    new ListResponse(list).run();
                    break;
                case MARK:
                    // Mark task as done
                    new MarkResponse(list, data).run();
                    break;
                case UNMARK:
                    // Mark task as undone
                    new UnmarkResponse(list, data).run();
                    break;
                case DELETE:
                    new DeleteResponse(list, data).run();
                    break;
                case TODO:
                    // Add task as to do
                    new TodoResponse(list, data).run();
                    break;
                case DEADLINE:
                    // Add task as deadline
                    new DeadlineResponse(list, data).run();
                    break;
                case EVENT:
                    // Add task as event
                    new EventResponse(list, data).run();
                    break;
                default:
                    // Unknown command
                    throw new DukeException("I'm sorry, but I don't know what that means :(");
                }
            } catch (DukeException e) {
                new ExceptionResponse(e).run();
            }
        }
    }

    private static DukeCommand getCommand(String input) {
        return DukeCommand.read(input.split(" ", 2)[0]);
    }

    private static String getData(String input) {
        if (input.contains(" ")) {
            return input.split(" ", 2)[1].trim();
        }
        return "";
    }
}