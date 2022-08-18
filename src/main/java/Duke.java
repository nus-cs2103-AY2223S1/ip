import java.util.Scanner;
import java.util.ArrayList;
class Task {
    private boolean isDone = false;
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
        return "[" + (isDone? 'X': ' ') + "] " + content;
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

    public static void echo(String text) {
        abilities.add(new Task(text));
        System.out.println("added: " + text);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showList() {
        for (int i = 0; i < abilities.size(); i++) {
            System.out.println(String.valueOf(i + 1)+ ". " + abilities.get(i));
        }
    }

    public static void mark(int index) {
        try {
            abilities.get(index - 1).check();
            System.out.println("Nice! I've marked this task as done:\n" + abilities.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + index + " out of bounds\nPlease input another index");
        }
    }

    public static void unmark(int index) {
        try {
            abilities.get(index - 1).uncheck();
            System.out.println("OK, I've marked this task as not done yet:\n" + abilities.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + index + " out of bounds\nPlease input another index");
        }
    }

    public static void main(String[] args) {
        greet();
        while (true) {
            Scanner scanner = new Scanner(System.in);
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
            } else {
                echo(message);
            }
        }
    }
}