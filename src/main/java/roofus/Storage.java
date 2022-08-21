package roofus;

import java.io.*;

import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Storage {
    private static String storagePath;
    
    private File storage;
    
    public Storage(String storagePath) {
        this.storagePath = storagePath;
        this.storage = new File(storagePath);
    }
    
    public List<String> load() throws FileNotFoundException {
        Scanner sc = new Scanner(this.storage);
        List<String> taskString = new ArrayList<>();
        while (sc.hasNextLine()) {
            taskString.add(sc.nextLine());
        }
        return taskString;
    }
    
    public void save(TaskList taskList) throws IOException {
        new File(this.storagePath).getParentFile().mkdirs();
        FileWriter editor = new FileWriter(this.storagePath);
        for (int i = 0; i < taskList.length(); i++) {
            editor.write(taskList.getTask(i).writeString() + "\n");
        }
        editor.close();
    }
}
