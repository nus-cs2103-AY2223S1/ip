package Duke;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage Class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fileReader);
            String nextLine = br.readLine();
            // break while loop is no more lines to read
            while (nextLine != null) {
                // split string
                String[] nextTask = nextLine.split(",", 4);
                // case 1 : To-Do Task
                switch (nextTask[0]) {
                    case "T":
                        Task newTodo = new Todo(nextTask[2]);
                        if (nextTask[1].equals("1")) {
                            newTodo.markAsDone();
                        }
                        this.tasks.add(newTodo);
                        // case 2 : Event Task
                        break;
                    case "E":
                        Task newEvent = new Event(nextTask[2], nextTask[3]);
                        if (nextTask[1].equals("1")) {
                            newEvent.markAsDone();
                        }
                        this.tasks.add(newEvent);
                        break;
                    case "D":
                        Task newDeadline = new Deadline(nextTask[2], nextTask[3]);
                        if (nextTask[1].equals("1")) {
                            newDeadline.markAsDone();
                        }
                        this.tasks.add(newDeadline);
                        break;
                }
                nextLine = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            try {
                // code adapted from : https://stackoverflow.com/questions/9620683/java-fileoutputstream-create-file-if-not-exists
                File file = new File(filePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e1a) {
                System.out.println("⚡️Luna has encountered an error while loading tasks⚡️" +
                        "\n️Please exit and try again ️⛈");
                e1a.printStackTrace();
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return this.tasks;
    }

    public void save() throws DukeException, IOException {
        try {
            FileWriter fw = new FileWriter("./data/tasks.txt");
            // loop through ArrayList<Task>
            for (Task task : this.tasks) {
                if (task instanceof Todo) {
                    Todo todo = (Todo) task;
                    if (todo.isDone) {
                        fw.write("T,1," + todo.description + "\n");
                    } else {
                        fw.write("T,0," + todo.description + "\n");
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    if (event.isDone) {
                        fw.write("E,1," + event.description + "," + event.event + "\n");
                    } else {
                        fw.write("E,0," + event.description + "," + event.event + "\n");
                    }
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.isDone) {
                        fw.write("D,1," + deadline.description + "," + deadline.deadline + "\n");
                    } else {
                        fw.write("D,0," + deadline.description + "," + deadline.deadline + "\n");
                    }
                }
            }
            fw.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Error finding file, the file might have been deleted from your computer.");
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}