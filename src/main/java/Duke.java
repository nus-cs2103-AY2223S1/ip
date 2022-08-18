import java.util.Scanner;
public class Duke {
    private static class Task {
        protected boolean completed;
        protected String name;
        protected int count;

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
            return comp + " " + name;
        }
    }

    private static class Todo extends Task {
        private static final String type = "[T]";

        public Todo(String name, int count) {
            super(name, count);
        }

        @Override
        public String toString() {
            String comp = this.completed
                    ? "[X]"
                    : "[ ]";
            return String.format("%d." + type + comp + " " + name, count);
        }

        @Override
        public String toStr() {
            String comp = this.completed
                    ? "[X]"
                    : "[ ]";
            return type + comp + " " + name;
        }
    }

    private static class Deadline extends Task {
        private static final String type = "[D]";
        private String date;

        public Deadline(String name, int count, String date) {
            super(name, count);
            this.date = "(by: " + date + ")";
        }

        @Override
        public String toString() {
            String comp = this.completed
                    ? "[X]"
                    : "[ ]";
            return String.format("%d." + type + comp + " " + name + date, count);
        }

        @Override
        public String toStr() {
            String comp = this.completed
                    ? "[X]"
                    : "[ ]";
            return type + comp + " " + name + date;
        }
    }

    private static class Event extends Task {
        private static final String type = "[E]";
        private String time;

        public Event(String name, int count, String time) {
            super(name, count);
            this.time = "(at: " + time + ")";
        }

        @Override
        public String toString() {
            String comp = this.completed
                    ? "[X]"
                    : "[ ]";
            return String.format("%d." + type + comp + " " + name + time, count);
        }

        @Override
        public String toStr() {
            String comp = this.completed
                    ? "[X]"
                    : "[ ]";
            return type + comp + " " + name + time;
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
                int index = Integer.valueOf(str);
                arr[index - 1].mark();
                System.out.println("Nice! I've marked this task as done:\n" + arr[index - 1].toStr());
            } else if (item.startsWith("unmark")) {
                String str = item.replace("unmark ", "");
                int index = Integer.valueOf(str);
                arr[index - 1].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + arr[index - 1].toStr());
            } else if (item.startsWith("todo")) {
                arr[count] = new Todo(item, count+1);
                count++;
                System.out.println(String.format("Got it. I've added this task:\n" +
                        "\t%s\n" +
                        "Now you have %d tasks in the list.",
                        arr[count - 1].toStr(),
                        count));
            } else if (item.startsWith("deadline")) {
                String[] input = item.split("/by ");
                String name = input[0].replace("deadline ", "");
                arr[count] = new Deadline(name, count + 1, input[1]);
                count++;
                System.out.println(String.format("Got it. I've added this task:\n" +
                                "\t%s\n" +
                                "Now you have %d tasks in the list.",
                        arr[count - 1].toStr(),
                        count));
            } else if (item.startsWith("event")) {
                String[] input = item.split("/at ");
                String name = input[0].replace("event ", "");
                arr[count] = new Event(name, count + 1, input[1]);
                count++;
                System.out.println(String.format("Got it. I've added this task:\n" +
                                "\t%s\n" +
                                "Now you have %d tasks in the list.",
                        arr[count - 1].toStr(),
                        count));
            }
            else {
                arr[count] = new Task(item, count+1);
                count++;
                System.out.println("Added: " + item);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
