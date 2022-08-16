import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> listOfTask = new ArrayList<>();
     static void showList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= listOfTask.size(); i++) {
            System.out.println(i + "." + listOfTask.get(i - 1).getMarkStatus());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String s = input.nextLine();
            if (s.equals("bye")) {
                break;
            } else if (s.equals("list")) {
                showList();
            } else if (s.length() > 5 && s.substring(0, 4).equals("mark")) {
                int i = Integer.parseInt(s.substring(5, 6)) - 1;
                Task task =  listOfTask.get(i);
                task.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.getMarkStatus());
            } else if (s.length() > 7 && s.substring(0, 6).equals("unmark")) {
                int i = Integer.parseInt(s.substring(7, 8)) - 1;
                Task task = listOfTask.get(i);
                task.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task.getMarkStatus());
            } else {
                listOfTask.add(new Task(s));
                System.out.println("added: " + s);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}


