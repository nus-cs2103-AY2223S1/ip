import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(Messages.startup);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputLessAction = input.split(" ", 2);
            switch (inputLessAction[0]) {
                case "bye":
                    System.out.println(Messages.ending);
                    System.exit(0);
                case "list":
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i+1) + "." + taskList.get(i).toString());
                    }
                    break;
                case "mark":
                    int currentPos = Integer.parseInt(inputLessAction[1]) - 1;
                    taskList.get(currentPos).markDone();
                    System.out.println(Messages.taskMarked);
                    System.out.println(taskList.get(currentPos).toString());
                    break;
                case "unmark":
                    currentPos = Integer.parseInt(inputLessAction[1]) - 1;
                    taskList.get(currentPos).markUndone();
                    System.out.println(Messages.taskUnmarked);
                    System.out.println(taskList.get(currentPos).toString());
                    break;
                case "todo":
                    String taskDesc = inputLessAction[1];
                    taskList.add(new Todo(taskDesc));
                    System.out.println(Messages.taskAdded);
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    System.out.printf((Messages.taskCount) + "%n", taskList.size());
                    break;
                case "deadline":
                    String temp = inputLessAction[1];
                    String[] tempStrArr = temp.split("/by", 2);
                    taskDesc = tempStrArr[0];
                    String taskTime = tempStrArr[1];
                    taskList.add(new Deadline(taskDesc, taskTime));
                    System.out.println(Messages.taskAdded);
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    System.out.printf((Messages.taskCount) + "%n", taskList.size());
                    break;
                case "event":
                    temp = inputLessAction[1];
                    tempStrArr = temp.split("/at", 2);
                    taskDesc = tempStrArr[0];
                    taskTime = tempStrArr[1];
                    taskList.add(new Event(taskDesc, taskTime));
                    System.out.println(Messages.taskAdded);
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    System.out.printf((Messages.taskCount) + "%n", taskList.size());
                    break;
                default:
                    taskList.add(new Task(input));
                    System.out.println("added: " + input);
            }

        }
    }
}
