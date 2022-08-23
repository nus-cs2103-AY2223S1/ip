package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    private String filePath = "";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    //@@author chengda300
    //Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/topics.html
    // with minor modifications
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
    public void writeToFile(String textToAdd, boolean hasFinishedLoading) throws IOException {
        if (hasFinishedLoading) {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(textToAdd);
            fw.close();
        }
    }
    //@@author
}
