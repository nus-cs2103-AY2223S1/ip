import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StoreManager {

    private ArrayList<Task> store;

    public StoreManager() {
        this.store = new ArrayList<>();
    }

    public void add(Task task) {
        store.add(task);
        System.out.println("     Let's see if you will actually get this done:");
        System.out.println("        " + task);
        System.out.println("     Now you have " + store.size() + " tasks in the list.");
        System.out.println("    ............................................................\n");
    }

    public void list() {
        System.out.println("     Here are the tasks in your list, now stop disturbing me:");
        for (int i = 0; i < store.size(); i++) {
            System.out.println("     " + (i + 1) + "." + store.get(i));
        }
        System.out.println("    ............................................................\n");
    }

    public void markTask(int taskNo) {
        System.out.println("     Wow! You actually finished the task, I didn't think you have it in you.");
        store.get(taskNo - 1).doTask();
        System.out.println("        " + store.get(taskNo - 1));
        System.out.println("    ............................................................\n");
    }

    public void unmarkTask(int taskNo) {
        System.out.println("     OK, I saw it coming you can't finish anything you started.");
        store.get(taskNo - 1).undoTask();
        System.out.println("        " + store.get(taskNo - 1));
        System.out.println("    ............................................................\n");
    }

    public void deleteTask(int taskNo) {
        System.out.println("     It's fine! Out of sight, out of mind. Am i right?");
        System.out.println("        " + store.get(taskNo - 1));
        store.remove(taskNo - 1);
        System.out.println("     Now you have " + store.size() + " tasks in the list.");
        System.out.println("    ............................................................\n");
    }

    public void load() throws IOException {
        Path path = Paths.get("./data", "amanda.txt");
        if (Files.notExists(path)) {
            File file = new File("./data/amanda.txt");
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            File file = new File("./data/amanda.txt");
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
                    store.add(task);
                } else if (token.equals("D")) {
                    boolean isDone = tokens.nextToken().equals("1");
                    Task task = new Deadline(tokens.nextToken(), tokens.nextToken());
                    if (isDone) {
                        task.doTask();
                    }
                    store.add(task);
                } else if (token.equals("E")) {
                    boolean isDone = tokens.nextToken().equals("1");
                    Task task = new Event(tokens.nextToken(), tokens.nextToken());
                    if (isDone) {
                        task.doTask();
                    }
                    store.add(task);
                }
                if (!read.hasNextLine()) {
                    break;
                }
                curr = read.nextLine();
            }
            read.close();
        }
    }

    public void store() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("./data/amanda.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (Task t : store) {
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
