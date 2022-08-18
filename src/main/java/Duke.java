import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> allTasks = new ArrayList<>();

    public enum RequestType {
        MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE, LIST
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        greet();
        String request = sc.nextLine();
        while (!request.equals("bye")) {
            try {
                RequestType rqType = checkRequest(request);
                switch (rqType) {
                    case LIST:
                        listTasks(allTasks);
                        break;
                    case MARK:
                        markTask(request);
                        break;
                    case UNMARK:
                        unMarkTask(request);
                        break;
                    case DELETE:
                        deleteTask(request);
                        break;
                    case TODO:
                        todoTask(request);
                        break;
                    case DEADLINE:
                        deadlineTask(request);
                        break;
                    case EVENT:
                        eventTask(request);
                        break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            request = sc.nextLine();
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

    public static void echo(String message) {
        String line = "____________________________________________________________";
        System.out.println("\t" + line);
        System.out.println("\t" + message);
        System.out.println("\t" + line);
    }

    public static void listTasks(ArrayList<Task> ls) {
        String line = "____________________________________________________________";
        System.out.println("\t" + line);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + ls.get(i));
        }
        System.out.println("\t" + line);
    }

    public static void addTask(Task toAdd) {
        allTasks.add(toAdd);
        String mess = "Got it. I've added this task:\n\t\t" + toAdd +
                "\n\tNow you have " + allTasks.size() + " tasks in the list.";
        echo(mess);
    }

    public static void deleteTask(String request) throws DukeException {
        Integer index = Integer.parseInt(request.substring(7));
        if (index < 1 || index > allTasks.size()) {
            throw new InvalidIndexException();
        } else {
            Task item = allTasks.remove(index - 1);
            String mess = "Noted. I've removed this task:\n\t\t" + item +
                    "\n\tNow you have " + allTasks.size() + " tasks in the list.";
            echo(mess);
        }
    }

    public static void markTask(String request) throws DukeException {
        Integer index = Integer.parseInt(request.substring(5));
        if (index < 1 || index > allTasks.size()) {
            throw new InvalidIndexException();
        } else {
            allTasks.get(index - 1).markDone();
            String mess = "Nice! I've marked this task as done:\n\t\t" + allTasks.get(index - 1);
            echo(mess);
        }
    }

    public static void unMarkTask(String request) throws DukeException {
        Integer index = Integer.parseInt(request.substring(7));
        if (index < 1 || index > allTasks.size()) {
            throw new InvalidIndexException();
        } else {
            allTasks.get(index - 1).unMark();
            String mess = "OK, I've marked this task as not done yet:\n\t\t" + allTasks.get(index - 1);
            echo(mess);
        }
    }

    public static void todoTask(String request) throws DukeException {
        String[] rq = request.split(" ", 2);
        if (rq.length < 2 || rq[1].trim().equals("")) {
            throw new InvalidToDoException();
        } else {
            addTask(new ToDo(rq[1]));
        }
    }

    public static void deadlineTask(String request) throws DukeException {
        if (request.matches("(?i)^deadline\\s.+\\s\\/(by)\\s.+")) {
            String[] deadline = request.substring(9).split("\\/(by)\\s", 2);
            addTask(new Deadline(deadline[0], deadline[1]));
        } else {
            throw new InvalidDeadlineException();
        }
    }

    public static void eventTask(String request) throws DukeException {
        if (request.matches("(?i)^event\\s.+\\s\\/(at)\\s.+")) {
            String[] event = request.substring(6).split("\\/(at)\\s", 2);
            addTask(new Event(event[0], event[1]));
        } else {
            throw new InvalidEventException();
        }
    }

    public static RequestType checkRequest(String request) throws DukeException{
        if (request.equals("list")) {
            return RequestType.LIST;
        } else if (request.matches("mark \\d+")) {
            return RequestType.MARK;
        } else if (request.matches("unmark \\d+")) {
            return RequestType.UNMARK;
        } else if (request.matches("delete \\d+")) {
            return RequestType.DELETE;
        } else if (request.matches("(?i)^(todo)(.*)")) {
            return RequestType.TODO;
        } else if (request.matches("(?i)^(deadline)(.*)")) {
            return RequestType.DEADLINE;
        } else if (request.matches("(?i)^(event)(.*)")) {
            return RequestType.EVENT;
        } else {
            throw new InvalidInputException();
        }
    }
}
