import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Stream;

class Storage {
    private final String filepath;
    Storage(String path) {
        filepath = path;
    }

    TaskList load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./tasks.txt"));
            Stream<String> lines = reader.lines();

            TaskList taskList = new TaskList(lines);
            reader.close();
            return taskList;
        } catch (Exception e) {
            System.out.println(e);
            return new TaskList();
        }
    }
}
