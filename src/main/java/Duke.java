import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String line = "____________________________________________________________";

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

    private static void addAndPrintTask(ArrayList<Task> list, Task task) {
        list.add(task);
        System.out.println(Duke.line);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(Duke.line);
    }

    private static void deleteAndPrintTask(ArrayList<Task> list, int index) {
        index = index - 1;
        Task task = list.remove(index);
        System.out.println(Duke.line);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(Duke.line);
    }

    private static boolean processUserInput(String userInput, ArrayList<Task> taskList) throws DukeException{
        String command;
        String taskDetails;

        String exitMessage = "Bye. Hope to see you again soon!";
        String exitKeyword = "bye";
        String listKeyword = "list";
        String todoKeyword = "todo";
        String deadlineKeyword = "deadline";
        String eventKeyword = "event";
        String deleteKeyword = "delete";
        String markKeyword = "mark";
        String unmarkKeyword = "unmark";

        if (userInput.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The user input cannot be empty.");
        }
        command = userInput.split(" ")[0];
        if (command.equals(exitKeyword)) {
            printResponse(exitMessage);
            return true;
        } else if (command.equals(listKeyword)) {
            printTaskList(taskList);
        } else if (command.equals(markKeyword) || command.equals(unmarkKeyword)) {
            if (userInput.split(" ").length == 1) {
                throw new DukeException("☹ OOPS!!! The mark/unmark command cannot have a missing index.");
            }
            String index = userInput.split(" ")[1];
            updateAndPrintTaskStatus(taskList, Integer.parseInt(index), (command.equals(markKeyword) ? true : false));
        } else {
            Task task;
            if (userInput.split(" ", 2).length == 1) {
                if (command.equals(todoKeyword)) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (command.equals(eventKeyword)) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                } else if (command.equals(deadlineKeyword)) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                } else if (command.equals(deleteKeyword)) {
                    throw new DukeException("☹ OOPS!!! The delete command cannot have a missing index.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            taskDetails = userInput.split(" ", 2)[1];
            if (command.equals(todoKeyword)) {
                task = new Todo(taskDetails);
                addAndPrintTask(taskList, task);
            } else if (command.equals(eventKeyword)) {
                task = new Event(taskDetails.split("/")[0], taskDetails.split("/")[1].split(" ", 2)[1]);
                addAndPrintTask(taskList, task);
            } else if (command.equals(deadlineKeyword)) {
                task = new Deadline(taskDetails.split("/")[0], taskDetails.split("/")[1].split(" ", 2)[1]);
                addAndPrintTask(taskList, task);
            }  else if (command.equals(deleteKeyword)) {
                int taskIndex = Integer.parseInt(taskDetails);
                if (taskIndex > taskList.size()) {
                    throw new DukeException("☹ OOPS!!! The task index is out of range");
                }
                deleteAndPrintTask(taskList, taskIndex);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);

        String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";

        String userInput;

        ArrayList<Task> taskList = new ArrayList<Task>();

        printResponse(greetingMessage);

        while(true) {
            userInput = scanner.nextLine();
            try {
                Boolean exitCommand = processUserInput(userInput, taskList);
                if (exitCommand) {
                    break;
                }
            } catch (DukeException exception) {
                printResponse(exception.toString());
            }

        }
    }
}
