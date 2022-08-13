import utils.Constants;
import utils.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private final List<Task> tasks;

    Duke() {
        tasks = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startDuke();
    }

    public void startDuke() {
        sendGreetings();
        Scanner sc = new Scanner(System.in);

        boolean isDone = false;
        while (!isDone) {
            String userInput = sc.nextLine();
            String[] userInputArray = userInput.split(" ", 2);
            String userCommand = userInputArray[0];

            switch (userCommand.toUpperCase()) {
            case "BYE":
                sendExit();
                isDone = true;
                break;
            case "LIST":
                printTasks();
                break;
            case "MARK": {
                Task task = tasks.get(Integer.parseInt(userInputArray[1]) - 1);
                markTask(task);
                break;
            }
            case "UNMARK": {
                Task task = tasks.get(Integer.parseInt(userInputArray[1]) - 1);
                unmarkTask(task);
                break;
            }
            default:
                tasks.add(new Task(tasks.size() + 1, userInput));
                Utilities.printMessage("added: " + userInput);
                break;
            }
        }

        sc.close();
    }

    public void printTasks() {
        Utilities.printLine();
        Utilities.printWithIndent("Here are the tasks in your list:");
        for (Task t : tasks) {
            Utilities.printWithIndent(t.toStringWithId());
        }
        Utilities.printLine();
    }

    private void markTask(Task task) {
        task.setDone(true);
        Utilities.printMessages("Nice! I've marked this task as done:", "  " + task);
    }

    private void unmarkTask(Task task) {
        task.setDone(false);
        Utilities.printMessages("OK, I've marked this task as not done yet:", "  " + task);
    }

    private void sendGreetings() {
        Utilities.printMessage(Constants.MSG_GREETINGS);
    }

    private void sendExit() {
        Utilities.printMessage(Constants.MSG_EXIT);
    }

}