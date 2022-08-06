import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> ls = new ArrayList<Task>();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String command = sc.nextLine();

        while(!(command.equals("bye"))) {
            // case 1: list
            if(command.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < ls.size(); i++){
                    int index = i + 1;
                    System.out.println(index + ". " + ls.get(i).toString());
                }
            }
            //case 2: mark
            else if(command.startsWith("mark")){
                System.out.println("Nice! I've marked this task as done:");
                String[] s = command.split(" ");
                int index = Integer.parseInt(s[1]);
                ls.get(index - 1).setDone(); // minus -1 because of list starts from 1.
            }
            // case 3: unmark
            else if(command.startsWith("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                String[] s = command.split(" ");
                int index = Integer.parseInt(s[1]);
                ls.get(index - 1).setNotDone();
            }
            else{   // add commands
                ls.add(new Task(command));
                System.out.println("added: " + command);
            }
            // remember to update to the next line
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}



