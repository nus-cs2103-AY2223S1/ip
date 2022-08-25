import java.util.Scanner;
import java.util.ArrayList;

public class Chacha {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chacha\n" + "What can I do for you?");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                for (int i = 0; i < taskList.size();i++) {
                    Task t = taskList.get(i);	      
	                System.out.println(i + 1 + 
                        ".[" + 
                        t.getStatusIcon() + 
                        "] " + 
                        t.getDescription()); 		
	            }   
                 
            } else if (s.contains("unmark")) {
                
                String[] split = s.split("\\s+");
                Task task = taskList.get(Integer.valueOf(split[1]) - 1);
                task.unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:\n  [" + 
                task.getStatusIcon() + 
                "] " + 
                task.getDescription());

            } else if (s.contains("mark")) {
                System.out.println(s.substring(0, 3));
                String[] split = s.split("\\s+");
                Task task = taskList.get(Integer.valueOf(split[1]) - 1);
                System.out.println("here");
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  [" + 
                task.getStatusIcon() + 
                "] " + 
                task.getDescription());

            } else {
                Task task = new Task(s);
                taskList.add(task);  
                System.out.println("added: " + task.getDescription());  
                 
            } 
            s = input.nextLine();    
        }
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }
}
