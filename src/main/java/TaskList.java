import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class TaskList {


    public static TaskList loadTaskListFromFile(String dataFilePath) {
        TaskList tl = new TaskList(dataFilePath);
        tl.readFromFile();

        return tl;
    }


    private LinkedList<Task> tasks;
    private String dataFilePath;



    private TaskList(String dataFilePath) {
        this.tasks = new LinkedList<>();
        this.dataFilePath = dataFilePath;
    }
    
    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int indexNumber) {
        return this.tasks.get(indexNumber);
    }

    public void setTask(int indexNumber, Task t) {
        this.tasks.set(indexNumber, t);
        writeToFile();
    }

    public void addTask(Task t) {
        this.tasks.addLast(t);
        writeToFile();
    }

    public Task removeTask(int indexNumber) {
        Task t = this.tasks.remove(indexNumber);
        writeToFile();
        return t;
    }


    // Serialize stored tasks to the data file
    // Method adapted from https://www.tutorialspoint.com/java/java_serialization.htm
    private void writeToFile() {

        try {
            FileOutputStream outputFile = new FileOutputStream(this.dataFilePath);
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);

            outputStream.writeObject(this.tasks);

            outputFile.close();
            outputStream.close();
            

        } catch (FileNotFoundException e) {
            System.out.println("Error, cannot open file");
            System.out.println(e);

        } catch (IOException e) {
            System.out.println("Error when writing to file");
            System.out.println(e);
        }

    }


    // Deserialize stored tasks from the data file
    // Method adapted from https://www.tutorialspoint.com/java/java_serialization.htm
    private void readFromFile() {
        
        try {
            FileInputStream inputFile = new FileInputStream(this.dataFilePath);
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);

            this.tasks = (LinkedList<Task>) inputStream.readObject();

            inputFile.close();
            inputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find data.ser file. Creating new task list instead.");
            this.tasks = new LinkedList<>();
            // System.out.println(e);

        } catch (IOException e) {
            System.out.println("Error when reading file");
            System.out.println(e);

        } catch (ClassNotFoundException e) {
            System.out.println("Error, serialized object can't be deserialized");
            System.out.println(e);
        }
    }


    @Override
    public String toString() {
        return String.format("%s\nData File Path: %s", this.tasks.toString(), this.dataFilePath);
    }


    // For testing
    public static void main(String[] args) {
        TaskList tl = TaskList.loadTaskListFromFile("./data.ser");

        System.out.println(tl.tasks);
        
        tl.addTask(new ToDo("buy bread"));
        
        tl.readFromFile();
        System.out.println(tl.tasks);
        

        tl.addTask(new ToDo("return book"));
        System.out.println(tl.tasks);
        tl.readFromFile();
        System.out.println(tl.tasks);

        System.out.println(tl);
    }
    
}
