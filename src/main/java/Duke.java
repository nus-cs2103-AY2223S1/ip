import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    private boolean isTerminated = false;
    private MessagePrinter mp = new MessagePrinter();
    private String HELLO_MESSAGE = "Hello! I'm Duke \n"
            + "What can I do for you?";
    private String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!";
    private ArrayList<Task> tasks = new ArrayList<>();

    private static HashMap<String, Integer> commandList = new HashMap<>();
    static {
        HashMap<String, Integer> map = commandList;
        map.put("exit", 0);
        map.put("add", 1);
        map.put("greet", 0);
        map.put("echo", 1);
        map.put("list", 0);
        map.put("mark", 1);
        map.put("unmark", 1);
    }

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

//    public static boolean isValidCommand(Command command) {
//        String action = command.getAction();
//        return commandList.containsKey(action) && commandList.get(action) == command.getParameters().size();
//    }
    public boolean getIsTerminated() {
        return this.isTerminated;
    }

    public Duke() {
        greet();
    }

    public void greet() {
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
        mp.printMessage(Stream.iterate(0, x -> x + 1)
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

    public void exit() {
        this.isTerminated = true;
        mp.printMessage(FAREWELL_MESSAGE);
    }

    public void execute(Command command) {
        switch (command.getAction()) {
            case GREET:
                greet();
                break;
            case ECHO:
                echo(command.getParameters().get(0));
                break;
            case EXIT:
                exit();
                break;
            case ADD:
                add(command.getParameters().get(0));
                break;
            case LIST:
                list();
                break;
            case MARK:
                mark(Integer.parseInt(command.getParameters().get(0)));
                break;
            case UNMARK:
                unmark(Integer.parseInt(command.getParameters().get(0)));
                break;
        }
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
