import java.util.Scanner;

public class Duke {
    private Scanner sc;

    private void run() {
        intro();
        this.sc = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            String line = this.sc.nextLine();
            if (line.equals("bye")) {
                end = true;
            } else {
                printLine();
                print(line);
                printLine();
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

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}