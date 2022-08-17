import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        String[] strArray = sc.nextLine().split(" ");
        String str = strArray[0];
        ArrayList<Task> arrayList = new ArrayList<>();

        while (!str.equals("bye")) {

            if (str.equals("list")) {
                for (int i = 0; i < arrayList.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + "." + arrayList.get(i));
                }
            } else if (str.equals("mark")) {
                int i = Integer.parseInt(strArray[1]) - 1;
                arrayList.get(i).markDone();

                System.out.println("Nice! You actually did something!:");
                System.out.println(arrayList.get(i));
            } else if (str.equals("unmark")) {
                int i = Integer.parseInt(strArray[1]) - 1;
                arrayList.get(i).markNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(arrayList.get(i));
            } else if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {

                String taskname = "";
                String time = "";
                int i = 1;

                while (i < strArray.length && strArray[i].charAt(0) != '/') {
                    taskname += strArray[i] + " ";
                    i++;
                }

                while (i < strArray.length) { //means now strArray[i] == /by or /at

                    if (strArray[i].charAt(0) != '/') {
                        time += strArray[i] + " ";
                    }

                    i++;
                }

                if (str.equals("todo")) {
                    Task todo = new Todo(taskname.trim());
                    arrayList.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);
                    System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");

                } else if (str.equals("deadline")) {
                    Task deadline = new Deadline(taskname.trim(), time.trim());
                    arrayList.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");

                } else {
                    Task event = new Event(taskname.trim(), time.trim());
                    arrayList.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
                }
            } else {
                System.out.println("Please enter a valid input");
            }

            strArray = sc.nextLine().split(" ");
            str = strArray[0];
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
