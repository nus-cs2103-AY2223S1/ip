import java.util.Scanner;

public class Duke {
    private String[] tasks = new String[100];
    private int numTasks = 0;

    public Duke() {
        this.tasks = new String[100];
        this.numTasks = 0;
    }
    public static void echo(Scanner sc) {
        String s = sc.nextLine();
        if (s.equals("bye")){
            System.out.println("    ____________________________________________________________");
            System.out.println("     Bye. Why are you still here?");
            System.out.println("    ____________________________________________________________");
            return;
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + s);
        System.out.println("    ____________________________________________________________");
        echo(sc);
    }

    public static void intro() {
        String logo = " ____         _        \n"
                + "|   | \\ _   _| | _____ \n"
                + "|  _|  | | | | |/ / _ \\\n"
                + "| |    | |_| |   <  __/\n"
                + "|_|    \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from puke\n" + logo);
        return;
    }
    public static void puke(Scanner sc, Duke d) {
        String s = sc.nextLine();
        if (s.equals("bye")){
            System.out.println("    ____________________________________________________________");
            System.out.println("     Bye. Why are you still here?");
            System.out.println("    ____________________________________________________________");
            return;
        } else if (s.equals("list")) {
            d.listTasks();
            puke(sc, d);
        } else {
            d.addIncrement(s);
            System.out.println("    ____________________________________________________________");
            System.out.println("     added: " + s);
            System.out.println("    ____________________________________________________________");
            puke(sc, d);
        }
    }

    public void listTasks() {
        System.out.println("    ____________________________________________________________");
        for(int i = 0; i < this.tasks.length ; i++) {
            if (this.tasks[i] == null) {
                break;
            }
            System.out.println("     " + (i+1) + ". " + this.tasks[i]);
        }
        System.out.println("    ____________________________________________________________");
        return;
    }

    public void addIncrement(String s) {
        this.tasks[numTasks] = s;
        this.numTasks++;
    }

    public static void main(String[] args) {
        Scanner receiver = new Scanner(System.in);

        Duke d = new Duke();

        intro();
        puke(receiver, d);

    }
}
