package Duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private TaskList currList;
    public Storage(TaskList taskList) {
        this.currList = taskList;
    }

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

    public void writeToFile(){
        try {
            FileWriter myWriter = new FileWriter("./src/main/data/duke.txt");
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

    public static void createFile(File fileToCreate) {
        try {
            fileToCreate.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleFile(File taskList) {
        if (!taskList.exists()) {
            createFile(taskList);
        }
    }

    public void readAndProcessFile(TaskList currList) {
        File taskList = new File("./src/main/data/duke.txt"); //file to read and write into
        Storage fileHandler = new Storage(currList);
        fileHandler.handleFile(taskList);
        try {
            Scanner myReader = new Scanner(taskList);
            fileHandler.loadFileInput(myReader);
        } catch (FileNotFoundException e) {
            Ui.handleFileNotFoundException();
        }
    }
}
