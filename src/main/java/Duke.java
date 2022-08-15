import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
      ArrayList<Task> listOfTask = new ArrayList<Task>();
        while (input.hasNextLine()) {
            String s = input.nextLine();
            if (s.equals("bye")) {
                break;
            } else if (s.equals("list")) {
                    for (int i = 1; i <= listOfTask.size(); i++) {
                        System.out.println(i + "." + listOfTask.get(i-1).getName());
                    }
           } else {
                listOfTask.add(new Task(s));
                System.out.println("added: " + s);
            }
        }
       System.out.println("Bye. Hope to see you again soon!");
    }
}


