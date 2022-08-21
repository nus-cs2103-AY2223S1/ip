import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class saves tasks to hard disk
 */
public class Storage {
    private Path filePath;
    private Path folderPath;

    Storage() {
        String userDir = System.getProperty("user.dir");
        this.filePath = Paths.get(userDir + "/data/dwuke.txt");
        this.folderPath = Paths.get(userDir + "/data");
    }

    /**
     * Loads the TaskList that is stored in the filePath.
     *
     * @return The TaskList that has been loaded.
     */
    public TaskList load() {
        TaskList taskList = null;
        try {
            createFile();
            List<String> encodedTasks = Files.readAllLines(this.filePath, Charset.defaultCharset());
            taskList = this.decodeTaskList(encodedTasks);
        } catch (IOException | DwukeException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Stores the given TaskList in the filePath.
     *
     * @param taskList The TaskList to be saved.
     */
    public void save(TaskList taskList) {
        try {
            List<String> encodedTasks = this.encodeTaskList(taskList);
            Files.write(this.filePath, encodedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the directory and/or file if they have not been created yet.
     *
     * @throws IOException If the directory or file does not exist.
     */
    public void createFile() throws IOException {
        if (Files.notExists(this.folderPath)) {
            Files.createDirectory(this.folderPath);
        }
        if (Files.notExists(this.filePath)) {
            Files.createFile(this.filePath);
        }
    }

    /**
     * Returns an encoded String representation of the given TaskList.
     *
     * @param taskList The TaskList to encode.
     * @return a String representation of the given TaskList.
     */
    public List<String> encodeTaskList(TaskList taskList) {
        List<String> encodedTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            encodedTasks.add(taskList.get(i).encode());
        }
        return encodedTasks;
    }

    /**
     * Returns a decoded TaskList, to be used by Duke.
     *
     * @param encodedTasks the encoded text used to store the TaskList.
     * @return a TaskList based on the encoded text.
     */
    public TaskList decodeTaskList(List<String> encodedTasks) throws DwukeException {
        TaskList decodedTasks = new TaskList();
        for (String s : encodedTasks) {
            Character taskType = s.charAt(0);
            String content = s.substring(2);

            switch(taskType) {
                case ('T'):
                    decodedTasks.add(Todo.decode(content));
                    break;
                case ('D'):
                    decodedTasks.add(Deadline.decode(content));
                    break;
                case ('E'):
                    decodedTasks.add(Event.decode(content));
                    break;
                default:
                    break;
            }
        }
        return decodedTasks;
    }
}
