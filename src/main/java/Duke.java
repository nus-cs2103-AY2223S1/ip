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
            } else if (isDelete(next, allMessages.size())) {
                String[] splited = next.split(" ");
                echoDelete(allMessages.get(Integer.valueOf(splited[1]) - 1), allMessages.size() - 1);
                allMessages.remove(Integer.valueOf(splited[1]) - 1);
            } else {
                try {
                    String[] words = next.split(" ", 2);
                    String type = words[0];
                    Task item;

                    if (type.equals("todo")) {
                        checkToDo(words);
                        item = new ToDo(words[1]);
                    } else if (type.equals("deadline")) {
                        checkDeadline(words);
                        String[] splitted = words[1].split("/by ", 2);
                        item = new Deadline(splitted[0], splitted[1]);
                    } else if (type.equals("event")) {
                        checkEvent(words);
                        String[] splitted = words[1].split("/at ", 2);
                        item = new Event(splitted[0], splitted[1]);
                    } else {
                        throw new InvalidInputException();
                    }
                    allMessages.add(item);
                    echoTask(item, allMessages.size());
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
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
                "\n\tNow you have " + size + " tasks in the list.";
        echo(mess);
    }

    public static void echoDelete(Task item, int size) {
        String mess = "Noted. I've removed this task:\n\t\t" + item +
                "\n\tNow you have " + size + " tasks in the list.";
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

    public static boolean isDelete(String phrase, int listLength) {
        String[] splited = phrase.split(" ");
        if (splited.length != 2 || !splited[1].matches("\\d+")) {
            return false;
        } else {
            return (splited[0].equals("delete"))
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

    public static void checkToDo(String[] words) throws DukeException {
        if (words.length < 2 || words[1].trim().equals("")) {
            throw new InvalidToDoException();
        }
    }

    public static void checkDeadline(String[] words) throws DukeException {
        if (words.length < 2 || !words[1].contains("/by")) {
            throw new InvalidDeadlineException();
        } else {
            String[] splitted = words[1].split("/by");
            if (splitted[0].trim().equals("")) throw new InvalidDeadlineException("description");
            if (splitted.length < 2 || splitted[1].trim().equals(""))
                throw new InvalidDeadlineException("date");
        }
    }

    public static void checkEvent(String[] words) throws DukeException {
        if (words.length < 2 || !words[1].contains("/at")) {
            throw new InvalidEventException();
        } else {
            String[] splitted = words[1].split("/at");
            if (splitted[0].trim().equals("")) throw new InvalidEventException("description");
            if (splitted.length < 2 || splitted[1].trim().equals(""))
                throw new InvalidEventException("time");
        }
    }
}
