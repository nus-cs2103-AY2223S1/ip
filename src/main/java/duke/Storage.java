package duke;

import task.Deadline;
import task.Event;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.nio.file.Path;

/**
 *  A class that handles reading and writing and processing file contents.
 *  @author  Chen Guanzhou
 *  @version v1
 */
public class Storage {
    protected static TaskList currList;
    private static final String HOME = System.getProperty("user.home");
    private static final String FILE_PATH = "./data/duke.txt";
    private static final File FILE = new File(FILE_PATH);
    private Path path;
    String dir = System.getProperty("user.dir");


    public Storage(TaskList taskList) {
        this.currList = taskList;
        this.path = Paths.get(dir, FILE_PATH);
    }

    /**
     * A method to read the file input into the task list.
     * @param myReader The scanner that contains the file input
     */
    public void loadFileInput(Scanner myReader) {
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            char key = data.charAt(1);
            String temp = data.substring(7); //description
            if (key == 'T') { //todo
                Todo task = new Todo(temp);
                currList.addTask(task);
            }
            else if (key == 'D') { //deadline
                String parts[] = temp.split(" \\(by: ", 2);
                String dateEnglish = parts[1].substring(0, 11); //handle ) at the end
                LocalDate inputDate = LocalDate.parse(dateEnglish, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                Deadline task = new Deadline(parts[0], inputDate);
                currList.addTask(task);
            }
            else { //event
                String parts[] = temp.split(" \\(at: ", 2);
                String dateEnglish = parts[1].substring(0, 11); //to handle the ) at the end
                LocalDate inputDate = LocalDate.parse(dateEnglish, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                Event task = new Event(parts[0], inputDate);
                currList.addTask(task);
            }
        }
        myReader.close();
    }

    /**
     * A method to save tasks by writing them to a specified file.
     */
    public void writeToFile(){
        try {
            FileWriter myWriter = new FileWriter(FILE);
            for (int i = 0; i < currList.getLength(); i++) {
                myWriter.write(currList.getTaskAt(i).toString());
                myWriter.write("\n");
            }
            myWriter.close();
        }
        catch (IOException e){
            System.out.println("Cannot write to file!");
        }
    }

    /**
     * A method to create a file in the specified file path.
     */
    public void createFile() {
        try {
            FILE.getParentFile().mkdirs();
            FILE.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot create file!");
        }
    }

    /**
     * A method to check if the file exists, and creates a file if it does not exist.
     * @param taskList The file which contains the saved user tasks.
     */
    public void handleFile(File taskList) {
        if (!taskList.exists()) {
            this.createFile();
        }
    }

    /**
     * A method to read file input tasks into the task list.
     */
    public void readAndProcessFile() {
        this.handleFile(FILE);
        try {
            assert FILE.exists();
            Scanner myReader = new Scanner(FILE);
            this.loadFileInput(myReader);
        } catch (FileNotFoundException e) {
            Ui.handleFileNotFoundException();
        }
    }
}
