package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static File savedFile = null;

    protected Storage() {

    }

    protected ArrayList<Task> ReadFileAtStart() {
        File dataDir = new File(System.getProperty("user.dir") + File.separator + "data");

        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        String fullPath = dataDir.getPath() + File.separator + "Save.txt";
        savedFile = new File(fullPath);
        boolean fileCreated = false;
        ArrayList<Task> arrayList = null;

        try {
            fileCreated = savedFile.createNewFile();

            if (!fileCreated) {
                System.out.println("Save file detected, reading it...");
                arrayList = ReadData();
            } else {
                System.out.println("Creating save file for first time use");
                arrayList = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating save file");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date found in save file!");
        }

        return arrayList;
    }

    private ArrayList<Task> ReadData() throws FileNotFoundException {

        Scanner sc = new Scanner(savedFile);
        ArrayList<Task> arrayList = new ArrayList<>();

        while (sc.hasNextLine()) { //type#boolean#taskname#time

            String[] array = sc.nextLine().split("#");
            boolean cond1 = (array.length == 3 && array[0].equals("T"));
            boolean cond2 = (array.length == 4 && (array[0].equals("D") || array[0].equals("E")));
            boolean cond3 = (array[1].equals("1") || array[1].equals("0"));

            if ((!cond1 && !cond2) || !cond3) {
                throw new RuntimeException("Cannot read file due to incorrect input");
            }

            char c = array[0].charAt(0);
            String taskname = array[2];
            boolean isDone = array[1].equals("1");

            if (c == 'T') {
                arrayList.add(new Todo(taskname));
            } else {
                String date = array[3];
                LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MMM-d"));

                if (c == 'E') {
                    arrayList.add(new Event(taskname, d));
                } else {
                    arrayList.add(new Deadline(taskname, d));
                }
            }

            if (isDone) {
                arrayList.get(arrayList.size() - 1).markDone();
            }
        }

        return arrayList;
    }

    protected void SaveData(ArrayList<Task> arrayList) throws IOException {

        FileWriter fw = new FileWriter(savedFile, false);

        for (Task t : arrayList) {
            fw.write(t.toSavedString() + System.lineSeparator());
        }

        fw.close();
    }

}
