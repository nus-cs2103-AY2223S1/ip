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
        try {
            storeman();
        } catch (DukeException de) {
            System.out.println("--------------------------------------\n");
            System.out.println(de.getMessage());
            System.out.println("--------------------------------------\n");
            main(args);
        }
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

    public static ArrayList<Task> storeman() throws DukeException {
        ArrayList<Task> al = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String uncap = str.toLowerCase();
        while (!uncap.equals("bye")) {
            if (uncap.startsWith("delete")) {
                Integer i = Integer.parseInt(String.valueOf(str.charAt(7)));
                Task del = delete(al, i);
                System.out.println("------------------------------\n");
                System.out.println("\tNoted. I've removed this task:");
                System.out.println("\t\t" + del.toString());
                System.out.println("\tNow you have " + al.size() + " task(s) in the list.");
                System.out.println("------------------------------\n");
            } else {
                if (!uncap.equals("list")) {
                    if (!uncap.startsWith("mark") && !uncap.startsWith("unmark")) {
                        Task t = null;
                        if (uncap.startsWith("deadline")
                                || uncap.startsWith("event")) {
                            if (!uncap.contains("/")) throw new DukeException(
                                    "☹ OOPS!!! Associated time for event or deadline cannot be empty.");
                            if (uncap.startsWith("deadline")) {
                                int idOfSlash = str.indexOf('/');
                                if (idOfSlash - 9 == 0) throw new DukeException("" +
                                        "☹ OOPS!!! The description of a deadline cannot be empty.");
                                if (str.length() < idOfSlash + 4) throw new DukeException(
                                        "☹ OOPS!!! The dude date of a deadline cannot be empty.");
                                t = new Deadline(str.substring(9, idOfSlash), str.substring(idOfSlash + 4));
                            } else if (uncap.startsWith("event")) {
                                int idOfSlash = str.indexOf('/');
                                if (idOfSlash - 6 == 0) throw new DukeException("" +
                                        "☹ OOPS!!! The description of an event cannot be empty.");
                                if (str.length() < idOfSlash + 4) throw new DukeException(
                                        "☹ OOPS!!! The duration of event cannot be empty.");
                                t = new Event(str.substring(6, idOfSlash), str.substring(idOfSlash + 4));
                            }
                        } else if (uncap.startsWith("todo")) {
                            if (str.length() < 6) throw new DukeException("" +
                                    "☹ OOPS!!! The description of a todo cannot be empty.");
                            t = new Todo(str.substring(5));
                        } else {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        al.add(t);
                        System.out.println("------------------------------\n");
                        System.out.printf("\tGot it. I've added this task: \n\t\t%s\n", t.toString());
                        System.out.println("\tNow you have " + al.size() + " task(s) in the list.");
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
    public static Task delete(ArrayList<Task> at, Integer id) {
        int actual = id - 1;
        return at.remove(actual);
    }

    public static void bye() {
        System.out.println("------------------------------\n");
        System.out.println("\tBye. Hope to see you soon again!");
        System.out.println("------------------------------\n");
    }
}
