import java.io.File;

import java.lang.reflect.Array;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Storage {
    private final Path path;
    private final String pathString;
    
    public Storage(String filePath) {
        this.path = Paths.get(filePath);
        this.pathString = path.toString();
        
        File parent = new File(new File(pathString).getParent());
        
        if (!parent.exists()) {
            parent.mkdirs();
        }
    }
    
    public List<Task> load() throws FileNotFoundException {
        File file = new File(pathString);
        Scanner sc = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            Task task = Task.getTaskFromString(sc.nextLine());
            tasks.add(task);
        }
        return tasks;
    }
    
    public void save(TaskList tasks) throws IOException {
        File file = new File(pathString);
        FileWriter fw =  new FileWriter(file);
 
        for (Task task : tasks.getTasks()) {
            fw.write(task.toFileFormatString() + "\n");
        }
        fw.close();
    }
}
