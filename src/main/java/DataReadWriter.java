import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataReadWriter {
    static File dir = new File("data");
    static File taskListFile = new File(dir, "task_list.txt");

    public static void checkFileExists() throws IOException {
        // System.out.println(dir.exists());
        if (! dir.exists()) {
            dir.mkdir();
        }
        if (! taskListFile.exists()) {
            taskListFile.createNewFile();
        }
    }

    public static void saveAddedTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(taskListFile, true);
        fw.write(task.toDataForm());
        fw.close();
    }

    public static void saveTaskList(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(taskListFile);
        for (int i = 0; i < taskList.getTaskCount(); ++i) {
            fw.write(taskList.getTask(i).toDataForm());
        }
        fw.close();
    }

    public static TaskList loadTaskList() throws Exception {
        checkFileExists();
        TaskList taskList = new TaskList();
        Scanner taskScanner = new Scanner(taskListFile);
        while (taskScanner.hasNextLine()) {
            String taskStr = taskScanner.nextLine();
            if (taskStr.equals("")) {
                break;
            }
            String[] taskInfoArr = taskStr.split("\\|");
            boolean isDone = taskInfoArr[1].equals("1");
            if (taskInfoArr[0].equals("T")) {
                taskList.appendLoadedTask(new Todo(taskInfoArr[2], isDone));
            } else if (taskInfoArr[0].equals("E")) {
                taskList.appendLoadedTask(new Event(taskInfoArr[2], taskInfoArr[3], isDone));
            } else if (taskInfoArr[0].equals("D")) {
                taskList.appendLoadedTask(new Deadline(taskInfoArr[2], taskInfoArr[3], isDone));
            } else {
                throw new RuntimeException("Something wrong with task types in data file");
            }
        }
        return taskList;
    }
}
