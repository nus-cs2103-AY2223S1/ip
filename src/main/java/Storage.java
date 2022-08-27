import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File f;
    private ArrayList<Task> ls;
    public Storage(String filepath) {
        ls = new ArrayList<Task>();
        f = new File(filepath);
    }

    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public void deleteFromFile(String filePath, ArrayList<Task> iterate) throws IOException {
        FileWriter fw = new FileWriter("temp.txt",true);
        File f = new File("temp.txt");
        for (int i = 0; i< iterate.size(); i++) {
            fw.write(iterate.get(i).toString()+ System.lineSeparator());
        }
        fw.close();
        Files.delete(Paths.get(filePath));
        File g = new File(filePath);
        f.renameTo(g);

    }

    public void add(Task task) {
        ls.add(task);
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                Parser.parseLineStorage(line, this);

            }
            s.close();
            return ls;
        } catch (FileNotFoundException e) {
            throw new DukeException("duke.txt is not found!");
        }

    }
}
