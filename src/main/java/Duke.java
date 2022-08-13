import utils.Constants;
import utils.DukeUtils;

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
            String userCommand = userInputArray[0].trim();

            switch (userCommand.toUpperCase()) {
            case "TODO":
                addTask(new ToDo(userInputArray[1].trim()));
                break;
            case "DEADLINE": {
                String[] userDescArray = userInputArray[1].split("/by");
                addTask(new Deadline(userDescArray[0].trim(), userDescArray[1].trim()));
                break;
            }
            case "EVENT": {
                String[] userDescArray = userInputArray[1].split("/at");
                addTask(new Event(userDescArray[0].trim(), userDescArray[1].trim()));
                break;
            }
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
            case "BYE":
                sendExit();
                isDone = true;
                break;
            default:
                break;
            }
        }

        sc.close();
    }



    public void printTasks() {
        DukeUtils.printLine();
        DukeUtils.printWithIndent("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            DukeUtils.printWithIndent(String.format("%d.%s", i + 1, tasks.get(i)));
        }
        DukeUtils.printLine();
    }

    public void addTask(Task task) {
        tasks.add(task);
        DukeUtils.printLine();
        DukeUtils.printWithIndent(Constants.MSG_TASK_ADDED);
        DukeUtils.print("\t  " + task);
        DukeUtils.printWithIndent("Now you have " + tasks.size() + " tasks in the list.");
        DukeUtils.printLine();
    }

    private void markTask(Task task) {
        task.setDone(true);
        DukeUtils.printMessages(Constants.MSG_TASK_MARK, "  " + task);
    }

    private void unmarkTask(Task task) {
        task.setDone(false);
        DukeUtils.printMessages(Constants.MSG_TASK_UNMARK, "  " + task);
    }

    private void sendGreetings() {
        DukeUtils.printMessage(Constants.MSG_GREETINGS);
    }

    private void sendExit() {
        DukeUtils.printMessage(Constants.MSG_EXIT);
    }

}