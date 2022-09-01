import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) throws Exception{
        // Reader and Writers
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Variables
        String spacing = "-----------------------------------------";
        ArrayList<Task> tasks = new ArrayList<Task>();
        int numTasks = 0;

        // Main code
        System.out.println(spacing);
        System.out.println("Hello! I am Greg!");
        System.out.println("What do you need help with?");
        System.out.println(spacing + "\n");

        // File check and creation
        new File("./data").mkdirs();
        File dataStore = new File("./data/duke.txt"); 
        if (dataStore.exists()) {
            BufferedReader fileRead = new BufferedReader(new FileReader(dataStore));
            String st;
            while ((st = fileRead.readLine()) != null) {
                String[] stSplit = st.split(" \\| ");
                if (stSplit[0].equals("T")) {
                    Todo newTask = new Todo(stSplit[2]);
                    tasks.add(newTask);
                    numTasks += 1;
                }
                else if (stSplit[0].equals("D")) {
                    Deadline newTask = new Deadline(stSplit[2], stSplit[3]);
                    tasks.add(newTask);
                    numTasks += 1;
                } 
                else if (stSplit[0].equals("E")) {
                    Event newTask = new Event(stSplit[2], stSplit[3]);
                    tasks.add(newTask);
                    numTasks += 1;
                }
                if (stSplit[1].equals("1")) {
                    tasks.get(numTasks-1).setStatus(1);
                }
            }
            fileRead.close();
        }

        // Queries for tasks until terminated
        while (true) {
            String word = br.readLine();
            // 1. Say goodbye
            if (word.equals("bye")) { 
                System.out.println("\n" + spacing);
                System.out.println("Goobye, see you again!\n");
                System.out.println(spacing + "\n");
                return;
            } 

            // 2. Lists out all the tasks
            else if (word.equals("list")) { 
                System.out.println("\n" + spacing);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numTasks; i++) {
                    System.out.println(Integer.toString(i+1) + "." + tasks.get(i).toString());
                }
                System.out.println("\n" + spacing + "\n");
            }

            // 3. Mark task as done 
            else if (word.startsWith("mark")) {
                int taskNumber = Integer.parseInt(word.split(" ")[1]) - 1;
                tasks.get(taskNumber).setStatus(1);
                System.out.println("\n" + spacing);
                System.out.println("Nice! I have marked this task as done:");
                System.out.println("[" + tasks.get(taskNumber).getStatusIcon() +"] " + tasks.get(taskNumber).getTask());
                System.out.println(spacing + "\n");
            }

            // 4. Mark task as undone
            else if (word.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(word.split(" ")[1]) - 1;
                tasks.get(taskNumber).setStatus(0);
                System.out.println("\n" + spacing);
                System.out.println("Ok, I have marked this task as not done yet:");
                System.out.println("[" + tasks.get(taskNumber).getStatusIcon() +"] " + tasks.get(taskNumber).getTask());
                System.out.println(spacing + "\n");
            }

            // 5. Adding in tasks
            // Todo
            else if (word.startsWith("todo")) {
                String todoTask = word.replace("todo ", "");
                Todo todo = new Todo(todoTask);
                tasks.add(todo);
                numTasks += 1;
                System.out.println("\n" +spacing);
                System.out.println("Got it. I've added this task:");
                System.out.println(todo.toString());
                System.out.println("Now you have " + Integer.toString(numTasks) + " tasks in the list.\n");
                System.out.println(spacing + "\n");
            }

            // Deadline
            else if (word.startsWith("deadline")) {
                String[] deadlineTask = (word.replace("deadline ", "")).split(" /by ");
                Deadline deadline = new Deadline(deadlineTask[0], deadlineTask[1]);
                tasks.add(deadline);
                numTasks += 1;
                System.out.println("\n" +spacing);
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline.toString());
                System.out.println("Now you have " + Integer.toString(numTasks) + " tasks in the list.\n");
                System.out.println(spacing + "\n");
            }

            // Event
            else if (word.startsWith("event")) {
                String[] eventTask = word.replace("event ", "").split(" /at ");
                Event event = new Event(eventTask[0], eventTask[1]);
                tasks.add(event);
                numTasks += 1;
                System.out.println("\n" +spacing);
                System.out.println("Got it. I've added this task:");
                System.out.println(event.toString());
                System.out.println("Now you have " + Integer.toString(numTasks) + " tasks in the list.\n");
                System.out.println(spacing + "\n");
            }

            // 6. Deleting tasks
            else if (word.startsWith("delete")) {
                if (numTasks == 0) { // No tasks in list
                    System.out.println("\n" + spacing);
                    System.out.println("List empty. Add tasks into your list!\n");
                    System.out.println(spacing + "\n");
                } else { 
                    String[] deleteTask = word.split(" ");
                    int taskIndex = Integer.parseInt(deleteTask[1]);
                    if (taskIndex > numTasks) {
                        System.out.println("\n" + spacing);
                    System.out.println("No such task!\n");
                    System.out.println(spacing + "\n");
                    } else {
                        Task deletedTask = tasks.remove(taskIndex - 1);
                        numTasks -= 1;
                        System.out.println("\n" + spacing);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(deletedTask.toString());
                        System.out.println("Now you have " + Integer.toString(numTasks) + " tasks in the list.\n");
                        System.out.println(spacing + "\n");
                    }
                }
            }

            // Invalid input
            else {
                System.out.println("\n" +spacing);
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                System.out.println(spacing + "\n");
            }

            // Writing to file when something is done
            FileWriter myWriter = new FileWriter("./data/duke.txt");
            for (int i = 0; i < numTasks; i++) {
                Task t = tasks.get(i);
                if (t instanceof Deadline || t instanceof Event) {
                    myWriter.write(t.getTaskType() + " | " +  t.getBinaryStatus() + " | " + t.getTask() + " | " + t.getDue() + "\n");
                } else {
                    myWriter.write(t.getTaskType() + " | " +  t.getBinaryStatus() + " | " + t.getTask() + "\n");
                }
            }
            myWriter.close();
        }
    }
}