package chad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import chad.exception.ChadException;
import chad.task.Deadline;
import chad.task.Event;
import chad.task.Task;
import chad.task.Todo;

/**
 * Deals with opening, writing and creating new files to manage task list
 */
public class Storage {
    /**
     * Returns an empty array list if no saved array list else read line by line
     * @return arraylist of tasks
     * @throws ChadException If there is trouble opening the file
     */
    public static ArrayList<Task> initializeArrayList() throws ChadException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File currentFile = new File("./data/chad_data.txt");

            if (currentFile.createNewFile()) {
                System.out.println("Created new file");
            }
            BufferedReader reader = new BufferedReader(new FileReader(currentFile));
            String line = reader.readLine();

            while (line != null) {
                String[] tempArr = line.split("\\|");
                String taskType = tempArr[0].trim();
                String strIsMark = tempArr[1].trim();
                String desc = tempArr[2].trim();

                switch (taskType) {
                case "D": {
                    String byDateString = tempArr[3].trim();
                    LocalDateTime dateTime = LocalDateTime.parse(byDateString);
                    Task t = new Deadline(desc, dateTime);
                    if (strIsMark.equals("1")) {
                        t.markAsDone();
                    }
                    taskList.add(t);
                    break;
                }
                case "E": {
                    String byDateTime = tempArr[3].trim();
                    LocalDateTime dateTime = LocalDateTime.parse(byDateTime);
                    Task t = new Event(desc, dateTime);
                    if (strIsMark.equals("1")) {
                        t.markAsDone();
                    }
                    taskList.add(t);
                    break;
                }
                case "T": {
                    Task t = new Todo(desc);
                    if (strIsMark.equals("1")) {
                        t.markAsDone();
                    }
                    taskList.add(t);
                    break;
                }
                default:
                    System.out.println("Failed to add task " + line);
                    break;

                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            throw new ChadException(e.getMessage());
        }
        return taskList;
    }

    /**
     * Writes task to text file
     * @param str formatted string
     * @throws IOException Thrown when file cannot be opened
     */
    public static void writeToFile(String str) throws ChadException {
        try {
            FileWriter fileWriter = new FileWriter("./data/chad_data.txt", true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(str);
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            throw new ChadException(e.getMessage());
        }
    }

    /**
     * Delete task in text file
     * @param index index of text to be deleted
     * @throws IOException Thrown when file cannot be opened
     */
    public static void deleteTaskInFile(int index) throws ChadException {
        try {
            File tempFile = new File("tempFile.txt");
            File currentFile = new File("./data/chad_data.txt");

            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            BufferedReader reader = new BufferedReader(new FileReader(currentFile));

            String currentLine;
            int currentIndex = 0;

            while ((currentLine = reader.readLine()) != null) {
                if (currentIndex != index) {
                    writer.write(currentLine);
                    writer.newLine();
                }
                currentIndex += 1;
            }
            writer.close();
            reader.close();
            currentFile.delete();
            tempFile.renameTo(currentFile);

        } catch (Exception e) {
            throw new ChadException(e.getMessage());
        }
    }

    /**
     * Toggles done attribute in task list based on index
     * @param index index of task
     * @throws IOException Thrown when file cannot be opened
     */
    public static void toggleMarkTaskInFile(int index) throws ChadException {
        try {
            File tempFile = new File("tempFile.txt");
            File currentFile = new File("./data/chad_data.txt");
            tempFile.deleteOnExit();

            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            BufferedReader reader = new BufferedReader(new FileReader(currentFile));

            String currentLine;
            int currentIndex = 0;

            while ((currentLine = reader.readLine()) != null) {
                if (currentIndex != index) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    String[] tempArr = currentLine.split("\\|");
                    String markIndex = tempArr[1].trim();
                    markIndex = markIndex.equals("0") ? "1" : "0";
                    tempArr[1] = markIndex;
                    StringBuilder line = new StringBuilder();
                    for (int i = 0; i < tempArr.length; i++) {
                        line.append(tempArr[i].trim());
                        if (i != tempArr.length - 1) {
                            line.append(" | ");
                        }
                    }
                    writer.write(line.toString().trim());
                    writer.newLine();
                }
                currentIndex += 1;
            }
            writer.close();
            reader.close();

            currentFile.delete();
            tempFile.renameTo(currentFile);
        } catch (Exception e) {
            throw new ChadException(e.getMessage());
        }
    }
}
