import java.util.Scanner;
public class Duke {
    private static class Task {
        private boolean completed;
        private String name;
        private int count;

        public Task(String name, int count) {
            this.name = name;
            this.count = count;
            this.completed = false;
        }

        public void mark() {
            this.completed = true;
        }

        public void unmark() {
            this.completed = false;
        }

        @Override
        public String toString() {
            String comp = this.completed
                    ? "[X]"
                    : "[ ]";
            return String.format("%d." + comp + " " + name, count);
        }

        public String toStr() {
            String comp = this.completed
                    ? "[X]"
                    : "[ ]";
            return String.format(comp + " " + name);
        }
    }

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

         */

        Task[] arr = new Task[100];
        int count = 0;
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String item = sc.nextLine();
            if (item.equals("bye")) {
                break;
            } else if (item.equals("list")) {
                for (int i =0; i < count; i++) {
                    System.out.println(arr[i].toString());
                }
            } else if (item.startsWith("mark")) {
                String str = item.replace("mark ", "");
                Integer index = Integer.valueOf(str);
                arr[index - 1].mark();
                System.out.println("Nice! I've marked this task as done:\n" + arr[index - 1].toStr());
            } else if (item.startsWith("unmark")) {
                String str = item.replace("unmark ", "");
                Integer index = Integer.valueOf(str);
                arr[index - 1].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + arr[index - 1].toStr());
            } else {
                arr[count] = new Task(item, count+1);
                count++;
                System.out.println("Added: " + item);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
