import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Duke {
    private boolean isTerminated;
    private MessagePrinter mp;
    private ArrayList<Task> tasks;

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private HashMap<Action, Consumer<Command>> actionConsumerMap = new HashMap<>();

    private void initializeActionConsumerMap() {
        HashMap<Action, Consumer<Command>> map = this.actionConsumerMap;
//        Level_1
        map.put(Action.GREET, command -> greet());
        map.put(Action.ECHO, command -> echo(command.getParameters().get(0)));
        map.put(Action.EXIT, command -> exit());
//        Level_2
        map.put(Action.ADD, command -> add(command.getParameters().get(0)));
        map.put(Action.LIST, command -> list());
//        Level_3
        map.put(Action.MARK, command -> mark(Integer.parseInt(command.getParameters().get(0))));
        map.put(Action.UNMARK, command -> unmark(Integer.parseInt(command.getParameters().get(0))));
        map.put(Action.TODO, command -> todo(command.getParameters().get(0)));
//        Level_4
        map.put(Action.EVENT, command -> event(command.getParameters().get(0), command.getParameters().get(1)));
        map.put(Action.DEADLINE, command -> deadline(command.getParameters().get(0), command.getParameters().get(1)));
    }

    private void initialize() {
        this.tasks = new ArrayList<>();
        initializeActionConsumerMap();
        this.mp = new MessagePrinter();
    }

    public boolean getIsTerminated() {
        return this.isTerminated;
    }

    public Duke() {
        initialize();
        greet();
    }

    public void greet() {
        String HELLO_MESSAGE = "Hello! I'm Duke \n" + "What can I do for you?";
        mp.printMessage("Hello from\n" + this.logo + "\n" + HELLO_MESSAGE);
    }

    public void echo(String msg) {
        mp.printMessage(msg);
    }

    public void add(String msg) {
        Task task = new Task(msg);
        tasks.add(task);
        mp.printMessage("added: " + task.getName());
    }

    public void list() {
        String startMsg = "Here are the tasks in your list:\n";
        mp.printMessage(startMsg + Stream.iterate(0, x -> x + 1)
                .limit(tasks.size())
                .map(x -> String.valueOf(x + 1) + ". " + tasks.get(x).toString())
                .reduce("", (x, y) -> x + y + "\n" ));
    }

    public void mark(int idTask) {
        String successMsg = "Nice! I've marked this task as done:";
        Task task = this.tasks.get(idTask - 1);
        task.setIsDone(true);
        mp.printMessage(successMsg + "\n" + task.toString());
    }

    public void unmark(int idTask) {
        String successMsg = "OK, I've marked this task as not done yet:";
        Task task = this.tasks.get(idTask - 1);
        task.setIsDone(false);
        mp.printMessage(successMsg + "\n" + task.toString());
    }

    public void todo(String msg) {
        String successMsg = "Got it. I've added this task:";
        Task todo = Task.todo(msg);
        tasks.add(todo);
        successMsg = successMsg + "\n" + todo.toString() + "\n" +
                "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
        mp.printMessage(successMsg);
    }

    public void event(String msg, String time) {
        String successMsg = "Got it. I've added this task:";
        Task event = Task.event(msg, time);
        tasks.add(event);
        successMsg = successMsg + "\n" + event.toString() + "\n" +
                "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
        mp.printMessage(successMsg);
    }

    public void deadline(String msg, String time) {
        String successMsg = "Got it. I've added this task:";
        Task deadline = Task.deadline(msg, time);
        tasks.add(deadline);
        successMsg = successMsg + "\n" + deadline.toString() + "\n" +
                "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
        mp.printMessage(successMsg);
    }

    public void exit() {
        String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!";
        this.isTerminated = true;
        mp.printMessage(FAREWELL_MESSAGE);
    }

    public void execute(Command command) {
        Optional.ofNullable(command).ifPresent(x -> this.actionConsumerMap.get(x.getAction()).accept(x));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        while (!duke.getIsTerminated()) {
            Command command = Parser.parseCommand(scanner.nextLine());
            duke.execute(command);
        }
    }
}
