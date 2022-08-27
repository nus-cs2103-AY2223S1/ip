import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class TaskList {
    private ArrayList<Task> tasks;
    private ArrayList<String> addCommands;   // running these commands will always give the tasks array
    // delete, update, list functions

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.addCommands = new ArrayList<>();
    }

    public void add(Task task, String command, Storage storage) throws IOException {
        this.tasks.add(task);
        this.addCommands.add(command);

        StringJoiner sj = new StringJoiner("\n", "", "\n");
        this.addCommands.stream().forEach(c -> sj.add(c));
        try {
            storage.write(sj.toString());
        } catch (IOException e) {
            this.tasks.remove(task);
            this.addCommands.remove(command);
            throw new IOException("There was a problem writing the change to the file. Task not added.");
        }
    }

    // gets the (i-1)th task in tasks
    public Task get(int i) throws IndexOutOfBoundsException {
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }

    public void remove(int i, Storage storage) throws IOException {
        Task task = this.get(i);
        String commandString = this.addCommands.get(i);
        this.tasks.remove(i);
        this.addCommands.remove(i);

        StringJoiner sj = new StringJoiner("\n", "", "\n");
        this.addCommands.stream().forEach(c -> sj.add(c));
        try {
            storage.write(sj.toString());
        } catch (IOException e) {
            this.tasks.add(i, task);
            this.addCommands.add(i, commandString);
            throw new IOException("There was a problem writing the change to the file. Task not removed.");
        }
    }

    public void mark(int i, Storage storage) throws IOException {
        this.get(i).mark();
        String commandString = this.addCommands.get(i);
        if (!commandString.contains("/done")) {
            this.addCommands.set(i, commandString + " /done");
        }

        StringJoiner sj = new StringJoiner("\n", "", "\n");
        this.addCommands.stream().forEach(c -> sj.add(c));
        try {
            storage.write(sj.toString());
        } catch (IOException e) {
            this.get(i).unmark();
            this.addCommands.set(i, commandString);
            throw new IOException("There was a problem writing the change to the file. Task not marked.");
        }
    }

    public void unmark(int i, Storage storage) throws IOException {
        this.get(i).unmark();
        String commandString = this.addCommands.get(i);
        if (commandString.contains("/done")) {
            this.addCommands.set(i, commandString.replace("/done", ""));
        }

        StringJoiner sj = new StringJoiner("\n", "", "\n");
        this.addCommands.stream().forEach(c -> sj.add(c));
        try {
            storage.write(sj.toString());
        } catch (IOException e) {
            this.get(i).mark();
            this.addCommands.set(i, commandString);
            throw new IOException("There was a problem writing the change to the file. Task not unmarked.");
        }
    }

    @Override
    public String toString() {
        return String.join("\n",
                IntStream.range(1, this.size() + 1)
                    .mapToObj(i -> String.format("%d. %s", i, this.get(i - 1).toString()))
                    .toArray(String[]::new));
    }
}
