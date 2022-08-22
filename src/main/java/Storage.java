import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> toStore;
    private final String FILEPATH;
    private final String DIRPATH;

    public Storage() {
        this.FILEPATH = "data/lilychat.txt";
        this.DIRPATH = "data";
    }

    public ArrayList<Task> initialise() {
        File file = new File(FILEPATH);
        try {
            Scanner s = new Scanner(file);
            ArrayList<Task> output = new ArrayList<>();
            while (s.hasNext()) {
                String task = s.nextLine();
                String[] splitTask = task.split("\\|");
                Task toAdd;
                if (splitTask[0].equals("T")) {
                    toAdd = new ToDo(splitTask[2]);
                } else if (splitTask[0].equals("E")) {
                    toAdd = new Event(splitTask[2], splitTask[3]);
                } else {
                    toAdd = new Deadline(splitTask[2], splitTask[3]);
                }
                if (splitTask[1].equals("1")) {
                    toAdd.markDone();
                }
                output.add(toAdd);
            }
            return output;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public void writeFile(TaskList list) {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            String textToAdd = "";
            for (int i = 0; i < list.getSize(); i++) {
                if (i == 0) {
                    textToAdd += list.taskSaveToString(i);
                } else {
                    textToAdd += "\n" + list.taskSaveToString(i);
                }
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            File file = new File(DIRPATH);
            if (file.mkdir()) {
                writeFile(list);
            } else {
                System.out.println("Failed to create file");
            }
        }
    }
}
