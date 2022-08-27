package kirby;

import kirby.tasks.*;
import java.io.*;
import java.util.ArrayList;

public class Storage {
    private File file;
    private String filePath;
    private File dir;

    public Storage(String filePath, String dirPath) throws IOException {
        this.filePath = filePath;
        this.dir = new File(dirPath);
        this.file = new File(filePath);
        if (!file.exists()) {
            file = createFile();
        }
    }

    public File createFile() throws IOException {
        try {
            dir.mkdir();
            if (file.createNewFile()) {
                return file;
            } else {
                System.out.println("File already exists.");
                return null;
            }
        } catch (IOException e) {
            System.out.println("File not created");
            e.printStackTrace();
            return null;
        }
    }

    public void writeTask(ArrayList<Task> Tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : Tasks) {
            writer.write(task.toFileOutput() + "\n");
        }
        writer.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("~");
                switch (parts[0]) {
                case "kirby.Todo":
                    result.add(new Todo(parts[1]));
                    break;
                case "kirby.tasks.Deadline":
                    result.add(new Deadline(parts[1], parts[2]));
                    break;
                case "kirby.Event":
                    result.add(new Event(parts[1], parts[2]));
                    break;
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
