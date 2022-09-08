import java.util.Scanner;
import java.time.LocalDate;

public class Duke {

    private final String SAVED_PATH = "data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private Duke() {
        this.storage = new Storage(SAVED_PATH);
        try {
            this.taskList = this.storage.load();
        } catch (DukeException e) {
            ui.showError(e.getMessage() + "\n");
            System.exit(0);
        }
        this.ui = new Ui(this.taskList);
    }

//    private static String errorMessage(String errorMessage) {
//        return HORIZONTAL_BORDER + errorMessage + HORIZONTAL_BORDER;
//    }

//    private static String welcomeMessage(){
//        return HORIZONTAL_BORDER + "Hello! I'm Duke\nWhat can I do for you?\n" + HORIZONTAL_BORDER;
//    }

//    private static String byeMessage(){
//        return HORIZONTAL_BORDER + "Bye. Hope to see you again soon!\n" + HORIZONTAL_BORDER;
//    }

//    private String listContents(){
//        return HORIZONTAL_BORDER + this.taskList + HORIZONTAL_BORDER;
//    }

//    public String addTaskMessage(String taskString) {
//        return HORIZONTAL_BORDER +  "Got it. I've added this task:\n" + taskString + "\n" + "Now you have " + this.taskList.getCount() + " tasks in the list.\n" + HORIZONTAL_BORDER;
//    }

//    public String taskNotFoundMessage(){
//        return "Task does not exist. Try another number between 1 and " + this.taskList.getCount();
//    }

    private void markDoneMessage(String command) throws DukeException{
        String[] commandList = command.strip().split(" ");
        try {
            int taskIndexNum = Integer.parseInt(commandList[1]);
            this.taskList.markTaskAtPos(taskIndexNum);
            Task currentTask = this.taskList.getTask(taskIndexNum);
            storage.save(this.taskList);
            ui.showMarked(currentTask);
        } catch (NumberFormatException e) {
            throw new DukeException(Message.INVALID_MARK_TASK_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            if (commandList.length <= 1) {
                throw new DukeException(Message.INVALID_MARK_TASK_FORMAT);
            } else if (this.taskList.getCount() == 0) {
                throw new DukeException(Message.INVALID_ACCESS_EMPTY_TASKLIST);
            } else {
                throw new DukeException("mark done lo");
            }
        }
    }

    private void unmarkDoneMessage(String command) throws DukeException {
        String[] commandList = command.strip().split(" ");
        try {
            int taskIndexNum = Integer.parseInt(commandList[1]);
            this.taskList.unmarkTaskAtPos(taskIndexNum);
            Task currentTask = this.taskList.getTask(taskIndexNum);
            storage.save(this.taskList);
            ui.showUnmarked(currentTask);
        } catch (NumberFormatException e) {
            throw new DukeException(Message.INVALID_UNMARK_TASK_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            if (commandList.length <= 1) {
                throw new DukeException(Message.INVALID_UNMARK_TASK_FORMAT);
            } else if (this.taskList.getCount() == 0) {
                throw new DukeException(Message.INVALID_ACCESS_EMPTY_TASKLIST);
            } else {
                throw new DukeException("unmark done lo");
            }
        }
    }

    private void deleteTaskMessage(String command) throws DukeException {
        String[] commandList = command.strip().split(" ");
        try {
            int taskIndexNum = Integer.parseInt(commandList[1]);
            Task deletedTask = this.taskList.deleteTaskAtPos(taskIndexNum);
            storage.save(this.taskList);
            ui.showDeleted(deletedTask);
        } catch (NumberFormatException e) {
            throw new DukeException(Message.INVALID_DELETE_TASK_FORMAT);
        } catch (IndexOutOfBoundsException e){
            if (commandList.length <= 1) {
                throw new DukeException(Message.INVALID_DELETE_TASK_FORMAT);
            } else if (this.taskList.getCount() == 0){
                throw new DukeException(Message.INVALID_ACCESS_EMPTY_TASKLIST);
            } else {
                throw new DukeException("Hola amigos");
            }
        }
    }

//    private boolean isInteger(String value) {
//        try {
//            Integer.parseInt(value);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }

    private void makeToDoFromInput(String input) throws DukeException {
        String description = input.substring("todo".length()).strip();
        if (!description.equals("")) {
            ToDo newToDo = new ToDo(description);
            this.taskList.add(newToDo);
            storage.save(newToDo.toSimpleString());
            ui.showAddition(newToDo, taskList.getCount());
        } else {
            throw new DukeException(Message.INVALID_TODO_INPUT);
        }
    }

    private void makeDeadlineFromInput(String input) throws DukeException {
        String[] stringArray = input.substring("deadline".length()).strip().split("/by");
        try {
            LocalDate deadlineDate = LocalDate.parse(stringArray[1].strip());
            if (deadlineDate.isBefore(LocalDate.now())){
                throw new DukeException(Message.INVALID_DATE_INPUT);
            }
            Deadline newDeadline = new Deadline(stringArray[0].strip(), deadlineDate);
            this.taskList.add(newDeadline);
            storage.save(newDeadline.toSimpleString());
            ui.showAddition(newDeadline, taskList.getCount());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Message.INVALID_DEADLINE_INPUT);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException(Message.INVALID_DATE_FORMAT);
        }
    }

    private void makeEventFromInput(String input) throws DukeException {
        String[] stringArray = input.substring("event".length()).strip().split("/at");
        if (stringArray.length > 1) {
            Event newEvent = new Event(stringArray[0].strip(), stringArray[1].strip());
            this.taskList.add(newEvent);
            storage.save(newEvent.toSimpleString());
            ui.showAddition(newEvent, taskList.getCount());
        } else {
            throw new DukeException(Message.INVALID_EVENT_INPUT);
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
//        Scanner scan = new Scanner(System.in);
//        String s = scan.nextLine();
//        boolean exitNow = false;
//        while(!exitNow) {
//            try {
//                String[] commandList = s.strip().split(" ");
//                String command = commandList[0].toLowerCase();
//                if (command.equals("bye") && commandList.length == 1) {
//                    exitNow = true;
//                    ui.showBye();
//                } else if (command.equals("list") && commandList.length == 1) {
//                    ui.showList();
//                } else if (command.equals("mark")) {
//                    markDoneMessage(s);
//                } else if (command.equals("unmark")) {
//                    unmarkDoneMessage(s);
//                } else if (command.equals("deadline")) {
//                    makeDeadlineFromInput(s);
//                } else if (command.equals("event")) {
//                    makeEventFromInput(s);
//                } else if (command.equals("todo")) {
//                   makeToDoFromInput(s);
//                } else if (command.equals("delete")){
//                    deleteTaskMessage(s);
//                } else {
//                    System.out.println(Message.INVALID_USER_INPUT);
//                }
//            } catch (DukeException e) {
//               ui.showError(e.getMessage());
//            } finally {
//                if (!exitNow) {
//                    s = scan.nextLine();
//                }
//            }
//        }
//        scan.close();
        }
    }

    public static void main(String[] args) {
        Duke sampleDuke = new Duke();
        sampleDuke.run();
    }
}
