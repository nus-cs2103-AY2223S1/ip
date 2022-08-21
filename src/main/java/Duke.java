import java.util.Scanner;

public class Duke {
    private boolean end;

    public Duke() {
        this.end = false;
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
        } else {
            echo(s);
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

    private void echo(String s) {
        printLine();
        print(s);
        printLine();
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}