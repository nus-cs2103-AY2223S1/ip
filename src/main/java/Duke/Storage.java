/**
 * This class deals with loading tasks from the file and saving tasks in the file
 */
package Duke;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Writes the tasks into a file
     */
    public void writeFile(ArrayList<String> tasks) {
        File file = new File(filepath);

        createDirectoryAndFile(file);

        // Write into File
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < tasks.size(); i++) {
                fileWriter.write(tasks.get(i));
            }
            fileWriter.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the existing saved file to task list
     */
    public ArrayList<Task> readFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filepath));
            String readLine = reader.readLine();
            while (readLine != null) {
                String[] parse = readLine.split(" > ");
                Task task;
                if (parse[0].equals(Constants.TODO)) {
                    task = new Todo(parse[2]);
                } else if (parse[0].equals(Constants.EVENT)) {
                    task = new Event(parse[2], LocalDateTime.parse(parse[3], formatter));
                } else if (parse[0].equals(Constants.DEADLINE)) {
                    task = new Deadline(parse[2], LocalDateTime.parse(parse[3], formatter));
                } else {
                    throw new DukeException(Constants.INVALID_FILE);
                }
                if (parse[1].equals("[X]")) {
                    task.setMarked();
                }
                tasks.add(task);
                readLine = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            createDirectoryAndFile(new File(filepath));
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Creates a new directory and file if the file does not exist
     * @param file
     */
    private void createDirectoryAndFile(File file) {
        // Create Directory or File if it does not exist
        try {
            if (!file.exists()) {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
