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

        // Queries for tasks until terminated
        while (true) {
            String word = br.readLine();
            // 1. Say goodbye
            if (word.equals("bye")) { 
                System.out.println(spacing);
                System.out.println("Goobye, see you again!\n");
                System.out.println(spacing + "\n");
                break;
            } 

            // 2. Lists out all the tasks
            else if (word.equals("list")) { 
                System.out.println(spacing);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numTasks; i++) {
                    System.out.println(Integer.toString(i+1) + "." + tasks[i].toString());
                }
                System.out.println("\n" + spacing + "\n");
            }

            // 3. Mark task as done 
            else if (word.startsWith("mark")) {
                int taskNumber = Integer.parseInt(word.split(" ")[1]) - 1;
                tasks[taskNumber].setStatus(1);
                System.out.println(spacing);
                System.out.println("Nice! I have marked this task as done:");
                System.out.println("[" + tasks[taskNumber].getStatusIcon() +"] " + tasks[taskNumber].getTask());
                System.out.println(spacing + "\n");
            }

            // 4. Mark task as undone
            else if (word.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(word.split(" ")[1]) - 1;
                tasks[taskNumber].setStatus(0);
                System.out.println(spacing);
                System.out.println("Ok, I have marked this task as not done yet:");
                System.out.println("[" + tasks[taskNumber].getStatusIcon() +"] " + tasks[taskNumber].getTask());
                System.out.println(spacing + "\n");
            }

            // 5. Adding in tasks
            // Todo
            else if (word.startsWith("todo")) {
                String todoTask = word.replace("todo ", "");
                Todo todo = new Todo(todoTask);
                tasks[numTasks] = todo;
                numTasks += 1;
                System.out.println("\n" +spacing);
                System.out.println("Got it. I've added this task:");
                System.out.println(todo.toString());
                System.out.println("Now you have " + Integer.toString(numTasks) + " tasks in the list.\n");
                System.out.println(spacing + "\n");
            }

            // Deadline
            else if (word.startsWith("deadline")) {
                String[] deadlineTask = (word.replace("deadline ", "")).split("/by ");
                Deadline deadline = new Deadline(deadlineTask[0], deadlineTask[1]);
                tasks[numTasks] = deadline;
                numTasks += 1;
                System.out.println("\n" +spacing);
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline.toString());
                System.out.println("Now you have " + Integer.toString(numTasks) + " tasks in the list.\n");
                System.out.println(spacing + "\n");
            }

            // Event
            else if (word.startsWith("event")) {
                String[] eventTask = word.replace("event ", "").split("/at ");
                Event event = new Event(eventTask[0], eventTask[1]);
                tasks[numTasks] = event;
                numTasks += 1;
                System.out.println("\n" +spacing);
                System.out.println("Got it. I've added this task:");
                System.out.println(event.toString());
                System.out.println("Now you have " + Integer.toString(numTasks) + " tasks in the list.\n");
                System.out.println(spacing + "\n");
            }
        }
    }
}