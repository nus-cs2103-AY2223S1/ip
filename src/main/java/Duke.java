import java.time.format.DateTimeParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final File FILE_PATH = new File("./data/duke.txt");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Introduction();
        ReadData();

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            Process(input);
            input = sc.nextLine();
        }

        SaveData();
    }

    private static void Introduction() {
        System.out.println("hello");
        System.out.println("can i help you?");
    }

    private static void ReadData() {
        try {
            Scanner fileScanner = new Scanner(FILE_PATH);
            while (fileScanner.hasNextLine()) {
                String[] info = fileScanner.nextLine().split(" \\| ");
                String type = info[0];
                boolean isDone = info[1].equals("1") ? true : false;
                String description = info[2];
                Task task;
                switch (type) {
                case "T":
                    task = new Todo(description, isDone);
                    break;
                case "D":
                    task = new Deadline(description, isDone, info[3]);
                    break;
                case "E":
                    task = new Event(description, isDone, info[3]);
                    break;
                default:
                    throw new DukeException();
                }
                tasks.add(task);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("data cannot be found");
        } catch (DukeException e) {
            System.out.println("data cannot be read");
        }
    }

    private static void Process(String s) {
        String[] words = s.split(" ");
        String command = words[0];
        switch (command) {
        case "list":
            OutputList();
            break;
        case "done":
            MarkItemDone(s);
            break;
        case "unmark":
            MarkItemUndone(s);
            break;
        case "todo":
            InsertTodo(s);
            break;
        case "deadline":
            InsertDeadline(s);
            break;
        case "event":
            InsertEvent(s);
            break;
        case "delete":
            DeleteTask(s);
            break;
        default:
            System.out.println("sorry, I don't understand you");
            break;
        }
    }

    private static void OutputList() {
        if (tasks.size() == 0) {
            System.out.println("you got no tasks");
        } else {
            System.out.println("heres your tasks");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.format("\t%d.%s\n", i + 1, tasks.get(i));
            }
        }
    }

    private static void InsertTodo(String input) {
        try {
            String description = input.substring(5);
            InsertTask(new Todo(description, false));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        }
    }

    private static void InsertDeadline(String input) {
        try {
            String[] items = input.substring(9).split(" /by ");
            InsertTask(new Deadline(items[0], false, items[1]));
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        } catch (DateTimeParseException e) {
            System.out.println("please enter a valid date format.");
            System.out.println("date: dd/mm/YYYY");
        }
    }

    private static void InsertEvent(String input) {
        try {
            String[] items = input.substring(6).split(" /at ");
            InsertTask(new Event(items[0], false, items[1]));
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        } catch (DateTimeParseException e) {
            System.out.println("please enter a valid date format.");
            System.out.println("date and time: dd/mm/YYYY HH:mm:ss");
        }
    }

    private static void InsertTask(Task task) {
        tasks.add(task);
        System.out.println("added: ");
        System.out.println("\t" + task);
        System.out.format("you have %d task(s) in the list\n", tasks.size());
    }

    private static void MarkItemDone(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException();
            }
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).markDone();
            System.out.println("cool, this task is marked as done");
            System.out.println("\t" + tasks.get(index - 1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("format: mark <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("enter a valid index");
        }
    }

    private static void MarkItemUndone(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException();
            }
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).markUndone();
            System.out.println("ok, this task is marked as not done yet");
            System.out.println("\t" + tasks.get(index - 1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("format: mark <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("enter a valid index");
        }
    }

    private static void DeleteTask(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException();
            }
            int index = Integer.parseInt(words[1]);
            tasks.remove(index - 1);
            System.out.println("ok, i removed this task");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("format: mark <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("enter a valid index");
        }
    }

    private static void SaveData() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                String toSave = task.SaveString();
                fw.write(toSave);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("data cannot be saved");
        }
    }
}
