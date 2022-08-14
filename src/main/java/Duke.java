import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private ArrayList<Task> l;
    private boolean end;
    private enum Inputs {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        ELSE
    }

    public Duke() {
        this.sc = new Scanner(System.in);
        this.l = new ArrayList<>();
        this.end = false;
    }

    private void run() {
        intro();
        while (!end) {
            try {
                String line = this.sc.nextLine();
                output(line);
            } catch (DukeException d) {
                printException(d);
            }
        }
        this.sc.close();
        exit();
    }

    private void output(String s) throws DukeException {
        switch (getInput(s)) {
            case BYE:
                this.end = true;
                break;
            case LIST:
                printList();
                break;
            case MARK:
                int markNum = Integer.parseInt(s.replace("mark ", ""));
                mark(markNum);
                break;
            case UNMARK:
                int unmarkNum = Integer.parseInt(s.replace("unmark ", ""));
                unmark(unmarkNum);
                break;
            case TODO:
                String tDes = s.replace("todo", "");
                addList(new Todo(tDes));
                break;
            case EVENT:
                String[] eDes = s.replace("event", "").split(" /at ");
                addList(new Event(eDes[0], eDes[1]));
                break;
            case DEADLINE:
                String[] dDes = s.replace("deadline", "").split(" /by ");
                addList(new Deadline(dDes[0], dDes[1]));
                break;
            case DELETE:
                int delNum = Integer.parseInt(s.replace("delete ", ""));
                delete(delNum);
                break;
            default:
                addList(new Task(null));
                break;
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

    private void delete(int i) {
        Task t = this.l.get(i - 1);
        this.l.remove(i - 1);
        printLine();
        print("Noted. I've removed this task:");
        print("  " + t.toString());
        print("Now you have " + l.size() + " tasks in the list.");
        printLine();
    }

    private Inputs getInput(String s) {
        if (s.equals("bye")) {
            return Inputs.BYE;
        } else if (s.equals("list")) {
            return Inputs.LIST;
        } else if (s.startsWith("mark")) {
            return Inputs.MARK;
        } else if (s.startsWith("unmark")) {
            return Inputs.UNMARK;
        } else if (s.startsWith("todo")) {
            return Inputs.TODO;
        } else if (s.startsWith("deadline")) {
            return Inputs.DEADLINE;
        } else if (s.startsWith("event")) {
            return Inputs.EVENT;
        } else if (s.startsWith("delete")) {
            return Inputs.DELETE;
        } else {
            return Inputs.ELSE;
        }
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}