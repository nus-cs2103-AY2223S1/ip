import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Collections;

public class Duke {
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);   // Create Scanner object
        ArrayList<Task> taskArrayList = new ArrayList<>();

        String intro = "Hello! My name is GustavoBot, but you can call me Gus\n"
                + "How may I help you today?";

        System.out.println(intro);

        while (true) {
            String act = scanObj.nextLine();            // read user input

            if (act.equals("list")) {
                ListIterator<Task> iterate = taskArrayList.listIterator();
                System.out.println("Here are the tasks in your list:");
                int qty = 0;
                while (iterate.hasNext()) {
                    qty++;
                    System.out.println(qty + "." + iterate.next().toString());
                }
            } else if (act.contains("delete")) {
                String numberOnly = act.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(numberOnly);
                Task deleted = taskArrayList.get(index-1);
                taskArrayList.remove(index-1);
                System.out.println("Noted. I've removed this task:\n"
                        + deleted.toString() + "\n"
                        + "Now you have " + taskArrayList.size() + " tasks in the list");

            } else if (act.contains("unmark")) {
                String numberOnly = act.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(numberOnly);
                taskArrayList.get(index-1).unmarkDone();
                System.out.println("Alright, this task is marked as not done yet:\n"
                        + taskArrayList.get(index-1).toString());

            } else if (act.contains("mark")) {
                String numberOnly = act.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(numberOnly);
                taskArrayList.get(index-1).markDone();
                System.out.println("Alright, this task is marked as done:\n"
                        + taskArrayList.get(index-1).toString());

            } else if (act.contains("todo")) {
                act = act.replace("todo", "");
                try {
                    ToDo temp = new ToDo(act);
                    taskArrayList.add(temp);
                    System.out.println("Got it. I've added this task:\n"
                            + temp.toString() + "\n"
                            + "Now you have " + taskArrayList.size() + "tasks in the list.");
                } catch (EmptyDescException ede) {
                    System.out.println(ede.toString());
                }

            } else if (act.contains("deadline")) {
                act = act.replace("deadline", "");
                try {
                    int slash = act.lastIndexOf("/");
                    Deadline temp = new Deadline(act.substring(0, slash - 1), act.substring(slash + 4));
                    taskArrayList.add(temp);
                    System.out.println("Got it. I've added this task:\n"
                            + temp.toString() + "\n"
                            + "Now you have " + taskArrayList.size() + "tasks in the list.");
                } catch (EmptyDescException ede) {
                    System.out.println(ede.toString());
                }

            } else if (act.contains("event")) {
                act = act.replace("event", "");
                try {
                    int slash = act.lastIndexOf("/");
                    Event temp = new Event(act.substring(0, slash - 1), act.substring(slash + 4));
                    taskArrayList.add(temp);
                    System.out.println("Got it. I've added this task:\n"
                            + temp.toString() + "\n"
                            + "Now you have " + taskArrayList.size() + "tasks in the list.");
                } catch (EmptyDescException ede) {
                    System.out.println(ede.toString());
                }

            } else if (act.equals("bye")) {
                System.out.println("Goodbye, hope to see you again soon!");
                break;

            } else {
                UnknownInputException uie = new UnknownInputException("?");
                System.out.println(uie.toString());
            }
        }
    }
}
