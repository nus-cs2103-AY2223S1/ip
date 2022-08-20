import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> taskArrayList;
    private File file;

    public TaskList(String filePath) {
        this.taskArrayList = new ArrayList<>();

        try {
            this.file = new File(filePath);
            if (this.file.isDirectory()) {
                throw new SkylarkException("This path is a directory!");
            }

            if (!this.file.exists()) {
                try {
                    // https://stackoverflow.com/questions/9620683/java-fileoutputstream-create-file-if-not-exists
                    if (!this.file.getParentFile().mkdirs()) {
                        throw new SkylarkException("Error creating directory!");
                    }

                    if (!this.file.createNewFile()) {
                        throw new SkylarkException("Error creating new file!");
                    }
                } catch (IOException ioException) {
                    throw new SkylarkException("IOException occurred when creating new file");
                }
            }

            try {
                Scanner scanner = new Scanner(this.file);
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

    public void saveToFile() throws SkylarkException {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            for (Task currentTask : this.taskArrayList) {
                // Reference: https://www.baeldung.com/java-string-newline
                fileWriter.write(currentTask.toStringFile() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new SkylarkException("IOException occurred when writing to file");
        }
    }
}
