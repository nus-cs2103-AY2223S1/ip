import java.util.Scanner;

public class Duke {
    private Task[] tasks = new Task[100];
    private int numTasks = 0;

    public Duke() {
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

    //Actually does what is needed to do
    public static void puke(Scanner sc, Duke d) {
        String s = sc.nextLine();
        String action = d.doWhat(s);
        if (action.equals("bye")){
            System.out.println("    ____________________________________________________________");
            System.out.println("     Bye. Why are you still here?");
            System.out.println("    ____________________________________________________________");
            return;
        } else if (action.equals("list")) {
            d.listTasks();
            puke(sc, d);
        } else if (action.equals("mark")) {
            int pos = Character.getNumericValue(s.charAt(5));
            d.taskManager("do", pos, d);
            puke(sc,d);
        } else if (action.equals("unmark")) {
            int pos = Character.getNumericValue(s.charAt(7));
            d.taskManager("undo", pos, d);
            puke(sc,d);
        } else {
            Task newTask = new Task(s);
            d.addIncrement(newTask);
            System.out.println("    ____________________________________________________________");
            System.out.println("     added: " + s);
            System.out.println("    ____________________________________________________________");
            puke(sc, d);
        }
    }

    //Acts as a controller to sort out what is needed to do as per the input
    public String doWhat(String s) {
        if (s.equals("list")) {
            return "list";
        } else if (s.equals("bye")) {
            return "bye";
        } else {
            String buildFour = "";
            String buildSix = "";
            for (int i = 0; i < s.length(); i++) {
                if (i < 4) {
                    buildFour += s.charAt(i);
                    buildSix += s.charAt(i);
                } else if (i<6) {
                    buildSix += s.charAt(i);
                } else {
                    break;
                }
            }
            if (buildFour.equals("mark")) {
                return "mark";
            }
            if (buildSix.equals("unmark")) {
                return "unmark";
            }
            return s;
        }
    }

    public void listTasks() {
        System.out.println("    ____________________________________________________________");
        for(int i = 0; i < this.tasks.length ; i++) {
            if (this.tasks[i] == null) {
                break;
            }
            System.out.println("     " + (i+1) + "." + this.tasks[i]);
        }
        System.out.println("    ____________________________________________________________");
        return;
    }

    public void taskManager(String s, int pos, Duke d) {
        if (s.equals("do")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done: ");
            d.tasks[pos - 1].markAsDone();
            System.out.println("       " + d.tasks[pos-1]);
            System.out.println("    ____________________________________________________________");
        } else if (s.equals("undo")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     OK, I've marked this task as not done yet: ");
            d.tasks[pos - 1].markAsUndone();
            System.out.println("       " + d.tasks[pos-1]);
            System.out.println("    ____________________________________________________________");
        } else {
            return;
        }
    }

    public void addIncrement(Task t) {
        this.tasks[numTasks] = t;
        this.numTasks++;
    }

    public static void main(String[] args) {
        Scanner receiver = new Scanner(System.in);

        Duke d = new Duke();

        intro();
        puke(receiver, d);

    }
}
