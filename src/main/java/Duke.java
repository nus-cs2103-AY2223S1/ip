import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner dukeScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello i'm Duke\nHow may i help you today?");
        Task[] taskList  = new Task[100];
        int[] count = new int[1];

        String input;
        while (true) {
            input = dukeScanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    taskDisplay(taskList, count[0]);
                } else {
                    checkTask(input, taskList, count);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Checks input of user and react accordingly
     * @param input String input of the user
     * @param list Array which stores tasks added by user
     * @param index Current index pointer of last element in array
     */
    private static void checkTask(String input, Task[] list, int[] index) throws DukeException {
        String[] cmdAndDesc = input.split(" ", 2);
        String command = cmdAndDesc[0];

        switch (command) {
            case "mark":
                if (checkException(cmdAndDesc)) {
                    throw new DukeException("☹ OOPS!!! " +
                            "The description of mark cannot be empty.");
                }
                int indexToMark = Integer.parseInt(cmdAndDesc[1]) - 1;
                list[indexToMark].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        String.format("%s", list[indexToMark]));
                break;
            case "unmark":
                if (checkException(cmdAndDesc)) {
                    throw new DukeException("☹ OOPS!!! " +
                            "The description of unmark cannot be empty.");
                }
                int indexToUnmark = Integer.parseInt(cmdAndDesc[1]) - 1;
                list[indexToUnmark].markAsUndone();
                System.out.println("Ok! I've marked this task as not done yet:\n" +
                        String.format("%s", list[indexToUnmark]));
                break;
            case "deadline" :
                if (checkException(cmdAndDesc)) {
                    throw new DukeException("☹ OOPS!!! " +
                            "The description of deadline cannot be empty.");
                }
                String[] descAndDue = cmdAndDesc[1].split("/by ");
                if (checkException(descAndDue)) {
                    throw new DukeException("☹ OOPS!!! " +
                            "There is no specified date/time for the deadline.");
                }
                taskAddition();
                list[index[0]] = new Deadline(descAndDue[0], descAndDue[1]);
                System.out.println(String.format("\t%s", list[index[0]]));
                index[0]++;
                listStatus(index[0]);
                break;
            case "event" :
                if (checkException(cmdAndDesc)) {
                    throw new DukeException("☹ OOPS!!! " +
                            "The description of event cannot be empty.");
                }
                String[] descAndDuration = cmdAndDesc[1].split("/at ");
                if (checkException(descAndDuration)) {
                    throw new DukeException("☹ OOPS!!! " +
                            "There is no specified time duration for the event.");
                }
                taskAddition();
                list[index[0]] = new Event(descAndDuration[0], descAndDuration[1]);
                System.out.println(String.format("\t%s", list[index[0]]));
                index[0]++;
                listStatus(index[0]);
                break;
            case "todo" :
                if (checkException(cmdAndDesc)) {
                    throw new DukeException("☹ OOPS!!! " +
                            "The description of todo cannot be empty.");
                }
                taskAddition();
                list[index[0]] = new ToDo(cmdAndDesc[1]);
                System.out.println(String.format("\t%s", list[index[0]]));
                index[0]++;
                listStatus(index[0]);
                break;
            default:
                throw new DukeException("☹ OOPS!!! " +
                        "I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns string representation of list of tasks.
     * @param list Array of tasks as inputted by the user
     * @param count Total number of tasks
     */
    private static void taskDisplay(Task[] list, int count) {
        if (count == 0) {
            System.out.println("There are currently no tasks in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                System.out.println(String.format("%d. %s", i + 1, list[i]));
            }
        }
    }

    /**
     * Print message for addition of task.
     */
    private static void taskAddition() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Prints current status of list.
     * @param count Count of how many total task there are
     */
    private static void listStatus(int count) {
        System.out.println(String.format("Now you have %d tasks in the list.",
                count));
    }

    /**
     * Checks if specified description is present
     * @param substrings Array of substrings
     * @return if there is an error
     */
    private static boolean checkException(String[] substrings) {
        return substrings.length == 1 ? true : false;
    }
}
