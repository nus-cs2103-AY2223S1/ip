package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File f;
    private static final String path = "src/main/data/userTasks.txt";

    public Storage() {
        f = new File(path);
    }

    public ArrayList<String> retrieveTasks() throws DukeException {
        ArrayList<String> strArr = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String sentence = sc.nextLine().trim();
                strArr.add(sentence);
            }
         } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
        return strArr;
    }

//    public void retrieveTasks(Parser duke.parser, Ui duke.ui) {
////        ArrayList<Task> duke.tasks = new ArrayList<>();
//        try {
//            Scanner sc = new Scanner(f);
//            while (sc.hasNext()) {
//                String sentence = sc.nextLine().trim();
//                duke.parser.parseTask(sentence);
//            }
//        } catch (FileNotFoundException e) {
//            duke.ui.showErrorMessage(e.getMessage());
//        } catch (DukeException e) {
//            duke.ui.showErrorMessage(e.getMessage());
//        }
////         return duke.tasks;
//    }

    public void writeToFile(ArrayList<? extends Task> tasks) {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            String s = tasks.get(i).toStringForFile();
            str += s + System.lineSeparator();
        }
        try (BufferedWriter bf = Files.newBufferedWriter(Path.of(path),
                StandardOpenOption.TRUNCATE_EXISTING)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(path, true));
            fw.append(str);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}