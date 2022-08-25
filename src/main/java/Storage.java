import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String path;
    File file;

    public Storage(String path) {
        this.path = path;
        this.file = new File(this.path);

    }

    public void save(ArrayList<Task> tasks) throws IOException {
        if (file.createNewFile()) {
            System.out.println("file created");
        } else {
            System.out.println("File already exists");
        }

        FileWriter fileWriter = new FileWriter(this.path);
        for (Task task : tasks) {
            fileWriter.write(task.toString() + System.lineSeparator());
        }
        fileWriter.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            tasks.add(new Task("test"));
            System.out.println(scanner.nextLine());
        }

        return tasks;
    }
}
