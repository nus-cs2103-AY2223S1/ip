import duke.exceptions.DukeException;
import duke.exceptions.InvalidInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> data;

    TaskList() throws DukeException {
        this.data = new ArrayList<>();
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, "data");
        File dataDir = new File(path.toString());
        if (!dataDir.exists()) {
            if (!dataDir.mkdir()) {
                throw new DukeException("Unable to create 'data' directory");
            }
        }
    }

    // lists all the tasks
    public String listData() {
        if (data.size() == 0) return "Nothing here...";
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            Task task = data.get(i);
            output.append(i+1).append(". ").append(task).append("\n");
        }
        return output.substring(0, output.length()-1);
    }

    public void loadData() throws DukeException {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, "data", "duke.txt");
        try {
            File savedData = new File(path.toString());
            Scanner sc = new Scanner(savedData);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] taskData = line.split("\\|");
                if (taskData.length < 3 || (taskData.length < 4 && !"T".equals(taskData[0]))) {
                    throw new DukeException("File format invalid!");
                }
                Task task = null;
                switch (taskData[0]) {
                    case "T":
                        task = new ToDo(taskData[2]);
                        break;
                    case "D":
                        task = new Deadline(taskData[2], LocalDate.parse(taskData[3]));
                        break;
                    case "E":
                        task = new Event(taskData[2], LocalDate.parse(taskData[3]));
                        break;
                }
                if (task != null) {
                    add(task);
                    if ("1".equals(taskData[1])) task.markDone();
                    else task.markNotDone();
                }
            }
            sc.close();
        } catch (FileNotFoundException ignored){}
    }

    public void saveData() throws DukeException {
        try {
            FileWriter file = new FileWriter("data/duke.txt", false);
            for (Task task : data) {
                file.write(task.stringToSave() + System.lineSeparator());
            }
            file.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred when writing to file");
        }
    }

    public int size() {
        return data.size();
    }

    public Task get(int index) {
        return data.get(index);
    }

    public void add(Task task) throws DukeException {
        data.add(task);
        saveData();
    }

    public void remove(int index) throws DukeException {
        data.remove(index);
        saveData();
    }

    // checks if number input is valid and returns the index of the task
    public int getIndex(String res) throws InvalidInput {
        if (!res.matches("[0-9]+")) throw new InvalidInput("Input is not a number");
        int target_index = Integer.parseInt(res) - 1;
        if (target_index < 0 || target_index >= data.size()) throw new InvalidInput("Please input a correct number");
        return target_index;
    }

}
