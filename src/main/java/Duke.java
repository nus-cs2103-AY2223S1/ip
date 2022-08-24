import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> store = new ArrayList<>();
    private static final String FILE_PATH = "data" + File.separator + "taskList.txt";
    private static final String[] COMMAND_HELP = new String[] { "todo [description]",
                                                                "deadline [description] /by [date]",
                                                                "event [description] /at [date]",
                                                                "list",
                                                                "bye" };
    enum COMMANDS {todo, deadline, event, mark, unmark, delete, }

    public static void main(String[] args) {
        loadFromFile();
        System.out.println("Hello! What are we gonna do today?");
        System.out.println("Use '/?' for help");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (isCommand(input.split(" ")[0])) {
                try {
                    parse(input, false);
                } catch (DukeWrongArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Pls try again");
                } catch (DateTimeParseException e) {
                    System.out.println("'" + e.getParsedString() + "'" + " is not valid date/time format");
                    System.out.println("It should be dd/MM/yy and optional 24hr time");
                    System.out.println("Pls try again");

                }
            } else if (input.equals("bye")) {
                saveToFile();
                System.out.println("\tBye! Hope that I was of service!");
                break;
            } else if (input.equals("list")) {
                int number = 1;
                for (Task task : store) {
                    if (task == null) continue;
                    System.out.println("\t" + number + "." + task);
                    number++;
                }
                System.out.println("\nYou currently have " + store.size() + " tasks in the list");
                if (store.size() != 0) {
                    System.out.println("Pls don't procrastinate on the above tasks!");
                }
            } else if (input.equals("/?")) {
                System.out.println("These are the commands available:\n");
                for (String desc : COMMAND_HELP) {
                    System.out.println("\t" + desc);
                }
            } else {
                System.out.println("what's this?! REDO!!!!");
            }
        }
        sc.close();
    }

    private static boolean isCommand(String input) {
        for (COMMANDS c : COMMANDS.values()) {
            if (c.name().equals(input)) return true;
        }
        return false;
    }

    private static void parse(String input, boolean fromSave) throws DukeWrongArgumentException, DateTimeParseException {
        String mark = null;
        String[] arr;
        if (fromSave) {
            String[] item = input.split("\\|");
            mark = item[1];
            arr = item[0].split(" ", 2);
        } else {
            arr = input.split(" ", 2);
        }
        COMMANDS command = COMMANDS.valueOf(arr[0]);
        try {
            switch (command) {
                case mark: {
                    int index = Integer.parseInt(arr[1]) - 1;
                    store.get(index).markAsDone();
                    System.out.println("\tnice! I've marked this task as done:");
                    System.out.println("\t\t" + store.get(index));
                    return;
                }
                case unmark: {
                    int index = Integer.parseInt(arr[1]) - 1;
                    store.get(index).markAsNotDone();
                    System.out.println("\tOk! I've marked this task as not done yet:");
                    System.out.println("\t\t" + store.get(index));
                    return;
                }
                case delete: {
                    int index = Integer.parseInt(arr[1]) - 1;
                    Task remove = store.remove(index);
                    System.out.println("\tOk! I've deleted this task:");
                    System.out.println("\t\t" + remove);
                    return;
                }
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            if (e instanceof IndexOutOfBoundsException) {
                throw new DukeWrongArgumentException(arr[1] + " is not a valid index", e);
            } else {
                throw new DukeWrongArgumentException("Unknown index '" + arr[1] + "'", e);
            }
        }

        switch (command) {
            case todo: {
                try {
                    store.add(new ToDo(arr[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeWrongArgumentException("The proper command is: todo [description]", e);
                }
                break;
            }
            case deadline: {
                try {
                    String[] desc = arr[1].split(" /by ");
                    store.add(new Deadline(desc[0], desc[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeWrongArgumentException("The proper command is: deadline [description] /by [date]", e);
                }
                break;
            }
            case event: {
                try {
                    String[] desc = arr[1].split(" /at ");
                    store.add(new Event(desc[0], desc[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeWrongArgumentException("The proper command is: event [description] /at [date]", e);
                }
                break;
            }
        }
        Task latestTask = store.get(store.size() - 1);
        if (mark != null && mark.equals("X")) {
            latestTask.markAsDone();
        }
        String item = latestTask.toString();
        if (!fromSave) {
            System.out.println("\tadded: " + item);
            System.out.println("You now have " + store.size() + " tasks in the list");
        }
    }

    private static void loadFromFile() {
        File dataFile = new File(FILE_PATH);
        if (dataFile.exists()) {
            try {
                Scanner s = new Scanner(dataFile);
                while (s.hasNext()) {
                    String item = s.nextLine();
                    parse(item, true);
                }
            } catch (FileNotFoundException | DukeWrongArgumentException e) {
                System.out.println("something went wrong while loading save file\n" + e);
            }
        }
    }

    private static void saveToFile() {
        File directory = new File("data" + File.separator);
        File save = new File(FILE_PATH);
        directory.mkdir();
        try {
            save.createNewFile();
        } catch (IOException f) {
            System.out.println("something went wrong while creating save file\n" + f);
        }
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task item : store) {
                fw.write(item.format() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("something went wrong while writing to save file\n" + e);
        }
    }
}
