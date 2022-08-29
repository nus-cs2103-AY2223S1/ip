package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The storage where data for Duke is stored.
 *
 * @author Bryan Ng Zi Hao
 */
public class Storage {
    private File currentFile;

    /**
     * Constructor for Storage.
     *
     * @param filePath The location where the data is stored.
     */
    public Storage(String filePath) {
        this.currentFile = new File(filePath);
    }

    /**
     * Loads the given file stored in filePath and checks if any existing data
     * is stored there. If so, the data is used for Duke's run. Else, a new
     * memory file will be created.
     *
     * @return An ArrayList of Tasks stored in the filePath.
     * @throws DukeException File could not be found or created.
     */
    public ArrayList<Task> loadFile() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File dataFile = this.currentFile;
        if (dataFile.exists()) {
            try {
                Scanner s = new Scanner(dataFile);
                while (s.hasNext()) {
                    String data = s.nextLine();
                    char type = data.charAt(0);
                    switch (type) {
                    case 'T':
                        ToDo todo = ToDo.parseFile(data);
                        taskList.add(todo);
                        break;
                    case 'D':
                        Deadline deadline = Deadline.parseFile(data);
                        taskList.add(deadline);
                        break;
                    case 'E':
                        Event event = Event.parseFile(data);
                        taskList.add(event);
                        break;
                    default:
                        throw new DukeException("File format is invalid!");
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return taskList;
    }

    /**
     * Rewrites the file in the filePath location to include all the newly
     * edited and created tasks.
     *
     * @param data The list of ArrayList of tasks to be added to the txt file.
     * @throws DukeException File could not be written.
     */
    public void writeFile(ArrayList<Task> data) throws DukeException {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task: data) {
                fw.write(task.toDataFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
