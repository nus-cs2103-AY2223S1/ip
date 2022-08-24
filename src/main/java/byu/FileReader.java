package byu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import task.*;

public class FileReader {

    File file;
    Scanner sc;
    ToDoList list;

    FileReader(String filePath) {
        try {
            this.file = new File("./data/duke.txt");
            this.sc = new Scanner(file);
            this.list = new ToDoList();
        } catch (FileNotFoundException e) {
            System.out.print(e.getMessage());
        }
    }

    public ToDoList load() {
        while(sc.hasNext()) {
            String line = sc.nextLine();
            String[] details = line.split(" \\| ");
            Task t;
            switch (details[0]) {
                case "D":
                    t = new Deadlines(details[2], details[3]);
                    break;
                case "E":
                    t = new Events(details[2], details[3]);
                    break;
                case "T":
                    t = new ToDos(details[2]);
                    break;
                default:
                    t = new Task("unknown");
            }
            if (details[1].equals("1")) {
                t.markDone();
            }
            list.addTask(t);
        }
        sc.close();
        return this.list;
    }

}
