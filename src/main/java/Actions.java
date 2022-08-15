import java.util.*;

public class Actions {
    static String doneSymbol = "[X]";
    public static void greetEcho() {
        String input = "";
        System.out.println("Hello! I'm Duke, what's up today?");
        while (input != "bye") {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("See ya!");
                return;
            }
            else {
                System.out.println(input);
            }
        }
    }
    public static void toDoList() {
        ArrayList<Task> ls = new ArrayList();
        String input = "";
        System.out.println("Hello! I'm Duke, what's up today?");
        while (input != "bye") {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            String[] parts = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("See ya! Come again~");
                return;
            }
            else if (input.equals("list")) {
                System.out.println("Here are your tasks: ");
                for (int i = 0; i < ls.size(); i++) {
                    System.out.println(i + 1 + ". " + ls.get(i).toString());
                }
            }
            else if ("mark".equals(parts[0])) { //parts[1] is the task to mark
                Task currTask = ls.get(Integer.parseInt(parts[1]) - 1); //the arraylist is 0 indexed, so task 1 is actually 0 index
                currTask.setDone();
                System.out.println("Let's go! I've marked this task as done: ");
                System.out.println(currTask);
            }
            else if ("unmark".equals(parts[0])) { //parts[1] is the task to mark
                Task currTask = ls.get(Integer.parseInt(parts[1]) - 1); //the arraylist is 0 indexed, so task 1 is actually 0 index
                currTask.setUndone();
                System.out.println("Oh man! I've marked this task as undone: ");
                System.out.println(currTask);
            }
            else {
                Task curr = new Task(input);
                ls.add(curr);
                System.out.println("added: " + input);
            }
        }
    }
}
