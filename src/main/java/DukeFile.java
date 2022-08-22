import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DukeFile {

    protected final String filePath;

    DukeFile(String fileDirectoryString, String fileName) {
        File fileDirectory = new File(fileDirectoryString);
        if (!fileDirectory.exists()) {
            fileDirectory.mkdir();
        }

        this.filePath = fileDirectoryString + fileName;
        File file = new File(this.filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public Task createTask(String inputLine) {
        String[] split = inputLine.split("\\|\\|");
        String command = split[0];
        boolean isDone = Boolean.parseBoolean(split[1]);
        Task task = null;
        switch (command) {
            case "T":
                task = new Todo(split[2], isDone);
                break;
            case "D":
                task = new Deadline(split[2], split[3], isDone);
                break;
            case "E":
                task = new Event(split[2], split[3], isDone);
                break;
        }
        return task;
    }

    public TaskList readFile() {
        TaskList taskList = new TaskList();
        File file = new File(this.filePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                taskList.addTask(createTask(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return taskList;
    }

    public void writeFile(TaskList taskList) {
        String text = "";
        for (int i=0; i<taskList.getSize(); i++) {
            Task task = taskList.getTask(i+1);
            String line = task.toFileString();
            text = String.format("%s%s\n", text, line);
        }

        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}