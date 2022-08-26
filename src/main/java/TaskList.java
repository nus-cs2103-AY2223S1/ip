import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private ArrayList<String> addCommands;   // running these commands will always give the tasks array
    // delete, update, list functions

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.addCommands = new ArrayList<>();
    }

    // takes in a string of add commands
    public TaskList(String commands) {
        this.tasks = new ArrayList<>();
        this.addCommands = new ArrayList<>();
        String[] commandArr = commands.split("\n");
        for(String commandString: commandArr) {
            Command command = Parser.parse(commandString, this);      // should get the command, then execute it to change the task list
            command.execute();
        }
    }

    public void add(Task task, String command) {
        this.tasks.add(task);
        this.addCommands.add(command);
    }

    // gets the (i-1)th task in tasks
    public Task get(int i) throws IndexOutOfBoundsException {
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }

    public void remove(int i) {
        this.tasks.remove(i);
        this.addCommands.remove(i);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.size(); i++) {
            s = s.concat(
                    String.format(
                            "%d. %s\n", i + 1, this.get(i).toString()
                    )
            );
        }
        return s;
    }
}
