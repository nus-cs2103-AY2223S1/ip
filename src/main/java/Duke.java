import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        String nextInstruction;
        String introduction = "Hello! I'm Jarvis \n"
                            + "What can I do for you?";
        String farewell = "Bye. Hope to see you again soon!";
        int curr = 0;

        String[] taskList = new String[100];

        System.out.println(introduction);

        while (true) {
            nextInstruction = inputScanner.nextLine();

            if (nextInstruction.equals("bye")) {
                break;
            }

            if (nextInstruction.equals("list")) {
                for (int i = 0; i < taskList.length; i ++) {
                    if (taskList[i] == null) {
                        break;
                    }
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
                continue;
            }

            taskList[curr] = nextInstruction;
            curr++;

            System.out.println("added: " + nextInstruction);

        }

        System.out.println(farewell);


    }
}
