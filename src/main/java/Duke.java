import java.util.Scanner;

public class Duke {
    private String greeting = "Hello";
    private String bye = "Goodbye";
    private Task[] list = new Task[100];
    private int items = 0;

    public Duke() {
    }

    public void doGreeting() {
        System.out.println(this.greeting);
    }

    public void doBye() {
        System.out.println(this.bye);
    }

    public boolean isBye(String input) {
        return input.equals("bye");
    }

    public void showList() {
        System.out.println("List of tasks:");
        for (int i = 1; i < this.items + 1; i++) {
            System.out.println(i + ". " + this.list[i - 1]);
        }
    }

    public void markTask(int ind, boolean done) throws DukeException {
        if (ind > this.items - 1) {
            throw new DukeException("Oops, no such task found.");
        } else if (done) {
            this.list[ind].markDone();
            System.out.println("Task done: " + this.list[ind]);
        } else {
            this.list[ind].markNotDone();
            System.out.println("Task not done: " + this.list[ind]);
        }
    }

    public void addTask(String type, String desc) {
        Task t;
        if (type.equals("todo")) {
            t = new Todo(desc);
            this.list[this.items] = t;
            this.items++;
            System.out.println("Added ToDo: " + t);
        }
    }

    public void addTask(String type, String desc, String date) {
        Task t;
        if (type.equals("deadline")) {
            t = new Deadline(desc, date);
            this.list[this.items] = t;
            this.items++;
            System.out.println("Added Deadline: " + t);
        } else if (type.equals("event")) {
            t = new Event(desc, date);
            this.list[this.items] = t;
            this.items++;
            System.out.println("Added Event: " + t);
        }
    }

    public void giveInput(String input) throws DukeException {
        if (input.equals("list")) {
            this.showList();
        } else if (input.startsWith("mark")) {
            if (input.length() < 6) {
                throw new DukeException("Oops, no task given to mark as done.");
            }
            int i = Integer.parseInt(input.substring(5)) - 1;
            this.markTask(i, true);
        } else if (input.startsWith("unmark")) {
            if (input.length() < 8) {
                throw new DukeException("Oops, no task given to mark as not done.");
            }
            int i = Integer.parseInt(input.substring(7)) - 1;
            this.markTask(i, false);
        } else if (input.startsWith("todo")) {
            if (input.length() < 6) {
                throw new DukeException("Oops, the description of a todo task cannot be empty.");
            }
            String desc = input.substring(5);
            this.addTask("todo", desc);
        } else if (input.startsWith("deadline")) {
            if (input.length() < 10) {
                throw new DukeException("Oops, the description of a deadline task cannot be empty.");
            } else if (!input.contains("/by")) {
                throw new DukeException("Oops, no deadline given for deadline task.");
            }
            String[] str = input.split(" /by ", 2);
            String s1 = str[0].substring(9, str[0].length());
            this.addTask("deadline", s1, str[1]);
        } else if (input.startsWith("event")) {
            if (input.length() < 7) {
                throw new DukeException("Oops, the description of an event task cannot be empty.");
            } else if (!input.contains("/at")) {
                throw new DukeException("Oops, no date given for event task.");
            }
            String[] str = input.split(" /at ", 2);
            String s1 = str[0].substring(6, str[0].length());
            this.addTask("event", s1, str[1]);
        } else {
            throw new DukeException("Oops, I don't know what that means");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.doGreeting();

        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while (!duke.isBye(input)) {
            try {
                duke.giveInput(input);
            } catch (DukeException e) {
                System.out.println(e);
            }
            input = sc.nextLine();
        }
        duke.doBye();
    }
}
