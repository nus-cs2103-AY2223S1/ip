import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public final String DEFAULT_STORAGE_FILEPATH = "./data/duke.txt";

    public void createStorage() throws IOException {
        if (!Files.exists(Path.of("./data/")) || !Files.isDirectory(Path.of("./data"))) {
            Files.createDirectory(Path.of("./data"));
        }
        if (!Files.exists(Path.of(DEFAULT_STORAGE_FILEPATH))) {
            Files.createFile(Path.of(DEFAULT_STORAGE_FILEPATH));
        }
    }

    // Storage format of TaskList
    // <T/D/E>|<0/1>|<TaskDescription>(|<TaskTime>)
    public TaskList loadFromStorage() throws IOException {
        List<String> contents = Files.readAllLines(Path.of(DEFAULT_STORAGE_FILEPATH));
        return decode(contents);
    }

    public void writeToStorage(TaskList t) throws IOException {
        Files.write(Path.of(DEFAULT_STORAGE_FILEPATH), encode(t));
    }

    private List<String> encode(TaskList t) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < t.getSize(); i++) {
            data.add(t.get(i).getStorageString());
        }
        return data;
    }

    private TaskList decode(List<String> data) {
        TaskList t = new TaskList();
        for (int i = 0; i < data.size(); i++) {
            String[] args = data.get(i).split("\\|");
            switch (args[0]) {
            case "T":
                t.add(new Todo(args[2]));
                if (args[1].equals("1")) {
                    t.mark(i + 1);
                }
                break;
            case "D":
                t.add(new Deadline(args[2], args[3]));
                if (args[1].equals("1")) {
                    t.mark(i + 1);
                }
                break;
            case "E":
                t.add(new Event(args[2], args[3]));
                if (args[1].equals("1")) {
                    t.mark(i + 1);
                }
                break;
            }
        }
        return t;
    }
}
