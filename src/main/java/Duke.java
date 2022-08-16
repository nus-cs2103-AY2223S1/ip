import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    final static String logo =
            "   __ __    ____       ___               __\n" +
                    "  / // /__ / / /__    / _ \\___ ____  ___/ /__ _\n" +
                    " / _  / -_) / / _ \\  / ___/ _ `/ _ \\/ _  / _ `/\n" +
                    "/_//_/\\__/_/_/\\___/ /_/   \\_,_/_//_/\\_,_/\\_,_/\n";

    private static void markTask(int i) {
        if (i >= 100) return;
        if (taskList.get(i) == null) return;
        taskList.get(i).doTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(i));
    }

    private static void unmarkTask(int i) {
        if (i >= 100) return;
        if (taskList.get(i) == null) return;
        taskList.get(i).undoTask();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskList.get(i));
    }

    private static void deleteTask(int i) {
        if (i >= 100) return;
        if (taskList.get(i) == null) return;
        Task t = taskList.get(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        taskList.remove(i);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void displayList() {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) == null) break;
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    private static void beginBot() throws DukeException {

            System.out.println("Hello from\n" + logo);
            Scanner sc = new Scanner(System.in);
            System.out.println("How can I help you today? :)");
            String command = sc.nextLine();
        try {
            while (!command.equals("bye")) {
                if (command.equals("list")) {
                    displayList();
                    command = sc.nextLine();
                    continue;
                } else if (command.contains("unmark")) {
                    String s = command.substring(7);
                    int i = Integer.parseInt(s);
                    unmarkTask(i - 1);
                    command = sc.nextLine();
                    continue;
                } else if (command.contains("mark")) {
                    String s = command.substring(5);
                    int i = Integer.parseInt(s);
                    markTask(i - 1);
                    command = sc.nextLine();
                    continue;
                } else if (command.contains("delete")) {
                    String s = command.substring(7);
                    int i = Integer.parseInt(s);
                    deleteTask(i - 1);
                    command = sc.nextLine();
                    continue;
                } else if (command.contains("deadline")) {
                    if (command.length() == 8) throw new DukeException("OOPS!!! I'm sorry but description of a deadline cannot be empty");
                    String s = command.substring(9);
                    String[] result = s.split(" /by ");
                    Deadline d = new Deadline(result[0], result[1]);
                    taskList = d.printAndStoreTask(taskList);
                    command = sc.nextLine();
                    continue;
                } else if (command.contains("todo")) {
                    if (command.length() == 4) throw new DukeException("OOPS!!! I'm sorry but description of a todo cannot be empty");
                    String s = command.substring(5);
                    if (s == " ") throw new DukeException("OOPS!!! I'm sorry but description of a todo cannot be empty");
                    Todo t = new Todo(s);
                    taskList = t.printAndStoreTask(taskList);
                    command = sc.nextLine();
                    continue;
                } else if (command.contains("event")) {
                    if (command.length() == 5) throw new DukeException("OOPS!!! I'm sorry but description or time period of an event cannot be empty");
                    String s = command.substring(6);
                    String[] result = s.split(" /at ");
                    Event e = new Event(result[0], result[1]);
                    e.printAndStoreTask(taskList);
                    command = sc.nextLine();
                    continue;
                }
                throw new DukeException("OOPS!!! I'm sorry but I don't know what that means :-(");
            }
            sc.close();
            System.out.println("Bye. Hope to see you again soon!");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }

    }

    public static void main(String[] args) {
        try {
            beginBot();
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

}
