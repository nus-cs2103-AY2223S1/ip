import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SaveSystem {
    private static final String SAVE_PATH = "savedata/";
    private static final String SAVE_NAME = "duke.txt";

    private static final File SAVE_FOLDER = new File(SAVE_PATH);
    private static final File SAVE_FILE = new File(SAVE_PATH + SAVE_NAME);

    public static void saveFile(Duke saveData) {
        try {
            if (!SAVE_FOLDER.exists()) {
                SAVE_FOLDER.mkdir();
            }
            FileWriter fw = new FileWriter(SAVE_FILE);
            for (Task t : saveData.getTasks()) {
                String saveTask = t.parseToSaveData();
                fw.write(saveTask + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while attempting to save the file");
        }
    }

    public static Duke loadFile() {
        Duke duke = new Duke();
        try {
            if (SAVE_FILE.exists()) {
                duke.LoadTasksFromSave(SAVE_FILE);
            }
            return duke;
        } catch (FileNotFoundException e) {
            System.out.println("The duke scanner is unable to load the information in the file.");
        }
        return duke;
    }
}
