package arc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File dataFile;

    public Storage(String relativePath) {
        this.dataFile = new File(relativePath);
    }

    public ArrayList<Task> load() {
        ArrayList<Task> arrayList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.dataFile);

            while (sc.hasNextLine()) {
                String[] currTask = sc.nextLine().split("\\|");

                switch (currTask[0]) {
                    case "T":
                        arrayList.add(new Todo(currTask[2], currTask[1].equals("1")));
                        break;
                    case "D":
                        LocalDate ldt = LocalDate.parse(currTask[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        arrayList.add(new Deadline(currTask[2], currTask[1].equals("1"), ldt));
                        break;
                    case "E":
                        arrayList.add(new Event(currTask[2], currTask[1].equals("1"), currTask[3]));
                        break;
                    default:
                        System.out.println("Undefined task type: " + currTask[0]);
                }
            }

            sc.close();

        } catch (FileNotFoundException e) {
            createFile();
        }

        return arrayList;
    }

    private void createFile() {
        this.dataFile.getParentFile().mkdir();
        try {
            if (this.dataFile.createNewFile()) {
                System.out.println("File created: " + this.dataFile.getPath());
            }
        } catch (IOException e) {
            System.out.println("File not successfully created");
        }
    }

    public void save(TaskList taskList) throws DukeException {
        ArrayList<String> newData = new ArrayList<>();

        for (int i = 0; i < taskList.numTasks(); i++) {
            newData.add(taskList.getTask(i).fileFormat());
        }

        try {
            FileWriter fw = new FileWriter(this.dataFile);
            fw.write(String.join("\n", newData));
            fw.close();
        } catch (IOException e) {
            throw new DukeException("File not saved properly");
        }
    }
}
