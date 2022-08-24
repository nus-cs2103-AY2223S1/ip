import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class FileReading {
    
  public static List<Task> load() {
    try {
        List<Task> tasksList = new ArrayList<Task>(100);
        File myObj = new File("src\\main\\java\\SavedTask.txt");
        Scanner myReader = new Scanner(myObj);

        while (myReader.hasNextLine()) {
            
            String input = myReader.nextLine();

            if (input.isEmpty()) {
                break;
            }
            char keyword = input.charAt(1);

            switch (keyword) {
                case 'E':
                char icon = input.charAt(4); 
                String at = input.substring(input.lastIndexOf(": ") + 1, input.length() - 1);
                String desciption = input.substring(8, input.lastIndexOf(": ") - 4);
                Task newTask = new Event(desciption, at);
                if (icon == ' ') {
                    tasksList.add(newTask);
                } else {
                    newTask.mark();
                    tasksList.add(newTask);
                }
                break;

                case 'T':
                icon = input.charAt(4); 
                desciption = input.substring(8);
                newTask = new Todo(desciption);
                if (icon == ' ') {
                    tasksList.add(newTask);
                } else {
                    newTask.mark();
                    tasksList.add(newTask);
                }
                break;

                case 'D':
                icon = input.charAt(4);
                at = input.substring(input.lastIndexOf(": ") + 1, input.length() - 1);
                desciption = input.substring(8, input.lastIndexOf(": ") - 4);
                newTask = new Deadline(desciption, at);
                if (icon == ' ') {
                    tasksList.add(newTask);
                } else {
                    newTask.mark();
                    tasksList.add(newTask);
                }
                break;
        }


    }
    
    myReader.close();
    return tasksList;
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } 
    return new ArrayList<Task>(100);
  } 
}
