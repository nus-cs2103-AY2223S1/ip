import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static boolean isAcceptingInput;
    private static ArrayList<Task> tasks;

    private static void startUp() {
        isAcceptingInput = true;
        tasks = new ArrayList<>();
        printStartupMessage();
    }

    private static void printMessage(String msg) {
        String border = "____________________________________________________________\n";
        System.out.println(border + msg + "\n" + border);
    }

    private static void printStartupMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String startupMsg = "Hello! I'm Duke\n" + "What can I do for you?";
        printMessage(startupMsg);
    }

    private static String getCurrentListStatus() {
        return "Now you have " + tasks.size() + " task(s) in the list.";
    }

    private static void exit() {
        isAcceptingInput = false;
        String exitMsg = "Bye. Hope to see you again soon!";
        printMessage(exitMsg);
    }

    private static void listTasks() {
        int pointer = 1;
        String reply = "Here are the tasks in your list:";
        for (Task task : tasks) {
            reply += "\n" + pointer + "." + task;
            pointer++;
        }
        printMessage(reply);
    }

    private static void addTask(Task task) {
        tasks.add(task);
        String msg = "Got it. I've added this task:\n"
                + task + "\n"
                + getCurrentListStatus();
        printMessage(msg);
    }

    private static void removeTask(int index) {
        Task removedTask = tasks.remove(index);
        String msg = "Noted. I've removed this task:\n"
                + removedTask + "\n"
                + getCurrentListStatus();
        printMessage(msg);
    }

    private static void addTodo(String description) {
        Todo todo = new Todo(description);
        addTask(todo);
    }

    private static void addDeadline(String description, String date) {
        Deadline deadline = new Deadline(description, date);
        addTask(deadline);
    }

    private static void addEvent(String description, String date) {
        Event event = new Event(description, date);
        addTask(event);
    }

    private static void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        printMessage("Nice! I've marked this task as done:\n" + task);
    }

    private static void unmarkTask(int index) {
        Task task = tasks.get(index);
        task.markAsUndone();
        printMessage("OK, I've marked this task as not done yet:\n" + task);
    }

    private static void processInput(String input) {
        try {
            input = input.trim();
            if (input.equals("bye")) {
                exit();
                return;
            } else if (input.equals("list")) {
                listTasks();
                return;
            }

            String[] str = input.split(" ", 2);
            String cmd = str[0];
            if (str.length == 1 &&
                    (cmd.equals("mark")
                            || cmd.equals("unmark")
                            || cmd.equals("delete")
                            || cmd.equals("todo")
                            || cmd.equals("deadline")
                            || cmd.equals("event"))) {
                throw new MissingDukeInputException(cmd);
            }

            switch (cmd) {
            case "mark": {
                int taskIndex = Integer.parseInt(str[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new InputIndexOutOfBoundsException("tried to mark task " + str[1]);
                }
                markTask(taskIndex);
                break;
            }
            case "unmark": {
                int taskIndex = Integer.parseInt(str[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new InputIndexOutOfBoundsException("tried to unmark task " + str[1]);
                }
                unmarkTask(taskIndex);
                break;
            }
            case "delete": {
                int taskIndex = Integer.parseInt(str[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new InputIndexOutOfBoundsException("tried to delete task " + str[1]);
                }
                removeTask(taskIndex);
                break;
            }
            case "todo":
                addTodo(str[1]);
                break;
            case "deadline": {
                String[] str2 = str[1].split(" /by ");
                if (str2.length == 1) {
                    throw new MissingDukeInputException(cmd);
                }
                String description = str2[0];
                String date = str2[1];
                addDeadline(description, date);
                break;
            }
            case "event": {
                String[] str2 = str[1].split(" /at ");
                if (str2.length == 1) {
                    throw new MissingDukeInputException(cmd);
                }
                String description = str2[0];
                String date = str2[1];
                addEvent(description, date);
                break;
            }
            default:
                throw new InvalidDukeInputException();
            }
        } catch (InvalidDukeInputException e) {
            printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (MissingDukeInputException e) {
            printMessage("☹ OOPS!!! The description after a \"" + e.getMessage() + "\" is missing or incomplete!!");
        } catch (InputIndexOutOfBoundsException e) {
            printMessage("☹ OOPS!!! You " + e.getMessage()
                    + " but it doesn't exist in the list!");
        }
    }

    private static void runDuke() {
        startUp();

        Scanner sc = new Scanner(System.in);
        while (isAcceptingInput) {
            String input = sc.nextLine();
            processInput(input);
        }
    }

    public static void main(String[] args) {
        runDuke();
    }
}
