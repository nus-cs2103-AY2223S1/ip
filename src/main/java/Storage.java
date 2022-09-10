import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    List<List<String>> tasks;
    private static final String TASK_FILE_PATH = "data/tasks.txt";
    private static final String TASK_FILE_DIR = "data";
    private final File taskFile;

    public Storage() throws IOException, DrakeException {
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
                Deadline deadline = new Deadline(taskParts[1], taskParts[3]);
                if (taskParts[2].equals("X")) {
                    deadline.markAsDone();
                }
                list.add(deadline);
                break;
            case "T":
                Task task = new Todo(taskParts[1]);
                if (taskParts[2].equals("X")) {
                    task.markAsDone();
                }
                list.add(task);
                break;
            case "E":
                Event event = new Event(taskParts[1], taskParts[3]);
                if (taskParts[2].equals("X")) {
                    event.markAsDone();
                }
                list.add(event);
                break;
            }
        }
        return list;
    }

    public void updateTask(int taskNumber, CommandType command) throws DrakeException {
        switch (command) {
        case MARK:
            tasks.get(taskNumber - 1).set(2, "X");
            break;

        case UNMARK:
            tasks.get(taskNumber - 1).set(2, " ");
            break;

        case DELETE:
            tasks.remove(taskNumber - 1);
        }

        updateFile();
    }

    //Inspired by parnikkapore's PR
    private void updateFile() throws DrakeException {
        try {
            File fileDir = new File(TASK_FILE_DIR);
            if (!fileDir.isDirectory() && !fileDir.mkdirs()) {
                throw new DrakeException("Higher powers taking a hold on me... I cannot save the task list.");
            }

            FileWriter fileWriter = new FileWriter(TASK_FILE_PATH);
            for (List<String> task : tasks) {
                fileWriter.write(listToCsv(task));
            }
            fileWriter.close();
        } catch (IOException e){
            throw new DrakeException(
                    "Higher powers taking a hold on me... I cannot save the task list. This might help: " + e);
        }
    }

    public void addTask(Task addedTask) throws DrakeException {
        tasks.add(addedTask.toList());
        updateFile();
    }

    private void addTasks(List<Task> addedTasks) throws DrakeException {
        for (Task addedTask : addedTasks) {
            addTask(addedTask);
        }
        updateFile();
    }

    private String listToCsv(List<String> list) {
        StringBuilder csv = new StringBuilder(list.get(0)).append(";");
        for (int i = 1; i < list.size(); i++) {
            csv.append(list.get(i));
            if (i != list.size() - 1) {
                csv.append(";");
            }
        }
        return csv.append("\n").toString();
    }

    //For debugging
    private void printTasks() {
        for (List<String> task : tasks) {
            System.out.println(task);
        }
    }
}
