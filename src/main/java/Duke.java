import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    String botSpeak(String phrase) {
        return CommonPhrase.BOT_DIVIDER.getPhrase() + phrase + "\n"
                + CommonPhrase.USER_DIVIDER.getPhrase();
    }

    private void addProcess(ArrayList<Task> lst, Task task) {
        if (!lst.contains(task)) {
            lst.add(task);
        } else {
            lst.set(lst.indexOf(task),task);
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.print(duke.botSpeak(CommonPhrase.HELLO.getPhrase()));
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        ArrayList<Task> lst = new ArrayList<Task>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                String lstFormat = "";
                for(int i = 1; i <= lst.size(); i++) {
                    lstFormat += i + ". " + lst.get(i - 1).toString();
                    lstFormat += (i == lst.size()) ? "" : "\n";
                }
                lstFormat = (lstFormat.length() == 0) ? "Nothing is added!" : lstFormat;
                System.out.print(duke.botSpeak(lstFormat));
            }
            else if (input.startsWith("todo")) {
                Task task = new ToDos(input.substring(5));
                duke.addProcess(lst, task);
                System.out.print(duke.botSpeak(String.format(
                        "New task is registered as you wish, Take note!:\n %s\n" +
                                "Now you have %d tasks on your list.", task.toString(), lst.size())));
            }
            else if (input.startsWith("deadline")) {
                //Test
                Task task = new Deadlines(input);
                duke.addProcess(lst, task);
                System.out.print(duke.botSpeak(String.format(
                        "New deadline is registered as you wish, don't rush things at last minute!:\n %s\n" +
                                "Now you have %d tasks on your list.", task.toString(), lst.size())));
            }
            else if (input.startsWith("event")) {
                Task task = new Events(input);
                duke.addProcess(lst, task);
                System.out.print(duke.botSpeak(String.format(
                        "New event is registered as you wish, please don't forget to attend!:\n %s\n" +
                                "Now you have %d tasks on your list.", task.toString(), lst.size())));
            }
            else if (input.startsWith("mark")) {
                int taskIdx = Integer.parseInt(input.substring(input.length() - 1)) - 1;
                Task updatedTask = lst.get(taskIdx).markDone();
                lst.set(taskIdx, updatedTask);
                System.out.print(duke.botSpeak("Nice! this task is marked as done. Good Job!\n"
                        + lst.get(taskIdx).toString()));
            }
            else if (input.startsWith("unmark")) {
                int taskIdx = Integer.parseInt(input.substring(input.length() - 1)) - 1;
                Task updatedTask = lst.get(taskIdx).unmarkDone();
                lst.set(taskIdx, updatedTask);
                System.out.print(duke.botSpeak("This task is marked as not done. Keep it up!\n"
                        + lst.get(taskIdx).toString()));
            }
            input = scn.nextLine();
        }

        System.out.print(CommonPhrase.BOT_DIVIDER.getPhrase());
        System.out.println(CommonPhrase.GOODBYE.getPhrase());
        System.out.println("----------------------------------------");
    }
}
