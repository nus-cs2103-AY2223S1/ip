package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/*
 * Storage class for Duke program. This takes care of reading and writing to the file.
 */
public class Storage {
    public String filePath;
    public FileWriter fileWriter;

    /*
     * Constructor for Storage class. This takes in the path of the file to be read
     * and written to.
     * 
     * @param filePath The path of the file to be read and written to.
     */
    public Storage(String filePath) throws DukeException, IOException {
        this.filePath = filePath;
        initialiseList(filePath);
    }

    /*
     * Initialises the file to be read and written to. If the file does not exist,
     * it will be created.
     * 
     * @param filePath The path of the file to be read and written to.
     */
    public void initialiseList(String filePath) throws DukeException, IOException {
        try {
            // Check if filePath has directories
            Path path = Path.of(filePath);
            if (path.getParent() != null) {
                // Create directories if they do not exist
                Files.createDirectories(path.getParent());
            }
            // Create file if it does not exist, or open it if it does
            if (path.toFile().createNewFile()) {
                System.out.println("File created: " + path.toFile().getName());
            } else {
                System.out.println("File opened: " + path.toFile().getName());
            }
            assert (path.toFile().exists());
        } catch (IOException e) {
            throw new DukeException("Unable to create file.");
        }
    }

    /*
     * Loads the list of tasks from the file.
     * 
     * @return The list of tasks.
     */
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
                    LocalDateTime dateTime = LocalDateTime.parse(by);
                    list.add(new Deadline(description, dateTime, isDone));
                } else if (type.equals("E")) {
                    String at = lineArray[3];
                    LocalDate date = LocalDate.parse(at);
                    list.add(new Event(description, date, isDone));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new DukeException("Unable to read file and load list.");
        }
        return list;
    }

    /*
     * Writes the list of tasks to the file.
     * 
     * @param list The list of tasks to be written to the file.
     */
    public void saveList(TaskList list) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            assert (fileWriter != null);
            // Save to file
            for (Task task : list.getList()) {
                fileWriter.write(task.save() + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            throw new DukeException("Unable to save list.");
        }
    }
}
