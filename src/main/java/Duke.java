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
                if (!uncap.contains("mark")) {
                    Task t = null;
                    if (uncap.contains("/") && (uncap.contains("deadline")
                            || uncap.contains("event"))) {
                        if (uncap.contains("deadline")) {
                            int idOfSlash= str.indexOf('/');
                            t = new Deadline(str.substring(9, idOfSlash), str.substring(idOfSlash + 4));
                        } else if (uncap.contains("event")) {
                            int idOfSlash = str.indexOf('/');
                            t = new Event(str.substring(6, idOfSlash), str.substring(idOfSlash + 4));
                        }
                    } else if (uncap.contains("todo")) {
                            t = new Todo(str.substring(5));
                    } else {
                        t = new Task(str);
                    }
                    al.add(t);
                    System.out.println("------------------------------\n");
                    System.out.printf("\tGot it. I've added this task: \n\t\t%s\n", t.toString());
                    int sizeOfAl = al.size();
                    if (sizeOfAl == 1) {
                        System.out.println("\tNow you have " + sizeOfAl + " task in the list.");
                    } else {
                        System.out.println("\tNow you have " + sizeOfAl + " tasks in the list.");
                    }
                    System.out.println("------------------------------\n");
                    str = sc.nextLine();
                    uncap = str.toLowerCase();
                } else {
                    if (uncap.contains("unmark")) {
                        int i = Integer.parseInt(String.valueOf(uncap.charAt(7)));
                        Task call = al.get(i - 1);
                        call.markAsUndone();
                        System.out.println("-------------------------------\n");
                        System.out.println("\tOK, I've marked this task as not done yet: ");
                        System.out.println("\t\t" + call.toString());
                        System.out.println("-------------------------------\n");
                    } else if (uncap.contains("mark")) {
                        int i = Integer.parseInt(String.valueOf(uncap.charAt(5)));
                        Task call = al.get(i - 1);
                        call.markAsDone();
                        System.out.println("-------------------------------\n");
                        System.out.println("\tNice! I have marked this task as done: ");
                        System.out.println("\t\t" + call.toString());
                        System.out.println("-------------------------------\n");
                    }
                    str = sc.nextLine();
                    uncap = str.toLowerCase();
                }
            } else {
                listOut(al);
                str = sc.nextLine();
                uncap = str.toLowerCase();
            }
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
