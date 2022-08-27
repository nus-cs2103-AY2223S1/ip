package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

class Storage {
    private final String filepath;
    Storage(String path) {
        filepath = path;
    }

    TaskList load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            Stream<String> lines = reader.lines();

            TaskList taskList = new TaskList(lines);
            reader.close();
            return taskList;
        } catch (Exception e) {
            System.out.println(e);
            return new TaskList();
        }
    }


    void save(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            tasks.forEach(x -> {
                try {
                    writer.write(x.toString());
                    writer.write("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
