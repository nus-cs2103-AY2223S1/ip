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
    public Storage (String filepath) {
        this.file = new File(filepath);
        this.filepath = filepath;
    }

    public void loads(File file) {
            System.out.println("Loading tasks...");
            load_initial(file);
    }

    public void load_initial(File file) {
        TaskList t = new TaskList();
        BufferedReader br = null;
        String st;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!((st = br.readLine()) != null)) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            t.getList().add(stringToTask(st));
            Duke.count++;
            System.out.println(st);
        }
    }

    public TaskList load(File file) {
        TaskList t = new TaskList();
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
              t.getList().add(stringToTask(st));
              Duke.count++;
        }
        return t;
    }
    public void addTaskToFile(File file, Task t) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(t.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void overwriteFile(File f, TaskList t) {
        try {
            FileWriter fw = new FileWriter(f);
            fw.close();
            fw = new FileWriter(f,true);
            for (Task task : t.getList()) {
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
        char task_type = s.charAt(1);
        char done = s.charAt(4);
        Task task = null;
        if (task_type == 'T') {
            task = new Todo(s.substring(6));
        } else if (task_type == 'E') {
            int indexofdate = s.indexOf('(');
            int indexofdates = s.indexOf(')');
            String name = s.substring(6,indexofdate);
            String date = s.substring(indexofdate + 5, indexofdates);
            Parser p = new Parser();
            task = new Event(name, p.parseFileString(date));
        } else if (task_type == 'D') {
            int indexofdate = s.indexOf('(');
            int indexofdates = s.indexOf(')');
            String name = s.substring(6,indexofdate);
            String date = s.substring(indexofdate + 5, indexofdates);
            Parser p = new Parser();
            task = new Deadline(name,p.parseFileString(date));
        }
        if (done == 'X') {
            task.setStatus("[X]");
        } return task;
    }
}
