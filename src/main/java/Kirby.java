import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * The Kirby class implements the main method of the bot
 * with all the relevant commands.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Kirby {
    enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    private ArrayList<Task> Tasks;
    private int taskCount;
    private File storeFile;

    public Kirby() throws FileNotFoundException {
        this.Tasks = new ArrayList<>();
        // has previously stored
        if (Save.checkIfCreated()) {
            this.storeFile = Save.alreadyExist();
            this.Tasks = Save.readFromFile();
            assert Tasks != null;
            this.taskCount = Tasks.size();
        } else {
            this.taskCount = 0;
        }
    }

    private void addTaskCount() {
        taskCount += 1;
    }

    private void subtractTaskCount() {
        taskCount -= 1;
    }

    private void printTaskCount() {
        if (taskCount > 1) {
            System.out.println("Now you have " + taskCount + " tasks in the bag!");
        } else {
            System.out.println("Now you have " + taskCount + " task in the bag!");
        }
    }

    private void welcome() {
        System.out.println("Hai I'm Kirby (੭｡╹▿╹｡)੭ your friendly chat assistant!! \n" + "What amazing plans do you have today?");
    }

    private void goodbye() {
        System.out.println("I loved talking to you ･ω･\n" + "Hope to see you again!");
    }

    private void showList(ArrayList<Task> Tasks) {
        System.out.println("Here is your bag of fabulous tasks:");
        for (int i = 0; i < Tasks.size(); i++) {
            Task currTask = Tasks.get(i);
            System.out.println(i + 1 + ": " + currTask.toString());
        }
    }

    private void mark(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("mark");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        if (Tasks.size() == 0 || taskIndex < 1 || taskIndex > Tasks.size()) {
            throw new KirbyMissingArgumentException("mark");
        }
            Task currTask = Tasks.get(taskIndex - 1);
            currTask.setCompleted();
            System.out.println("Awesome :D I've marked " + currTask + " completed!");
    }

    private void unmark(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("unmark");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        if (Tasks.size() == 0 || taskIndex < 1 || taskIndex > Tasks.size()) {
            throw new KirbyMissingArgumentException("unmark");
        }
        Task currTask = Tasks.get(taskIndex - 1);
        currTask.setIncomplete();
        System.out.println("Okay, I've marked " + currTask + " pending!");
    }

    private void toDo(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException, IOException {
        if (inputString.length() <= 5) {
            throw new KirbyMissingArgumentException("todo");
        }
        String taskName = inputString.substring(inputString.indexOf(' ') + 1);
        Todo todo = new Todo(taskName);
        Tasks.add(todo);
        System.out.println("Added into your bag of fabulous tasks: " + todo);
        addTaskCount();
        printTaskCount();
        try {
            Save.writeTask(this.Tasks, this.storeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deadline(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException, IOException {
        if (!inputString.contains("/by") || inputString.length() - 1 < inputString.indexOf("/by") + 4) {
            throw new KirbyMissingArgumentException("deadline");
        }
        String taskName = inputString.substring(inputString.indexOf("deadline") + 9, inputString.indexOf(" /by"));
        String by = inputString.substring(inputString.indexOf("/by") + 4);
        Deadline deadline = new Deadline(taskName, by);
        Tasks.add(deadline);
        System.out.println("Added into your bag of fabulous tasks: " + deadline);
        addTaskCount();
        printTaskCount();
        try {
            Save.writeTask(this.Tasks, this.storeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void event(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException, IOException {
        if (!inputString.contains("/at") || inputString.length() - 1 < inputString.indexOf("/at") + 4) {
            throw new KirbyMissingArgumentException("event");
        }
        String taskName = inputString.substring(inputString.indexOf("event") + 6, inputString.indexOf(" /at"));
        String at = inputString.substring(inputString.indexOf("/at") + 4);
        Event event = new Event(taskName, at);
        Tasks.add(event);
        System.out.println("Added into your bag of fabulous tasks: " + event);
        addTaskCount();
        printTaskCount();
        try {
            Save.writeTask(this.Tasks, this.storeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void notDefinedCommand() throws KirbyInvalidCommandException {
        throw new KirbyInvalidCommandException();
    }

    private void delete(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("delete");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        if (Tasks.size() == 0 || taskIndex < 1 || taskIndex > Tasks.size()) {
            throw new KirbyMissingArgumentException("delete");
        }
        Task currTask = Tasks.get(taskIndex - 1);
        Tasks.remove(taskIndex - 1);
        subtractTaskCount();
        System.out.println("Removed from your bag of fabulous tasks: " + currTask.toString());
        printTaskCount();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Kirby kirby = new Kirby();
        kirby.welcome();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
                String inputString = scanner.nextLine();
                try {
                    TaskType taskType = null;
                    switch (inputString.split(" ")[0]) {
                    case "todo":
                        taskType = TaskType.TODO;
                        break;
                    case "deadline":
                        taskType = TaskType.DEADLINE;
                        break;
                    case "event":
                        taskType = TaskType.EVENT;
                        break;
                    }

                    if (taskType != null) {
                        // no stored file
                        if (!Save.checkIfCreated()) {
                            kirby.storeFile = Save.createDataFile();
                        }
                        switch (taskType) {
                        case TODO:
                            try {
                                kirby.toDo(inputString, kirby.Tasks);
                                break;
                            } catch (KirbyMissingArgumentException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                        case DEADLINE:
                            try {
                                kirby.deadline(inputString, kirby.Tasks);
                                break;
                            } catch (KirbyMissingArgumentException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                        case EVENT:
                            try {
                                kirby.event(inputString, kirby.Tasks);
                                break;
                            } catch (KirbyMissingArgumentException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                        }

                    }

                    else if (inputString.equals("bye")) {
                        kirby.goodbye();
                        break;
                    }

                    else if (inputString.equals("list")) {
                        kirby.showList(kirby.Tasks);
                    }

                    else if (inputString.split(" ")[0].equals("mark")) {
                        try {
                            kirby.mark(inputString, kirby.Tasks);
                        } catch (KirbyMissingArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    else if (inputString.split(" ")[0].equals("unmark")) {
                        try {
                            kirby.unmark(inputString, kirby.Tasks);
                        } catch (KirbyMissingArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    else if (inputString.split(" ")[0].equals("delete")) {
                        try {
                            kirby.delete(inputString, kirby.Tasks);
                        } catch (KirbyMissingArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    else {
                        kirby.notDefinedCommand();
                    }
                } catch (KirbyInvalidCommandException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}