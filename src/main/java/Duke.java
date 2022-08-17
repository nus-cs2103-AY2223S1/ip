import java.util.Scanner;

public class Duke {
    private static abstract class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (this.isDone ? "[X]" : "[ ]");
        }

        @Override
        public String toString() {
            return this.getStatusIcon() + " " + description;
        }

        public void setDone() {
            this.isDone = true;
        }
        public void setNotDone() {
            this.isDone = false;
        }
    }

    private static class Deadline extends Task {
        protected String deadline;

        public Deadline(String description, String deadline) {
            super(description);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by:" + deadline + ")";
        }

    }

    private static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Event extends Task {
        protected String time;

        public Event(String description, String time) {
            super(description);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + "(at:" + time + ")";
        }
    }

    //  initialise task list and counter
    private static Task[] list = new Task[100];
    private static int counter = 0;

    // Methods Start
    public static void addTask(Task task) {
        list[counter] = task;
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %s task(s) in the list", counter));
    }

    public static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int x = 0; x < counter; x++) {
            System.out.println(String.format("%s.%s", x+1, list[x].toString()));
        }
    }

    public static void mark(int index) {
        //  error checking
        if (index < 0 || list[index] == null) {
            System.out.println("Invalid task!");
        } else {
        list[index].setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("%s.%s", index+1, list[index].toString()));
        }
    }

    public static void unmark(int index) {
        //  error checking
        if (index < 0 || list[index] == null) {
            System.out.println("Invalid task!");
        } else {
        list[index].setNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format("%s.%s", index+1, list[index].toString()));
        }
    }

    // Methods End


    public static void main(String[] args) {
        //  Opening statements
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // initialise scanner
        Scanner sc = new Scanner(System.in);

        //  program start
        while (true) {
            //  User Input
            String str = sc.nextLine();
            //  Bye
            if (str.equals("bye")) {
                sayBye();
                break;
            }
            //  List
            if (str.equals("list")) {
                list();
                continue;
            }
            //  Marking
            if ((str.length() >= 6) && (str.substring(0, 4).equals("mark"))) {
                String remainder = str.substring(5);
                int index = Integer.valueOf(remainder) - 1;
                mark(index);
                continue;
            }
            //  Unmarking
            if ((str.length() >= 8) && (str.substring(0, 6).equals("unmark"))) {
                String remainder = str.substring(7);
                int index = Integer.valueOf(remainder) - 1;
                unmark(index);
                continue;
            }
            //  Add Todo Task
            if (str.length() >= 4 && str.substring(0, 4).equals("todo")) {
                String remainder = str.substring(5);
                addTask(new ToDo(remainder));
            }
            //  Add Deadline Task
            if (str.length() >= 8 && str.substring(0, 8).equals("deadline")) {
                String remainder = str.substring(9);
                String[] arr = remainder.split("/by");
                String description = arr[0];
                String deadline = arr[1];
                addTask(new Deadline(description, deadline));
            }
            //  Add Event Task
            if (str.length() >= 5 && str.substring(0, 5).equals("event")) {
                String remainder = str.substring(6);
                String[] arr = remainder.split("/at");
                String description = arr[0];
                String time = arr[1];
                addTask(new Event(description, time));
            }
        }
    }
}
