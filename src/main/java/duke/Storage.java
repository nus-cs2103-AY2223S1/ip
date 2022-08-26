package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {

    private String filepath;
    private File file;

    public Storage(String filepath) {
        this.file = new File(filepath);
        this.filepath = filepath;
    }

    public void load_task(File file) {
            System.out.println("Loading tasks...");
            load_initial(file);
    }

    public void load_initial(File file) {
        TaskList tl = new TaskList();
        BufferedReader br = null;
        String st;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            try {
                if (!((st = br.readLine()) != null)) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            tl.getList().add(stringToTask(st));
            Duke.count++;
            System.out.println(st);
        }
    }

    public TaskList load(File file) {
        TaskList tl = new TaskList();
        Duke.count = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String st;
        while (true) {
            try {
                if (!((st = br.readLine()) != null)) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            tl.getList().add(stringToTask(st));
            Duke.count++;
        }
        return tl;
    }

    public void addTaskToFile(File file, Task task) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(task.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void overwriteFile(File file, TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.close();
            fw = new FileWriter(file, true);
            for (Task task : taskList.getList()) {
                fw.write(task.toString());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task stringToTask(String s) {
        if (s.length() == 0) {
            return null;
        }
        char taskType = s.charAt(1);
        char done = s.charAt(4);
        Task task = null;
        if (taskType == 'T') {
            task = new Todo(s.substring(6));
        } else if (taskType == 'E') {
            int firstDateIndex = s.indexOf('(');
            int lastDateIndex = s.indexOf(')');
            String name = s.substring(6, firstDateIndex);
            String date = s.substring(firstDateIndex + 5, lastDateIndex);
            Parser p = new Parser();
            task = new Event(name, p.parseFileString(date));
        } else if (taskType == 'D') {
            int firstDateIndex = s.indexOf('(');
            int lastDateIndex = s.indexOf(')');
            String name = s.substring(6, firstDateIndex);
            String date = s.substring(firstDateIndex + 5, lastDateIndex);
            Parser p = new Parser();
            task = new Deadline(name,p.parseFileString(date));
        }
        if (done == 'X') {
            task.setStatus("[X]");
        }
        return task;
    }
}
