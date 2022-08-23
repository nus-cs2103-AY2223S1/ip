package duke.modules.todos;

import duke.MessagefulException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String fileDir;
    private final String filePath;

    public Storage(String fileDir, String fileName) {
        this.fileDir = fileDir;
        this.filePath = fileDir + "/" + fileName;
    }

    public Storage() {
        this("data", "tasks.csv");
    }

    public void saveList(List<Task> todos) throws MessagefulException {
        try {
            File fileDir = new File(this.fileDir);
            if (!fileDir.isDirectory() && !fileDir.mkdirs()) {
                throw new MessagefulException("cannot create task save dir", "Uh oh! I cannot save the task list.");
            }

            FileWriter fw = new FileWriter(filePath);
            for (Task task : todos) {
                fw.write(String.join(",", task.flatPack()) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e){
            throw new MessagefulException(
                    "file writing error",
                    "Uh oh! I cannot save the task list. This might help: " + e);
        }
    }

    public ArrayList<Task> loadList() throws MessagefulException {
        try {
            ArrayList<Task> todos = new ArrayList<>();
            Scanner file = new Scanner(new File(filePath));
            while (file.hasNextLine()) {
                List<String> line = Arrays.asList(file.nextLine().split(",", -1));
                final Task newTask;
                switch (line.get(0)) {
                case Task.typeCode:
                case Todo.typeCode:
                    newTask = new Todo(line);
                    break;
                case Deadline.typeCode:
                    newTask = new Deadline(line);
                    break;
                case Event.typeCode:
                    newTask = new Event(line);
                    break;
                default:
                    throw new MessagefulException(
                            "unknown task type",
                            "Uh oh! I cannot load the task list.");
                }
                todos.add(newTask);
            }
            return todos;
        } catch (FileNotFoundException e) {
            throw new MessagefulException(
                    "tasks file missing",
                    "I have gotten you started with an empty task list. Welcome!");
        }
    }
}
