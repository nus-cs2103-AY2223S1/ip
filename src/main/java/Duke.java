import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?";
        wrapPrint(greeting);
        ArrayList<Task> taskList = new ArrayList<>();
        repeat(taskList);
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
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static void repeat(ArrayList<Task> taskList) {
        String input = "";
        Scanner in = new Scanner(System.in);
        while(true) {
            input = in.nextLine();
            String[] inputSplit = input.split(" ");
            switch(inputSplit[0]) {
                case "bye":
                    wrapPrint(leftPad("Bye. Hope to see you again soon!"));
                    return;
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
                    if (inputSplit.length < 2 || !isNumeric(inputSplit[1])) return;
                    int index = Integer.parseInt(inputSplit[1]) - 1;
                    taskList.get(index).markDone();
                    wrapPrint(leftPad("Nice! I've marked this task as done:\n  ")
                            + leftPad(taskList.get(index).toString()));
                    break;
                case "unmark":
                    if (inputSplit.length < 2 || !isNumeric(inputSplit[1])) return;
                    index = Integer.parseInt(inputSplit[1]) - 1;
                    taskList.get(index).unmarkDone();
                    wrapPrint(leftPad("OK, I've marked this task as not done yet:\n  ")
                            + leftPad(taskList.get(index).toString()));
                    break;
                case "todo":
                    taskList.add(new Task(inputSplit[1]));
                    printAddTaskSuccessfully(taskList);
                    break;
                case "deadline":
                    String time = input.split("/")[1];
                    taskList.add(new Deadline(inputSplit[1].split(" /")[0], time));
                    printAddTaskSuccessfully(taskList);
                    break;
                case "event":
                    time = input.split("/")[1];
                    taskList.add(new Event(inputSplit[1].split(" /")[0], time));
                    printAddTaskSuccessfully(taskList);
                    break;
            }
        }
    }
}
