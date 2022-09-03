import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //read text from entire file
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

    public void writeToFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content);
        writer.close();
    }

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

    public void addLine(String content) throws IOException {
        String fileContent = readFromFile();
        fileContent += content + "\n";
        writeToFile(fileContent);
    }

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
                        task = new Task.DeadlineTask(taskName, date, time);
                    } else {
                        task = new Task.DeadlineTask(taskName, date);
                    }
                } else if (line.charAt(1) == 'E') {
                    int index = line.indexOf('|');
                    String taskName = line.substring(7, index - 1);
                    String date = line.substring(index + 2, index + 13);
                    date = Parser.parseWordDate(date);
                    if (line.length() > index + 13) {
                        String time = line.substring(index + 14);
                        task = new Task.DeadlineTask(taskName, date, time);
                    } else {
                        task = new Task.DeadlineTask(taskName, date);
                    }
                } else if (line.charAt(1) == 'T') {
                    String taskName = line.substring(7);
                    task = new Task.TodoTask(taskName);
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
