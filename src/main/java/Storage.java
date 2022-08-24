import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private File currentFile;

    public Storage(String filePath) {
        this.currentFile = new File(filePath);
    }

    public void loadFile() throws DukeException {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File dataFile = this.currentFile;
        if (dataFile.exists()) {
            try {
                Scanner s = new Scanner(dataFile);
                while (s.hasNext()) {
                    String data = s.nextLine();
                    char type = data.charAt(0);
                    switch (type) {
                        case 'T':
                            ToDo todo = ToDo.parseFile(data);
                            Duke.loadTask(todo);
                            break;
                        case 'D':
                            Deadline deadline = Deadline.parseFile(data);
                            Duke.loadTask(deadline);
                            break;
                        case 'E':
                            Event event = Event.parseFile(data);
                            Duke.loadTask(event);
                            break;
                        default:
                            throw new DukeException("File format is invalid!");
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeFile(ArrayList<Task> data) throws DukeException {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task: data) {
                fw.write(task.toDataFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
