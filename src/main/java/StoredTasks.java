import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StoredTasks {
    private static final int TASKTYPE = 0;
    private static final int ISTASKDONE = 1;
    private static final int TASKDESCRIPTION = 2;
    private static final int TASKTIME = 3;

    private String fileDir;
    private String filePath;

    public StoredTasks(String fileDir, String filePath) {
        this.fileDir = fileDir;
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> storedTasks = new ArrayList<>(100);
        try {
            File dir = new File(this.fileDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File fileName = new File(this.filePath);
            if (!fileName.exists()) {
                fileName.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while (line != null) {
                String[] taskArr = line.split("\\|");
                switch (taskArr[TASKTYPE]) {
                case "T":
                    Todos todo = new Todos(taskArr[TASKDESCRIPTION]);
                    if (Boolean.parseBoolean(taskArr[ISTASKDONE])) {
                        todo.markAsDone();
                    }
                    storedTasks.add(todo);
                    break;
                case "D":
                    Deadlines deadline = new Deadlines(taskArr[TASKDESCRIPTION], taskArr[TASKTIME],
                            DateAndTimeParser.validateAndParse(taskArr[TASKTIME]));
                    if (Boolean.parseBoolean(taskArr[ISTASKDONE])) {
                        deadline.markAsDone();
                    }
                    storedTasks.add(deadline);
                    break;
                case "E":
                    Events event = new Events(taskArr[TASKDESCRIPTION], taskArr[TASKTIME]);
                    if (Boolean.parseBoolean(taskArr[ISTASKDONE])) {
                        event.markAsDone();
                    }
                    storedTasks.add(event);
                    break;
                }
                line = br.readLine();
            }
            br.close();

        } catch (IOException e) {
            throw new DukeException("Failure in reading file, creating new save file");
        }
        return storedTasks;
    }

    public void save(ArrayList<Task> storedTasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : storedTasks) {
                fw.write(task.storedTaskString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
