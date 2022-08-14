import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private ArrayList<Task> l;

    public Duke() {
        this.l = new ArrayList<>();
    }

    private void run() {
        intro();
        this.sc = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            try {
                String line = this.sc.nextLine();
                if (line.equals("bye")) {
                    end = true;
                } else if (line.equals("list")) {
                    printList();
                } else if (line.startsWith("mark")) {
                    int i = Integer.parseInt(line.replace("mark ", ""));
                    mark(i);
                } else if (line.startsWith("unmark")) {
                    int i = Integer.parseInt(line.replace("unmark ", ""));
                    unmark(i);
                } else if (line.startsWith("todo")) {
                    String s = line.replace("todo", "");
                    addList(new Todo(s));
                } else if (line.startsWith("deadline")) {
                    String[] s = line.replace("deadline", "").split(" /by ");
                    addList(new Deadline(s[0], s[1]));
                } else if (line.startsWith("event")) {
                    String[] s = line.replace("event", "").split(" /at ");
                    addList(new Event(s[0], s[1]));
                } else {
                    addList(new Task(null));
                }
            } catch (DukeException d) {
                printException(d);
            }
        }
        this.sc.close();
        exit();
    }

    private void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    private void print(String s) {
        System.out.println("\t " + s);
    }

    private void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        print("Hello! I'm Duke");
        print("What can I do for you?");
        printLine();
    }

    private void exit() {
        printLine();
        print("Bye. Hope to see you again soon!");
        printLine();
    }

    private void printList() {
        printLine();
        print("Here are the tasks in your list:");
        for (int i = 0; i < l.size(); i++) {
            print(String.format("%d. %s", i + 1, this.l.get(i)));
        }
        printLine();
    }

    private void addList(Task t) {
        this.l.add(t);
        printLine();
        print("Got it. I've added this task:");
        print("  " + t.toString());
        print("Now you have " + l.size() + " tasks in the list.");
        printLine();
    }

    private void mark(int i) {
        Task t = this.l.get(i - 1);
        t.markDone();
        printLine();
        print("Nice! I've marked this task as done:");
        print("  " + t.toString());
        printLine();
    }

    private void unmark(int i) {
        Task t = this.l.get(i - 1);
        t.unmarkDone();
        printLine();
        print("OK, I've marked this task as not done yet:");
        print("  " + t.toString());
        printLine();
    }

    private void printException(DukeException d) {
        printLine();
        print(d.getMessage());
        printLine();
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}