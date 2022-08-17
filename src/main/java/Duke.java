import java.util.Scanner;

public class Duke {
    private static final TaskList taskList = new TaskList();
    private static boolean notEnded = true;
    private static GreetingResponse greetResponse = new GreetingResponse();
    private static GoodbyeResponse byeResponse = new GoodbyeResponse();
    private static InvalidResponse invalidResponse = new InvalidResponse();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greetResponse.action();

        while (notEnded) {
            String input = scanner.nextLine();
            String command = input.split(" ", 2)[0];
            switch (command) {
                case "bye":
                    scanner.close();
                    byeResponse.action();
                    notEnded = false;
                    break;
                case "list":
                    ListResponse listResponse = new ListResponse(taskList);
                    listResponse.action();
                    break;
                case "mark": {
                    MarkResponse markResponse = new MarkResponse(taskList, input);
                    markResponse.action();
                    break;
                }
                case "unmark": {
                    UnmarkResponse unmarkResponse = new UnmarkResponse(taskList, input);
                    unmarkResponse.action();
                    break;
                } case "todo": {
                    ToDoResponse toDoResponse = new ToDoResponse(taskList, input);
                    toDoResponse.action();
                    break;
                } case "event": {
                    EventResponse eventResponse = new EventResponse(taskList, input);
                    eventResponse.action();
                    break;
                } case "deadline": {
                    DeadlineResponse deadlineResponse = new DeadlineResponse(taskList, input);
                    deadlineResponse.action();
                    break;
                }
                default:
                    invalidResponse.action();
            }
        }
    }
}