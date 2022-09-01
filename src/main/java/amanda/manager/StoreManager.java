package amanda.manager;

import amanda.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StoreManager {

    protected String path;

    public StoreManager(String filepath) {
        this.path = filepath;
    }


    public void load(TaskManager taskManager) throws IOException {
        if (Files.notExists(Paths.get(path))) {
            File file = new File(this.path);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            File file = new File(this.path);
            System.out.println(file);
            Scanner read = new Scanner(file);
            String curr = read.nextLine();
            while (true) {
                StringTokenizer tokens = new StringTokenizer(curr, "/");
                String token = tokens.nextToken();
                if (token.equals("T")) {
                    boolean isDone = tokens.nextToken().equals("1");
                    Task task = new Todo(tokens.nextToken());
                    if (isDone) {
                        task.doTask();
                    }
                    taskManager.getList().add(task);
                } else if (token.equals("D")) {
                    boolean isDone = tokens.nextToken().equals("1");
                    Task task = new Deadline(tokens.nextToken(), tokens.nextToken());
                    if (isDone) {
                        task.doTask();
                    }
                    taskManager.getList().add(task);
                } else if (token.equals("E")) {
                    boolean isDone = tokens.nextToken().equals("1");
                    Task task = new Event(tokens.nextToken(), tokens.nextToken());
                    if (isDone) {
                        task.doTask();
                    }
                    taskManager.getList().add(task);
                }
                if (!read.hasNextLine()) {
                    break;
                }
                curr = read.nextLine();
            }
            read.close();
        }
    }

    public void store(TaskManager taskManager) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(this.path, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (Task t : taskManager.getList()) {
            String curr = t.getType() + "/" + t.getState() + "/" + t.getTask();
            if (!t.getType().equals("T")) {
                curr += "/" + t.getTime();
            }
            assert writer != null;
            writer.println(curr);
        }
        assert writer != null;
        writer.close();
    }
}
