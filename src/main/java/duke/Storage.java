package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class Storage {
    public String filePath;
    public FileWriter fileWriter;

    public Storage(String filePath) throws DukeException, IOException {
        this.filePath = filePath;
        this.fileWriter = initialiseList(filePath);
    }

    public FileWriter initialiseList(String filePath) throws DukeException, IOException {
        try {
            // Check if filePath has directories
            Path path = Path.of(filePath);
            if (path.getParent() != null) {
                // Create directories if they do not exist
                Files.createDirectories(path.getParent());
            }
            // Create file if it does not exist, or open it if it does
            return new FileWriter(filePath, true);
        } catch (IOException e) {
            throw new DukeException("Unable to create file.");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            // Read file and create TaskList
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArray = line.split(" \\| ");
                String type = lineArray[0];
                boolean isDone = lineArray[1].equals("1");
                String description = lineArray[2];
                if (type.equals("T")) {
                    list.add(new Todo(description, isDone));
                } else if (type.equals("D")) {
                    String by = lineArray[3];
                    String date = by.split(" ")[0];
                    String time = by.split(" ")[1];
                    list.add(new Deadline(description, date, time, isDone));
                } else if (type.equals("E")) {
                    String at = lineArray[3];
                    list.add(new Event(description, at, isDone));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new DukeException("Unable to read file and load list.");
        }
        return list;
    }

    public void saveList(TaskList list) {
        try {
            // Save to file
            for (int i = 0; i < list.size(); i++) {
                fileWriter.write(list.get(i).toString() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error while saving list to file.");
        }
    }
}
