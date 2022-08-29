import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Storage {
    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public void fillData(TaskList taskList){
        File file = new File(this.fileName);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (java.io.FileNotFoundException e){
            return;
        }

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            try {
                Task task = Task.parseFromString(line);
                taskList.addTask(task);
            } catch (DukeException e) { }
        }
        scanner.close();
    }

    public void SaveToStorage(TaskList taskList) throws IOException{
        FileWriter writer = new FileWriter(this.fileName);
        for (int i = 0; i < taskList.getSize(); i++) {
            try {
                writer.write(taskList.getTask(i) + "\n");
            } catch (DukeException e) {
                //Impossible to happen as we are iterating.
            }
        }
        writer.close();
    }
}
