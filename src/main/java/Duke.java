import java.util.Scanner;

public class Duke {

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

        public String getDescription() {
            return this.description;
        }
        public void flip() {
            this.isDone = !isDone;
        }

        public void markDone() {
            if (!this.isDone) {
                this.flip();
            }
        }

        public void markUndone() {
            if (this.isDone) {
                this.flip();
            }
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }


    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static void chat() {
        Scanner myScan = new Scanner(System.in);
        String s;
        Task[] list = new Task[100];
        int list_counter = 0;

        while (true) {
            s = myScan.nextLine();
            if (s.equals("bye")) {
                System.out.println("----------------------");
                System.out.println("Bye, hope to see you again!");
                System.out.println("----------------------");
                break;
            } else if (s.equals("list")) {
                System.out.println("----------------------");
                for (int i = 0; i < 100; i++) {
                    if (list[i] != null) {
                        String display = String.format("%d.%s", i + 1, list[i].toString());
                        System.out.println(display);
                    }
                }
                System.out.println("----------------------");
            } else if (s.indexOf("mark") == 0) {
                String subString = s.substring(5, s.length());
                int a = Integer.parseInt(subString) - 1;
                list[a].markDone();

                System.out.println("----------------------");
                System.out.println("Ok! I've marked this task as done");
                System.out.println(list[a].toString());
                System.out.println("----------------------");

            } else if (s.indexOf("unmark") == 0) {
                String subString = s.substring(7, s.length());
                int a = Integer.parseInt(subString) - 1;
                list[a].markUndone();

                System.out.println("----------------------");
                System.out.println("Ok! I've marked this task as undone");
                System.out.println(list[a].toString());
                System.out.println("----------------------");

            } else if (s.indexOf("todo") == 0) {
                String subString = s.substring(5, s.length());
                list[list_counter] = new Todo(subString);
                list_counter += 1;
                System.out.println("----------------------");
                System.out.println("added: " + list[list_counter - 1].toString());
                System.out.println(String.format("Now you have %d tasks in the list", list_counter));
                System.out.println("----------------------");

            } else if (s.indexOf("deadline") == 0 ) {
                String descript = s.substring(9, s.indexOf("/") - 1);
                String by = s.substring(s.indexOf("/") + 4);
                list[list_counter] = new Deadline(descript, by);
                list_counter += 1;
                System.out.println("----------------------");
                System.out.println("added: " + list[list_counter - 1].toString());
                System.out.println(String.format("Now you have %d tasks in the list", list_counter));
                System.out.println("----------------------");

            } else if (s.indexOf("event") == 0 ) {
                String descript = s.substring(6, s.indexOf("/") - 1);
                String at = s.substring(s.indexOf("/") + 4);
                list[list_counter] = new Event(descript, at);
                list_counter += 1;
                System.out.println("----------------------");
                System.out.println("added: " + list[list_counter - 1].toString());
                System.out.println(String.format("Now you have %d tasks in the list", list_counter));
                System.out.println("----------------------");

            } else {
                list[list_counter] = new Task(s);
                list_counter += 1;
                System.out.println("----------------------");
                System.out.println("added: " + s);
                System.out.println("----------------------");
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("----------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        System.out.println("----------------------");

        chat();
    }
}
