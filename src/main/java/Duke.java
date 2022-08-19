import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        List<Task> list = new ArrayList<>(); // to store list of inputs

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMsg = "Hello! I'm\n" + logo + "\nWhat can I do for you?\n";
        System.out.println(welcomeMsg);

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (true) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                taskPrinter(list);
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.replaceAll("mark","")
                        .trim()) - 1;
                list.get(index).Done();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(index).toString());
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.replaceAll("unmark", "")
                        .trim()) - 1;
                list.get(index).unDone();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(list.get(index).toString());
            } else {
                Task task;
                if (input.startsWith("todo")) {
                    task = new ToDo(input.replaceAll("todo","").trim());
                } else if (input.startsWith("event")) {
                    String[] arr = input.split("/");
                    // check if split into 2 strings only?
                    task = new Event(arr[0].replaceAll("event","").trim(), arr[1].replaceAll("at","")
                            .trim());
                } else  {
                    input.replaceAll("deadline","").trim();
                    String[] arr = input.split("/");
                    task = new Deadline(arr[0].replaceAll("deadline","").trim(), arr[1].replaceAll("by","")
                            .trim());
                }
                list.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + task.toString());
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
            }
        }
    }

    static void taskPrinter(List<Task> list) {
        String out = "";
        int num = 1;
        for (Task x : list) {
            out += num + ". " + x.toString() + "\n";
            num++;
        }
        System.out.println(out);
    }

}
