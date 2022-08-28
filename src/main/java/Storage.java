import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> load() {
        File tasks = new File(filePath);
        tasks.getParentFile().mkdirs();
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (!tasks.createNewFile()) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(tasks.getPath()));
                    label:
                    for (String line; (line = reader.readLine()) != null; ) {
                        String[] taskInfo = line.split("[*]", 4);

                        switch (taskInfo[0]) {
                            case "T":
                                Task toDo = new ToDo(taskInfo[2]);
                                if (taskInfo[1].equals("1")) {
                                    toDo.setDone();
                                }
                                taskList.add(toDo);
                                break;
                            case "D":
                                Task deadline = new Deadline(taskInfo[2], taskInfo[3]);
                                if (taskInfo[1].equals("1")) {
                                    deadline.setDone();
                                }
                                taskList.add(deadline);
                                break;
                            case "E":
                                Task event = new Event(taskInfo[2], taskInfo[3]);
                                if (taskInfo[1].equals("1")) {
                                    event.setDone();
                                }
                                taskList.add(event);
                                break;
                            default:
                                System.out.println("Error in file" + tasks.getAbsolutePath());
                                break label;
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Unable to load file!");
                }
            }
        } catch (IOException e) {
            System.out.println("Error in locating/creating the save file");
        }
        return taskList;
    }

}
