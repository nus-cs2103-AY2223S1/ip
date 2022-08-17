import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        final int MAXSIZE = 100;
        Task[] storedTasks = new Task[MAXSIZE];
        int index = 0;
        while (true) {
            String str = sc.nextLine();
            if ("bye".equals(str)) {
                System.out.println("Bye! Hope to see you again soon");
                break;
            } else if ("list".equals(str)) {
                int p = 0;
                while (p < MAXSIZE && storedTasks[p] != null) {
                    System.out.printf("%d. %s\n", p + 1, storedTasks[p]);
                    p++;
                }
            } else if(str.length() > 5 && str.substring(0, 5).equals("mark ")) {
                String[] s = str.split(" ");
                if (s.length > 1) {
                    int i = Integer.parseInt(s[1]) - 1;
                    storedTasks[i].markAsDone();
                    System.out.println("Marked task " + (i + 1) + " as done!");
                    System.out.printf("%d. %s\n", i + 1, storedTasks[i]);
                }
            } else if(str.length() > 7 && str.substring(0, 7).equals("unmark ")) {
                String[] s = str.split(" ");
                if (s.length > 1) {
                    int i = Integer.parseInt(s[1]) - 1;
                    storedTasks[i].markAsNotDone();
                    System.out.println("Marked task " + (i + 1) + " as not done!");
                    System.out.printf("%d. %s\n", i + 1, storedTasks[i]);
                }
            } else if(str.length() > 5 && str.substring(0, 5).equals("todo ")) {
                str = str.substring(5);
                Task task = new Todo(str);
                storedTasks[index++] = task;
                System.out.printf("Got it! I stored this task:\n" + task + "\nNow you have %d tasks in the list.\n", index);
            } else if(str.length() > 9 && str.substring(0, 9).equals("deadline ")) {
                str = str.substring(9);
                String[] s = str.split("/by ");
                Task task = new Deadline(s[0], s[1]);
                storedTasks[index++] = task;
                System.out.printf("Got it! I stored this task:\n" + task + "\nNow you have %d tasks in the list.\n", index);
            } else if(str.length() > 6 && str.substring(0, 6).equals("event ")) {
                str = str.substring(6);
                String[] s = str.split("/at ");
                Task task = new Event(s[0], s[1]);
                storedTasks[index++] = task;
                System.out.printf("Got it! I stored this task:\n" + task + "\nNow you have %d tasks in the list.\n", index);
            } else {
//                storedTasks[index++] = new Task(str);
//                System.out.println("Added: " + str);
                System.out.println("Error in entering new entry: Unrecognised task type.");
            }
        }
    }
}

