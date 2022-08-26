import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class Pluto {
    private static final String CHATBOT = "Pluto";
    private static final String PATH = "PlutoData.txt";
    private enum Type {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        LIST
    }
    private static ArrayList<Task> missions = new ArrayList<>();

    public static void main(String[] args) {

        String introduction = String.format("\tHello I am %s.\n\tWhat can I do for you?", CHATBOT);
        System.out.println(introduction);

        Scanner sc = new Scanner(System.in);

        FileHandler file = new FileHandler(PATH);
        missions = file.getTasks();
        inputCommand(sc, file);

    }

    public static void inputCommand(Scanner sc, FileHandler file) {
        String inputLine = sc.nextLine().strip();
        String exit = "bye";
        while (!inputLine.equals(exit)) {
            try {
                String[] textArr = inputLine.split(" ", 2);
                String command = textArr[0];

                switch (Type.valueOf(command.toUpperCase())) {
                    case TODO:
                        addTask(inputLine, Type.TODO, file);
                        break;
                    case DEADLINE:
                        addTask(inputLine, Type.DEADLINE, file);
                        break;
                    case EVENT:
                        addTask(inputLine, Type.EVENT, file);
                        break;
                    case MARK:
                        isOnlyCommand(inputLine);
                        Task toMark = missions.get(Integer.parseInt(textArr[1]) - 1);
                        toMark.markAsDone();
                        file.rewriteFile(missions);
                        break;
                    case UNMARK:
                        isOnlyCommand(inputLine);
                        Task toUnmark = missions.get(Integer.parseInt(textArr[1]) - 1);
                        toUnmark.markAsUndone();
                        file.rewriteFile(missions);
                        break;
                    case DELETE:
                        deleteTask(inputLine);
                        file.rewriteFile(missions);
                        break;
                    case LIST:
                        System.out.println("\tHere are the tasks in your list:");
                        for (int i = 0; i < missions.size(); i++) {
                            String output = String.format("\t\t%d. %s",i + 1, missions.get(i).toString());
                            System.out.println(output);
                        }
                        break;
                    default:
                        throw new PlutoException("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (PlutoException | IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                inputLine = sc.nextLine().strip();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addTask(String inputLine, Type type, FileHandler file) throws PlutoException, IOException {
        isOnlyCommand(inputLine);
        Task t = null;
        switch (type) {
            case TODO:
                String todo = inputLine.substring(5);
                t = new Todo(todo);
                break;
            case DEADLINE:
                String[] arrDeadline = inputLine.substring(9).split("/by", 2);
                if (arrDeadline.length == 1) {
                    throw new PlutoException(String.format("\tOOPS!!! The deadline date is required."));
                }
                t = new Deadline(arrDeadline[0].strip(), arrDeadline[1].strip());
                break;
            case EVENT:
                String[] arrEvent = inputLine.substring(6).split("/at", 2);
                if (arrEvent.length == 1) {
                    throw new PlutoException(String.format("\tOOPS!!! The event date is required."));
                }
                t = new Event(arrEvent[0].strip(), arrEvent[1].strip());
                break;
        }
        missions.add(t);
        file.appendToFile(t.toFile());
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t.toString());
        System.out.println(String.format("\tNow you have %d tasks in the list.", missions.size()));
    }

    public static void deleteTask(String inputLine) {
        int idx = Integer.parseInt(inputLine.substring(7).strip());
        System.out.println("\tNoted. I've removed this task:");
        System.out.println(String.format("\t\t%s", missions.get(idx - 1).toString()));
        missions.remove(idx - 1);
        System.out.println(String.format("\tNow you have %d tasks in the list.", missions.size()));
    }

    public static void isOnlyCommand(String str) throws PlutoException {
        HashSet<String> commands = new HashSet<>();
        commands.add("todo");
        commands.add("deadline");
        commands.add("event");
        commands.add("mark");
        commands.add("unmark");
        commands.add("delete");
        if (commands.contains(str)) {
            throw new PlutoException(String.format("\tOOPS!!! The description of %s cannot be empty.", str));
        }
    }
}
