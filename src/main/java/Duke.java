import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        storeman();
        //checklist(storeman());
    }

    public static void greeting() {
        System.out.println("--------------------------------------\n");
        System.out.println("\tHello I'm Duke, what can I do for you?");
        System.out.println("--------------------------------------\n");
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String uncap = str.toLowerCase();
        while (!uncap.equals("bye")) {
            System.out.println("------------------------------\n");
            System.out.printf("\t  %s  \n", str);
            System.out.println("------------------------------\n");
            str = sc.nextLine();
            uncap = str.toLowerCase();
        }
        bye();
        sc.close();
    }

    public static ArrayList<Task> storeman() {
        ArrayList<Task> al = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String uncap = str.toLowerCase();
        while (!uncap.equals("bye")) {
            if (!uncap.equals("list")) {
                if (!uncap.startsWith("mark") && !uncap.startsWith("unmark")) {
                    Task t = new Task(str);
                    al.add(t);
                    System.out.println("------------------------------\n");
                    System.out.printf("\tAdded: %s\n", str);
                    System.out.println("------------------------------\n");
                } else {
                    if (uncap.startsWith("unmark")) {
                        int i = Integer.parseInt(String.valueOf(uncap.charAt(7)));
                        Task call = al.get(i - 1);
                        call.markAsUndone();
                        System.out.println("-------------------------------\n");
                        System.out.println("\tOK, I've marked this task as not done yet: ");
                        System.out.println("\t\t" + call.toString());
                        System.out.println("-------------------------------\n");
                    } else if (uncap.startsWith("mark")) {
                        int i = Integer.parseInt(String.valueOf(uncap.charAt(5)));
                        Task call = al.get(i - 1);
                        call.markAsDone();
                        System.out.println("-------------------------------\n");
                        System.out.println("\tNice! I have marked this task as done: ");
                        System.out.println("\t\t" + call.toString());
                        System.out.println("-------------------------------\n");
                    }
                }
            } else {
                listOut(al);
            }
            str = sc.nextLine();
            uncap = str.toLowerCase();
        }
        sc.close();
        bye();
        return al;
    }

    private static void listOut(ArrayList<Task> al) {
        int count = 1;
        System.out.println("-------------------------------\n");
        System.out.println("\tHere are the tasks in your list: ");
        for (Iterator<Task> it = al.iterator(); it.hasNext(); ) {
            Task curr = it.next();
            System.out.println("\t\t" +count + ". " + curr.toString());
            count++;
        }
        System.out.println("-------------------------------\n");
        count = 1;
    }

    public static void bye() {
        System.out.println("------------------------------\n");
        System.out.println("\tBye. Hope to see you soon again!");
        System.out.println("------------------------------\n");
    }
}
