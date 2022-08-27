import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public void makeParentDirectories(File file) {
        File parentFile = file.getParentFile();
        if (parentFile.exists()) {
            file.mkdir();
        } else {
            makeParentDirectories(parentFile);
            file.mkdir();
        }
    }

    public ArrayList<Task> setFile() {
        File parentFolder = this.file.getParentFile();
        if (parentFolder.exists()) {
            if (this.file.exists()) {
                return scanExistingFile();
            } else {
                return createNewFile();
            }
        } else {
            makeParentDirectories(parentFolder);
            return createNewFile();
        }
    }

    public ArrayList<Task> scanExistingFile() {
        ArrayList<Task> tempTaskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String curr = sc.nextLine();
                Task addTask = Task.fileStringToTask(curr);
                if (addTask != null) {
                    tempTaskList.add(addTask);
                }
            }
            return tempTaskList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Task> createNewFile() {
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

}
