import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filepath) {
        this.filePath = filepath;
    }

    public void save(ArrayList<Task> taskList, Ui ui) throws FredException {

        try {
            File f = new File(filePath);

            if (!f.exists()) {
                f.createNewFile();
                ui.showMessage("No data file exists. New data file has been created.");
            }

            FileWriter fileWriter = new FileWriter(filePath);

            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                fileWriter.write(task.getSaveFormat());
            }
            fileWriter.close();
            ui.showMessage("Tasks have been saved!");
        } catch (IOException e) {
            throw new FredException("IOException!");
        }
    }

    public ArrayList<Task> load() throws FredException {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            File file = new File(filePath);
            Scanner scanner = new Scanner(file).useDelimiter("\\s\\|\\s");

            while (scanner.hasNext()) {
                String start = scanner.next();
                if (start.equals("T")) {
                    String isDoneSymbol = scanner.next();
                    scanner.skip("\\s\\|\\s");
                    String description = scanner.nextLine();
                    Task t = new ToDo(description, (isDoneSymbol.equals("1")));
                    taskList.add(t);
                } else if (start.equals("E")) {
                    String isDoneSymbol = scanner.next();
                    String description = scanner.next();
                    scanner.skip("\\s\\|\\s");
                    String at = scanner.nextLine();
                    Task t = new Event(description, at, (isDoneSymbol.equals("1")));
                    taskList.add(t);
                } else if (start.equals("D")) {
                    String isDoneSymbol = scanner.next();
                    String description = scanner.next();
                    scanner.skip("\\s\\|\\s");
                    String by = scanner.nextLine();
                    Task t = new Deadline(description, by, (isDoneSymbol.equals("1")));
                    taskList.add(t);
                } else {
                    throw new FredException("Loading... Data file entry is wrong!");
                }
            }
            scanner.close();
            return taskList;

        } catch (FileNotFoundException e) {
            throw new FredException("Loading Error!");
        }
    }
}
