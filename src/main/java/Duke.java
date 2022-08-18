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
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                taskDisplay(taskList, count[0]);
            } else {
                checkTask(input, taskList, count);
            }
        }
    }

    /**
     * Checks input of user and react accordingly
     * @param input String input of the user
     * @param list Array which stores tasks added by user
     * @param index Current index pointer of last element in array
     */
    private static void checkTask(String input, Task[] list, int[] index) {
        String[] cmdAndDesc = input.split(" ", 2);
        String command = cmdAndDesc[0];

        switch (command) {
            case "mark":
                int indexToMark = Integer.parseInt(cmdAndDesc[1]) - 1;
                list[indexToMark].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        String.format("%s", list[indexToMark]));
                break;
            case "unmark":
                int indexToUnmark = Integer.parseInt(cmdAndDesc[1]) - 1;
                list[indexToUnmark].markAsUndone();
                System.out.println("Ok! I've marked this task as not done yet:\n" +
                        String.format("%s", list[indexToUnmark]));
                break;
            case "deadline" :
                String[] descAndDue = cmdAndDesc[1].split("/by ");
                taskAddition();
                list[index[0]] = new Deadline(descAndDue[0], descAndDue[1]);
                System.out.println(String.format("\t%s", list[index[0]]));
                index[0]++;
                listStatus(index[0]);
                break;
            case "event" :
                String[] descAndDuration = cmdAndDesc[1].split("/at ");
                taskAddition();
                list[index[0]] = new Event(descAndDuration[0], descAndDuration[1]);
                System.out.println(String.format("\t%s", list[index[0]]));
                index[0]++;
                listStatus(index[0]);
                break;
            case "todo" :
                taskAddition();
                list[index[0]] = new ToDo(cmdAndDesc[1]);
                System.out.println(String.format("\t%s", list[index[0]]));
                index[0]++;
                listStatus(index[0]);
            default:
        }
    }

    /**
     * Returns string representation of list of tasks
     * @param list Array of tasks as inputted by the user
     * @param count Total number of tasks
     */
    private static void taskDisplay(Task[] list, int count) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println(String.format("%d. %s", i + 1, list[i]));
        }
    }

    private static void taskAddition() {
        System.out.println("Got it. I've added this task:");
    }

    private static void listStatus(int count) {
        System.out.println(String.format("Now you have %d tasks in the list.",
                count));
    }
}
