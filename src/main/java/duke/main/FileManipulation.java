package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class FileManipulation {

    private static void tryOverwrite(String filePath, String s) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(s);
        fw.close();
    }

    private static ArrayList<Task> tryRead(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> arr = new ArrayList<>();
        while (sc.hasNext()) {
            String s = sc.nextLine();
            char c = s.charAt(0);
            if (c == 'T') {
                ToDo t = new ToDo(s.substring(8));
                if (s.charAt(4) == '1') {
                    t.mark();
                }
                arr.add(t);
            } else if (c == 'D') {
                String[] strings = s.substring(8).split(" \\| ");
                Deadline d = new Deadline(strings[0], LocalDate.parse(strings[1]));
                if (s.charAt(4) == '1') {
                    d.mark();
                }
                arr.add(d);
            } else {
                String[] strings = s.substring(8).split(" \\| ");
                Event e = new Event(strings[0], LocalDate.parse(strings[1]));
                if (s.charAt(4) == '1') {
                    e.mark();
                }
                arr.add(e);
            }
        }
        return arr;
    }

    public static void overwrite(String filepath, String s) {
        try {
            tryOverwrite(filepath, s);
        } catch (IOException e) {
            System.out.println("Something went wrong. " + e.getMessage());
        }
    }

    public static ArrayList<Task> read(String filepath) throws DukeException {
        try {
            return tryRead(filepath);
        } catch (FileNotFoundException e) {
            throw new DukeException("Something went wrong. " + e.getMessage());
        }
    }
}