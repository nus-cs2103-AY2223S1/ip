package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a storage attached to the Duke bot.
 */
public class Storage {
    private String filePath = "";

    /**
     * Constructor for the storage.
     *
     * @param filePath Path of the file that contains saved information from the last run.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //@@author chengda300
    //Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/topics.html
    // with minor modifications
    /**
     * Loads data from the file to restore the last run status.
     *
     * @param d Duke bot that is running.
     * @param filePath Path of the file that contains saved information from the last run.
     * @throws FileNotFoundException If file is not found.
     */
    public void loadData(Duke d, String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            d.parse(s.nextLine());
        }
    }
    //@@author

    //@@author chengda300
    //Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/topics.html
    /**
     * Writes to the file to save the new valid instruction.
     *
     * @param textToAdd Valid instruction to be added.
     * @param hasFinishedLoading Boolean to indicate if the Duke bot has finished loading.
     * @throws IOException If an input/output error occurs.
     */
    public void writeToFile(String textToAdd, boolean hasFinishedLoading) throws IOException {
        if (hasFinishedLoading) {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(textToAdd);
            fw.close();
        }
    }
    //@@author
}
