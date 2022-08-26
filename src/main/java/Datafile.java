import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Datafile {
    private final String fileName;

    public Datafile(String fileName) {
        this.fileName = fileName;
    }

    public void fillData(ArrayList<Task> listOfTasks){
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
                listOfTasks.add(task);
            } catch (DukeException e) { }
        }
        scanner.close();
    }

    public void SaveToDatafile(ArrayList<Task> listOfTasks) throws IOException{
        FileWriter writer = new FileWriter(this.fileName);
        for (Task task : listOfTasks) {
            writer.write(task + "\n");
        }
        writer.close();
    }
}
