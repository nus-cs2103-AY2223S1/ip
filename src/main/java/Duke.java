import javax.swing.undo.UndoManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private TasksList tasksList;
    private boolean hasEnded = false;
    private static final WelcomeRequest welcomeRequest = new WelcomeRequest();
    private static final GoodbyeRequest goodbyeRequest = new GoodbyeRequest();
    private static final InvalidRequest invalidRequest = new InvalidRequest();

    public Duke() {
        this.tasksList = new TasksList();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        greet();
        while (!hasEnded) {
            String userInput = getInput(sc);
            String[] inputArray = userInput.split(" ", 2);
            String command = inputArray[0];

            switch (command.toUpperCase()) {
                case "BYE":
                    goodBye();
                    sc.close();
                    hasEnded = true;
                    break;
                case "LIST":
                    ListRequest listRequest= new ListRequest(this.tasksList);
                    listRequest.execute();
                    break;
                case "MARK":
                    MarkRequest markRequest = new MarkRequest(this.tasksList, inputArray[1]);
                    markRequest.execute();
                    break;
                case "UNMARK":
                    UnmarkRequest unmarkRequest = new UnmarkRequest(this.tasksList, inputArray[1]);
                    unmarkRequest.execute();
                    break;
                case "TODO":
                    TodoRequest todoRequest = new TodoRequest(this.tasksList, inputArray[1]);
                    todoRequest.execute();
                    break;
                case "DEADLINE":
                    DeadlineRequest deadlineRequest = new DeadlineRequest(this.tasksList, inputArray[1]);
                    deadlineRequest.execute();
                    break;
                case "EVENT":
                    EventRequest eventRequest = new EventRequest(this.tasksList, inputArray[1]);
                    eventRequest.execute();
                    break;
                default:
                    InvalidRequest invalidRequest = new InvalidRequest();
                    invalidRequest.execute();
            }
        }
    }

    public String getInput(Scanner sc) {
        System.out.println();
        return sc.nextLine();
    }

    public void greet() {
        Duke.welcomeRequest.execute();
    }

    public void goodBye() {
        Duke.goodbyeRequest.execute();
    }
}

