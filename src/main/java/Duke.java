import java.util.Scanner;

public class Duke {
    private static Task[] all = new Task[100];
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I am Duke");
        System.out.println("What can I do for you?");

        String s = "";
        while (true) {
            s = sc.nextLine();
            if (s.equals("bye")) {
                bye();
                break;
            } else if (s.equals("list")) {
                displayList();
            } else if (startWith(s, "mark")) {
                int index = Integer.parseInt(extractIndex(s, "mark"));
                //System.out.println(index);
                all[index - 1].markAsDone();
            } else if (startWith(s, "unmark")) {
                int index = Integer.parseInt(extractIndex(s, "unmark"));
                //System.out.println(index);
                all[index - 1].markAsUndone();
            } else {
                store(s);
            }
        }
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static  void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.printf("%s. %s\n", i + 1, all[i].toString());
        }
    }

    private static void store(String s) {
        all[count] = new Task(s);
        System.out.printf("added: %s\n", s);
        count += 1;
    }

    private static boolean startWith(String s, String target) {
        for (int i = 0; i < target.length(); i++) {
            if (s.charAt(i) != target.charAt(i)) return false;
        }

        try {
            int val = Integer.parseInt(s.substring(target.length() + 1));
            return true;
        } catch (NumberFormatException e) {
            //System.out.println("Input String cannot be parsed to Integer.");
            return false;
        }

    }

    private static String extractIndex(String s, String target) {
        return s.substring(target.length() + 1);
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String toString() {
            return String.format("[%s] %s", getStatusIcon(), description);
        }

        public void markAsDone() {
            isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.toString());
        }

        public void markAsUndone() {
            isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.toString());
        }
    }
}
