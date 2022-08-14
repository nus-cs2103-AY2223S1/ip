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
        boolean isRunning = true;

        while (isRunning) {
            try {
                String[] inputs = sc.nextLine().trim().split(" ", 2);
                if (inputs.length == 1) {
                    switch (inputs[0].toUpperCase()) {
                    // Fallthrough in this switch case are intentional
                    case "TODO":
                    case "DEADLINE":
                    case "EVENT":
                        throw new DukeException(String.format(Constants.ERROR_EMPTY_DESCRIPTION, inputs[0]));
                    case "MARK":
                    case "UNMARK":
                    case "DELETE":
                        throw new DukeException((Constants.ERROR_TASK_NOT_SPECIFIED));
                    case "LIST":
                        printTasks();
                        break;
                    case "BYE":
                        sendExit();
                        sc.close();
                        isRunning = false;
                        break;
                    default:
                        throw new DukeException(Constants.ERROR_UNKNOWN_COMMAND);
                    }
                    continue;
                }

                String userCommand = inputs[0].trim();

                switch (userCommand.toUpperCase()) {
                case "TODO":
                    addTask(new ToDo(inputs[1].trim()));
                    break;
                case "DEADLINE": {
                    String[] userDescArray = inputs[1].split("/by");
                    addTask(new Deadline(userDescArray[0].trim(), userDescArray[1].trim()));
                    break;
                }
                case "EVENT": {
                    String[] userDescArray = inputs[1].split("/at");
                    addTask(new Event(userDescArray[0].trim(), userDescArray[1].trim()));
                    break;
                }
                case "MARK": {
                    Task task = tasks.get(Integer.parseInt(inputs[1]) - 1);
                    markTask(task);
                    break;
                }
                case "UNMARK": {
                    Task task = tasks.get(Integer.parseInt(inputs[1]) - 1);
                    unmarkTask(task);
                    break;
                }
                case "DELETE": {
                    Task task = tasks.get(Integer.parseInt(inputs[1]) - 1);
                    deleteTask(task);
                    break;
                }
                default:
                    throw new DukeException(Constants.ERROR_UNKNOWN_COMMAND);
                }

            } catch (DukeException e) {
                DukeUtils.printMessage(e.getMessage());
            }
        }
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

    private void deleteTask(Task task) {
        tasks.remove(task);
        DukeUtils.printMessages(
                Constants.MSG_TASK_DELETED,
                "  " + task,
                "Now you have " + tasks.size() + " tasks in the list");
    }

    private void sendGreetings() {
        DukeUtils.printMessage(Constants.MSG_GREETINGS);
    }

    private void sendExit() {
        DukeUtils.printMessage(Constants.MSG_EXIT);
    }

}