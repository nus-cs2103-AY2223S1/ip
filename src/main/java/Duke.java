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
            } else if (isMarkUnMark(next, allMessages.size())) {
                printMarkUnMark(next, allMessages);
            } else if (isToDos(next)) {
                printToDos(next, allMessages);
            } else {
                allMessages.add(new Task(next));
                echo(next);
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

    public static void echoTask(Task toAdd, int size) {
        String mess = "Got it. I've added this task:\n\t\t" + toAdd +
                "\n\t Now you have " + size + "tasks in the list.";
        echo(mess);
    }

    public static boolean isMarkUnMark(String phrase, int listLength) {
        String[] splited = phrase.split(" ");
        if (splited.length != 2 || !splited[1].matches("\\d+")) {
            return false;
        } else {
            return (splited[0].equals("mark") || splited[0].equals("unmark"))
                    && Integer.valueOf(splited[1]) <= listLength
                    && Integer.valueOf(splited[1]) > 0;
        }
    }

    public static void printMarkUnMark(String next, ArrayList<Task> allMessages) {
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
    }

    public static boolean isToDos(String phrase) {
        String[] words = phrase.split(" ", 2);
        try {
            String type = words[0];
            String text = words[1];
            if (type.equals("todo")) {
                return true;
            }
            if (type.equals("deadline") && text.contains("/by")) {
                return true;
            }
            if (type.equals("event") && text.contains("/at")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void printToDos(String next, ArrayList<Task> allMessages) {
        String[] words = next.split(" ", 2);
        String type = words[0];
        String text = words[1];
        Task task;

        if (type.equals("todo")) {
            task = new ToDo(text);
        } else if (type.equals("deadline")) {
            String[] splitted = text.split("/by ", 2);
            task = new Deadline(splitted[0], splitted[1]);
        } else {
            String[] splitted = text.split("/at ", 2);
            task = new Event(splitted[0], splitted[1]);
        }
        allMessages.add(task);
        echoTask(task, allMessages.size());
    }
}
