package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    private TaskList currList;
    private static final String home = System.getProperty("user.home");
    private static final Path FILE_PATH = java.nio.file.Paths.get(home, "Desktop", "duke.txt");
    private static final File file = new File(FILE_PATH.toUri());
    public Storage(TaskList taskList) {
        this.currList = taskList;
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
            FileWriter myWriter = new FileWriter(file);
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
        File dir = file;
        try {
            dir.createNewFile();
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

    public void readAndProcessFile() {
        this.handleFile(file);
        try {
            Scanner myReader = new Scanner(file);
            this.loadFileInput(myReader);
        } catch (FileNotFoundException e) {
            Ui.handleFileNotFoundException();
        }
    }
}
