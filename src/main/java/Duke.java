import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public enum Instructions {
        bye, list, mark, unmark, todo, deadline, event, delete
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Fungusta\n" + "Peter's personal chatbot\n");
        PullSave();
        String userInput = sc.nextLine();
        while (InstructionReader(userInput) && sc.hasNextLine()) {
            userInput = sc.nextLine();
        }
    }

    public static boolean InstructionReader(String userInput) {
        try {
            if (!userInput.contains(" ")) {
                switch (Instructions.valueOf(userInput)) {
                case bye:
                    System.out.println("Goodbye!");
                    sc.close();
                    return false;
                case list:
                    ListOut();
                    return true;
                case mark:
                case unmark:
                    throw new DukeException(String.format("Choose which index to %s.", userInput));
                case todo:
                case deadline:
                case event:
                    throw new DukeException(String.format("The description of a %s cannot be empty.", userInput));
                case delete:
                    throw new DukeException("Choose which index to delete.");
                }
            }
            String[] input = userInput.split(" ", 2);
            switch (Instructions.valueOf(input[0])) {
            case delete:
                Delete(input[1]);
                Save();
                return true;
            case mark:
            case unmark:
                Marking(input[0], input[1]);
                Save();
                return true;
            case todo:
            case deadline:
            case event:
                AddList(input);
                Save();
                return true;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry I do not understand what that means :(\n");
        }
        return true;
    }

    public static void Delete(String num) throws DukeException {
        try {
            int index = Integer.parseInt(num);
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(index - 1));
            tasks.remove(index - 1);
            if (tasks.size() == 1) {
                System.out.println("Now you have " + tasks.size() + " task in the list");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("deleting requires an integer as index");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Index %d does not exist on the list.", Integer.parseInt(num)));
        }
    }

    public static void Marking(String name, String num) throws DukeException {
        try {
            int index = Integer.parseInt(num);
            switch (Instructions.valueOf(name)) {
            case mark:
                if (!tasks.get(index - 1).getDone()) {
                    tasks.get(index - 1).markTask();
                    break;
                } else {
                    throw new DukeException("Task is already marked.");
                }
            case unmark:
                if (tasks.get(index - 1).getDone()) {
                    tasks.get(index - 1).unmarkTask();
                    break;
                } else {
                    throw new DukeException("Task is already unmarked");
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException(name + "ing requires an integer as index");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Index %s does not exist on the list.", num));
        }
    }

    public static void AddList(String[] input) throws DukeException {
        Task newTask;
        switch (Instructions.valueOf(input[0])) {
        case todo:
            newTask = new ToDos(input[1]);
            break;
        case deadline:
            if (input[1].contains(" /by ")) {
                String[] tempOne = input[1].split(" /by ", 2);
                newTask = new Deadlines(tempOne[0], tempOne[1]);
                break;
            } else {
                throw new DukeException("Deadline does not have proper format.");
            }
        case event:
            if (input[1].contains(" /at ")) {
                String[] tempTwo = input[1].split(" /at ", 2);
                newTask = new Events(tempTwo[0], tempTwo[1]);
                break;
            } else {
                throw new DukeException("Event does not have proper format.");
            }
        default:
            throw new IllegalStateException("Unexpected value: " + input[0]);
        }

        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);

        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.\n");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
        }
    }
    
    public static void ListOut() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("You do not have any tasks in the list");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
        System.out.println();
    }

    public static void Save() {
        try {
            FileWriter fw = new FileWriter("./data/Duke.txt");
            for (Task task : tasks) {
                fw.write(task.toSaveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void CreateFolder(String path) {
        File dataFolder = new File(path);
        dataFolder.mkdir();
    }

    public static void CreateFile(String path) throws IOException {
        File newFile = new File(path);
        newFile.createNewFile();
    }

    public static void PullSave() {
        CreateFolder("./data");
        try {
            CreateFile("./data/Duke.txt");
            UpdateTasks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void UpdateTasks() {
        File save = new File("./data/Duke.txt");
        try {
            Scanner s = new Scanner(save);
            while (s.hasNextLine()) {
                UpdateTaskHelper(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void UpdateTaskHelper(String input) {
        String[] inputSplit = input.split(" ", 2);
        String instruction = inputSplit[0];
        String information = inputSplit[1];
        String[] temp = information.split(" ", 2);
        boolean done = temp[0].equals("1");
        switch (Instructions.valueOf(instruction)) {
        case todo:
            tasks.add(new ToDos(temp[1], done));
            break;
        case deadline:
            String[] taskAndBy = temp[1].split(" ", 2);
            tasks.add(new Deadlines(taskAndBy[0], taskAndBy[1], done));
            break;
        case event:
            String[] taskAndAt = temp[1].split(" ", 2);
            tasks.add(new Events(taskAndAt[0], taskAndAt[1], done));
            break;
        }
    }
}
