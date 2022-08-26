import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructor to create Storage object linked to file in specified file path.
     *
     * @param filePath File path specified as a String.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            this.file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("file created!");
            } else {
                System.out.println("file exists!");
            }
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    /**
     * Method to read data from storage file into the current instance of Duke.
     *
     * @param tasks TaskList object that will store the parsed data from the storage file.
     */
    public void readData(TaskList tasks) {
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                Task toAdd = Parser.dataToInfo(data);
                tasks.add(toAdd);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            //todo
        }
    }

    /**
     * Method to write data to the storage file without overriding the data on it.
     *
     * @param toWrite The task that will be written onto the storage file.
     */
    public void writeData(Task toWrite) {
        try {
            FileWriter writer = new FileWriter(this.file, true);
            writer.write(toWrite.processData() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("ioexception");
        }
    }

    /**
     * Method to override data in storage file, replacing it with data from specified TaskList.
     *
     * @param tasks TaskList whose data will be written onto the storage file.
     */
    public void overwriteData(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(this.file, false);
            writer.write("");
            writer.close();
            for (int i = 0; i < tasks.size(); i++) {
                this.writeData(tasks.get(i));
            }
        } catch (IOException e) {
            System.out.println("ioexception");
        }
    }

}
