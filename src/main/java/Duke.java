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

            // Exits Loop
            if (input.equals("bye")) {
                break;
            }

            // Outputs Task List
            if (input.equals("list")) {
                int i = 1;
                for (Task t : taskList) {
                    // Reach end of list
                    if (t == null) {
                        break;
                    }

                    String taskListString = String.format("\t%d. %s", i, t.toString());
                    System.out.println(taskListString);
                    i++;
                }
                continue;
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
