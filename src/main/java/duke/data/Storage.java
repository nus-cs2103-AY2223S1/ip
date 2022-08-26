package duke.data;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public static char LINE_SEPARATOR = '|';
    private File file;

    public Storage(String filePath) {
        this.setup(filePath);
    }

    public void setup(String filePath) {
        String curDirectory = System.getProperty("user.dir");
        String directoryName = "data";

        File directory = new File(curDirectory + "/" + directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(curDirectory + "/" + filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.file = file;
    }

    public List<Task> load() {
        List<Task> newList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            String line;
            while (s.hasNext()) {
                line = s.nextLine();
                String[] strArr = line.split("\\|");
                Task task;
                String desc = strArr[2];
                switch (strArr[0]) {
                    case "T":
                        task = new Todo(desc);
                        break;
                    case "E":
                        task = new Event(desc, strArr[3]);
                        break;
                    case "D":
                        task = new Deadline(desc, LocalDate.parse(strArr[3]));
                        break;
                    default:
                        throw new IOException();
                }
                if (strArr[1].equals("true")) {
                    TaskList.markAsDone(task);
                }
                newList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error with input file");
        } catch (DateTimeParseException e) {
            System.out.println("Deadline format is incorrect");
        }
        return newList;
    }

    public void store(TaskList taskList) {
        String dataList = taskList.getDataList();
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.print(dataList);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
