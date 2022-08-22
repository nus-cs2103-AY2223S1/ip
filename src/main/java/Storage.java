import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public Path tasksPath;

    public Storage (String filePath) {
        this.tasksPath = Paths.get(filePath);
    }

    private String taskToFileFormat(Task task) {
        String s = task.toString();
        char taskType = s.charAt(1);
        int isDone = s.charAt(4) == ' ' ? 0 : 1;
        String description = s.substring(7);
        if (taskType == 'D' || taskType == 'E') {
            description = description.substring(0, description.length() - 1);
            description = taskType == 'D'
                    ? description.replaceFirst("\\(by:", "|")
                    : description.replaceFirst("\\(at:", "|");
        }
        return String.format("%c | %d | %s", taskType, isDone, description);
    }

    public void saveToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(tasksPath.toString(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(taskToFileFormat(task));
        bw.newLine();
        bw.close();
    }

    public void saveToFile(TaskList tasks) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(tasksPath);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            bw.write(taskToFileFormat(task));
            bw.newLine();
        }
        bw.close();
    }

    private Task fileFormatToTask(String line) {
        String[] splitLine = line.split(" \\| ");
        String taskType = splitLine[0];
        Task task = null;
        switch (taskType) {
        case "T":
            task = new Todo(splitLine[2]);
            break;
        case "D":
            LocalDateTime deadlineDateTime = LocalDateTime.parse(
                    splitLine[3].replace(' ', 'T'));
            task = new Deadline(splitLine[2], deadlineDateTime);
            break;
        case "E":
            LocalDateTime eventDateTime = LocalDateTime.parse(
                    splitLine[3].replace(' ', 'T'));
            task = (new Event(splitLine[2], eventDateTime));
            break;
        default:
            break;
        }
        if (task != null) {
            if (splitLine[1].equals("1")) {
                task.markAsDone();
            }
        }
        return task;
    }

    public List<Task> load() throws IOException {
        Path parent = tasksPath.getParent();
        if (!Files.isDirectory(parent)) {
            Files.createDirectories(parent);
        }
        if (!Files.isRegularFile(tasksPath)) {
            Files.createFile(tasksPath);
        }

        List<Task> tasks = new ArrayList<>();
        BufferedReader br = Files.newBufferedReader(tasksPath);
        String line = br.readLine();
        while (line != null) {
            tasks.add(fileFormatToTask(line));
            line = br.readLine();
        }
        br.close();
        return tasks;
    }
}
