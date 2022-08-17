import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
        System.out.println(Messages.startup);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputLessAction = input.split("\s+", 2);

            try {
                switch (inputLessAction[0]) {
                    case "bye":
                        System.out.println(Messages.ending);
                        sc.close();
                        System.exit(0);
                    case "list":
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println((i + 1) + "." + taskList.get(i).toString());
                        }
                        break;
                    case "remove":
                        if (inputLessAction.length < 2) {
                            throw new EmptyDescriptionException("remove");
                        }
                        int currentPos = Integer.parseInt(inputLessAction[1]) - 1;
                        System.out.println(Messages.taskRemoved);
                        System.out.println(taskList.get(currentPos).toString());
                        taskList.remove(Integer.parseInt(inputLessAction[1]) - 1);
                        System.out.printf((Messages.taskCount) + "%n", taskList.size());
                        break;
                    case "mark":
                        if (inputLessAction.length < 2) {
                            throw new EmptyDescriptionException("mark");
                        }
                        currentPos = Integer.parseInt(inputLessAction[1]) - 1;
                        taskList.get(currentPos).markDone();
                        System.out.println(Messages.taskMarked);
                        System.out.println(taskList.get(currentPos).toString());
                        break;
                    case "unmark":
                        if (inputLessAction.length < 2) {
                            throw new EmptyDescriptionException("unmark");
                        }
                        currentPos = Integer.parseInt(inputLessAction[1]) - 1;
                        taskList.get(currentPos).markUndone();
                        System.out.println(Messages.taskUnmarked);
                        System.out.println(taskList.get(currentPos).toString());
                        break;
                    case "todo":
                        if (inputLessAction.length < 2) {
                            throw new EmptyDescriptionException("todo");
                        }
                        String taskDesc = inputLessAction[1];
                        taskList.add(new Todo(taskDesc));
                        System.out.println(Messages.taskAdded);
                        System.out.println(taskList.get(taskList.size() - 1).toString());
                        System.out.printf((Messages.taskCount) + "%n", taskList.size());
                        break;
                    case "deadline":
                        if (inputLessAction.length < 2) {
                            throw new EmptyDescriptionException("deadline");
                        }
                        String temp = inputLessAction[1];
                        String[] tempStrArr = temp.split("/by\s+", 2);
                        if (tempStrArr.length < 2) {
                            throw new InvalidFormatException("/by");
                        }
                        taskDesc = tempStrArr[0];
                        if (taskDesc.equals("")) {
                            throw new EmptyDescriptionException("deadline");
                        }
                        String taskTime = tempStrArr[1];
                        if (taskTime.equals("")) {
                            throw new EmptyTimeException("deadline");
                        }
                        taskList.add(new Deadline(taskDesc, taskTime));
                        System.out.println(Messages.taskAdded);
                        System.out.println(taskList.get(taskList.size() - 1).toString());
                        System.out.printf((Messages.taskCount) + "%n", taskList.size());
                        break;
                    case "event":
                        if (inputLessAction.length < 2) {
                            throw new EmptyDescriptionException("event");
                        }
                        temp = inputLessAction[1];
                        tempStrArr = temp.split("/at\s+", 2);
                        if (tempStrArr.length < 2) {
                            throw new InvalidFormatException("/at");
                        }
                        taskDesc = tempStrArr[0];
                        if (taskDesc.equals("")) {
                            throw new EmptyDescriptionException("event");
                        }
                        taskTime = tempStrArr[1];
                        if (taskTime.equals("")) {
                            throw new EmptyTimeException("event");
                        }
                        taskList.add(new Event(taskDesc, taskTime));
                        System.out.println(Messages.taskAdded);
                        System.out.println(taskList.get(taskList.size() - 1).toString());
                        System.out.printf((Messages.taskCount) + "%n", taskList.size());
                        break;
                    default:
                        throw new InvalidKeywordException();
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}
