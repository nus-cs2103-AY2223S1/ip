import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TaskList {
    private List<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<Task>();
        loadFromFile();
    }

    public void addTask(Task t) {
        lst.add(t);
        System.out.println(String.format("%s\n%s\n%s",
                            Constants.addTask, t.toString(), Constants.numTasks(lst.size())));
    }

    public void deleteTask(int i) throws DukeException {
        checkError(i);
        Task removed = lst.get(i - 1);
        lst.remove(i - 1);
        System.out.println(String.format("%s\n%s\n%s",
                Constants.deleteTask, removed.toString(), Constants.numTasks(lst.size())));
    }

    public void markTask(int i) throws DukeException {
        checkError(i);
        lst.get(i - 1).setMarked();
        System.out.println(String.format("%s\n%s", Constants.markMsg, lst.get(i - 1).toString()));
    }

    public void unmarkTask(int i) throws DukeException {
        checkError(i);
        lst.get(i - 1).setUnmarked();
        System.out.println(String.format("%s\n%s", Constants.unmarkMsg, lst.get(i - 1).toString()));
    }

    public void checkError(int i) throws DukeException{
        if (i > lst.size()) {
            throw new DukeException(Constants.invalidIndex);
        }
    }

    public void printList() {
        System.out.println(Constants.list);
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, lst.get(i).toString()));
        }
    }

    public void loadFromFile(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("list.txt"));
            String line = reader.readLine();
            System.out.println(line);
            while (line != null) {
                String[] parse = line.split(" - ");
                Task t = null;
                if (parse[0].equals("T")) {
                    t = new ToDo(parse[2]);
                } else if (parse[0].equals("E")) {
                    t = new Event(parse[2], parse[3]);
                } else if (parse[0].equals("D")) {
                    t = new Deadline(parse[2], parse[3]);
                } else {
                    throw new DukeException(Constants.invalidFile);
                }
                if (parse[1].equals("[X]")) {
                    t.setMarked();
                }
                lst.add(t);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void savetoFile() {
        String pathname = "list.txt";
        FileWriter fileWriter = null;
        try {
            File file = new File(pathname);
            fileWriter = new FileWriter(file);
            for (int i = 0; i < lst.size(); i++) {
                Task t = lst.get(i);
                String type = t.toString().substring(1,2);
                if (type.equals("T")) {
                    fileWriter.write(String.format("%s - %s- %s\n", type, t.getMarkedStatus(), t.getTaskName()));
                } else {
                    fileWriter.write(String.format("%s - %s- %s-%s\n", type, t.getMarkedStatus(), t.getTaskName(), t.getDate()));
                }
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
