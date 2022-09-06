package duke;

import duke.task.Task;
import duke.task.TodoTask;
import duke.task.DeadlineTask;
import duke.task.EventTask;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Used to read and write data between Duke and a local file.
 */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads from an entire text file.
     *
     * @return The contents of the text file as a single string.
     * @throws IOException if the file cannot be located or read from.
     */
    public String readFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder outputMessage = new StringBuilder();
        String line = reader.readLine();
        while(line != null) {
            outputMessage.append(line);
            outputMessage.append("\n");
            line = reader.readLine();
        }
        reader.close();
        return outputMessage.toString();
    }

    /**
     * Replaces the file's content with an input string.
     *
     * @param content Contents used to overwrite the file.
     * @throws IOException if the file cannot be located or written to.
     */
    public void writeToFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content);
        writer.close();
    }

    /**
     * Reads a single line from the file.
     *
     * @param lineNum The line number to read from. The first line is line 1.
     * @return The contents of the specified line.
     * @throws IOException if the file cannot be located or read from.
     */
    public String readLine(int lineNum) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = "INIT";
        int counter = 0;
        while(line != null) {
            counter++;
            line = reader.readLine();
            if (counter == lineNum) {
                return line;
            }
        }
        reader.close();
        return null;
    }

    /**
     * Overwrites a single line in the file with the provided contents.
     *
     * @param content The new content to overwrite the line.
     * @param lineNum The line number to overwrite.
     * @return true if the line exists, false otherwise.
     * @throws IOException if the file cannot be located, read from or written to.
     */
    public boolean editLine(String content, int lineNum) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder outputMessage = new StringBuilder();
        String line = reader.readLine();
        int counter = 0;
        while(line != null) {
            counter++;
            if (counter == lineNum) {
                outputMessage.append(content);
            } else {
                outputMessage.append(line);
            }
            outputMessage.append("\n");
            line = reader.readLine();
        }
        reader.close();
        writeToFile(outputMessage.toString());
        return lineNum <= counter;
    }

    /**
     * Adds a new line with contents to the end of the file.
     *
     * @param content Contents to be added to the end of the file.
     * @throws IOException if the file cannot be located, read from or written to.
     */
    public void addLine(String content) throws IOException {
        String fileContent = readFromFile();
        fileContent += content + "\n";
        writeToFile(fileContent);
    }

    /**
     * Load up a list of tasks from the file into Duke when he starts up.
     *
     * @return An ArrayList of tasks specified by the file.
     * @throws IOException if the file cannot be located or read from.
     */
    public ArrayList<Task> load() throws IOException {
        try {
            String line = readLine(1);
            int lineNum = 1;
            ArrayList<Task> listOfTasks = new ArrayList<>();
            while (line != null) {
                Task task;
                if (line.charAt(1) == 'D') {
                    int index = line.indexOf('|');
                    String taskName = line.substring(7, index - 1);
                    String date = line.substring(index + 2, index + 13);
                    date = Parser.parseWordDate(date);
                    if (line.length() > index + 13) {
                        String time = line.substring(index + 14);
                        task = new DeadlineTask(taskName, date, time);
                    } else {
                        task = new DeadlineTask(taskName, date);
                    }
                } else if (line.charAt(1) == 'E') {
                    int index = line.indexOf('|');
                    String taskName = line.substring(7, index - 1);
                    String date = line.substring(index + 2, index + 13);
                    date = Parser.parseWordDate(date);
                    if (line.length() > index + 13) {
                        String time = line.substring(index + 14);
                        task = new EventTask(taskName, date, time);
                    } else {
                        task = new EventTask(taskName, date);
                    }
                } else if (line.charAt(1) == 'T') {
                    String taskName = line.substring(7);
                    task = new TodoTask(taskName);
                } else {
                    throw new InputMismatchException();
                }
                if (line.charAt(4) == '1') {
                    task.markComplete();
                }
                listOfTasks.add(task);
                lineNum++;
                line = readLine(lineNum);
            }
            return listOfTasks;
        } catch (FileNotFoundException e) {
            System.out.println("No file found to read from");
        } catch (IOException e) {
            System.out.println("Unknown IO Exception");
        } catch (NullPointerException | InputMismatchException e) {
            System.out.println("Input file format not recognised");
        }
        return new ArrayList<>();
    }

}
