import java.util.Scanner;

public class Duke {
    private static GreetingResponse greetResponse = new GreetingResponse();
    private static GoodbyeResponse byeResponse = new GoodbyeResponse();
    private TaskList taskList;

    public Duke() {
        this.taskList = new TaskList();
    }
    public void run() {
        boolean notEnded = true;
        Scanner scanner = new Scanner(System.in);
        greetResponse.action();

        while (notEnded) {
            String input = scanner.nextLine();
            String [] inputArr = input.split(" ", 2);
            String command = inputArr[0];
            try {
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
                        MarkResponse markResponse = new MarkResponse(taskList, inputArr);
                        markResponse.action();
                        break;
                    }
                    case "unmark": {
                        UnmarkResponse unmarkResponse = new UnmarkResponse(taskList, inputArr);
                        unmarkResponse.action();
                        break;
                    } case "todo": {
                        ToDoResponse toDoResponse = new ToDoResponse(taskList, inputArr);
                        toDoResponse.action();
                        break;
                    } case "event": {
                        EventResponse eventResponse = new EventResponse(taskList, inputArr);
                        eventResponse.action();
                        break;
                    } case "deadline": {
                        DeadlineResponse deadlineResponse = new DeadlineResponse(taskList, inputArr);
                        deadlineResponse.action();
                        break;
                    }
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e){
                InvalidResponse invalidResponse = new InvalidResponse(e.getMessage());
                invalidResponse.action();
            }

        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}