import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    ArrayList<Task> taskArrayList;

    public TaskList(String filePath) {
        this.taskArrayList = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (file.isDirectory()) {
                throw new SkylarkException("This path is a directory!");
            }

            if (!file.exists()) {
                try {
                    // https://stackoverflow.com/questions/9620683/java-fileoutputstream-create-file-if-not-exists
                    if (!file.getParentFile().mkdirs()) {
                        throw new SkylarkException("Error creating directory!");
                    }

                    if (!file.createNewFile()) {
                        throw new SkylarkException("Error creating new file!");
                    }
                } catch (IOException ioException) {
                    throw new SkylarkException("IOException occurred when creating new file");
                }
            }

            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    String[] splitString = nextLine.split(" \\| ", -1);

                    if (splitString.length < 3) {
                        throw new SkylarkException("Not enough parameters!");
                    }

                    Task currentTask;
                    switch (splitString[0]) {
                    case "T":
                        currentTask = new ToDo(splitString[2]);
                        break;
                    case "D": {
                        String desc = splitString[2];
                        String endDate = splitString[3];
                        currentTask = new Deadline(desc, endDate);
                        break;
                    }
                    case "E": {
                        String desc = splitString[2];
                        String timing = splitString[3];
                        currentTask = new Event(desc, timing);
                        break;
                    }
                    default:
                        throw new SkylarkException("No suitable task found!");
                    }

                    boolean isDone = Integer.parseInt(splitString[1]) == 1;
                    if (isDone) {
                        currentTask.markAsDone();
                    } else {
                        currentTask.markAsUndone();
                    }

                    this.taskArrayList.add(currentTask);
                }
            } catch (FileNotFoundException fileNotFoundException) {
                throw new SkylarkException("Cannot find file!");
            }
        } catch (SkylarkException skylarkException) {
            Printer.printText(skylarkException.toString());
        }
    }

    public int size() {
        return this.taskArrayList.size();
    }

    public Task get(int index) {
        return this.taskArrayList.get(index);
    }

    public void add(Task task) {
        this.taskArrayList.add(task);
    }

    public void remove(int index) {
        this.taskArrayList.remove(index);
    }

    public boolean doesIndexExist(int index) {
        return index >= 0 && index < this.taskArrayList.size();
    }
}
