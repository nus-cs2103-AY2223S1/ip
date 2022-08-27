package duke;

import java.io.*;
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

    void save(TaskList tasks) {
        List<Task> taskList = tasks.getTasks();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            taskList.forEach(x -> {
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
