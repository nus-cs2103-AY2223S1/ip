import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;


    public Storage(String filepath) {
        this.file = new File(filepath);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> currList = new ArrayList<>();

        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String[] tempArray = s.nextLine().split(" \\| ");
                String typeOfTask = tempArray[0];

                switch (typeOfTask) {
                    case "T" :
                        currList.add(new Todo(tempArray[2], tempArray[1].equals("1")));
                        break;
                    case "D" :
                        LocalDate tempDate = LocalDate.parse(tempArray[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        Deadline tmpTask = new Deadline(tempArray[2], tempArray[1].equals("1"), tempDate);
                        currList.add(tmpTask);
                        break;
                    case "E" :
                        currList.add(new Event(tempArray[2], tempArray[1].equals("1"), tempArray[3]));
                        break;
                    default:
                        System.out.println("Error: Wrong type of task");
                    }
                }
            return currList;
        } catch (IOException e) {
            throw new DukeException("Error: file not loaded correctly");
        }
    }

    public void save(TaskList newTasks) throws DukeException {

        ArrayList<String> newFile = new ArrayList<>();

        for(int i = 0; i < this.file.length(); i++) {
            newFile.add(newTasks.getTask(i).toFileString());
        }
        try {
            FileWriter fw = new FileWriter(file);

            fw.write(String.join("\n", newFile));
            fw.close();
        } catch(IOException e) {
            throw new DukeException("Error: File added wrongly");
        }
    }
}
