import Exceptions.InvalidInstructionException;
import Exceptions.InvalidNumberException;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> ls = new ArrayList<Task>();
        TaskManager tm = new TaskManager(ls);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String command = sc.nextLine();

        while(!(command.equals("bye"))) {
            // case 1: list
            if(command.equals("list")){
                System.out.println("Here are the tasks in your list:");
                tm.printAllOut();
            }
            //case 2: mark
            else if(command.startsWith("mark")){
                try{
                    String[] s = command.split(" ");
                    int index = Integer.parseInt(s[1]);
                    if(index < 0 || index > tm.getSize())
                        throw new InvalidNumberException();
                    ls.get(index - 1).setDone(); // minus -1 because of list starts from 1.
                    System.out.println("Nice! I've marked this task as done:");
                }catch(InvalidNumberException e){
                    System.out.println(e.getMessage());
                }
            }
            // case 3: unmark
            else if(command.startsWith("unmark")) {
                try{
                    String[] s = command.split(" ");
                    int index = Integer.parseInt(s[1]);
                    if(index < 0 || index > tm.getSize())
                        throw new InvalidNumberException();
                    ls.get(index - 1).setNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                }catch(InvalidNumberException e){
                    System.out.println(e.getMessage());
                }
            }
            // case 4: todo
            else if(command.startsWith("todo")){
                String substr = command.substring(4).trim();
                ls.add(new Todo(substr));
                System.out.println("Got it. I've added this task:");
                System.out.println(ls.get(ls.size() - 1));
                System.out.println("Now you have " + tm.getSize() + " tasks in the list.");
            }
            //case 5: deadline
            else if(command.startsWith("deadline")){
                String[] s = command.split("/by");
                String by = s[1].trim();
                String substr = s[0].substring(8).trim();
                ls.add(new Deadline(substr,by));
                System.out.println("Got it. I've added this task:");
                System.out.println(ls.get(ls.size() - 1));
                System.out.println("Now you have " + tm.getSize() + " tasks in the list.");

            }
            //case 6: event
            else if(command.startsWith("event")){
                String[] s = command.split("/at");
                String at = s[1].trim();
                String substr = s[0].substring(5).trim();
                ls.add(new Event(substr,at));
                System.out.println("Got it. I've added this task:");
                System.out.println(ls.get(ls.size() - 1));
                System.out.println("Now you have " + tm.getSize() + " tasks in the list.");
            }
            //case 7: delete
            else if(command.startsWith("delete")){
                String[] s = command.split(" ");
                int index = Integer.parseInt(s[1].trim());
                System.out.println("Noted. I've removed this task:");
                System.out.println(ls.get(index - 1));
                tm.remove(index - 1);
                System.out.println("Now you have " + tm.getSize() + " tasks in the list.");
            }
            else{   // add commands
                try{
                    throw new InvalidInstructionException();
                }catch(InvalidInstructionException e) {
                    System.out.println(e.getMessage());
                }
            }
            // remember to update to the next line
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}



