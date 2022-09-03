import java.util.Scanner;

public class Duke {
    private static final String SAVED_PATH = "data/duke.txt";
    private static final String HORIZONTAL_BORDER = "_________________________________\n";
    private static final String INVALID_TODO_INPUT = " ☹ OOPS!!! The description of a todo cannot be empty.\n";
    private static final String INVALID_DEADLINE_INPUT = "☹ OOPS!!! Please use proper deadline formatting: deadline {task} /by {time}\n";
    private static final String INVALID_EVENT_INPUT = "☹ OOPS!!! Please use proper event formatting: event {task} /at {time}\n";
    private static final String INVALID_ACCESS_EMPTY_TASKLIST = "☹ OOPS!!! Task does not exist. Initialise a task first, then try again\n";
    private static final String INVALID_USER_INPUT = "☹ OOPS!!! Please use one of these keywords: {deadline, event, todo} followed by \\\"by\\\" and \\\"at\\\" for deadline and event tasks respectively.\n";

    private simpleDatabase database;
    private TaskList taskList;

    private Duke(){
        this.database = new simpleDatabase(SAVED_PATH);
        try {
            this.taskList = this.database.getMemory();
        } catch (DukeException e) {
            System.out.println(errorMessage(e.getMessage() + "\n"));
            System.exit(0);
        }
    }

    private static String errorMessage(String errorMessage) {
        return HORIZONTAL_BORDER + errorMessage + HORIZONTAL_BORDER;
    }

    private static String welcomeMessage(){
        return HORIZONTAL_BORDER + "Hello! I'm Duke\nWhat can I do for you?\n" + HORIZONTAL_BORDER;
    }

    private static String byeMessage(){
        return HORIZONTAL_BORDER + "Bye. Hope to see you again soon!\n" + HORIZONTAL_BORDER;
    }

    private String listContents(){
        return HORIZONTAL_BORDER + this.taskList + HORIZONTAL_BORDER;
    }

    public String addTaskMessage(String taskString) {
        return HORIZONTAL_BORDER +  "Got it. I've added this task:\n" + taskString + "\n" + "Now you have " + this.taskList.getCount() + " tasks in the list.\n" + HORIZONTAL_BORDER;
    }

    public String taskNotFoundMessage(){
        return "☹ OOPS!!! Task does not exist. Try another number between 1 and " + this.taskList.getCount() + "\n";
    }

    private String markDoneMessage(int position) throws DukeException{
        boolean isTaskMarked = this.taskList.markTaskAtPos(position);
        if (isTaskMarked) {
            Task currentTask = this.taskList.getTask(position);
            database.save(this.taskList);
            return HORIZONTAL_BORDER + "Nice! I've marked this task as done:\n" + currentTask + "\n" + HORIZONTAL_BORDER;
        } else if (this.taskList.getCount() == 0) {
            throw new DukeException(INVALID_ACCESS_EMPTY_TASKLIST);
        } else {
            throw new DukeException(taskNotFoundMessage());
        }
    }

    private String unmarkDoneMessage(int position) throws DukeException {
        boolean isTaskUnmarked = this.taskList.unmarkTaskAtPos(position);
        if (isTaskUnmarked) {
            Task currentTask = this.taskList.getTask(position);
            database.save(this.taskList);
            return HORIZONTAL_BORDER + "OK, I've marked this task as not done yet:\n" + currentTask + "\n" + HORIZONTAL_BORDER;
        } else if (this.taskList.getCount() == 0) {
            throw new DukeException(INVALID_ACCESS_EMPTY_TASKLIST);
        } else {
            throw new DukeException(taskNotFoundMessage());
        }
    }

    private String deleteTaskMessage(int position) throws DukeException {
        try {
            Task deletedTask = this.taskList.deleteTaskAtPos(position);
            database.save(this.taskList);
            return HORIZONTAL_BORDER + "Noted. I've removed this task:\n" + deletedTask + "\n" + "" + "Now you have " + this.taskList.getCount() + " tasks in the list.\n" + HORIZONTAL_BORDER;
        } catch (IndexOutOfBoundsException e){
            if (this.taskList.getCount() == 0){
                throw new DukeException(INVALID_ACCESS_EMPTY_TASKLIST);
            } else {
                throw new DukeException(taskNotFoundMessage());
            }
        }
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String makeToDoFromInput(String input) throws DukeException {
        String description = input.substring("todo".length()).strip();
        if (!description.equals("")) {
            ToDo newToDo = new ToDo(description);
            this.taskList.add(newToDo);
            database.save(newToDo.toSimpleString());
            return addTaskMessage(newToDo.toString());
        } else {
            throw new DukeException(INVALID_TODO_INPUT);
        }
    }

    private String makeDeadlineFromInput(String input) throws DukeException {
        String[] stringArray = input.substring("deadline".length()).strip().split("/by");
        if (stringArray.length > 1) {
            Deadline newDeadline = new Deadline(stringArray[0].strip(), stringArray[1].strip());
            this.taskList.add(newDeadline);
            database.save(newDeadline.toSimpleString());
            return addTaskMessage(newDeadline.toString());
        } else {
            throw new DukeException(INVALID_DEADLINE_INPUT);
        }
    }

    private String makeEventFromInput(String input) throws DukeException {
        String[] stringArray = input.substring("event".length()).strip().split("/at");
        if (stringArray.length > 1) {
            Event newEvent = new Event(stringArray[0].strip(), stringArray[1].strip());
            this.taskList.add(newEvent);
            database.save(newEvent.toSimpleString());
            return addTaskMessage(newEvent.toString());
        } else {
            throw new DukeException(INVALID_EVENT_INPUT);
        }
    }

    public void run(){
        System.out.println(welcomeMessage());
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        boolean exitNow = false;
        while(!exitNow) {
            try {
                String[] commandList = s.strip().split(" ");
                String command = commandList[0].toLowerCase();
                if (command.equals("bye") && commandList.length == 1) {
                    exitNow = true;
                    System.out.println(byeMessage());
                } else if (command.equals("list") && commandList.length == 1) {
                    System.out.println(listContents());
                } else if (command.equals("mark") && commandList.length > 1 && isInteger(commandList[1])) {
                    int taskIndexNum = Integer.parseInt(commandList[1]);
                    System.out.println(markDoneMessage(taskIndexNum));
                } else if (command.equals("unmark") && commandList.length > 1 && isInteger(commandList[1])) {
                    int taskIndexNum = Integer.parseInt(commandList[1]);
                    System.out.println(unmarkDoneMessage(taskIndexNum));
                } else if (command.equals("deadline")) {
                    System.out.println(makeDeadlineFromInput(s));
                } else if (command.equals("event")) {
                    System.out.println(makeEventFromInput(s));
                } else if (command.equals("todo")) {
                    System.out.println(makeToDoFromInput(s));
                } else if (command.equals("delete") && commandList.length > 1 && isInteger(commandList[1])){
                    int taskIndexNum = Integer.parseInt(commandList[1]);
                    System.out.println(deleteTaskMessage(taskIndexNum));
                } else if (!s.strip().equals("")) {
                    System.out.println(HORIZONTAL_BORDER + INVALID_USER_INPUT + HORIZONTAL_BORDER);
                }
            } catch (DukeException e) {
                System.out.println(HORIZONTAL_BORDER + e.getMessage() + HORIZONTAL_BORDER);
            } finally {
                if (!exitNow) {
                    s = scan.nextLine();
                }
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        Duke sampleDuke = new Duke();
        sampleDuke.run();
    }
}
