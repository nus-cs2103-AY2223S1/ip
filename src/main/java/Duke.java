import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you ?");
        Scanner help = new Scanner(System.in);
        class Task {
            protected String description;
            protected boolean isDone;
            protected int num;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
                this.num = num;
            }

            public String toString() {
                return this.getStatusIcon() + this.description;

            }

            public String getStatusIcon() {
                return (isDone ? "[X]" : "[ ]"); // mark done task with X
            }

            public void mark() {
                isDone = true;
            }

            public void unmark() {
                isDone = false;
            }

        }
        class Deadline extends Task {
            protected String by;
            public Deadline(String description,String by) {
                super(description);
                this.by = by;
            }

            @Override
            public String toString() {
                return "[D]" + super.toString() + " (by: " + by + ")";
            }

        }
        class ToDos extends Task {

            public ToDos(String description) {
                super(description);
            }

            @Override
            public String toString() {
                return "[T]" + super.toString();
            }

        }

        class Events extends Task {
            protected String at;
            public Events(String description,String at) {
                super(description);
                this.at = at;
            }

            @Override
            public String toString() {
                return "[E]" + super.toString() + " (at: " + at + ")";
            }

        }

        class DukeException extends Exception {

            public DukeException(String error) {
                super(error);
            }
        }

        ArrayList<Task> lst = new ArrayList<>();
        for(int j = 0;j<100;j++) {
            lst.add(null);
        }
        int i = 0;


        while(true) {
           String str = help.nextLine();
            String first = str.split(" ")[0];

           if (first.equals("bye")) {
               System.out.println("Bye see you again buddy !");
               break;
           }

           else if (first.equals("list")) {
               System.out.println("This is your tasks in your list: \n");
               for (Task item : lst) {
                   if (item != null)
                       System.out.println(lst.indexOf(item) + 1 + "." + item);
               }
           }

           else if (first.equals("mark") ) {
               Task current = lst.get(Integer.parseInt(str.split(" ")[1]) - 1);
               System.out.println("Nice I have marked this as done:");
               current.mark();
               System.out.println( " " + current);

           }
           else if (first.equals("unmark") ) {
               Task current = lst.get(Integer.parseInt(str.split(" ")[1]) - 1);
               System.out.println("Ok I have marked this as still to be done:");
               current.unmark();
               System.out.println(" " + current);

           }
           else if (first.equals("delete")) {
              Task current = lst.get(Integer.parseInt(str.split(" ")[1]) - 1);
              lst.remove(current);
               i--;
               System.out.println("Noted I have removed this task");
               System.out.println(current);
               System.out.println("Now you have" + " " + i + " " + "tasks in list");



           }
           else {
               if (first.equals("deadline")) {
                   if (str.endsWith("deadline")) {
                       throw new DukeException("Oops The description of deadline cannot be empty !");
                   }
                   System.out.println("Got it.I've added this task");
                   String currD = str.substring(str.indexOf("deadline") + 8, str.indexOf("/by"));
                   lst.set(i,new Deadline(currD, str.substring(str.indexOf("/by") + 3)));
                   System.out.println(lst.get(i));
                   i++;
                   System.out.println("Now you have" + " " + i + " " + "tasks in list");
               } else if (first.equals("event")) {
                   if (str.endsWith("event")) {
                       throw new DukeException("Oops The description of event cannot be empty !");
                   }
                   System.out.println("Got it.I've added this task");
                   String currE = str.substring(str.indexOf("event") + 5, str.indexOf("/at"));
                   lst.set(i, new Events(currE, str.substring(str.indexOf("/at") + 3)));
                   System.out.println(lst.get(i));
                   i++;
                   System.out.println("Now you have" + " " + i + " " + "tasks in list");
               }
               else if (first.equals("todo")) {
                   if (str.endsWith("todo")) {
                       throw new DukeException("Oops The description of todo cannot be empty !");
                   }
                   System.out.println("Got it.I've added this task");
                   String currT = str.substring(str.indexOf("todo") + 4);
                   lst.set(i, new ToDos(currT));
                   System.out.println(lst.get(i));
                   i++;
                   System.out.println("Now you have" + " " + i + " " + "tasks in list");
               } else {
                 throw new DukeException("Oops Sorry I dont know what you are talking about :( ");
               }
           }

       }


    }
}
