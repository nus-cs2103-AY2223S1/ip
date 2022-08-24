import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.io.File;



public class Duke {

    public static boolean isNumeric(String string) {
        int intValue;
        
        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {

        }
        return false;
    }



    public static void main(String[] args) throws DukeException, IOException, ParseException {
        String LINE_BREAK = "------------------";
        System.out.println("\n" + "Hello I am LUNA!\n" + "How can I be of help?\n" + LINE_BREAK);
        Scanner sc = new Scanner(System.in);
        SavedTaskHandler savedFile = new SavedTaskHandler();
        ArrayList<Task> taskList =  savedFile.getTaskList();

        while(true) {
            String input = sc.nextLine();
            String[] splitStr = input.split("\\s+");
            String farewell = LINE_BREAK + "\n" + "LUNA bids farewell\n\n May the Moon shine bright and illuminate your night.";

            if (input.equalsIgnoreCase("bye")) {
                savedFile.write(taskList);
                System.out.println(farewell);
                break;

            } else if (input.equalsIgnoreCase("list")) {
                int counter = 1;
                System.out.println(LINE_BREAK);
                for (Task task : taskList) {
                    System.out.println(counter + ". " + task.toString());
                    counter++;
                }

            } else if (input.substring(0, Math.min(input.length(), 4)).equals("mark") || input.substring(0, Math.min(input.length(), 6)).equals("unmark") || input.substring(0, Math.min(input.length(), 6)).equals("delete")) {
                String str = "";
                boolean error = false;
                String type = splitStr[0];

                try {

                    if (splitStr.length > 2 || splitStr.length == 1 || !isNumeric(splitStr[1])) {
                        error = true;
                        throw new DukeException("LUNA is unsure of what you are asking of her... ");
                    }
                }

                catch (DukeException e) {
                    System.out.println(e);
                }

                if (!error) {
                    str = splitStr[1];
                    int index = Integer.valueOf(str) - 1;

                    if (input.contains("unmark")) {
                        Task task = taskList.get(index);
                        task.unmark();
                        String taskName = task.toString();
                        taskList.set(index, task);
                        System.out.println("LUNA thought you were already done with this?\n" + taskName + "\n" + LINE_BREAK);

                    } else if (input.contains("mark")) {
                        Task task = taskList.get(index);
                        task.mark();
                        String taskName = task.toString();
                        taskList.set(index, task);
                        System.out.println("LUNA waited many moons for you to finish this one\n" + taskName + "\n" + LINE_BREAK);
                    } else {
                        Task task = taskList.get(index);
                        taskList.remove(index);
                        String taskName = task.toString();
                        System.out.println("LUNA has deleted that task... Erased forever, lost in the depth of Space...\n" + taskName);
                        System.out.println("You now have " + taskList.size() + " tasks left\n" + "How many moons before you complete them?" + "\n" + LINE_BREAK);

                    }
                }
            } else {
                String type = splitStr[0];
                String[] findTask = input.split(type);
                String actualTask = "";
                boolean error = false;

                try {
                    if (findTask.length == 0) {
                        error = true;

                        if (type.equals("todo")) {
                            throw new DukeException("To do? To do what? ");
                        } else if (type.equals("event")) {
                            throw new DukeException("And what event exactly? When is the event? ");
                        } else if (type.equals("deadline")){
                            throw new DukeException("You'll probably miss it if you continue to give LUNA vague requests... ");
                        } else {
                            throw new DukeException("LUNA has consulted the Moon Goddess and even she has no idea what you're saying. ");
                        }
                    } else {
                        String theTask = findTask[1].split(" /")[0];
                        actualTask = theTask;
                    }
                }
                catch(DukeException e) {
                    System.out.println(e);
                }

                if (!error) {
                    if (type.equals("todo")) {

                            ToDos todo = new ToDos(actualTask);
                            taskList.add(todo);
                            System.out.println(LINE_BREAK + "\n" + "LUNA will write this one down on her finest crater...:\n" + todo + "\n" + LINE_BREAK);
                            System.out.println("You now have " + taskList.size() + " tasks added\n" + "How many moons before you complete them?");

                    } else if (type.equals("event")) {
                        String[] splitStr2 = input.split("/at");
                        Events event = new Events(actualTask, splitStr2[1]);

                        taskList.add(event);
                        System.out.println(LINE_BREAK + "\n" + "What event could possibly be more important than the Moon Festival?\n" + event + "\n" + LINE_BREAK);
                        System.out.println("You now have " + taskList.size() + " tasks added\n" + "How many moons before you complete them?");

                    } else if (type.equals("deadline")) {
                        String[] splitStr2 = input.split("/by ");
                        Deadlines deadline = new Deadlines(actualTask, splitStr2[1]);

                        taskList.add(deadline);
                        System.out.println(LINE_BREAK + "\n" + "Deadlines... LUNA isn't too good with those...\n" + deadline + "\n" + LINE_BREAK);
                        System.out.println("You now have " + taskList.size() + " tasks added\n" + "How many moons before you complete them?");

                    } else {
                        try {
                            throw new DukeException("LUNA has consulted the Moon Goddess and even she has no idea what you're saying. ");
                        }
                        catch(DukeException e) {
                            System.out.println(e);
                        }
                    }
                }
            }
        }
    }
}
