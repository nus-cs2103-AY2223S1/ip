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
                list.add(new Task(input));
                System.out.println("added: " + input);
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
