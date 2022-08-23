import java.util.ArrayList;
import java.util.Scanner;

public class BotUI {

    private String botSpeak(String phrase) {
        return this.botDivider() + phrase + "\n"
                + this.userDivider();
    }

    String readCommand() {
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        return input;
    }
    String sayHello() {
        return botSpeak("Hello! I'm Duke\n" + "I'm ready to serve you!");
    }

    String sayBye() {
        return "Goodbye, Hope to see you soon!\n" +
                "----------------------------------------------\n";
    }

    String botDivider() {
        return "~~~~~-----DUKE-----~~~~~\n";
    }

    String userDivider() {
        return "~~~~~-----YOU-----~~~~~~\n";
    }

    String showList(TaskRecords taskList) {
        ArrayList<Task> lst = taskList.getList();
        if (lst.size() == 0) {
            return botSpeak("Nothing is added!");
        }
        StringBuilder lstFormat = new StringBuilder();
        for(int i = 1; i <= lst.size(); i++) {
            lstFormat.append(i).append(". ").append(lst.get(i - 1).toString());
            lstFormat.append((i == lst.size()) ? "" : "\n");
        }
        return botSpeak(lstFormat.toString());
    }

    String informMarkStatus(Task task) {
       return botSpeak((task.isDone()) ? "Nice! this task is marked as done. Good Job!\n" + task :
               "This task is marked as not done. Keep it up!\n" + task);
    }

    String addStatus(TaskRecords taskList, Task task) {
        ArrayList<Task> lst = taskList.getList();
        return botSpeak(String.format(
                "New task is registered as you wish, you can come back to check if you wish!:\n %s\n"
                        + "Now you have %d tasks on your list.", task, lst.size()));
    }

    String successRemoved(TaskRecords taskList, Task task) {
        ArrayList<Task> lst = taskList.getList();
        return botSpeak(String.format(
                "Ching Ching Poof~~ This task is removed:\n %s\n"
                        + "Now you have %d tasks on your list.", task, lst.size()));
    }

    String invalidCommand() {
        return botSpeak("Huh? I don't understand your instructions XD\n" +
                "------Available command------\n" +
                "modify list command: | todo | deadline | event | delete |\n" +
                "modify task command: | mark [number] | unmark [number]|\n" +
                "other: | bye | list |");
    }

    String invalidFormat() {
        return botSpeak("Based on my understanding, your instruction didn't follow the format\n" +
                "todo : todo [task description]\n" +
                "deadline: deadline [task description] /by [YYYY-MM-DD HHmm]\n" +
                "event: event [task description] /at [YYYY-MM-DD HHmm]\n" +
                "individual command: bye | list |\n" +
                "mark/unmark/delete: [command] [number of task in list you wish to modify])\n");
    }

    String taskNotExist(TaskRecords taskList) {
        return botSpeak(String.format("Opps! we only have %d tasks in the list :(",
                taskList.getList().size()));
    }

    String invalidCheckFormat() {
        return botSpeak("Sorry, last character after mark/unmark/delete command should be integer!");
    }

    String invalidDateFormat() {
        return botSpeak("Date and time format should be [YYYY-MM-DD HHmm]!\n"
                + "(eg. 2022-08-21 1300)");
    }
}
