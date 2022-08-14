import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private ArrayList<String> l;

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
        for (int i = 0; i < l.size(); i++) {
            print(String.format("%d. %s", i + 1, this.l.get(i)));
        }
        printLine();
    }

    private void addList(String message) {
        this.l.add(message);
        printLine();
        print("added: " + message);
        printLine();
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}