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
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";

        while (!command.equals(quit)) {
            command = scanner.nextLine();

            if (!command.equals(quit)) {
                if (command.equals(list)) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(String.valueOf(i + 1) + "." + task);
                    }
    
                } else if (command.substring(0,4).equals(mark)) {
                    int taskNumber = Integer.parseInt(command.substring(5));
                    tasks.get(taskNumber - 1).setDone();
    
    
                } else if (command.substring(0, 6).equals(unmark)) {
                    int taskNumber = Integer.parseInt(command.substring(7));
                    tasks.get(taskNumber - 1).setUndone();
                    
    
                } else {
                    System.out.println("Got it. I've added this task:"); 
    
                    if (command.substring(0, 4).equals(todo)) {
                        Task task = new Todo(command.substring(5));
                        tasks.add(task);
                        System.out.println(task);
    
                    } else if (command.substring(0, 5).equals(event)) {
                        String[] split = command.substring(6).split("/");
                        Task task = new Event(split[0], split[1].substring(3));
                        tasks.add(task);
                        System.out.println(task);
    
                    } else if (command.substring(0,8).equals(deadline)) {
                        String[] split = command.substring(9).split("/");
                        Task task = new Deadline(split[0], split[1].substring(3));
                        tasks.add(task);
                        System.out.println(task);
    
                    } else {
                        tasks.add(new Task(command));
                        System.out.println("added: " + command);
                    }
    
                    System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ?" task" : " tasks") + " in the list.");
                }
                
            } else {
                System.out.println("Bye! Thanks for using Luke!");
                scanner.close();
            }
        }
            
            
            
        

        
    }
}
