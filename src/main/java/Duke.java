import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> ls = new ArrayList<String>();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String command = sc.nextLine();

        while(!(command.equals("bye"))) {
            // case 1: list
            if(command.equals("list")){
                for(int i = 0; i < ls.size(); i++){
                    int index = i + 1;
                    System.out.println(index + ". " + ls.get(i));
                }
            }
            else{   // add commands
                ls.add(command);
                System.out.println("added: " + command);
            }
            // remember to update to the next line
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}

