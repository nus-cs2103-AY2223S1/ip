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
            } else {
                addList(line);
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

    private void addList(String message) {
        this.l.add(new Task(message));
        printLine();
        print("added: " + message);
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