import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Greets User
        System.out.println("Hey there! I'm Tutter! \nHow can I help?");

        // Receive Input
        Scanner sc = new Scanner(System.in);
        String input = "";
        Task[] taskList = new Task[100];
        int pointer = 0;
        while (true) {
            input = sc.nextLine();
            String[] inputTokens = input.split(" ");

            // Exits Loop
            if (input.equals("bye")) {
                break;
            }

            // Handles marking tasks as done or not done
            if (inputTokens[0].equals("mark") || inputTokens[0].equals("unmark")) {
                int index;
                try {
                    index = Integer.parseInt(inputTokens[1]);
                    Task task = taskList[index - 1];

                    // Mark as done or not done
                    if (inputTokens[0].equals("mark")) {
                        task.markAsDone();
                        String taskListString = String.format("\tGood Job! The following task " +
                                "has been marked as done:\n\t[%s] %s", task.getStatusIcon(), task.toString());
                        System.out.println(taskListString);
                    } else {
                        task.markAsNotdone();
                        String taskListString = String.format("\tOkay! The following task " +
                                "has been marked as not done:\n\t[%s] %s", task.getStatusIcon(), task.toString());
                        System.out.println(taskListString);
                    }
                }
                catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("\tSorry, that Task Number doesn't look right...");
                }
                finally {
                    continue;
                }
            }

            // Outputs Task List
            if (input.equals("list")) {
                int i = 1;
                for (Task t : taskList) {
                    // Reach end of list
                    if (t == null) {
                        break;
                    }

                    String taskListString = String.format("\t%d. [%s] %s", i, t.getStatusIcon(), t.toString());
                    System.out.println(taskListString);
                    i++;
                }
                continue;
            }

            if (input.contains("mark")) {

            }

            // Echo
            String output = String.format("\tYou have added \"%s\" into your task list!", input);
            System.out.println(output);

            // Add into task list
            taskList[pointer] = new Task(input);
            pointer++;
        }

        // Exits Program
        System.out.println("\tGoodbye!");
    }
}


