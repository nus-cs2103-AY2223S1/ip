import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Storage class which stores data for Duke.
 *
 * @author Leong Jia Hao Daniel
 */
public class Storage {

    private File currentFile;

    /**
     *
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.currentFile = new File(filePath);
    }

    public void loadFile() throws IOException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File("data/duke.txt");
        try {
            if (data.exists()) {
                Scanner fileScanner = new Scanner(data);
                while (fileScanner.hasNext()) {
                    String string = fileScanner.nextLine();
                    char taskType = string.charAt(0);
                    try {
                        switch (taskType) {
                            case 'T':
                                ToDo todo = ToDo.parseFile(string);
                                Duke.loadTask(todo);
                                break;
                            case 'D':
                                Deadline deadline = Deadline.parseFile(string);
                                Duke.loadTask(deadline);
                                break;
                            case 'E':
                                Event event = Event.parseFile(string);
                                Duke.loadTask(event);
                                break;
                            default:
                                throw new DukeException("Error in storage!");
                        }
                    } catch (DukeException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                data.createNewFile();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveFile(ArrayList<Task> data) throws IOException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileWriter writer = new FileWriter("data/duke.txt", false);
            for (Task task : data) {
                writer.write(task.toDataFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
