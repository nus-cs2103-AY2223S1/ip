import java.util.*;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        // create scanner to receive user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Kuke\n" +
                "What can I do for you?");
        String a = sc.nextLine();
        String[] arr = new String[100];
        int[] status = new int[100];
        String[] taskType = new String[100];
        int pos = 0;

        // if input received is anything but "bye" system will output what the user
        // inputted
        while (!a.equals("bye")) {
            if (a.equals("list")) {
                //lists out all elements in task list
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= pos; i++) {
                    if (status[i - 1] == 0) {
                        System.out.println(i + ".[" + taskType[i - 1] + "][ ] " + arr[i - 1]);
                    } else {
                        System.out.println(i + ".[" + taskType[i - 1] + "][X] " + arr[i - 1]);
                    }
                }
                a = sc.nextLine();
            } else if (a.contains("unmark")) {
                // if unmark, update status
                char b = a.charAt(7);
                int c = Character.getNumericValue(b);
                status[c - 1] = 0;
//                System.out.println(status[c - 1]);
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("[" + taskType[c - 1] + "][ ] " + arr[c - 1]);
                a = sc.nextLine();
            } else if (a.contains("mark")) {
                char b = a.charAt(5);
                int c = Character.getNumericValue(b);
//                System.out.println(b);
//                System.out.println(c);
                status[c - 1] = 1;
//                System.out.println(status[c - 1]);
//                System.out.println(c - 1);
//                System.out.println(status[0]);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + taskType[c - 1] + "][X] " + arr[c - 1]);
                a = sc.nextLine();
            } else if (a.contains("delete")) {
                char b = a.charAt(7);
                int c = Character.getNumericValue(b);
                int numberTasksLeft = pos - 1;
                String deletedTask = arr[c - 1];

                if (status[c - 1] == 0) {
                    System.out.println("Noted. I've removed this task:\n" +
                            "[" + taskType[c - 1] + "][ ] " + deletedTask + "\n" +
                            "Now you have " + (numberTasksLeft) + " tasks in the list.");
                } else {
                    System.out.println("Noted. I've removed this task:\n" +
                            "[" + taskType[c - 1] + "][X] " + deletedTask + "\n" +
                            "Now you have " + (numberTasksLeft) + " tasks in the list.");
                }

                pos--;
                for (int i = (c - 1); i <= numberTasksLeft; i++) {
                    arr[i] = arr[i + 1];
                    status[i] = status[i + 1];
                    taskType[i] = taskType[i + 1];
                }

                a = sc.nextLine();
            } else if (a.contains("todo")) {
                try {
                    a.substring(5);
                } catch (Exception StringIndexOutOfBoundsException) {
                    DukeException.todoException();
                    a = sc.nextLine();
                }
                String task = a.substring(5);
                arr[pos] = task;
                status[pos] = 0;
                taskType[pos] = "T";
                pos++;
                System.out.println("Got it. I've added this task:\n" +
                        "[T][ ] " + task + "\n" +
                        "Now you have " + pos + " tasks in the list.");
                a = sc.nextLine();
            } else if (a.contains("deadline")) {
                String task = a.substring(9, a.lastIndexOf("/") - 1);
                String day = a.substring(a.lastIndexOf("/by") + 4);
//                System.out.println(task);
//                System.out.println(day);
                String overall = task + " (by: " + day + ")";
//                System.out.println(overall);
                arr[pos] = overall;
                status[pos] = 0;
                taskType[pos] = "D";
                pos++;
                System.out.println("Got it. I've added this task:\n" +
                        "[D][ ] " + overall + "\n" +
                        "Now you have " + pos + " tasks in the list.");
                a = sc.nextLine();
            } else if (a.contains("event")) {
                String task = a.substring(6, a.lastIndexOf("/") - 1);
                String time = a.substring(a.lastIndexOf("/at") + 4);
//                System.out.println(time);
                String overall = task + " (at: " + time + ")";
//                System.out.println(overall);
                arr[pos] = overall;
                status[pos] = 0;
                taskType[pos] = "E";
                pos++;
                System.out.println("Got it. I've added this task:\n" +
                        "[E][ ] " + overall + "\n" +
                        "Now you have " + pos + " tasks in the list.");
                a = sc.nextLine();
            } else {
                // else
//                arr[pos] = a;
//                status[pos] = 0;
//                pos++;
//                System.out.println("added: " + a);
//                a = sc.nextLine();
                DukeException.taskException();
                a = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}

