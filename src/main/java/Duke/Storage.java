package Duke;

import Duke.commands.Constants;
import Duke.tasks.Deadline;
import Duke.tasks.Event;
import Duke.tasks.Task;
import Duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    List<List<String>> tasks;
    private static final String TASK_FILE_PATH = "database/duke.txt";
    private FileWriter fileWriter;
    private final File taskFile;

    public Storage() throws IOException {
        taskFile = new File(TASK_FILE_PATH);
        tasks = new ArrayList<>();
        addTasks(fileToList());
    }

    public List<Task> fileToList() {
        ArrayList<Task> list = new ArrayList<>();
        Scanner fileReader;
        try {
            fileReader = new Scanner(taskFile);
        } catch (FileNotFoundException e) {
            return list;
        }
        while (fileReader.hasNext()) {
            String[] taskParts = fileReader.nextLine().split(";");
            switch (taskParts[0]) {
                case "D":
                    Deadline deadline = new Deadline(taskParts[2], taskParts[3]);
                    if (taskParts[1].equals("X")) {
                        deadline.mark();
                    }
                    list.add(deadline);
                    break;
                case "T":
                    Task task = new Todo(taskParts[2]);
                    if (taskParts[1].equals("X")) {
                        task.mark();
                    }
                    list.add(task);
                    break;
                case "E":
                    Event event = new Event(taskParts[2], taskParts[3]);
                    if (taskParts[1].equals("X")) {
                        event.mark();
                    }
                    list.add(event);
                    break;
            }
        }
        
        return list;
    }

    public void updateTask(int taskNumber, Constants command) throws IOException {
        switch (command) {
            case MARK:
                tasks.get(taskNumber - 1).set(1, "X");
                break;

            case UNMARK:
                tasks.get(taskNumber - 1).set(1, " ");
                break;

            case DELETE:
                tasks.remove(taskNumber - 1);
        }

        updateFile();
    }

    private void updateFile() throws IOException {
        fileWriter = new FileWriter(TASK_FILE_PATH);
        for (List<String> task : tasks) {
            StringBuilder taskString = new StringBuilder(task.get(0)).append(";");
            for (int i = 1; i < task.size(); i++) {
                taskString.append(task.get(i));
                if (i != task.size() - 1) {
                    taskString.append(";");
                }
            }
            fileWriter.write(taskString.append("\n").toString());
            System.out.println("ayo");
        }
        for (List<String> task : tasks)
            System.out.println(String.join(", ", task));
    }

    public void addTask(Task addedTask) throws IOException {
        tasks.add(addedTask.toList());
        updateFile();
    }

    private void addTasks(List<Task> addedTasks) throws IOException {
        for (Task addedTask : addedTasks) {
            addTask(addedTask);
        }
        updateFile();
    }

    public void close() throws IOException {
        if (fileWriter != null) {
            fileWriter.close();
        }
    }
}
