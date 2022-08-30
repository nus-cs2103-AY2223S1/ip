package utility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Storage {

    private final String filePath;

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private void emptyFile(String filePath) throws IOException {
        new FileWriter(filePath, false).close();
    }

    public void save(Task task) {
        try {
            appendToFile(this.filePath, task.toSave() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    

    public void update(List<Task> taskList) {
        try {
            emptyFile(this.filePath);
            for (int i = 0; i < taskList.size(); i++) {
                appendToFile(this.filePath, taskList.get(i).toSave() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public List<Task> load() {
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
                String at = input.substring(input.lastIndexOf(": ") + 2, input.length() - 1);
                String desciption = input.substring(8, input.lastIndexOf(": ") - 4);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime date = LocalDateTime.parse(at, formatter);
                Task newTask = new Event(desciption, date);
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
                at = input.substring(input.lastIndexOf(": ") + 2, input.length() - 1);
                desciption = input.substring(8, input.lastIndexOf(": ") - 4);
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                date = LocalDateTime.parse(at, formatter);
                newTask = new Deadline(desciption, date);
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
