import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner dukeScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello i'm Duke\nHow may i help you today?");
        ArrayList<Task> taskList  = new ArrayList<Task>();

        String input;
        while (true) {
            input = dukeScanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    taskDisplay(taskList);
                } else {
                    checkTask(input, taskList);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Checks input of user and react accordingly based on the command
     * @param input String input of the user
     * @param taskArrayList Arraylist which stores tasks added by user
     */
    private static void checkTask(String input, ArrayList<Task> taskArrayList) throws DukeException {
        String[] cmdAndDesc = input.split(" ", 2);
        String command = cmdAndDesc[0];

        switch (command) {
            case "mark":
                if (cmdAndDesc.length == 1) {
                    throw new DukeException("☹ OOPS!!! " +
                            "The description of mark cannot be empty.");
                }
                int indexToMark = Integer.parseInt(cmdAndDesc[1]) - 1;
                taskArrayList.get(indexToMark).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        String.format("%s", taskArrayList.get(indexToMark)));
                break;
            case "unmark":
                if (cmdAndDesc.length == 1) {
                    throw new DukeException("☹ OOPS!!! " +
                            "The description of unmark cannot be empty.");
                }
                int indexToUnmark = Integer.parseInt(cmdAndDesc[1]) - 1;
                taskArrayList.get(indexToUnmark).markAsUndone();
                System.out.println("Ok! I've marked this task as not done yet:\n" +
                        String.format("%s", taskArrayList.get(indexToUnmark)));
                break;
            case "delete" :
                if (cmdAndDesc.length == 1) {
                    throw new DukeException("☹ OOPS!!! " +
                            "The description of delete cannot be empty.");
                }
                int indexToDelete = Integer.parseInt(cmdAndDesc[1]) - 1;
                System.out.println("Noted. I've removed this task:\n" +
                        String.format("\t%s", taskArrayList.get(indexToDelete)));
                taskArrayList.remove(indexToDelete);
                listStatus(taskArrayList);
                break;
            case "deadline" :
                if (cmdAndDesc.length == 1) {
                    throw new DukeException("☹ OOPS!!! " +
                            "The description of deadline cannot be empty.");
                }
                String[] descAndDue = cmdAndDesc[1].split("/by ");
                if (descAndDue.length == 1) {
                    throw new DukeException("☹ OOPS!!! " +
                            "There is no specified date/time for the deadline.");
                }
                taskAddition();
                taskArrayList.add(new Deadline(descAndDue[0], descAndDue[1]));
                System.out.println(String.format("\t%s",
                        taskArrayList.get(taskArrayList.size() - 1)));
                listStatus(taskArrayList);
                break;
            case "event" :
                if (cmdAndDesc.length == 1) {
                    throw new DukeException("☹ OOPS!!! " +
                            "The description of event cannot be empty.");
                }
                String[] descAndDuration = cmdAndDesc[1].split("/at ");
                if (descAndDuration.length == 1) {
                    throw new DukeException("☹ OOPS!!! " +
                            "There is no specified time duration for the event.");
                }
                taskAddition();
                taskArrayList.add(new Event(descAndDuration[0], descAndDuration[1]));
                System.out.println(String.format("\t%s",
                        taskArrayList.get(taskArrayList.size() - 1)));
                listStatus(taskArrayList);
                break;
            case "todo" :
                if (cmdAndDesc.length == 1) {
                    throw new DukeException("☹ OOPS!!! " +
                            "The description of todo cannot be empty.");
                }
                taskAddition();
                taskArrayList.add(new ToDo(cmdAndDesc[1]));
                System.out.println(String.format("\t%s",
                        taskArrayList.get(taskArrayList.size() - 1)));
                listStatus(taskArrayList);
                break;
            default:
                throw new DukeException("☹ OOPS!!! " +
                        "I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns string representation of list of tasks.
     * @param list Arraylist of tasks as inputted by the user
     */
    private static void taskDisplay(ArrayList<Task> list) {
        if (list.size() == 0) {
            System.out.println("There are currently no tasks in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, list.get(i)));
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
     * Prints current status of arraylist.
     * @param list Arraylist
     */
    private static void listStatus(ArrayList<Task> list) {
        System.out.println(String.format("Now you have %d tasks in the list.",
                list.size()));
    }
}
