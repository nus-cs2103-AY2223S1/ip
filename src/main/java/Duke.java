import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final String GREETING = "Hello";
    private final String BYE = "Goodbye";
    private ArrayList<Task> list = new ArrayList<>();
    private File directory = new File("data");
    private File file = new File("data/duke.txt");

    public Duke() {
    }

    public void getTasksFromDisk() throws FileNotFoundException, DukeException {
        if (this.file.exists()) {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] taskArr = line.split(" \\| ");
                if (taskArr[0].equals("T")) {
                    String done = taskArr[1];
                    String description = taskArr[2];
                    Todo t = new Todo(description);
                    if (done.equals("X")) {
                        t.markDone();
                    }
                    this.loadTask("todo", t);
                } else if (taskArr[0].equals("D")) {
                    String done = taskArr[1];
                    String description = taskArr[2];
                    String date = taskArr[3];
                    Deadline t = new Deadline(description, date);
                    if (done.equals("X")) {
                        t.markDone();
                    }
                    this.loadTask("deadline", t);
                } else if (taskArr[0].equals("E")) {
                    String done = taskArr[1];
                    String description = taskArr[2];
                    String date = taskArr[3];
                    Event t = new Event(description, date);
                    if (done.equals("X")) {
                        t.markDone();
                    }
                    this.loadTask("event", t);
                } else {
                    throw new DukeException("Oops, unknown task type.");
                }
            }
            System.out.println("Loaded tasks.");
        }
    }

    public void saveTasks() throws IOException {
        if (!this.directory.exists()) {
            this.directory.mkdir();
        }
        if (!this.file.exists()) {
            this.file.createNewFile();
        }
        FileWriter fw = new FileWriter(this.file);
        for (int i = 0; i < this.list.size(); i++) {
            fw.write(this.list.get(i).save() + System.lineSeparator());
        }
        fw.close();
    }

    public void doGreeting() {
        System.out.println(this.GREETING);
    }

    public void doBye() {
        System.out.println(this.BYE);
    }

    public boolean isBye(String input) {
        return input.equals("bye");
    }

    public void showList() {
        System.out.println("List of tasks:");
        for (int i = 1; i < this.list.size() + 1; i++) {
            System.out.println(i + ". " + this.list.get(i - 1));
        }
    }

    public void markTask(int ind, boolean done) throws DukeException {
        if (ind > this.list.size() - 1) {
            throw new DukeException("Oops, no such task found.");
        } else if (done) {
            this.list.get(ind).markDone();
            System.out.println("Task done: " + this.list.get(ind));
        } else {
            this.list.get(ind).markNotDone();
            System.out.println("Task not done: " + this.list.get(ind));
        }
    }

    public void loadTask(String type, Task task) {
        if (type.equals("todo")) {
            this.list.add(this.list.size(), task);
        } else if (type.equals("deadline")) {
            this.list.add(this.list.size(), task);
        } else if (type.equals("event")) {
            this.list.add(this.list.size(), task);
        }
    }

    public void addTask(String type, String desc) {
        Task t;
        if (type.equals("todo")) {
            t = new Todo(desc);
            this.list.add(this.list.size(), t);
            System.out.println("Added ToDo: " + t);
        }
    }

    public void addTask(String type, String desc, String date) {
        Task t;
        if (type.equals("deadline")) {
            t = new Deadline(desc, date);
            this.list.add(this.list.size(), t);
            System.out.println("Added Deadline: " + t);
        } else if (type.equals("event")) {
            t = new Event(desc, date);
            this.list.add(this.list.size(), t);
            System.out.println("Added Event: " + t);
        }
    }

    public void deleteTask(int ind) throws DukeException {
        if (ind > this.list.size() - 1) {
            throw new DukeException("Oops, no such task to delete.");
        } else {
            System.out.println("Task removed: " + this.list.get(ind));
            this.list.remove(ind);
            System.out.println(this.list.size() + " tasks remaining.");
        }
    }

    public void giveInput(String input) throws DukeException {
        if (input.equals("list")) {
            this.showList();
        } else if (input.startsWith("mark")) {
            if (input.length() < 6) {
                throw new DukeException("Oops, no task given to mark as done.");
            }
            int i = Integer.parseInt(input.substring(5)) - 1;
            this.markTask(i, true);
        } else if (input.startsWith("unmark")) {
            if (input.length() < 8) {
                throw new DukeException("Oops, no task given to mark as not done.");
            }
            int i = Integer.parseInt(input.substring(7)) - 1;
            this.markTask(i, false);
        } else if (input.startsWith("todo")) {
            if (input.length() < 6) {
                throw new DukeException("Oops, the description of a todo task cannot be empty.");
            }
            String desc = input.substring(5);
            this.addTask("todo", desc);
        } else if (input.startsWith("deadline")) {
            if (input.length() < 10) {
                throw new DukeException("Oops, the description of a deadline task cannot be empty.");
            } else if (!input.contains("/by")) {
                throw new DukeException("Oops, no deadline given for deadline task.");
            }
            String[] str = input.split(" /by ", 2);
            String s1 = str[0].substring(9);
            this.addTask("deadline", s1, str[1]);
        } else if (input.startsWith("event")) {
            if (input.length() < 7) {
                throw new DukeException("Oops, the description of an event task cannot be empty.");
            } else if (!input.contains("/at")) {
                throw new DukeException("Oops, no date given for event task.");
            }
            String[] str = input.split(" /at ", 2);
            String s1 = str[0].substring(6, str[0].length());
            this.addTask("event", s1, str[1]);
        } else if (input.startsWith("delete")) {
            if (input.length() < 8) {
                throw new DukeException("Oops, no task given to delete.");
            }
            int i = Integer.parseInt(input.substring(7)) - 1;
            this.deleteTask(i);
        } else {
            throw new DukeException("Oops, I don't know what that means");
        }
    }

    public void start() throws FileNotFoundException, IOException, DukeException {
        this.doGreeting();
        this.getTasksFromDisk();
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while (!this.isBye(input)) {
            try {
                this.giveInput(input);
            } catch (DukeException e) {
                System.out.println(e);
            }
            input = sc.nextLine();
        }
        this.saveTasks();
        this.doBye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Oops, something went wrong: " + e.getMessage());
        } catch (DukeException e) {
        }
    }
}
