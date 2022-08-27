import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public void readFromFile(TaskList receivingList) throws FileNotFoundException {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] line = task.split("~");
                String label = line[0];
                boolean isDone = line[1].equals("1");
                String description = line[2];
                Task newTask;
                switch (line[0]) {
                    case "T":
                        newTask = new ToDo(description);
                        if (isDone) {
                            newTask.setDone();
                        }
                        receivingList.addTaskWithoutOutput(newTask);
                        break;
                    case "D":
                        newTask = new Deadline(description, line[3]);
                        if (isDone) {
                            newTask.setDone();
                        }
                        receivingList.addTaskWithoutOutput(newTask);
                        break;
                    case "E":
                        newTask = new Event(description, line[3]);
                        if (isDone) {
                            newTask.setDone();
                        }
                        receivingList.addTaskWithoutOutput(newTask);
                        break;
                }
            }
    }

        public void updateStorage(TaskList list) {
            try {
                FileWriter writer = new FileWriter(path);
                for (int x = 0; x < list.size(); x++) {
                    writer.write(list.get(x).toWrite() + "\n");
                }
                writer.close();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
}

