package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        List<Task> t = taskList.getTaskList();
        for (Task task : t) {
            String temp = task.writeToFile();
            fw.write(temp + "\n");
        }
        fw.close();
    }

    public ArrayList<Task> extractFile() throws WrongMessageException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String temp = reader.nextLine();
                    if (temp.equals("")) continue;
                    String[] series = temp.split("\\|");
                    String type = series[0].trim();
                    if (type.equals("T")) {
                        Task task = Todo.fromFileDescription(temp);
                        list.add(task);
                    } else if (type.equals("E")) {
                        Task task = Event.fromFileDescription(temp);
                        list.add(task);
                    } else if (type.equals("D")) {
                        Task task = Event.fromFileDescription(temp);
                        list.add(task);
                    } else {
                        throw new WrongMessageException();
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
}