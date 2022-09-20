package duke;

import duke.DukeException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/*
Deals with loading tasks from file and saving tasks to file
*/
public class Storage {

    private String filePath;
    private static final String DIRECTORY = "./data";
    private static final String FILENAME = "duke.txt";

    public Storage() throws DukeException {
        this.filePath = DIRECTORY + "/" + FILENAME;

        File file = new File(filePath);
        if (!file.exists()) {
            File directory = new File(DIRECTORY);
            directory.mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("tasks can't be loaded :< message: " + e);
            }
        }
    }

    public ArrayList<Task> load() throws DukeException, FileNotFoundException {

        ArrayList<Task> al = new ArrayList<>();
        Scanner s = new Scanner(new File(this.filePath));


        while (s.hasNext()) {
            String currLine = s.nextLine();

            if (currLine.length() == 0) {
                throw new DukeException("No existing tasks yet! New list created.");
            }

            String[] splitLine = currLine.split("\\|");
            String taskType = splitLine[0].trim();
            boolean done = splitLine[1].trim().equals("1");
            String taskName = splitLine[2].trim();
            String date = splitLine[3].trim();

            Task tempTask = new Task(taskName, taskType, date, done);

            al.add(tempTask);
        }
        return al;
    }

    public void writeItems(ArrayList<Task> al) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (int i = 0; i < al.size(); i++) {
                writer.write(al.get(i).toTxt());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
