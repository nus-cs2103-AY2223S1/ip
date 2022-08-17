import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);   // Create Scanner object
        Task[] taskLst = new Task[100];
        int qty = 0;

        String intro = "Hello! My name is GustavoBot, but you can call me Gus\n"
                + "How may I help you today?";

        System.out.println(intro);

        while (true) {
            String act = scanObj.nextLine();            // read user input

            if (act.equals("list")) {
                for (int i = 0; i < 100; i++) {
                    if (taskLst[i] == null) {
                        break;
                    }
                    int index = i + 1;
                    System.out.println(index + "." + taskLst[i].toString());
                }
            } else if (act.contains("unmark")) {
                String numberOnly = act.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(numberOnly);
                taskLst[index - 1].unmarkDone();
                System.out.println("Alright, this task is marked as not done yet:\n"
                        + taskLst[index - 1].toString());

            } else if (act.contains("mark")) {
                String numberOnly = act.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(numberOnly);
                taskLst[index - 1].markDone();
                System.out.println("Alright, this task is marked as done:\n"
                        + taskLst[index - 1].toString());

            } else if (act.contains("todo")) {
                act = act.replace("todo ", "");
                ToDo temp = new ToDo(act);
                taskLst[qty] = temp;
                qty++;
                System.out.println("Got it. I've added this task:\n"
                        + temp.toString() + "\n"
                        + "Now you have " + qty + " in the list.");

            } else if (act.contains("deadline")) {
                act = act.replace("deadline ", "");
                int slash = act.lastIndexOf("/");
                Deadline temp = new Deadline(act.substring(0, slash - 1), act.substring(slash + 4));
                taskLst[qty] = temp;
                qty++;
                System.out.println("Got it. I've added this task:\n"
                        + temp.toString() + "\n"
                        + "Now you have " + qty + " in the list.");

            } else if (act.contains("event")) {
                act = act.replace("event ", "");
                int slash = act.lastIndexOf("/");
                Event temp = new Event(act.substring(0, slash - 1), act.substring(slash + 4));
                taskLst[qty] = temp;
                qty++;
                System.out.println("Got it. I've added this task:\n"
                        + temp.toString() + "\n"
                        + "Now you have " + qty + " in the list.");

            } else if (act.equals("bye")) {
                System.out.println("Goodbye, hope to see you again soon!");
                break;

            } else {
                qty++;
                for (int i = 0; i < 100; i++) {
                    if (taskLst[i] == null) {
                        taskLst[i] = new Task(act);
                        break;
                    }
                }
                System.out.println("added: " + act);
            }
        }
    }
}
