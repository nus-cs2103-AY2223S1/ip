import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    private final static String UNDERLINE = "_________________________________";
    private ArrayList<Task> taskList = new ArrayList<>();
    boolean inProcess = true;

    private String greet(String res) {
        try {
            if (res.toLowerCase().equals("bye")) {

                inProcess = false;
                return "Bye. Hope to see you again soon!";

            } else if (res.toLowerCase().equals("list")) {

                String log = "Tasks that you have:";
                for (int i = 0; i < taskList.size(); i++) {
                    log += String.format("\n %d. %s", i + 1, this.taskList.get(i));
                }
                return log;

            } else if (res.toLowerCase().startsWith("mark")) {
                int min_length = "mark ".length();
                if (res.length() <= min_length) {
                    throw new IncompleteParamException(res);
                } else {
                    int taskNumber = Integer.valueOf(res.substring("mark ".length()));
                    if (taskNumber < 1 || taskNumber > this.taskList.size()) {
                        throw new OutOfListException(res);
                    } else {
                        return this.handleMark(taskNumber);
                    }
                }
            } else if (res.toLowerCase().startsWith("unmark")) {
                int min_length = "unmark ".length();
                if (res.length() <= min_length) {
                    throw new IncompleteParamException(res);
                } else {
                    int taskNumber = Integer.valueOf(res.substring("unmark ".length()));
                    if (taskNumber < 1 || taskNumber > this.taskList.size()) {
                        throw new OutOfListException(res);
                    } else {
                        return this.handleUnmark(taskNumber);
                    }
                }
            } else if (res.toLowerCase().startsWith("todo")) {
                int min_length = "todo ".length();
                if (res.length() <= min_length) {
                    throw new IncompleteParamException(res);
                } else {
                    String taskdes = res.substring("todo ".length());
                    return this.handleAdd(new ToDo(taskdes));
                }

            } else if (res.toLowerCase().startsWith("deadline")) {
                int endPointer = res.indexOf('/');
                if (res.length() <= endPointer + 3 || endPointer == -1) {
                    throw new IncompleteParamException(res);
                } else {
                    String taskdes = res.substring("deadline ".length(), endPointer);
                    String by = res.substring(endPointer + 3);
                    return this.handleAdd(new Deadline(taskdes, by));
                }
            } else if (res.toLowerCase().startsWith("event")) {
                int endPointer = res.indexOf('/');
                if (res.length() <= endPointer + 3 || endPointer == -1) {
                    throw new IncompleteParamException(res);
                } else {
                    String taskdes = res.substring("event ".length(), endPointer);
                    String at = res.substring(endPointer + 3);
                    return this.handleAdd(new Event(taskdes, at));
                }
            } else if (res.toLowerCase().startsWith("delete")){
                int min_length = "delete ".length();
                if (res.length() <= min_length) {
                    throw new IncompleteParamException(res);
                } else {
                    int taskNumber = Integer.valueOf(res.substring("delete ".length()));
                    if (taskNumber < 1 || taskNumber > this.taskList.size()) {
                        throw new OutOfListException(res);
                    } else {
                        return this.handleDelete(taskNumber);
                    }
                }

            } else {
                throw new InvalidInputException(res);
            }
        } catch (DukeException e){
            return e.toString();
        }
    }

    private String handleMark(int num){
        Task task = this.taskList.get(num - 1);
        task.markAsDone();
        return String.format("Nice! I've marked this task as done: \n %s",task);
    }

    private String handleUnmark(int num){
        Task task = this.taskList.get(num - 1);
        task.unmarkAsDone();
        return String.format("Ok! I have marked this task as not done yet: \n %s",task);
    }

    private String handleAdd(Task task){
        this.taskList.add(task);
        return String.format("Got it. I've added this task: \n %s \n  Now you have %d tasks in the list.", task ,this.taskList.size());
    }

    private String handleDelete(int num){
        Task task = this.taskList.get(num - 1);
        this.taskList.remove(num -1 );
        return String.format("Got it. I've removed this task: \n %s \n  Now you have %d tasks in the list.", task ,this.taskList.size());
    }

    private void run() {
        Scanner sc= new Scanner(System.in);
        System.out.println(UNDERLINE);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println(UNDERLINE);
        while(inProcess) {
            String a= sc.nextLine();
            System.out.println(UNDERLINE + "\n" + greet(a) + "\n" + UNDERLINE);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke().run();
        }
    }

