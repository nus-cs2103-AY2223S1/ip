import java.util.Scanner;

public class Duke {

    /**
     * This method checks if a string typed can be a number or not
     * @param number
     * @return a boolean value indicating whether the string entered can be parsed as an integer
     */
    public static boolean isNumeric(String number) {
        if (number == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(number);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        // Create an Array to store the Tasks the user has entered of size 100
        Task[] taskArray = new Task[100];
        // Create a count to track the number of items the user has stored
        int count = 0;

        String userCommand = "";

        // Creates a scanner to accept input
        Scanner enterInput = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        userCommand = enterInput.nextLine();

        // When user does not type bye, ask for input and print out the input
        while (!userCommand.equals("bye")) {
            System.out.println("----------------------");
            // If user types list, output all the text being stored
            if (userCommand.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    Task currentTask = taskArray[i];
                    System.out.println(String.valueOf(i + 1) + "." + currentTask.toString());
                }
            } else {
                String[] words = userCommand.split(" ");
                if (words.length > 1 &&
                        (words[0].equals("mark") || words[0].equals("unmark")) && isNumeric(words[1])) {
                    // If user first word is marked or unmarked and second word can be a number, it is a mark or unmark
                    // Get the index of task to mark or unmark
                    // Get the task and mark it as done or not done depending on the user
                    int taskNumber = Integer.parseInt(words[1]);
                    Task currentTask = taskArray[taskNumber - 1];
                    if (words[0].equals("mark")) {
                        System.out.println("Nice! I've marked this task as done:");
                        currentTask.markAsDone();
                        System.out.println(currentTask.toString());
                    } else {
                        System.out.println("0K, I've marked this task as not done yet:");
                        currentTask.markNotDone();
                        System.out.println(currentTask.toString());
                    }
                } else {
                    // If first word is not marked or unmarked or user only typed one word, user is trying to add a task
                    taskArray[count] = new Task(userCommand);
                    count += 1;
                    System.out.println("added:" + userCommand);
                }

            }
            System.out.println("----------------------");
            userCommand = enterInput.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon ^^!");
    }
}
