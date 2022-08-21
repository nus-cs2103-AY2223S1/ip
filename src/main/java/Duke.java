import java.util.Scanner;

public class Duke {
    private static GreetingResponse greetResponse = new GreetingResponse();
    private static GoodbyeResponse byeResponse = new GoodbyeResponse();
    private static String TASKS_STORAGE_PATH = "./data/duke.txt";
    private TaskList taskList;

    public Duke() {
        this.taskList = new TaskList(TASKS_STORAGE_PATH);
    }

    public void run() {
        boolean hasEnded = false;
        Scanner scanner = new Scanner(System.in);
        greetResponse.action();

        while (!hasEnded) {
            try {
                String input = scanner.nextLine();
                String [] inputArr = input.split(" ", 2);
                Command command = Command.parse(inputArr[0]);
                switch (command) {
                    case BYE:
                        scanner.close();
                        byeResponse.action();
                        hasEnded = true;
                        break;
                    case LIST:
                        ListResponse listResponse = new ListResponse(taskList);
                        listResponse.action();
                        break;
                    case MARK: {
                        MarkResponse markResponse = new MarkResponse(taskList, inputArr);
                        markResponse.action();
                        break;
                    }
                    case UNMARK: {
                        UnmarkResponse unmarkResponse = new UnmarkResponse(taskList, inputArr);
                        unmarkResponse.action();
                        break;
                    } case TODO: {
                        ToDoResponse toDoResponse = new ToDoResponse(taskList, inputArr);
                        toDoResponse.action();
                        break;
                    } case EVENT: {
                        EventResponse eventResponse = new EventResponse(taskList, inputArr);
                        eventResponse.action();
                        break;
                    } case DEADLINE: {
                        DeadlineResponse deadlineResponse = new DeadlineResponse(taskList, inputArr);
                        deadlineResponse.action();
                        break;
                    } case DELETE: {
                        DeleteResponse deleteResponse = new DeleteResponse(taskList, inputArr);
                        deleteResponse.action();
                        break;
                    }
                }
                taskList.saveTasks();
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