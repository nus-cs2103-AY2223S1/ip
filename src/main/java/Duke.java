import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";

    private static void printTaskList(ArrayList<Task> list) {
        System.out.println(Duke.line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i ++) {
            Task task = list.get(i);
            System.out.println((i+ 1) + ". " + (task.getTaskStatus() ? "[X]" : "[ ]") + " " + task.getTaskName());
        }
        System.out.println(Duke.line);
    }

    private static void printResponse(String response) {
        System.out.println(Duke.line);
        System.out.println(response);
        System.out.println(Duke.line);
    }

    private static void updateAndPrintTaskStatus(ArrayList<Task> list, int index, boolean isMark) {
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
        System.out.println((task.getTaskStatus() ? "[X]" : "[ ]") + " " + task.getTaskName());
    }
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);

        String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";
        String exitMessage = "Bye. Hope to see you again soon!";
        String exitKeyword = "bye";
        String listKeyword = "list";
        String markKeyword = "mark";
        String unmarkKeyword = "unmark";
        String userInput;
        String command;

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
                taskList.add(new Task(userInput));
                printResponse("added: " + userInput);
            }
        }
    }
}
