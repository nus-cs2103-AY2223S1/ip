import java.util.Scanner;

public class Duke {
    private static final String EXIT_KEYWORD = "bye";
    private static final String LIST_KEYWORD = "list";
    private static final String MARK_KEYWORD = "mark";
    private static final String UNMARK_KEYWORD = "unmark";
    private static final String DELETE_KEYWORD = "delete";
    private static final String TODO_KEYWORD = "todo";
    private static final String DEADLINE_KEYWORD = "deadline";
    private static final String EVENT_KEYWORD = "event";

    public static void main(String[] args) {
        DukeResponse.intro();

        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        DukeList list = new DukeList();

        while (isRunning) {
            String input = scanner.nextLine();
            String command = input.contains(" ") ? input.split(" ", 2)[0] : input;
            String data = input.contains(" ") ? input.split(" ", 2)[1].trim() : "";

            try {
                switch (command) {
                case EXIT_KEYWORD:
                    // Exit Duke
                    scanner.close();
                    isRunning = false;

                    break;
                case LIST_KEYWORD:
                    // Print list
                    new ListResponse(list).run();
                    break;
                case MARK_KEYWORD:
                    // Mark task as done
                    new MarkResponse(list, data).run();
                    break;
                case UNMARK_KEYWORD:
                    // Mark task as undone
                    new UnmarkResponse(list, data).run();
                    break;
                case DELETE_KEYWORD:
                    new DeleteResponse(list, data).run();
                    break;
                case TODO_KEYWORD:
                    // Add task as to do
                    new TodoResponse(list, data).run();
                    break;
                case DEADLINE_KEYWORD:
                    // Add task as deadline
                    new DeadlineResponse(list, data).run();
                    break;
                case EVENT_KEYWORD:
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

        DukeResponse.outro();
    }
}