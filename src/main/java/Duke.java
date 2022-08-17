import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?";
        wrapPrint(greeting);
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while(true) {
            try {
                if (repeat(in, taskList)) {
                    break;
                }
            } catch (DukeException e) {
                wrapPrint(leftPad("☹ OOPS!!! " + e.getLocalizedMessage()));
            }
        }
    }

    private static void wrapPrint(String toPrint) {
        System.out.println("    ____________________________________________________________");
        System.out.println(toPrint);
        System.out.println("    ____________________________________________________________");
    }

    private static void printAddTaskSuccessfully(ArrayList<Task> taskList) {
        String taskString = "tasks";
        if (taskList.size() == 1) {
            taskString = "task";
        }
        wrapPrint(leftPad("Got it. I've added this task:\n")
                + leftPad("  " + taskList.get(taskList.size() - 1).toString())
                + leftPad(String.format("\n" + leftPad("Now you have %d %s in the list."), taskList.size(), taskString)));
    }

    private static String leftPad(String toPrint) {
        return "     " + toPrint;
    }

    private static boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }

    private static boolean repeat(Scanner in, ArrayList<Task> taskList) throws DukeException {
        String input = in.nextLine();
        String[] inputSplit = input.split(" ");
        switch(inputSplit[0]) {
            case "bye":
                wrapPrint(leftPad("Bye. Hope to see you again soon!"));
                return true;
            case "list":
                StringBuilder listString = new StringBuilder(leftPad("Here are the tasks in your list:\n"));
                for (int i = 0; i < taskList.size(); i++) {
                    listString.append(leftPad(String.format("%d.", i + 1)));
                    listString.append(taskList.get(i).toString());
                    listString.append("\n");
                }
                if (listString.length() > 0) {
                    listString.setLength(listString.length() - 1);
                }
                wrapPrint(listString.toString());
                break;
            case "mark":
            case "unmark":
                // Error handling
                if (inputSplit.length < 2) {
                    throw new DukeException("You did not specify what task number to mark as done. Unable to mark task.");
                } else if (!isNumeric(inputSplit[1])) {
                    throw new DukeException(String.format("Invalid task number provided: %s. Unable to mark task.", inputSplit[1]));
                }
                int index = Integer.parseInt(inputSplit[1]) - 1;
                if (index == -1 || index >= taskList.size()) {
                    throw new DukeException(String.format("Task number %d not found! Unable to mark task.", index + 1));
                }
                // Mark/unmark operations
                if (inputSplit[0].equals("mark")) {
                    taskList.get(index).markDone();
                    wrapPrint(leftPad("Nice! I've marked this task as done:\n  ")
                            + leftPad(taskList.get(index).toString()));
                } else {
                    taskList.get(index).unmarkDone();
                    wrapPrint(leftPad("OK, I've marked this task as not done yet:\n  ")
                            + leftPad(taskList.get(index).toString()));
                }
                break;
            case "todo":
                // Error handling
                if (inputSplit.length < 2) {
                    throw new DukeException("Please provide a description for your todo.");
                }
                taskList.add(new Task(String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length))));
                printAddTaskSuccessfully(taskList);
                break;
            case "deadline":
            case "event":
                String[] inputSlashSplit = input.split("/");
                // Error handling
                if (inputSlashSplit.length < 2 || inputSlashSplit[1].split(" ").length < 2) {
                    throw new DukeException(String.format("Please specify a time for your %s.", inputSplit[0]));
                }
                if (inputSlashSplit[0].split(" ").length < 2) {
                    throw new DukeException(String.format("Please provide a description for your %s.", inputSplit[0]));
                }
                String time = input.split("/")[1];
                String[] description = inputSlashSplit[0].split(" ");
                if (inputSplit[0].equals("deadline")) {
                    taskList.add(new Deadline(String.join(" ", Arrays.copyOfRange(description, 1, description.length)), time));
                } else {
                    taskList.add(new Event(String.join(" ", Arrays.copyOfRange(description, 1, description.length)), time));
                }
                printAddTaskSuccessfully(taskList);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }
}
