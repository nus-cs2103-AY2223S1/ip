import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    private final String directoryPath;
    private String fileName;

    public Storage(String fileName) throws DukeException {
        String _directoryPath = null;
        try {
            _directoryPath = Path.of(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).toString();
            _directoryPath = _directoryPath.substring(0, _directoryPath.lastIndexOf(File.separator));
            _directoryPath = URLDecoder.decode(_directoryPath, StandardCharsets.UTF_8).concat(File.separator + "data");
            File directory = new File(_directoryPath);
            if (!directory.exists()) {
                if (!directory.mkdir()) {
                    _directoryPath = null;
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (_directoryPath == null) {
            throw new DukeException("Unable to save tasks to disk.");
        }
        this.directoryPath = _directoryPath;
        this.fileName = fileName;
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        return loadTasks(this.fileName);
    }

    public ArrayList<Task> loadTasks(String fileName) throws DukeException {
        this.fileName = fileName;
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(directoryPath.concat(File.separator + fileName));
        if (!file.exists()) {
            return tasks;
        }
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            br.lines().forEach(line -> {
                String[] entries = line.strip().split("\\|", 3);
                Task.Type type = Task.Type.decode(entries[0]);
                boolean completed = entries[1].equals("1");
                switch (type) {
                    case DEADLINE:
                        tasks.add(Deadline.decode(entries[2], completed));
                        break;
                    case EVENT:
                        tasks.add(Event.decode(entries[2], completed));
                        break;
                    case TODO:
                        tasks.add(ToDo.decode(entries[2], completed));
                        break;
                }
            });
            br.close();
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Could not read file! " + e.getMessage());
        }

    }

    private void writeToFile(String fileName, String content) throws DukeException {
        String filePath = directoryPath.concat(File.separator + fileName);
        File file = new File(filePath);
        try {
            FileWriter writer = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            throw new DukeException("Could not save file! " + e.getMessage());
        }
    }

    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.encode()).append(System.lineSeparator());
        }
        this.writeToFile(fileName, sb.toString());
    }
}
