import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    //private Task[] tasks = new Task[100];
    private ArrayList<Task> tasks = new ArrayList();
    private int numTasks = tasks.size();

    private int removed = 0;

    private Scanner receiver = new Scanner(System.in);
    private static Duke d = new Duke();

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

    private static void intro() {
        String logo = " ____         _        \n"
                + "|   | \\ _   _| | _____ \n"
                + "|  _|  | | | | |/ / _ \\\n"
                + "| |    | |_| |   <  __/\n"
                + "|_|    \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from puke\n" + logo);
    }

    //Actually does what is needed to do
    public static void puke(Scanner sc, Duke d) throws DukeException {
        String a = sc.next();
        if (a.equals("bye")) {
            d.systemMessage(1,d, new Task(""));
            d.tasks = new ArrayList<>();
            return;
        }
        if (a.equals("list")) {
            d.listTasks();
            puke(sc,d);
        }
        String s = sc.nextLine();

        //String action = d.doWhat(s);
        if (a.equals("mark")) {
            int pos = Character.getNumericValue(s.charAt(1));
            d.taskManager("do", pos, d);
            puke(sc,d);
        } else if (a.equals("unmark")) {
            int pos = Character.getNumericValue(s.charAt(1));
            d.taskManager("undo", pos, d);
            puke(sc,d);
        } else if (a.equals("todo")) {
            String desc = d.getMessage(s, "ToDo");
            String date = d.getDate(s);
            Task newTask = new ToDo(desc);
            d.addIncrement(newTask);
            d.systemMessage(2, d, newTask);
            puke(sc,d);
        } else if (a.equals("deadline")) {
            String desc = d.getMessage(s, "Deadline");
            String date = d.getDate(s);
            Task newTask = new Deadline(desc, date);
            d.addIncrement(newTask);
            d.systemMessage(2, d, newTask);
            puke(sc,d);
        } else if (a.equals("event")) {
            String desc = d.getMessage(s, "Event");
            String date = d.getDate(s);
            Task newTask = new Event(desc, date);
            d.addIncrement(newTask);
            d.systemMessage(2, d, newTask);
            puke(sc,d);
        } else if (a.equals("delete")) {
            int pos = Character.getNumericValue(s.charAt(1));
            Task temp = d.tasks.get(pos - 1);
            d.delete(pos - 1);
            d.systemMessage(3, d, temp);
        } else {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "OOPS!!! I'm sorry, but I dont't know what that means\n" +
                    "    ____________________________________________________________");
        }
    }

    private static String getMessage(String s, String type) throws DukeException{
        String result = "";
        for (int i = 0 ; i < s.length(); i++) {
            if (i == 0) {
                continue;
            }
            if (s.charAt(i) == "/".charAt(0)) {
                break;
            }
            result += s.charAt(i);
        }

        if (result.length() == 0) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     OOPS!!! The Description of a " + type + " cannot be empty.\n" +
                    "    ____________________________________________________________");
        }
        return result;
    }

    private static String getDate(String s) {
        String result = "";
        int temp = 0;
        for (int i = 0 ; i < s.length(); i++) {
            if (s.charAt(i) == "/".charAt(0)) {
                temp = i;
            }
        }
        for (int r = temp + 4 ; r < s.length(); r++) {
            result += s.charAt(r);
        }
        return result;
    }

    private void systemMessage(int i , Duke d, Task t) {
        if (i == 1) {
            //Bye message
            System.out.println("    ____________________________________________________________");
            System.out.println("     Bye. Why are you still here?");
            System.out.println("    ____________________________________________________________");
        } else if (i == 2) {
            // to do message
            System.out.println("    ____________________________________________________________");
            System.out.println("     Got it. I've added this task:");
            System.out.println("      " + t);
            System.out.println("     Now you have " + (d.numTasks - d.removed) + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        }  else if (i == 3) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Noted. I've removed this task:");
            System.out.println("     " + t);
            System.out.println("     Now you have " + (d.numTasks - d.removed) + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        }else {
            return;
        }

    }



    private void listTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for(int i = 0; i < this.tasks.size() ; i++) {
            /*if (this.tasks[i] == null) {
                break;
            }*/
            System.out.println("     " + (i+1) + "." + this.tasks.get(i));
        }
        System.out.println("    ____________________________________________________________");
        return;
    }

    private void taskManager(String s, int pos, Duke d) {
        if (s.equals("do")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done: ");
            d.tasks.get(pos - 1).markAsDone();
            System.out.println("       " + d.tasks.get(pos-1));
            System.out.println("    ____________________________________________________________");
        } else if (s.equals("undo")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     OK, I've marked this task as not done yet: ");
            d.tasks.get(pos - 1).markAsUndone();
            System.out.println("       " + d.tasks.get(pos-1));
            System.out.println("    ____________________________________________________________");
        } else {
            return;
        }
    }

    private void addIncrement(Task t) {
        this.tasks.add(t);
        this.numTasks++;
    }

    private void delete(int index) {
        this.tasks.remove(index);
        d.removed++;
    }

    public static void startBot() {
        try {
            puke(Duke.d.receiver, d);
        } catch (DukeException e) {
            System.out.println(e);
        } finally {
            startBot();
        }
    }

    public static void main(String[] args) {
        intro();
        Duke.startBot();
        Duke.d.receiver.close();
    }
}
