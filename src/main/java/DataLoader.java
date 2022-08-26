import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataLoader {
    
    public static ArrayList<Task> loadData(String path) throws FileNotFoundException, IanaException {
        ArrayList<Task> todoList = new ArrayList<Task>();

        try{
            File file = new File(path);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] taskArray = input.split(" \\| ", 4);
                String taskTime;
                String taskString;

                try {
                    String taskClass = taskArray[0];
                    boolean isCompleted = Integer.parseInt(taskArray[1]) == 1; // cannot just check int val as string
                    String taskDescription =  taskArray[2];

                    switch(taskClass) {
                        case "T":
                        taskString = String.format("todo %s", taskDescription);
                        Task todo = Task.of(taskString);
                        todo.toggleComplete(isCompleted);
                        todoList.add(todo);
                        break;

                        case "E":
                        taskTime = taskArray[3];
                        taskString = String.format("event %s /at %s", taskDescription, taskTime);
                        Task event = Task.of(taskString);
                        event.toggleComplete(isCompleted);
                        todoList.add(event);
                        break;

                        case "D":
                        taskTime = taskArray[3];
                        taskString = String.format("deadline %s /by %s", taskDescription, taskTime);
                        Task deadline = Task.of(taskString);
                        deadline.toggleComplete(isCompleted);
                        todoList.add(deadline);
                        break;
                    }
                } catch (Exception e) {
                    throw new IanaException("File is corrupted!!");
                }
            }
            sc.close();
            return todoList;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } 
    } 
}
