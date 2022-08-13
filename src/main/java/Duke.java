import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "\t____________________________________________________________";

    private static void run() {
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isExited = false;
        String command = "";
        Scanner sc = new Scanner(System.in);

        System.out.println(LOGO
                + LINE
                + "\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + LINE);

        while (!isExited) {
            command = sc.next();
            int taskNumber;
            String[] splitStringArray;
            Task t;
            switch (command) {
                case "bye":
                    isExited = true;
                    System.out.println(LINE
                            + "\n\tBye. Hope to see you again soon!\n"
                            + LINE);
                    break;
                case "list":
                    System.out.println(LINE);
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("\t%d.%s\n", i + 1, tasks.get(i));
                    }
                    System.out.println(LINE);
                    break;
                case "mark":
                    taskNumber = sc.nextInt();
                    t = tasks.get(taskNumber - 1);
                    t.markAsDone();
                    System.out.println(LINE
                            + "\n\t"
                            + "Nice! I've marked this task as done:\n\t  "
                            + t);
                    break;
                case "unmark":
                    taskNumber = sc.nextInt();
                    t = tasks.get(taskNumber - 1);
                    t.markAsUndone();
                    System.out.println(LINE
                            + "\n\t"
                            + "OK, I've marked this task as not done yet:\n\t  "
                            + t);
                    break;
                case "todo":
                    command = sc.nextLine();
                    ToDo td = new ToDo(command);
                    tasks.add(td);
                    System.out.println(LINE
                            + "\n\t"
                            + "Got it. I've added this task:\n\t  "
                            + td
                            + "\n\tNow you have "
                            + tasks.size()
                            + " tasks in the list.\n"
                            + LINE);
                    break;
                case "deadline":
                    command = sc.nextLine();
                    splitStringArray = command.split(" /by ");
                    Deadline dl = new Deadline(splitStringArray[0], splitStringArray[1]);
                    tasks.add(dl);
                    System.out.println(LINE
                            + "\n\t"
                            + "Got it. I've added this task:\n\t  "
                            + dl
                            + "\n\tNow you have "
                            + tasks.size()
                            + " tasks in the list.\n"
                            + LINE);
                    break;
                case "event":
                    command = sc.nextLine();
                    splitStringArray = command.split(" /at ");
                    Event e = new Event(splitStringArray[0], splitStringArray[1]);
                    tasks.add(e);
                    System.out.println(LINE
                            + "\n\t"
                            + "Got it. I've added this task:\n\t  "
                            + e
                            + "\n\tNow you have "
                            + tasks.size()
                            + " tasks in the list.\n"
                            + LINE);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Duke.run();
    }
}
