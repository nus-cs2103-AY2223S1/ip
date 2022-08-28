package duke;

import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    //private File file;

    public Storage(String filePath) throws DukeException {

        this.filePath = filePath;
    }

    public void save(TaskList tasklist) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasklist.getSize(); i++) {
                fw.write(tasklist.getTask(i).savedData());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save file.");
        }
    }

    public TaskList load() throws DukeException, FileNotFoundException {
        ArrayList<Task> taskArr = new ArrayList<>();

        try {
            File file = new File(filePath);

            if (file.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already exists");
            }
        } catch (Exception de) {
            throw new DukeException("New file can't be created");
        }

        try {
            File dukeFile = new File(filePath);
            Scanner sc = new Scanner(dukeFile);

            while (sc.hasNext()) {
                //taskArr.add(sc.next());

                String[] strArr = sc.next().split("|");
                if (strArr[0].equals("T")) {
                    taskArr.add(new ToDos(strArr[2]));
                }
                if (strArr[0].equals("E")) {
                    taskArr.add(new Events(strArr[2], LocalDateTime.parse(strArr[3])));
                }
                if (strArr[0].equals("D")) {
                    taskArr.add(new Deadlines(strArr[2], LocalDateTime.parse(strArr[3])));
                }
                //System.out.println(sc.nextLine());
            }
            return new TaskList(taskArr);
        } catch (FileNotFoundException fe) {
            throw new FileNotFoundException("No File Found.");
        }
    }

    private String descStr(String str) {
        int k = 6;
        String desc = "";

        while (str.charAt(k) != '(') {
            desc += str.charAt(k);
            k++;
        }
        return desc;
    }

    private String dateStr(String str) {
        int atIndex = str.indexOf("(at: ");
        int start = atIndex + 5;
        String date = str.substring(start, str.length() - 1);
        return date;
    }
}
