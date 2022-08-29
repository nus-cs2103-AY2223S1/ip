package duke;

import duke.task.Task;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private String fileName;
    private String folderPath;

    public Storage(String fileName, String folderPath) {
        this.fileName = fileName;
        this.folderPath = folderPath;
    }

    public List<String> load() throws DukeException {
        try {
            List<String> res = Files.readAllLines(Paths.get(fileName));
            return res;
        } catch (Exception e) {
            throw(new DukeException("No saved data found!"));
        }
    }

    public void save(TaskList tasks) throws Exception {
        String res = "";
        for (Task task : tasks.tasks()) {
            res += task;
            res += "\n";
        }

        Files.createDirectories(Paths.get(folderPath));
        Files.write(Paths.get(fileName), res.getBytes(StandardCharsets.UTF_8));
    }
}
