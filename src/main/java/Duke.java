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
        String[] substrings = input.split(" ", 2);
        String command = substrings[0];

        switch (command) {
            case "mark":
                int indexToMark = Integer.parseInt(substrings[1]) - 1;
                list[indexToMark].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        String.format("%s", list[indexToMark]));
                break;
            case "unmark":
                int indexToUnmark = Integer.parseInt(substrings[1]) - 1;
                list[indexToUnmark].markAsUndone();
                System.out.println("Ok! I've marked this task as not done yet:\n" +
                        String.format("%s", list[indexToUnmark]));
                break;
            default:
                System.out.println("added: " + input);
                list[index[0]] = new Task(input);
                index[0]++;
        }
    }

    /**
     * Returns string representation of list of tasks
     * @param list Array of tasks as inputted by the user
     * @param count Total number of tasks
     */
    private static void taskDisplay(Task[] list, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(String.format("%d. %s", i + 1, list[i]));
        }
    }
}
