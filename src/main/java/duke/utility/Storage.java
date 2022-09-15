package duke.utility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents class for storing
 * and reading to and from txt file
 */
public class Storage {

    private final String filePath;

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Instantiates a new Storage object
     */
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

    /**
     * Adds task to text file to be saved
     *
     * @param task tasks we want to add to text file to be saved
     */
    public void save(Task task) {
        try {
            appendToFile(this.filePath, task.toSave() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * updates and add all task to text file
     *
     * @param taskList the list we add into the text file
     */
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

    /**
     * Loads task from text file into List
     *
     * @return List of tasks given in text file
     */
    public List<Task> load() {
        try {
            List<Task> tasksList = new ArrayList<Task>(100);
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String input = myReader.nextLine();
                if (input.isEmpty()) {
                    break;
                }
                char keyword = input.charAt(1);
                switch (keyword) {
                case 'E':
                    loadEvent(input, tasksList);
                    break;
                case 'T':
                    loadTodo(input, tasksList);
                    break;
                case 'D':
                    loadDeadline(input, tasksList);
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

    public void loadEvent(String input, List<Task> tasksList) {
        char icon = input.charAt(4);
        String at = input.substring(input.lastIndexOf(": ") + 2, input.length() - 1);
        String description = input.substring(7, input.lastIndexOf(": ") - 4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(at, formatter);
        Task newTask = new Event(description, date);
        if (icon == ' ') {
            tasksList.add(newTask);
        } else {
            newTask.mark();
            tasksList.add(newTask);
        }
    }

    public void loadTodo(String input, List<Task> tasksList) {
        char icon = input.charAt(4);
        String description = input.substring(7);
        Task newTask = new Todo(description);
        if (icon == ' ') {
            tasksList.add(newTask);
        } else {
            newTask.mark();
            tasksList.add(newTask);
        }
    }

    public void loadDeadline(String input, List<Task> tasksList) {
        char icon = input.charAt(4);
        String at = input.substring(input.lastIndexOf(": ") + 2, input.length() - 1);
        String description = input.substring(7, input.lastIndexOf(": ") - 4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(at, formatter);
        Task newTask = new Deadline(description, date);
        if (icon == ' ') {
            tasksList.add(newTask);
        } else {
            newTask.mark();
            tasksList.add(newTask);
        }
    }
}
