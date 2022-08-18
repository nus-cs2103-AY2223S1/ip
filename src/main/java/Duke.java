import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Duke {
    private static final String startUpMessage  = "Hello! I'm Duke\n"
                                                + "What can I do for you?";
    private static final String exitMessage = "Bye. Hope to see you again soon!";
    private static final String exitCmd = "bye";
    private static final String noTaskMessage   = "It appears you have no tasks right now,\n"
                                                + "would you like to add some?";
    private static final String taskMarkedMessage = "Nice! I've marked this task as done:\n";
    private static final String taskUnmarkedMessage = "OK, I've marked this task as not done yet:\n";
    private static final String alreadyMarkedMessage = "This task is already marked:\n";
    private static final String alreadyUnmarkedMessage = "This task is already unmarked:\n";
    private static final String noSuchTaskMessage   = "It appears there is no such task, \n"
                                                    + "Please try again";
    private static final String invalidInputMessage = "The input is invalid, please try again.";
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(startUpMessage);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals(exitCmd)) {
            String[] inputSplit = inputSplit(userInput);
            if (userInput.equals("list")) {
                System.out.println(printTasks());
            } else if (inputSplit[0].equals("mark")) {
                try {
                    int taskNum = Integer.parseInt(inputSplit[1]);
                    if (inputSplit.length > 2) {
                        System.out.println(invalidInputMessage);
                    } else if (taskNum > tasks.size()) {
                        System.out.println(noSuchTaskMessage);
                    } else {
                            boolean valid = tasks.get(taskNum - 1).check();
                            System.out.println(
                                    (valid ? taskMarkedMessage : alreadyMarkedMessage)
                                            + tasks.get(taskNum - 1).toString());
                        }
                } catch (NumberFormatException e) {
                    System.out.println(invalidInputMessage);
                }
            } else if (inputSplit[0].equals("unmark")) {
                try {
                    int taskNum = Integer.parseInt(inputSplit[1]);
                    if (inputSplit.length > 2) {
                        System.out.println(invalidInputMessage);
                    } else if (taskNum > tasks.size()) {
                        System.out.println(noSuchTaskMessage);
                    } else {
                        boolean valid = tasks.get(taskNum - 1).uncheck();
                        System.out.println(
                                (valid ? taskUnmarkedMessage : alreadyUnmarkedMessage)
                                        + tasks.get(taskNum - 1).toString());
                    }
                } catch (NumberFormatException e) {
                    System.out.println(invalidInputMessage);
                }
            } else if (inputSplit[0].equals("todo")) {

            } else if (inputSplit[0].equals("deadline")) {

            } else if (inputSplit[0].equals("event")) {

            } else {
                Task newTask = new Task(userInput);
                tasks.add(newTask);
                System.out.println("added: " + newTask);
            }
            userInput = sc.nextLine();
        }
        System.out.println(exitMessage);
    }

    static String printTasks() {
        if (tasks.isEmpty()) {
            return noTaskMessage;
        } else {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                message.append(String.format("%d. %s"
                                + (i != tasks.size() - 1 ? "\n" : "")
                                , i + 1, tasks.get(i).toString()));
            }
            return message.toString();
        }
    }

    static String[] inputSplit(String input) {
        if (input.contains("mark") || input.contains("unmark")) {
            String[] result = input.split(" ");
            return result;
        } else if (input.contains("todo")) {
            String[] result = new String[2];
            result[0] = "todo";
            result[1] = input.substring(4);
            return result;
        } else if (input.contains("deadline") || input.contains("event")) {
            String[] result = new String[3];
            result[0] = input.contains("deadline") ? "deadline" : "event";
            result[1] = input.substring(input.contains("deadline") ? 8 : 5, input.lastIndexOf('/'));
            result[2] = input.substring(input.lastIndexOf("/") + 1);
            return result;
        } else {
            return new String[] {input};
        }
    }
}
