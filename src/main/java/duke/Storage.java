package duke;

import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Files;

public class Storage {
    protected Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
        try {
            Files.createDirectories(this.filePath);
        } catch (IOException exception) {
            System.out.println("Catch exception");
            System.out.println(exception);
        }
    }

    public ArrayList<String> load() {
        BufferedReader bufferedReader = null;
        ArrayList<String> result = new ArrayList<>();
        try {
            String currentLine;
            bufferedReader = new BufferedReader(new FileReader(this.filePath.toString()
                    + File.separator + "task-list.txt"));
            while ((currentLine = bufferedReader.readLine()) != null) {
                result.add(currentLine);
            }
        } catch (IOException exception) {
            saveData(new TaskList());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException exception) {
                System.out.println(exception);
            }
        }
        return result;
    }

    public void saveData(TaskList list) {
        ArrayList<String> textArray = list.extractToStringArray();
        try {
            File file = createFile(this.filePath, "task-list.txt");
            OutputStream os = new FileOutputStream(file);
            String text = String.join("\n", textArray);
            byte[] bytes = text.getBytes();
            os.write(bytes);
            os.close();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    public File createFile(Path path, String filename) {
        File file = new File(path.toString(), filename);
        return file;
    }
}