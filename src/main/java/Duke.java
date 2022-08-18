import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello!\nWhat can I do for you ?");
        Scanner help = new Scanner(System.in);
        class Task {
            protected String description;
            protected boolean isDone;
            protected int num;

            public Task(String description,int num) {
                this.description = description;
                this.isDone = false;
                this.num = num;
            }

            public String getStatusIcon() {
                return (isDone ? "[X] " : "[ ] "); // mark done task with X
            }

            public void mark() {
                isDone = true;
            }

            public void unmark() {
                isDone = false;
            }

        }

        Task[] lst = new Task[100];
        int i = 0;


        while(true) {
           String str = help.nextLine();
           if (str.equals("bye")) {
               System.out.println("Bye see you again buddy !");
               break;
           }
           else if (str.equals("list")) {
               System.out.println("This is your tasks in your list: \n");
               for (Task item : lst) {
                   if (item != null)
                       System.out.println(item.num + "." + item.getStatusIcon() + item.description);
               }
           }
           else if (str.split(" ")[0].equals("mark") ) {
               Task current = lst[Integer.parseInt(str.split(" ")[1]) - 1];
               System.out.println("Nice I have marked this as done:");
               current.mark();
               System.out.println(current.getStatusIcon() + " " + current.description);

           }
           else if (str.split(" ")[0].equals("unmark") ) {
               Task current = lst[Integer.parseInt(str.split(" ")[1]) - 1];
               System.out.println("Ok I have marked this as still to be done:");
               current.unmark();
               System.out.println(current.getStatusIcon() + " " + current.description);


           }
           else {
               lst[i] = new Task(str,i+1);
               i++;
               System.out.println("added: " + str);

           }

       }


    }
}
