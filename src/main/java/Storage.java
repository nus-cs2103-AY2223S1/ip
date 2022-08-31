import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    private final String filePathStr;

    public Storage(String filePathStr) {
        this.filePathStr = filePathStr;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path filePath = Paths.get(filePathStr);

        boolean isDataFileExisting = Files.exists(filePath);

        if (!isDataFileExisting) {
            File f1 = new File(filePathStr);
            boolean isDirsMade = f1.getParentFile().mkdirs();
            if (!isDirsMade) {
                System.out.println("Directories not made");
            }
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(filePathStr))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task newTask;
                // process data
                switch (line.charAt(4)) {
                    case 'T':
                        newTask = ToDo.createTodoFromString(line);
                        break;
                    case 'E':
                        newTask = Event.createEventFromString(line);
                        break;
                    case 'D':
                        newTask = Deadline.createDeadlineFromString(line);
                        break;
                    default:
                        newTask = null;
                        break;
                }
                tasks.add(newTask);
            }
        } catch (IOException | NullPointerException e) {
            throw new DukeException("There was an error loading your existing tasks, no tasks were loaded :(");
        }
        return tasks;
    }

    public void save(TaskList taskList) throws DukeException {
        byte[] data = taskList.toString().getBytes();
        Path filePath = Paths.get(filePathStr);

        boolean isDataFileExisting = Files.exists(filePath);

        if (!isDataFileExisting) {
            File f1 = new File(filePathStr);
            boolean isDirsMade = f1.getParentFile().mkdirs();
            if (!isDirsMade) {
                System.out.println("Directories not made");
            }
        }

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(filePath))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            throw new DukeException("There was an error saving your changes!");
        }
    }
}
