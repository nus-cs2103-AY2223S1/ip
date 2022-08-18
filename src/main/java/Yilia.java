import java.util.Scanner;
import java.util.ArrayList;

enum Type {
    TODO,
    DEADLINE,
    EVENT
}

class DontKnowException extends Exception {

    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}

class DescriptionEmptyException extends Exception {
    private Type type;
    DescriptionEmptyException(Type content) {
        this.type = content;
    }
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a " + type.toString().toLowerCase() + " cannot be empty.";
    }
}

class Task {
    public boolean isDone = false;
    private String content;
    Task(String content) {
        this.content = content;
    }
    public void check() {
        isDone = true;
    }
    public void uncheck() {
        isDone = false;
    }
    @Override
    public String toString() {
        return "[" + (isDone? 'X': ' ') + "]" + content;
    }
}

class Todo extends Task {
    Todo(String content) {
        super(content);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    String date;
    Deadline(String content, String date) {
        super(content);
        this.date = date;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + date.substring(0,2) + ":" + date.substring(2) + ")";
    }
}

class Event extends Task {
    String date;
    Event(String content, String date) {
        super(content);
        this.date = date;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + date.substring(0,2) + ":" + date.substring(2) + ")";
    }
}

class Yilia {
    private static ArrayList<Task> abilities = new ArrayList();

    public static void greet() {
        String logo = "  \\‾\\     /‾/  |‾|  |‾|          |‾|       /‾‾‾\\      \n" +
                "   \\ \\   / /   | |  | |          | |      / /‾\\ \\     \n" +
                "    \\ \\_/ /    | |  | |          | |     / /___\\ \\    \n" +
                "     ‾| |‾     | |  | |          | |    / /_____\\ \\   \n" +
                "      | |      | |  | |_______   | |   / /       \\ \\  \n" +
                "      |_|      |_|  |_________|  |_|  /_/         \\_\\ \n";
        System.out.println("Hello! I'm Yilia\n" + logo + "What can I do for you?");
    }

    public static void echo(Task task) {
        System.out.println("Got it. I've added this task:\n  " + task + "\nNow you have " + abilities.size() + (abilities.size() < 2 ? " task" : " tasks") + " in the list.");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showList() {
        for (int i = 0; i < abilities.size(); i++) {
            System.out.println(String.valueOf(i + 1)+ "." + abilities.get(i));
        }
    }

    public static void mark(int index) {
        try {
            abilities.get(index - 1).check();
            System.out.println("Nice! I've marked this task as done:\n" + abilities.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + index + " out of bounds\nPlease input another index");
        }
    }

    public static void unmark(int index) {
        try {
            abilities.get(index - 1).uncheck();
            System.out.println("OK, I've marked this task as not done yet:\n" + abilities.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + index + " out of bounds\nPlease input another index");
        }
    }

    public static void addTask(String text, Type type) throws DescriptionEmptyException {
        if (text.strip().isEmpty()) {
            throw new DescriptionEmptyException(type);
        }
        if (type.equals(Type.TODO)) {
            Todo todo = new Todo(text);
            abilities.add(todo);
            echo(todo);
            return;
        }
        String info[] = text.split("/");
        if (info.length == 1 || info[1].strip().isEmpty()) {
            throw new DescriptionEmptyException(type);
        }
        if (type.equals(Type.DEADLINE)) {
            Deadline deadline = new Deadline(info[0], info[1]);
            abilities.add(deadline);
            echo(deadline);
        } else if (type.equals(Type.EVENT)) {
            Event event = new Event(info[0], info[1]);
            abilities.add(event);
            echo(event);
        }
    }

    public static void delete(int index) {
        Task task = abilities.remove(index - 1);
        System.out.println("Noted. I've removed this task:\n  " + task + "\nNow you have " + abilities.size() + " tasks in the list.");
    }

    public static void main(String[] args) throws DontKnowException {
        greet();
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                String message = scanner.next();
                if (message.equals("bye")) {
                    exit();
                    return;
                } else if (message.equals("list")) {
                    showList();
                } else if (message.contains("unmark")) {
                    unmark(scanner.nextInt());
                } else if (message.contains("mark")) {
                    mark(scanner.nextInt());
                } else if (message.contains("deadline")) {
                    addTask(scanner.nextLine(), Type.DEADLINE);
                } else if (message.contains("event")) {
                    addTask(scanner.nextLine(), Type.EVENT);
                } else if (message.contains("todo")) {
                    addTask(scanner.nextLine(), Type.TODO);
                } else if (message.contains("delete")) {
                    delete(scanner.nextInt());
                } else {
                    throw new DontKnowException();
                }
            } catch (DescriptionEmptyException e) {
                System.out.println(e);
            } catch (DontKnowException e) {
                System.out.println(e);
            }
        } while (scanner.hasNext());
    }
}