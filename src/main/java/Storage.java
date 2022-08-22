import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File saveFile = new File(this.filePath);
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                Task temp = Task.of(line);
                tasks.add(temp);
            }
        } catch (FileNotFoundException e) {
            throw new BlinkException("Save file not found");
        }
        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            new File(this.filePath).getParentFile().mkdirs();
            FileWriter fw = new FileWriter(this.filePath);
            for (int x = 0; x < tasks.length(); x++) {
                Task temp = tasks.get(x);
                fw.write(temp.saveString());
            }
            fw.close();
        } catch (IOException e) {
            throw new BlinkException("Unable to save");
        }
    }

}
