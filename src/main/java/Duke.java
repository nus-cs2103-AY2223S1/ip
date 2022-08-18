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
    private static final String addTaskMessage = "Got it. I've added this task:\n";
    private static final String taskMarkedMessage = "Nice! I've marked this task as done:\n";
    private static final String taskUnmarkedMessage = "OK, I've marked this task as not done yet:\n";
    private static final String alreadyMarkedMessage = "This task is already marked:\n";
    private static final String alreadyUnmarkedMessage = "This task is already unmarked:\n";
    private static final String deleteTaskMessage = "Noted. I've removed this task:\n";
    private static final String noSuchTaskMessage   = "It appears there is no such task, \n"
                                                    + "Please try again";
    private static final String invalidInputMessage = "The input is invalid, please try again.";
    private static final String noDescriptionMessage = "The description of the task cannot be empty.";
    private static final String noTimeGivenMessage  = "Please provide the relevant time for this type of task,\n"
                                                    + "by typing \"/\" followed by the time.";
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(startUpMessage);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals(exitCmd)) {
            try {
                String[] inputSplit = inputSplit(userInput);
                if (userInput.equals("list")) {
                    System.out.println(printTasks());
                } else if (inputSplit[0].equals("mark")) {
                    try {
                        int taskNum = Integer.parseInt(inputSplit[1]);
                        if (taskNum > tasks.size() || taskNum <= 0) {
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
                        } else if (taskNum > tasks.size() || taskNum <= 0) {
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
                    Task newTask = new TodoTask(inputSplit[1]);
                    tasks.add(newTask);
                    System.out.println(addTaskMessage + newTask);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                } else if (inputSplit[0].equals("deadline")) {
                    Task newTask = new DeadlineTask(inputSplit[1], inputSplit[2]);
                    tasks.add(newTask);
                    System.out.println(addTaskMessage + newTask);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                } else if (inputSplit[0].equals("event")) {
                    Task newTask = new EventTask(inputSplit[1], inputSplit[2]);
                    tasks.add(newTask);
                    System.out.println(addTaskMessage + newTask);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                } else if (inputSplit[0].equals("delete")) {
                    try {
                        int taskNum = Integer.parseInt(inputSplit[1]);
                        if (inputSplit.length > 2) {
                            System.out.println(invalidInputMessage);
                        } else if (taskNum > tasks.size() || taskNum <= 0) {
                            System.out.println(noSuchTaskMessage);
                        } else {
                            Task removed = tasks.get(taskNum - 1);
                            tasks.remove(taskNum - 1);
                            System.out.println(deleteTaskMessage + removed.toString());
                            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(invalidInputMessage);
                    }
                } else {
                    throw new DukeException(invalidInputMessage);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
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

    static String[] inputSplit(String input) throws DukeException {
        if (input.contains("mark") || input.contains("unmark") || input.contains("delete")) {
            String[] result = input.split(" ");
            if (result.length == 2
                    && (result[0].equals("mark")
                        || result[0].equals("unmark")
                        || result[0].equals("delete"))) {
                return result;
            }
        }
        if (input.contains("todo")) {
            String[] result = new String[2];
            result[0] = input.substring(0,4);
            result[1] = input.substring(4);
            if (result[1].isBlank()) {
                throw new DukeException(noDescriptionMessage);
            } else if (result[0].equals("todo")) {
                return result;
            }
        }
        if (input.contains("deadline") || input.contains("event")) {
            String[] result = new String[3];
            if (input.lastIndexOf("/") == -1) {
                throw new DukeException(noTimeGivenMessage);
            }
            result[0] = input.contains("deadline") ? input.substring(0,8) : input.substring(0,5);
            result[1] = input.substring(input.contains("deadline") ? 8 : 5, input.lastIndexOf('/'));
            result[2] = input.substring(input.lastIndexOf("/") + 1);
            if (result[1].length() == 0) {
                throw new DukeException(noDescriptionMessage);
            }
            return result;
        }
        return new String[]{input};
    }
}
