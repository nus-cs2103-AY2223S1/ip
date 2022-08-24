import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperations {
    private final String fileName;

    public FileOperations(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Overwrites the contents of fileName (if exists) with the current tasks
     */
    public void writeAllTasksToFile() {
        File file = new File(this.fileName);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, false);
            for (int i = 0; i < Task.count(); i++) {
                Task task = Task.get(i);
                String encodedTask = task.encode();
                fw.write(encodedTask);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(String.format("\tError writing to %s in FileOperations.addAllTasksToFile()",
                    fileName));
        }
    }

    /**
     * This function is only called when we are sure that data.txt exists (has already been created).
     * It inserts all the tasks that were stored in data.txt, decodes them into Task objects and stores them
     * in the Task.tasks array
     */
    public void loadAllTasksFromFile() {
        // Get all tasks from file and store in ArrayList
        File file = new File(fileName);
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasksInFile = new ArrayList<>();
            while (sc.hasNextLine()) {
                String encodedTask = sc.nextLine();
                Task task = Task.decode(encodedTask);
                if (task == null) {
                    continue;
                }
                tasksInFile.add(task);
            }
            Task.addTasks(tasksInFile);

        } catch (FileNotFoundException e) {
            System.out.println(String.format("\tFile %s not found!!!", fileName));
        } catch (DukeException e) {
            System.out.println(String.format("\t%s", e.getMessage()));
        }
    }

    /**
     * Appends the encoded representation of the task into the file.
     * @param task The task to be stored in the file
     */
    public void addTaskToFile(Task task) {
        File file = new File(fileName);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            String encodedTask = task.encode();
            fw.write(encodedTask);
            fw.write("\n");
            fw.close();
        } catch ( IOException e) {
            System.out.println(String.format("\tError writing to %s in FileOperations.addAllTasksToFile()",
                    fileName));
        }
    }
}
