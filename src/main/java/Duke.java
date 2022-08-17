import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        ArrayList<Task> allMessages = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        greet();
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                echo(allMessages);
            } else if (isSetStatus(next, allMessages.size())) {
                String[] splited = next.split(" ");
                int index = Integer.valueOf(splited[1]) - 1;
                if (splited[0].equals("mark")) {
                    allMessages.get(index).markDone();
                    String mess = "Nice! I've marked this task as done:\n\t\t" + allMessages.get(index);
                    echo(mess);
                }
                if (splited[0].equals("unmark")) {
                    allMessages.get(index).unMark();
                    String mess = "OK, I've marked this task as not done yet:\n\t\t" + allMessages.get(index);
                    echo(mess);
                }
            } else {
                allMessages.add(new Task(next));
                echo("added: " + next);
            }
            next = sc.nextLine();
        }
        exitMessage();
        sc.close();
    }

    public static void greet() {
        echo("Hello! I'm Duke\n" + "\tWhat can I do for you?");
    }

    public static void exitMessage() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(ArrayList<Task> ls) {
        String line = "____________________________________________________________";
        System.out.println("\t" + line);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + ls.get(i));
        }
        System.out.println("\t" + line);
    }

    public static void echo(String message) {
        String line = "____________________________________________________________";
        System.out.println("\t" + line);
        System.out.println("\t" + message);
        System.out.println("\t" + line);
    }

    public static boolean isSetStatus(String phrase, int listLength) {
        String[] splited = phrase.split(" ");
        if (splited.length != 2 || !splited[1].matches("\\d+")) {
            return false;
        } else {
            return (splited[0].equals("mark") || splited[0].equals("unmark"))
                    && Integer.valueOf(splited[1]) <= listLength
                    && Integer.valueOf(splited[1]) > 0;
        }
    }
}
