import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws Exception{
        // Reader and Writers
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Variables
        String spacing = "-----------------------------------------";
        Task[] tasks = new Task[100];
        int numTasks = 0;


        // Main code
        System.out.println(spacing);
        System.out.println("Hello! I am Greg!");
        System.out.println("What do you need help with?");
        System.out.println(spacing + "\n");
        while (true) {
            String word = br.readLine();
            String[] splitWord = word.split(" ");
            // Say goodbye
            if (word.equals("bye")) { 
                System.out.println(spacing);
                System.out.println("Goobye, see you again!\n");
                System.out.println(spacing + "\n");
                break;
            } 

            // Lists out all the tasks
            else if (word.equals("list")) { 
                System.out.println(spacing);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numTasks; i++) {
                    Task task = tasks[i];
                    String output = Integer.toString(i+1) + ". [" + task.getStatusIcon()+ "] " + task.getTask();
                    System.out.println(output);
                }
                System.out.println("\n" + spacing + "\n");
            }

            // Mark task as done 
            else if (splitWord[0].equals("mark")) {
                int taskNumber = Integer.parseInt(splitWord[1]) - 1;
                tasks[taskNumber].setStatus(1);
                System.out.println(spacing);
                System.out.println("Nice! I have marked this task as done:");
                System.out.println("[" + tasks[taskNumber].getStatusIcon() +"] " + tasks[taskNumber].getTask());
                System.out.println(spacing + "\n");
            }

            // Mark task as undone
            else if (splitWord[0].equals("unmark")) {
                int taskNumber = Integer.parseInt(splitWord[1]) - 1;
                tasks[taskNumber].setStatus(0);
                System.out.println(spacing);
                System.out.println("Ok, I have marked this task as not done yet:");
                System.out.println("[" + tasks[taskNumber].getStatusIcon() +"] " + tasks[taskNumber].getTask());
                System.out.println(spacing + "\n");
            }

            // Adds in tasks
            else {
                Task task = new Task(word);
                tasks[numTasks] = task;
                numTasks += 1;
                System.out.println(spacing);
                System.out.println("added: " + word + "\n");
                System.out.println(spacing + "\n");
            }
        }
    }
}