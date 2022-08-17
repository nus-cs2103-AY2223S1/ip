import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";

    private static void printTaskList(ArrayList<Task> list) {
        System.out.println(Duke.line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i ++) {
            Task task = list.get(i);
            System.out.println((i+ 1) + "." + task.toString());
        }
        System.out.println(Duke.line);
    }

    private static void printResponse(String response) {
        System.out.println(Duke.line);
        System.out.println(response);
        System.out.println(Duke.line);
    }

    private static void updateAndPrintTaskStatus(ArrayList<Task> list, int index, boolean isMark) {
        System.out.println(Duke.line);
        index = index - 1;
        Task task = list.get(index);
        if (isMark) {
            System.out.println("Nice! I've marked this task as done:");
            task.setTaskStatus(true);
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            task.setTaskStatus(false);
        }
        list.set(index, task);
        System.out.println(task.toString());
        System.out.println(Duke.line);
    }

    private static void printTaskAdded(ArrayList<Task> list, Task task) {
        System.out.println(Duke.line);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(Duke.line);
    }

    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);

        String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";
        String exitMessage = "Bye. Hope to see you again soon!";
        String exitKeyword = "bye";
        String listKeyword = "list";
        String todoKeyword = "todo";
        String deadlineKeyword = "deadline";
        String eventKeyword = "event";
        String markKeyword = "mark";
        String unmarkKeyword = "unmark";

        String userInput;
        String command;
        String taskDetails;

        ArrayList<Task> taskList = new ArrayList<Task>();

        printResponse(greetingMessage);

        while(true) {
            userInput = scanner.nextLine();
            command = userInput.split(" ")[0];
            if (command.equals(exitKeyword)) {
                printResponse(exitMessage);
                break;
            } else if (command.equals(listKeyword)) {
                printTaskList(taskList);
            } else if (command.equals(markKeyword)) {
                String index = userInput.split(" ")[1];
                updateAndPrintTaskStatus(taskList, Integer.parseInt(index), true);
            } else if (command.equals(unmarkKeyword)) {
                String index = userInput.split(" ")[1];
                updateAndPrintTaskStatus(taskList, Integer.parseInt(index), false);
            } else {
                Task task;
                taskDetails = userInput.split(" ", 2)[1];
                if (command.equals(todoKeyword)) {
                    task = new Todo(taskDetails);
                    taskList.add(task);
                    printTaskAdded(taskList, task);
                } else if (command.equals(eventKeyword)) {
                    task = new Event(taskDetails.split("/")[0], taskDetails.split("/")[1].split(" ", 2)[1]);
                    taskList.add(task);
                    printTaskAdded(taskList, task);
                } else if (command.equals(deadlineKeyword)) {
                    task = new Deadline(taskDetails.split("/")[0], taskDetails.split("/")[1].split(" ", 2)[1]);
                    taskList.add(task);
                    printTaskAdded(taskList, task);
                }  else {
                    printResponse("Invalid command. Please try again.");
                }
            }
        }
    }
}
