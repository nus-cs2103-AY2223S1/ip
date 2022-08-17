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

    }

    public static void add_list() {
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
                        String display = String.format("%d.[%s] %s", i + 1, list[i].getStatusIcon(),
                                list[i].getDescription());
                        System.out.println(display);
                    }
                }
                System.out.println("----------------------");
            } else if (s.indexOf("mark") == 0) {
                String subString = s.substring(5, s.length());
                int a = Integer.parseInt(subString) - 1;
                list[a].markDone();

                String display = String.format("[%s] %s", list[a].getStatusIcon(), list[a].getDescription());
                System.out.println("----------------------");
                System.out.println("Ok! I've marked this task as done");
                System.out.println(display);
                System.out.println("----------------------");

            } else if (s.indexOf("unmark") == 0) {
                String subString = s.substring(7, s.length());
                int a = Integer.parseInt(subString) - 1;
                list[a].markUndone();

                String display = String.format("[%s] %s", list[a].getStatusIcon(), list[a].getDescription());
                System.out.println("----------------------");
                System.out.println("Ok! I've marked this task as undone");
                System.out.println(display);
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

        add_list();
    }
}
