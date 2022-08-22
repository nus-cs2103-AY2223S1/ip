import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String horizontalBorder = "_________________________________\n";
    private static final String INVALID_TODO_INPUT = " ☹ OOPS!!! The description of a todo cannot be empty.\n";
    private static final String INVALID_DEADLINE_INPUT = "☹ OOPS!!! Please use proper deadline formatting: deadline {task} /by {time}\n";
    private static final String INVALID_EVENT_INPUT = "☹ OOPS!!! Please use proper event formatting: event {task} /at {time}\n";
    private static final String INVALID_ACCESS_EMPTY_TASKLIST = "☹ OOPS!!! Task does not exist. Initialise a task first, then try again\n";
    private static final String INVALID_USER_INPUT = "☹ OOPS!!! Please use one of these keywords: {deadline, event, todo} followed by \\\"by\\\" and \\\"at\\\" for deadline and event tasks respectively.\n";

    private Tasklist tasklist;

    private Duke(){
        this.tasklist = new Tasklist();
    }

    private Duke(Tasklist tasklist){
        this.tasklist = tasklist;
    }

    private static String welcomeMessage(){
        return horizontalBorder + "Hello! I'm Duke\nWhat can I do for you?\n" + horizontalBorder;
    }

    private static String byeMessage(){
        return horizontalBorder + "Bye. Hope to see you again soon!\n" + horizontalBorder;
    }

    private String listContents(){
        return horizontalBorder + this.tasklist + horizontalBorder;
    }

    public String addTaskMessage(String taskString) {
        return horizontalBorder +  "Got it. I've added this task:\n" + taskString + "\n" + "Now you have " + this.tasklist.getCount() + " tasks in the list.\n" + horizontalBorder;
    }

    public String taskNotFoundMessage(){
        return "☹ OOPS!!! Task does not exist. Try another number between 1 and " + this.tasklist.getCount() + "\n";
    }

    private String markDoneMessage(int position) throws DukeException{
        boolean isTaskMarked = this.tasklist.markTaskAtPos(position);
        if (isTaskMarked) {
            Task currentTask = this.tasklist.getTask(position);
            return horizontalBorder + "Nice! I've marked this task as done:\n" + currentTask + "\n" + horizontalBorder;
        } else if (this.tasklist.getCount() == 0) {
            throw new DukeException(INVALID_ACCESS_EMPTY_TASKLIST);
        } else {
            throw new DukeException(taskNotFoundMessage());
        }
    }

    private String unmarkDoneMessage(int position) throws DukeException {
        boolean isTaskUnmarked = this.tasklist.unmarkTaskAtPos(position);
        if (isTaskUnmarked) {
            Task currentTask = this.tasklist.getTask(position);
            return horizontalBorder + "OK, I've marked this task as not done yet:\n" + currentTask + "\n" + horizontalBorder;
        } else if (this.tasklist.getCount() == 0) {
            throw new DukeException(INVALID_ACCESS_EMPTY_TASKLIST);
        } else {
            throw new DukeException(taskNotFoundMessage());
        }
    }

    private String deleteTaskMessage(int position) throws DukeException {
        try {
            Task deletedTask = this.tasklist.deleteTaskAtPos(position);
            return horizontalBorder + "Noted. I've removed this task:\n" + deletedTask + "\n" + "" + "Now you have " + this.tasklist.getCount() + " tasks in the list.\n" + horizontalBorder;
        } catch (IndexOutOfBoundsException e){
            if (this.tasklist.getCount() == 0){
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
            this.tasklist.add(newToDo);
            return addTaskMessage(newToDo.toString());
        } else {
            throw new DukeException(INVALID_TODO_INPUT);
        }
    }

    private String makeDeadlineFromInput(String input) throws DukeException {
        String[] stringArray = input.substring("deadline".length()).strip().split("/by");
        if (stringArray.length > 1) {
            Deadline newDeadline = new Deadline(stringArray[0].strip(), stringArray[1].strip());
            this.tasklist.add(newDeadline);
            return addTaskMessage(newDeadline.toString());
        } else {
            throw new DukeException(INVALID_DEADLINE_INPUT);
        }
    }

    private String makeEventFromInput(String input) throws DukeException{
        String[] stringArray = input.substring("event".length()).strip().split("/at");
        if (stringArray.length > 1) {
            Event newEvent = new Event(stringArray[0].strip(), stringArray[1].strip());
            this.tasklist.add(newEvent);
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
                    System.out.println(horizontalBorder + INVALID_USER_INPUT + horizontalBorder);
                }
            } catch (DukeException e) {
                System.out.println(horizontalBorder + e.getMessage() + horizontalBorder);
            } finally {
                if (!exitNow) {
                    s = scan.nextLine();
                }
            }
        }
        scan.close();
    }


    public static void main(String[] args) {
        Duke sampleDuke = new Duke(new Tasklist());
        sampleDuke.run();
//        ArrayList<String> a = new ArrayList<>();
//        a.add("lol");
//        a.add("gan");
//        a.add("lasso");
//        a.remove(1);
//        for (int i = 0; i < a.size(); i++){
//            System.out.println(a.get(i));
//        }
    }
}
