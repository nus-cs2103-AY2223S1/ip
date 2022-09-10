package roofus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents Roofus's memory.
 */
public class Storage {
    private static String storagePath;
    private File storage;

    /**
     * Constructs an instance of Storage.
     *
     * @param storagePath File path to storage file.
     */
    public Storage(String storagePath) {
        this.storagePath = storagePath;
        this.storage = new File(storagePath);
    }

    /**
     * Extracts data in Roofus's memory into a list.
     *
     * @return List Returns lists containing saved data in Storage.
     * @throws FileNotFoundException If storage path is invalid.
     */
    public List<String> load() throws FileNotFoundException {
        Scanner sc = new Scanner(this.storage);
        List<String> taskString = new ArrayList<>();
        while (sc.hasNextLine()) {
            taskString.add(sc.nextLine());
        }
        return taskString;
    }

    /**
     * Saves a task list into Roofus's memory.
     *
     * @throws  IOException If storage path is invalid.
     */
    public void save(TaskList taskList) throws IOException {
        new File(this.storagePath).getParentFile().mkdirs();
        FileWriter editor = new FileWriter(this.storagePath);
        for (int i = 0; i < taskList.length(); i++) {
            editor.write(taskList.getTask(i).writeString() + "\n");
        }
        editor.close();
    }
}
