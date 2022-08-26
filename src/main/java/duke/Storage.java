package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> lst = new ArrayList<>();
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            new File(path).getParentFile().mkdirs();
            try {
                new File(path).createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        else if (!file.exists()) {
            try {
                new File(path).createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            lst.add(sc.nextLine());
        }
        sc.close();
        return lst;
    }

    public void update(ArrayList<Task> lst) throws IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(this.path);
        for (int i = 0; i < lst.size(); i++) {
            fw.write(lst.get(i).toString() +"\n");
        }
        fw.close();
    }
}
