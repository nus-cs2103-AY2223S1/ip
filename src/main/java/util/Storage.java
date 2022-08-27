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

public class Storage {
    private final File saveFile;

    private Storage(File saveFile) {
        this.saveFile = saveFile;
    }

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
