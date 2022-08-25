import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * A file reader to read and write to the task file.
 */
public class FileReader {

    /** The file that is to be read from and to write to. */
    private File file;

    /**
     * Empty constructor for a FileReader object.
     */
    public FileReader() {

    }

    public void readFrom(String path) {
        this.file = new File(path);
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void writeFrom(List<Task> lst) {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            String str = "";
            for (int i = 0; i < lst.size(); i++) {
                str = str + lst.get(i).toFile();
            }
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void updateList(List<Task> lst) {
        try {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNextLine()) {
                String currLine = fileScanner.nextLine();
                String lineSplit[] = currLine.split(" \\| ");
                Task temp = null;
                switch (lineSplit[0]) {
                case "T":
                    temp = new Todo(lineSplit[2]);
                    break;
                case "D":
                    temp = new Deadline(lineSplit[2], lineSplit[3]);
                    break;
                case "E":
                    temp = new Event(lineSplit[2], lineSplit[3]);
                }
                if (lineSplit[1].equals("1")) {
                    temp.setDone();
                }
                lst.add(temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
