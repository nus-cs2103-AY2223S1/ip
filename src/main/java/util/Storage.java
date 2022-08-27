package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import command.Command;
import exception.DukeException;
import task.Task;
import tasklist.TaskList;

/**
 * Handles the any storage related functionality related to the save file
 *
 * @author Bryan Lim Jing Xiang
 */
public class Storage {
    private final File saveFile;

    private Storage(File saveFile) {
        this.saveFile = saveFile;
    }

    /**
     * Initialises the save file, creates one if needed
     *
     * @param storagePathName Relative path of the save file
     * @return Storage object that handles operations to the save file
     */
    public static Storage initialize(String storagePathName) {
        File saveFile = new File(storagePathName);
        try {
            saveFile.getParentFile().mkdirs();
            if (saveFile.createNewFile()) {
                Ui.formatLinesIntoParagraph("Successfully created save file for storage!");
            }
        } catch (IOException e) {
            String errorMsg = "☹ OOPS!!! Error opening/creating saved storage.";
            System.out.println(Ui.formatLinesIntoParagraph(errorMsg));
        }

        return new Storage(saveFile);
    }

    /**
     * Loads and stores all saved tasks into the current duke list
     *
     * @param list Duke list of the current running instance of the app
     */
    public void load(TaskList list) {
        try (Scanner sc = new Scanner(this.saveFile)) {
            while (sc.hasNext()) {
                try {
                    Command cmd = Parser.parseEncodedTask(sc.nextLine());
                    cmd.execute(list, this);
                } catch (DukeException e) {
                    System.out.println(Ui.formatLinesIntoParagraph(e.errorMessage()));
                }
            }
            String successMsg = "Successfully loaded tasks from saved storage!";
            System.out.println(Ui.formatLinesIntoParagraph(successMsg));
        } catch (FileNotFoundException e) {
            System.out.println(Ui.formatLinesIntoParagraph(e.getMessage()));
        }
    }

    /**
     * Saves all tasks from the current duke list into the save file
     *
     * @param list Duke list of the current running instance of the app
     */
    public void saveAllTasks(TaskList list) {
        try (FileWriter fileWriter = new FileWriter(this.saveFile, false)) {
            for (int i = 1; i <= list.getTaskCount(); i++) {
                Task task = list.getItem(i);
                String encodedTask = task.encode();
                fileWriter.write(encodedTask);
            }
        } catch (IOException e) {
            System.out.println(Ui.formatLinesIntoParagraph(e.getMessage()));
        }
    }
}
