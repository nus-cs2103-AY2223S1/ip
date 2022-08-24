import java.io.*;
import java.util.ArrayList;

public class StoredTasks {
    private static final String FILEDIR = "data";
    private static final String FILEPATH = FILEDIR + File.separator + "duke.txt";
    private static final int TASKTYPE = 0;
    private static final int ISTASKDONE = 1;
    private static final int TASKDESCRIPTION = 2;
    private static final int TASKTIME = 3;

    public static ArrayList<Task> load() {
        ArrayList<Task> storedTasks = new ArrayList<>(100);
        try {
            File dir = new File(FILEDIR);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File fileName = new File(FILEPATH);
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
                    Deadlines deadline = new Deadlines(taskArr[TASKDESCRIPTION], taskArr[TASKTIME]);
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
            System.out.println(e.getMessage());
        }
        return storedTasks;
    }

    public static void save(ArrayList<Task> storedTasks) {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (Task task : storedTasks) {
                fw.write(task.storedTaskString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
