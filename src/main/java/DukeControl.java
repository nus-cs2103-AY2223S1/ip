import java.util.ArrayList;
import java.util.Arrays;

public class DukeControl {
    ArrayList<Task> arrayList;

    public DukeControl() {
        this.arrayList = new ArrayList<>();
    }

    public void evaluate(String input) {
        String[] command = input.split(" ");
        String mainCommand = command[0];
        String[] commandArgs = Arrays.copyOfRange(command, 1, command.length);

        if (mainCommand.equals("list") && commandArgs.length == 0) {
            list();
        } else if (mainCommand.equals("mark")) {
            if (commandArgs.length != 1) {
                System.out.println("Invalid argument numbers for command MARK");
            } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.arrayList.size()) {
                System.out.println("Invalid task index for command MARK");
            } else {
                arrayList.get(Integer.parseInt(commandArgs[0]) - 1).mark();
            }
        } else if (mainCommand.equals("unmark")) {
            if (commandArgs.length != 1) {
                System.out.println("Invalid argument numbers for command UNMARK");
            } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.arrayList.size()) {
                System.out.println("Invalid task index for command UNMARK");
            } else {
                arrayList.get(Integer.parseInt(commandArgs[0]) - 1).unmark();
            }
        }
        else {
            add(input);
        }
    }

    public void echo(String input) {
        System.out.println(input);
    }

    public void list() {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, arrayList.get(i).print()));
        }
    }

    public void add(String task) {
        Task newTask = new Task(task);
        arrayList.add(newTask);
        System.out.println(String.format("added: %s", newTask.print()));
    }
}
