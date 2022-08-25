package carbon;

import carbon.task.Deadline;
import carbon.task.Event;
import carbon.task.Task;
import carbon.task.Todo;
import carbon.error.CarbonException;
import carbon.error.InvalidInputException;
import carbon.error.InvalidParamException;
import carbon.error.OutOfBoundsException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Carbon {
    // chat-bot specific displays
    // ascii art generated from patorjk.com
    // logo for Carbon
    private static final String LOGO = "                 _ _ _ ____ _    ____ ____ _  _ ____ \n" + 
        "                 | | | |___ |    |    |  | |\\/| |___ \n" + 
        "                 |_|_| |___ |___ |___ |__| |  | |___ \n\n" + 
        "  ▄████████    ▄████████    ▄████████ ▀█████████▄   ▄██████▄  ███▄▄▄▄   \n" + 
        " ███    ███   ███    ███   ███    ███   ███    ███ ███    ███ ███▀▀▀██▄ \n" + 
        " ███    █▀    ███    ███   ███    ███   ███    ███ ███    ███ ███   ███ \n" + 
        " ███          ███    ███  ▄███▄▄▄▄██▀  ▄███▄▄▄██▀  ███    ███ ███   ███ \n" + 
        " ███        ▀███████████ ▀▀███▀▀▀▀▀   ▀▀███▀▀▀██▄  ███    ███ ███   ███ \n" +
        " ███    █▄    ███    ███ ▀███████████   ███    ██▄ ███    ███ ███   ███ \n" + 
        " ███    ███   ███    ███   ███    ███   ███    ███ ███    ███ ███   ███ \n" + 
        " ████████▀    ███    █▀    ███    ███ ▄█████████▀   ▀██████▀   ▀█   █▀  \n" + 
        "                           ███    ███                                   \n";

    // actual introduction
    private static final String INTRO = "Hey, Carbon here. ";
    private static final String[] INIT_PROMPTS = {
        "What's up?",
        "How's things going?",
        "Nice weather today, huh?",
        "How can I help you?",
        "Please don't talk to me.",
        "To get an A+ for CS2103T, you have t---[REDACTED]"
    };

    // text for exits
    private static final String[] GOODBYES = {
        "Bye-bye, see you again soon!",
        "Farewell. Stay safe.",
        "Nice meeting you. Let's catch up again sometime.",
        "Bye. Good riddance.",
        "...zzzzzz...",
        "What? Yeah, sorry I gotta run now."
    };

    // scanner for inputs
    private static Scanner sysScanner = new Scanner(System.in);
    
    // filepath for tasks savefile
    private static final String WORKING_DIR = System.getProperty("user.dir");
    private static final String SAVEFILE = Carbon.WORKING_DIR + "/../../../store/tasks.txt";
    private static final String SAVEFILE_DIR = Carbon.WORKING_DIR + "/../../../store/";

    // own fields
    private Random rand;
    private boolean isRunning;
    private List<Task> tasks;

    // io display standardisation methods
    private static void printOut(String text) {
        String divider = "\n···---······---······---······---······---······---······---···\n";
        System.out.println(divider);
        System.out.println("==> " + text);
    }

    private static String printIn() {
        String receiver = "\n··-··--···--\n";
        System.out.println(receiver);
        System.out.print("<-- ");
        String input = Carbon.sysScanner.nextLine();
        return input;
    }

    // actual constructor and init method
    private Carbon() {
        // init fields
        this.rand = new Random();
        this.tasks = this.loadSavefile();

        // String randomPrompt = Carbon.initPrompts[
        //     this.rand.nextInt(Carbon.initPrompts.length)
        // ];

        String randomPrompt = Carbon.INIT_PROMPTS[0];

        System.out.println(Carbon.LOGO);

        // extra space
        System.out.println("\n\n");
        Carbon.printOut(Carbon.INTRO + randomPrompt);
    }

    // main shell method
    private void runShell() {
        this.isRunning = true;
        while (this.isRunning) {
            String input = Carbon.printIn();
            this.process(input);
            if (!this.isRunning) {
                this.exit();
            }
        }
    }

    private List<Task> loadSavefile() {
        List<Task> savedTasks = new ArrayList<Task>();
        try {
            File savefile = new File(Carbon.SAVEFILE);
            if (savefile.isFile()) {
                Scanner savefileScanner = new Scanner(savefile);
                while (savefileScanner.hasNextLine()) {
                    String data = savefileScanner.nextLine();
                    this.loadTask(data, savedTasks);
                }
                savefileScanner.close();
            }
        } catch (FileNotFoundException error) {
            System.out.println(error);
        }
        return savedTasks;
    }

    private void loadTask(String data, List<Task> savedTasks) {
        try {
            Task task = Task.decodeTask(data);
            savedTasks.add(task);
        } catch (CarbonException error) {
            this.processError(error);
        }
    }

    private void saveTasks() throws CarbonException {
        File savefile = new File(Carbon.SAVEFILE);
        File savefileDir = new File(Carbon.SAVEFILE_DIR);
        try {
            // ensures dir and file exists
            savefileDir.mkdir();
            savefile.createNewFile();

            FileWriter writer = new FileWriter(savefile);
            writer.write(this.encodeTasks(this.tasks));
            writer.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }

    private String encodeTasks(List<Task> tasks) {
        String encodedTasks = "";
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            encodedTasks += tasks.get(i).encode();
        }
        return encodedTasks;
    }

    private void process(String input) {
        String lowerCaseInput = input.toLowerCase();
        switch (lowerCaseInput) {
            case "list":
                this.listItems();
                break;
            case "bye":
                this.isRunning = false;
                break;
            default:
                // unable to process as a simple command, pass to next handler
                this.processAdvanced(input);
        }
    }

    private void processAdvanced(String input) {
        String lowerCaseInput = input.toLowerCase();
        try {
            if (lowerCaseInput.startsWith("mark")) {
                this.validateAndMark(input, true);
            } else if (lowerCaseInput.startsWith("unmark")) {
                this.validateAndMark(input, false);
            } else if (lowerCaseInput.startsWith("delete")) {
                this.deleteTask(input);
            } else if (lowerCaseInput.startsWith("todo")) {
                this.addTask(input, Task.Type.TODO);
            } else if (lowerCaseInput.startsWith("deadline")) {
                this.addTask(input, Task.Type.DEADLINE);
            } else if (lowerCaseInput.startsWith("event")) {
                this.addTask(input, Task.Type.EVENT);
            } else {
                // not a command, return invalid input
                CarbonException invalidInput = new InvalidInputException(input);
                throw invalidInput;
            }
            this.saveTasks();
        } catch (CarbonException error) {
            this.processError(error);
        }
    }

    private void processError(CarbonException error) {
        Carbon.printOut(error.toString());
    }

    private void validateAndMark(String input, boolean doneness) throws CarbonException {
        int taskNumber;
        int len = input.length();
        if (doneness) {
            int requiredLen = "mark ".length();
            if (len <= requiredLen) {
                CarbonException invalidParam = new InvalidParamException(input);
                throw invalidParam;
            } else {
                taskNumber = Integer.valueOf(input.substring(requiredLen));
            }
        } else {
            int requiredLen = "unmark ".length();
            if (len <= requiredLen) {
                CarbonException invalidParam = new InvalidParamException(input);
                throw invalidParam;
            } else {
                taskNumber = Integer.valueOf(input.substring(requiredLen));
            }
        }

        if (taskNumber < 1 || taskNumber > this.tasks.size()) {
            CarbonException outOfBounds = new OutOfBoundsException(taskNumber, this.tasks.size());
            throw outOfBounds;
        } else {
            this.setTaskDoneness(taskNumber, doneness);
        }
    }

    private void setTaskDoneness(int taskNumber, boolean doneness) {
        Task task = this.tasks.get(taskNumber - 1);
        task.changeDoneness(doneness);
        String log = String.format("Got it! \n\n    %s", task);
        Carbon.printOut(log);
    }

    private void addTask(String input, Task.Type type) throws CarbonException {
        Task newTask;
        switch (type) {
            case TODO: {
                newTask = Todo.createTask(input);
                break;
            }
            case DEADLINE: {
                newTask = Deadline.createTask(input);
                break;
            }
            case EVENT: {
                newTask = Event.createTask(input);
                break;
            }
            default:
                // should never reach here
                newTask = null;
        }
        this.tasks.add(newTask);
        String log = String.format(
                "I have added: \n    %s\n\n    We've got %s so far.", 
                newTask, 
                this.countTasks()
                );
        Carbon.printOut(log);
    }

    private void deleteTask(String input) throws CarbonException {
        int len = input.length();
        int requiredLen = "delete ".length();
        if (len <= requiredLen) {
            CarbonException invalidParam = new InvalidParamException(input);
            throw invalidParam;
        } else {
            int taskNumber = Integer.valueOf(input.substring(requiredLen));
            if (taskNumber < 1 || taskNumber > this.tasks.size()) {
                CarbonException outOfBounds = new OutOfBoundsException(taskNumber, this.tasks.size());
                throw outOfBounds;
            } else {
                Task taskDeleted = this.tasks.remove(taskNumber - 1);
                String log = String.format(
                        "I have removed: \n    %s\n\n    We've got %s left.",
                        taskDeleted,
                        this.countTasks()
                        );
                Carbon.printOut(log);
            }
        }
    }

    private void listItems() {
        int size = this.tasks.size();
        if (size == 0) {
            String log = "There are no tasks so far.";
            Carbon.printOut(log);
            return;
        }

        String log = "Here are the tasks so far. \n";
        for (int i = 0; i < size; i++) {
            String taskLog = String.format(
                    "\n    %d: %s", 
                    i + 1, 
                    this.tasks.get(i)
                    );
            log += taskLog;
        }
        Carbon.printOut(log);
    }

    private void exit() {
        // String randomGoodbye = Carbon.goodbyes[
        //     this.rand.nextInt(Carbon.goodbyes.length)
        // ];
        
        String randomGoodbye = Carbon.GOODBYES[0] + "\n";
        Carbon.printOut(randomGoodbye);
    }

    private String countTasks() {
        int count = this.tasks.size();
        if (count == 1) {
            return String.format("%d task", count);
        } else {
            return String.format("%d tasks", count);
        }
    }
    
    public static void main(String[] args) {
        Carbon shell = new Carbon();
        shell.runShell();
    }
}
