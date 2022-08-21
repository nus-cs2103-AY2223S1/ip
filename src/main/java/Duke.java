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
            String line = sc.nextLine();
            output(line);
        }
        sc.close();
        exit();
    }

    private void output(String s) {
        if (s.equals("bye")) {
            this.end = true;
        } else if (s.equals("list")) {
            printList();
        } else if (s.startsWith("mark")) {
            int markNum = Integer.parseInt(s.replace("mark ", ""));
            mark(markNum);
        } else if (s.startsWith("unmark")) {
            int unmarkNum = Integer.parseInt(s.replace("unmark ", ""));
            unmark(unmarkNum);
        } else {
            addList(new Task(s));
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
        for (int i = 0; i < l.size(); i++) {
            print(String.format("%d.%s", i + 1, this.l.get(i)));
        }
        printLine();
    }

    private void addList(Task t) {
        this.l.add(t);
        printLine();
        print("added: " + t.description);
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

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}