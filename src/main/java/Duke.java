import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Hello! I'm Luke\nWhat can I do for you?");
        String command = "";
        String quit = "bye";
        String list = "list";
        String mark = "mark";
        String unmark = "unmark";

        while (!command.equals(quit)) {
            command = scanner.nextLine();

            if (command.equals(list)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println(String.valueOf(i + 1) + ".[" + task.getStatusIcon() + "] " + task.getDescription());
                }

            } else if (command.substring(0,4).equals(mark)) {
                int taskNumber = Integer.parseInt(command.substring(5));
                tasks.get(taskNumber - 1).setDone();


            } else if (command.substring(0, 6).equals(unmark)) {
                int taskNumber = Integer.parseInt(command.substring(7));
                tasks.get(taskNumber - 1).setUndone();
                
            } else if (!command.equals(quit)) {  
                System.out.println("added: " + command);
                tasks.add(new Task(command));
                
            } else {
                System.out.println("Bye! Thanks for using Luke!");
                scanner.close();
            }
        }
            
            
            
        

        
    }
}
