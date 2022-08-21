import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean end;
    private ArrayList<Task> l;

    public Duke() {
        this.end = false;
        this.l = new ArrayList<>();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        intro();
        while (!end) {
            try {
                String line = sc.nextLine();
                output(line);
            } catch (DukeException e) {
                printException(e);
            }
        }
        sc.close();
        exit();
    }

    private void output(String s) throws DukeException {
        if (s.equals("bye")) {
            this.end = true;
        } else if (s.equals("list")) {
            printList();
        } else if (s.startsWith("mark")) {
            int markNum = Integer.parseInt(s.replace("mark ", ""));
            mark(markNum);
        } else if (s.startsWith("unmark")) {
            int unMarkNum = Integer.parseInt(s.replace("unmark ", ""));
            unMark(unMarkNum);
        } else if (s.startsWith("todo")) {
            String tDes = s.replace("todo", "");
            addList(new Todo(tDes));
        } else if (s.startsWith("deadline")) {
            String[] dDes = s.replace("deadline", "").split(" /by ");
            addList(new Deadline(dDes[0], dDes[1]));
        } else if (s.startsWith("event")) {
            String[] eDes = s.replace("event", "").split(" /at ");
            addList(new Event(eDes[0], eDes[1]));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
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

    private void unMark(int i) {
        Task t = this.l.get(i - 1);
        t.unmarkDone();
        printLine();
        print("OK, I've marked this task as not done yet:");
        print("  " + t.toString());
        printLine();
    }

    private void printException(Exception e) {
        printLine();
        print(e.getMessage());
        printLine();
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}