import java.util.Scanner;

public class Duke {
    static Scanner input = new Scanner(System.in);

    public static void responseRepeater() {
        String response = input.nextLine();
        Task[] taskStore = new Task[100];
        int counter = 0;

        while (!response.equals("bye")) {
            if (response.equals("list")) {
                System.out.println("     The following are your saved tasks: ");
                for (int i = 0; i < counter; i++) {
                    Task t = taskStore[i];
                    System.out.println("       "
                                      + (i + 1)
                                      + "."
                                      + t.toString());
                }
            } else if (response.length() > 4 && response.substring(0, 4).equals("mark")) {
                int taskNumber = Integer.parseInt(response.substring(5, 6)) - 1;
                Task t = taskStore[taskNumber];
                t.markAsDone();
            } else if (response.length() > 6 && response.substring(0, 6).equals("unmark")) {
                int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
                Task t = taskStore[taskNumber];
                t.markAsUnDone();
            } else {
                Task newTask = new Task(response);
                taskStore[counter] = newTask;
                counter += 1;
                System.out.println("     Task has been added: \n"
                                    + "       " + newTask.toString());
            }
            response = input.nextLine();
        }
        System.out.println("     Sad to see you go! Visit me again soon!");
    }

    public static void main(String[] args) {
        String greetings = "Good day to you! I'm Jake\n"
                            + "How can I help you?";
        System.out.println(greetings);
        responseRepeater();
    }
}
