import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();

        String line = " _______________________________________ \n";
        System.out.println(line + " I'm Dukie\n" + " What can I do for you?\n" + line);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String userInput = "0";

        while (true) {

            userInput = myObj.nextLine();  // Read user input

            if (userInput.equals("bye")) {
                System.out.println("Goodbaiiiii\n");
                System.exit(0);
            } else if (userInput.startsWith("list")) {
                if (list.isEmpty()) {
                    System.out.println(line + "nuuuu list empty! add an item first :-DD\n" + line);
                } else {
                    System.out.println(line + makeList(list) + line);
                }
            } else if (userInput.startsWith("mark")){
                String task = userInput.substring(5);
                String s = markDone(task, list);
                System.out.println(line + s + line);
            } else if (userInput.startsWith("unmark")){
                String task = userInput.substring(7);
                String s = unmarkDone(task, list);
                System.out.println(line + s + line);
            } else if (userInput.startsWith("delete")) {
                try {
                    deleteItem(userInput.substring(7), list);
                } catch (DukeException e) {
                    System.out.println(line + e.getMessage() + "\n" + line);
                }
            } else {
                try {
                    addTask(userInput, list);
                } catch (DukeException e) {
                    System.out.println(line + e.getMessage() + "\n" + line);
                }
            }
        }
    }

    public static String makeList(ArrayList<Task> ls) {
        String s = "";
        for (int i = 0; i < ls.size(); i++) {
            int index = i + 1;
            s += " " + index + "." + ls.get(i).toString() + "\n";
        }
        return s;
    }

    public static String markDone(String task, ArrayList<Task> ls) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getTask().equals(task)) {
                ls.get(i).markDone();
                return " okie! " + task + " is done ~\n [X] " + task + "\n";
            }
        }
        return task + " not found :(\n";
    }

    public static String unmarkDone(String task, ArrayList<Task> ls) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getTask().equals(task)) {
                ls.get(i).unmarkDone();
                return " owh ;< so you haven't done " + task + ". unmarked ~\n [ ] " + task + "\n";
            }
        }
        return task + " not found :(\n";
    }

    public static void addTask(String userInput, ArrayList<Task> ls) throws DukeException {
        String line = " _______________________________________ \n";
        if (!userInput.startsWith("todo") && !userInput.startsWith("deadline") && !userInput.startsWith("event")) {
            throw new DukeException("sowwie idk what this means.");
        } else if (!userInput.contains(" ") || userInput.substring(userInput.indexOf(" ")).trim().isEmpty()) {
            throw new DukeException("the description of a task cannot be empty.");
        }
        Task t = new Task(userInput.substring(userInput.indexOf(" ") + 1),
                userInput.substring(0, userInput.indexOf(" ")).toUpperCase(), false);
        ls.add(t);
        System.out.println(line + " okie! i've added: \n " + t + "\n now you have " + ls.size() + " task(s) in your list!\n" + line);
    }

    public static void deleteItem(String userInput, ArrayList<Task> ls) throws DukeException {
        String line = " _______________________________________ \n";
        int index = Integer.parseInt(userInput.trim());
        if (index <= 0 || index > ls.size()) {
            throw new DukeException("sowwie this item is not found. enter a valid index number from list please!");
        }
        Task taskRemoved = ls.get(index - 1);
        ls.remove(index - 1);
        System.out.println(line + " okie! i've removed: \n " + taskRemoved + "\n now you have " + ls.size() + " task(s) in your list!\n" + line);
    }
}