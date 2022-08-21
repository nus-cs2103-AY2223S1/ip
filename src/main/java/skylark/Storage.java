package skylark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> readFromFile() {
        ArrayList<Task> taskArrayList = new ArrayList<>();

        try {
            File file = new File(this.filePath);
            if (file.isDirectory()) {
                throw new SkylarkException("This path is a directory!");
            }

            if (!file.exists()) {
                try {
                    // https://stackoverflow.com/questions/9620683/java-fileoutputstream-create-file-if-not-exists
                    // https://stackoverflow.com/a/4040667
                    File parent = file.getParentFile();
                    if (parent != null && !parent.exists() && !parent.mkdirs()) {
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

                    taskArrayList.add(currentTask);
                }
            } catch (FileNotFoundException fileNotFoundException) {
                throw new SkylarkException("Cannot find file!");
            }
        } catch (SkylarkException skylarkException) {
            Printer.printText(skylarkException.toString());
        }

        return taskArrayList;
    }

    public void saveToFile(ArrayList<Task> taskArrayList) throws SkylarkException {
        try {
            File file = new File(this.filePath);

            if (file.exists() && !file.canWrite()) {
                boolean isWritable = file.setWritable(true);
                if (!isWritable) {
                    throw new SkylarkException("File is not writable!");
                }
            }

            FileWriter fileWriter = new FileWriter(file);
            for (Task currentTask : taskArrayList) {
                // Reference: https://www.baeldung.com/java-string-newline
                fileWriter.write(currentTask.toStringFile() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException ioException) {
            throw new SkylarkException("IOException occurred when writing to file: "
                    + ioException.getMessage());
        } catch (SecurityException securityException) {
            throw new SkylarkException("SecurityException occurred when writing to file: "
                    + securityException.getMessage());
        }
    }
}
