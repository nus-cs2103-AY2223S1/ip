package carbon;

import carbon.error.CarbonException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    // filepath for tasks savefile
    private static final String WORKING_DIR = System.getProperty("user.dir");
    private static final String SAVEFILE = Storage.WORKING_DIR + "/../../../store/tasks.txt";
    private static final String SAVEFILE_DIR = Storage.WORKING_DIR + "/../../../store/";

    public Storage() {}

    public TaskList loadSavefile() {
        TaskList taskList = new TaskList();

        try {
            File savefile = new File(Storage.SAVEFILE);
            if (savefile.isFile()) {
                Scanner savefileScanner = new Scanner(savefile);
                while (savefileScanner.hasNextLine()) {
                    String data = savefileScanner.nextLine();
                    taskList.loadTask(data);
                }
                savefileScanner.close();
            }
        } catch (FileNotFoundException error) {
            System.out.println(error);
        }

        return taskList;
    }

    public void saveTasks(TaskList taskList) throws CarbonException {
        File savefile = new File(Storage.SAVEFILE);
        File savefileDir = new File(Storage.SAVEFILE_DIR);
        try {
            // ensures dir and file exists
            savefileDir.mkdir();
            savefile.createNewFile();

            FileWriter writer = new FileWriter(savefile);
            writer.write(taskList.encodeTasks());
            writer.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }
}
