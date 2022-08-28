import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    private final ArrayList<Task> ls = new ArrayList<>(100);
    private static final String line = "Dino:\n";

    public enum TaskList {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        TODO,
        EVENT,
        DELETE
    }

    protected void list() {
        for (int i = 0; i < Task.lsSize(); i++) {
            System.out.println("\t" + (i + 1) + ". " + ls.get(i).toString());
        }
    }

    protected void mark(String[] str) throws DukeException {
        int index;
        Task myTask;
        try {
            index = Integer.parseInt(str[1]) - 1;
        } catch (Exception e) {
            throw new DukeException("Oops! You did not choose which task to mark!");
        }

        if (index > Task.lsSize() || index < 0) {
            throw new DukeException("Oops! Invalid task!");
        }
        myTask = ls.get(index);
        myTask.markAsDone();
        writeFile();

        System.out.println("\tHooray! You have completed this task:\n\t" + myTask);
    }

    protected void unmark(String[] str) throws DukeException {
        int index;
        Task myTask;
        try {
            index = Integer.parseInt(str[1]) - 1;
        } catch (Exception e) {
            throw new DukeException("Oops! You did not choose which task to mark!");
        }

        if (index > Task.lsSize() || index < 0) {
            throw new DukeException("Oops! Invalid task!");
        }
        myTask = ls.get(index);
        myTask.markAsUndone();
        writeFile();

        System.out.println("\tOh no! You have more things to complete:\n\t" + myTask);
    }

    protected void deadline(String[] str) throws DukeException {
        String[] dl;
        Task myTask;
        try {
            dl = str[1].split(" /by ");
        } catch (Exception e) {
            throw new DukeException("Oops! The description of a deadline cannot be empty!");
        }

        try {
            myTask = new Deadline(dl[0], dl[1]);
        } catch (Exception e) {
            throw new DukeException("Oops! When is the deadline?");
        }

        ls.add(myTask);
        writeFile();
        System.out.println("\tadded: " + myTask);
        System.out.println("\tYou have " + Task.lsSize() + " task" + (Task.lsSize() > 1 ? "s!" : "!"));
    }

    protected void todo(String[] str) throws DukeException {
        Task myTask;
        try {
            myTask = new ToDo(str[1]);
        } catch (Exception e) {
            throw new DukeException("Oops! The description of a todo cannot be empty!");
        }

        ls.add(myTask);
        writeFile();
        System.out.println("\tadded: " + myTask);
        System.out.println("\tYou have " + Task.lsSize() + " task" + (Task.lsSize() > 1 ? "s!" : "!"));
    }

    protected void event(String[] str) throws DukeException {
        String[] evnt;
        Task myTask;
        try {
            evnt = str[1].split(" /at ");
        } catch (Exception e) {
            throw new DukeException("Oops! The description of an event cannot be empty!");
        }

        try {
            myTask = new Event(evnt[0], evnt[1]);
        } catch (Exception e) {
            throw new DukeException("Oops! When is the event?");
        }

        ls.add(myTask);
        writeFile();
        System.out.println("\tadded: " + myTask);
        System.out.println("\tYou have " + Task.lsSize() + " task" + (Task.lsSize() > 1 ? "s!" : "!"));
    }

    protected void delete(String[] str) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(str[1]) - 1;
        } catch (Exception e) {
            throw new DukeException("Oops! You did not choose which task to delete!");
        }

        if (index > Task.lsSize() || index < 0) {
            throw new DukeException("Oops! Invalid task!");
        }

        ls.get(index).remove();
        ls.remove(index);
        writeFile();
    }

    protected void createFile() {
        try {
            File file = new File("tasks.txt");
            if (file.createNewFile()) {
                System.out.println("Dino created a new file: " + file.getName() + "\n");
            } else {
                readFile(file);
                System.out.println("Dino found the file in your directory and loaded contents.\n");
            }
        } catch (IOException e) {
            System.out.println("Dino failed at creating file./n");
            e.printStackTrace();
        }
    }

    protected void readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String current = br.readLine();
            while (current != null) {
                String[] str = current.split("\\|", 3);
                TaskList myTask = TaskList.valueOf(str[0].toUpperCase(Locale.ROOT));
                switch (myTask) {
                case DEADLINE:
                    String[] dl = str[2].split("\\|", 2);
                    Task deadline = new Deadline(dl[0], dl[1]);
                    ls.add(deadline);
                    if (Objects.equals(str[1], "1")) {
                        deadline.markAsDone();
                    }
                    break;
                case TODO:
                    Task todo = new ToDo(str[2]);
                    ls.add(todo);
                    if (Objects.equals(str[1], "1")) {
                        todo.markAsDone();
                    }
                    break;
                case EVENT:
                    String[] evnt = str[2].split("\\|", 2);
                    Task event = new Event(evnt[0], evnt[1]);
                    ls.add(event);
                    if (Objects.equals(str[1], "1")) {
                        event.markAsDone();
                    }
                    break;
                }
                current = br.readLine();
            }
        } catch (IOException | IllegalArgumentException | DukeException e) {
            e.printStackTrace();
        }
        br.close();
    }

    protected void writeFile() {
        try {
            FileWriter myWriter = new FileWriter("tasks.txt");
            StringBuilder str = new StringBuilder();
            for (Task l : ls) {
                str.append(format(l));
            }
            myWriter.write(str.toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String format(Task task) {
        if (task instanceof ToDo) {
            return "TODO|" + task.getStatus() + "|" + task.getDescription() + "\n";
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "EVENT|" + task.getStatus() + "|" + task.getDescription() + "|" + event.getAt() + "\n";
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "DEADLINE|" + task.getStatus() + "|" + task.getDescription() + "|" + deadline.getBy() + "\n";
        }
        return "";
    }

    protected void taskLoop() throws DukeException {
        createFile();
        Scanner sc = new Scanner(System.in);
        String curr = "";


        while (!Objects.equals(curr, "bye")) {
            curr = sc.nextLine();
            String[] str = curr.split("\\s", 2);
            System.out.print(line);
            TaskList myTask;

            try {
                myTask = TaskList.valueOf(str[0].toUpperCase(Locale.ROOT));
            } catch (Exception e) {
                throw new DukeException("Oops! I don't know what that means.");
            }

            switch (myTask) {
            case BYE:
                System.out.println("\tBye bye. Hope to see you again soon!");
                break;
            case LIST:
                this.list();
                break;
            case MARK:
                this.mark(str);
                break;
            case UNMARK:
                this.unmark(str);
                break;
            case DEADLINE:
                this.deadline(str);
                break;
            case TODO:
                this.todo(str);
                break;
            case EVENT:
                this.event(str);
                break;
            case DELETE:
                this.delete(str);
                break;
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) throws DukeException {

        System.out.println("               __\n" +
                "              / _)\n" +
                "     _.----._/ /\n" +
                "    /         /\n" +
                " __/ (  | (  |\n" +
                "/__.-'|_|--|_|\n");

        System.out.println(line
                + "\tHello! I'm Dino\n"
                + "\tWhat can I do for you?\n");

        new Duke().taskLoop();

    }
}
