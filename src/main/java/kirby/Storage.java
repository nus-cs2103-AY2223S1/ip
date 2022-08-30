package kirby;

import kirby.tasks.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Storage class handles writing into and reading from the text file created to store previous user's entries.
 */
public class Storage {
    private File file;
    private String filePath;
    private File dir;

    /**
     * Constructor of the Storage class.
     *
     * @param filePath Name of the file path as a string.
     * @param dirPath Name of the directory path as a string.
     * @throws IOException If there is an error with user's input.
     */
    public Storage(String filePath, String dirPath) throws IOException {
        this.filePath = filePath;
        this.dir = new File(dirPath);
        this.file = new File(filePath);
        if (!file.exists()) {
            file = createFile();
        }
    }

    /**
     * Creates a file if the file has not been created.
     *
     * @return New file created.
     * @throws IOException If there is an error with creating the file.
     */
    public File createFile() throws IOException {
        try {
            dir.mkdir();
            if (file.createNewFile()) {
                return file;
            } else {
                System.out.println("File already exists.");
                return null;
            }
        } catch (IOException e) {
            System.out.println("File not created");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Writes the list of task into a file.
     *
     * @throws IOException If there is an error with writing into the file.
     */
    public void writeTask(ArrayList<Task> Tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : Tasks) {
            writer.write(task.toFileOutput() + "\n");
        }
        writer.close();
    }

    /**
     * Reads from a file and returns the list of tasks that is written in the file.
     *
     * @throws FileNotFoundException If the file does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("~");
                switch (parts[0]) {
                case "kirby.Todo":
                    result.add(new Todo(parts[1]));
                    break;
                case "kirby.tasks.Deadline":
                    result.add(new Deadline(parts[1], parts[2]));
                    break;
                case "kirby.Event":
                    result.add(new Event(parts[1], parts[2]));
                    break;
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
