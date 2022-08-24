import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class DataLoader {
    
    public static ArrayList<Task> loadData(String path) throws FileNotFoundException, DukeException {
        ArrayList<Task> todoList = new ArrayList<Task>();

        try{
            File file = new File(path);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                //String[] taskArray = input.split(" ", 2);

                try {
                    Task newTask = Task.of(input);
                    todoList.add(newTask);
                } catch (DukeException e) {
                    throw new DukeException("File is corrupted!");
                }
            }
            return todoList;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } 
    } 
}
