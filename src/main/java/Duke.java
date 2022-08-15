import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        System.out.println("Hello! I'm Luke\nWhat can I do for you?");
        String command = "";
        String quit = "bye";
        String list = "list";

        while (!command.equals(quit)) {
            command = scanner.nextLine();

            if (command.equals(list)) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + tasks.get(i));
                }

            } else if (!command.equals(quit)) {  
                System.out.println("added: " + command);
                tasks.add(command);
                
            } else {
                System.out.println("Bye! Thanks for using Luke!");
                scanner.close();
            }
        }
        

        
    }
}
