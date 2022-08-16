import java.util.Scanner;
import java.util.ArrayList;
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

    public static void main(String[] args) throws DukeException {

        String lineBreak = "------------------";
        System.out.println("\n" + "Hello I am LUNA!\n" + "How can I be of help?\n" + lineBreak);
        ArrayList<Task> arrayList =  new ArrayList<>(100);
        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();
            String[] splitStr = input.split("\\s+");
            String farewell = lineBreak + "\n" + "LUNA bids farewell\n\n May the Moon shine bright and illuminate your night.";

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(farewell);
                break;

            } else if (input.equalsIgnoreCase("list")) {
                int counter = 1;
                System.out.println(lineBreak);
                for (Task s : arrayList) {
                    System.out.println(counter + ". " + s.toString());
                    counter++;
                }
            } else if (input.contains("mark")) {
                String str = splitStr[1];
                String type = splitStr[0];

                if (splitStr.length > 2 || !isNumeric(str) ) {
                    String[] findTask = input.split(type);
                    String actualTask = "";
                    try {
                        String theTask = findTask[1].split(" /")[0];
                        actualTask = theTask;
                    } catch(Exception e) {
                        throw new DukeException("To do? To do what? LUNA isn't a mindreader");
                    }

                    if (type.equals("todo")) {
                        ToDos todo = new ToDos(actualTask);

                        arrayList.add(todo);
                        System.out.println(lineBreak + "\n" + "LUNA reminds you to get this done... By the next Full Moon:\n" + todo + "\n" + lineBreak);
                        System.out.println("You now have " + arrayList.size() + " tasks added\n" + "How many moons would you need to complete them?");

                    } else if (type.equals("event")) {
                        String[] splitStr2 = input.split("/at");
                        Events event = new Events(actualTask, splitStr2[1]);

                        arrayList.add(event);
                        System.out.println(lineBreak + "\n" + "What event could possibly be more important than the Moon Festival?:\n" + event + "\n" + lineBreak);
                        System.out.println("You now have " + arrayList.size() + " tasks added\n" + "How many moons would you need to complete them?");

                    } else {
                        String[] splitStr2 = input.split("/by");
                        Deadlines deadline = new Deadlines(actualTask, splitStr2[1]);

                        arrayList.add(deadline);
                        System.out.println(lineBreak + "\n" + "The only deadline LUNA has is when the Moon dies!:\n" + deadline + "\n" + lineBreak);
                        System.out.println("You now have " + arrayList.size() + " tasks added\n" + "How many moons would you need to complete them?");

                    }

                    int len = arrayList.size();
                    System.out.println("You now have " + len + " tasks added\n" + "How many moons would you need to complete them?");

                } else {

                    int index = Integer.valueOf(str) - 1;

                    if (input.contains("unmark")) {
                        Task task = arrayList.get(index);
                        task.unmark();
                        String taskName = task.toString();

                        arrayList.set(index, task);

                        System.out.println("LUNA thought you were already done with this?\n" + taskName + "\n" + lineBreak);
                    } else {


                        Task task = arrayList.get(index);
                        task.mark();
                        String string = task.toString();

                        arrayList.set(index, task);

                        System.out.println("LUNA waited many moons for you to finish this one\n" + string + "\n" + lineBreak);
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
                            throw new DukeException("If you don't hurry up and tell me what the deadline is, you'll probably miss it. ");
                        } else {
                            throw new DukeException("LUNA has consulted the Moon Goddess and even she has no idea what you're saying. ");
                        }
                    }
                }
                catch(DukeException e) {
                    System.out.println(e);
                }

                if (!error) {
                    if (type.equals("todo")) {

                            ToDos todo = new ToDos(actualTask);
                            arrayList.add(todo);
                            System.out.println(lineBreak + "\n" + "LUNA reminds you to get this done... By the next Full Moon:\n" + todo + "\n" + lineBreak);
                            System.out.println("You now have " + arrayList.size() + " tasks added\n" + "How many moons would you need to complete them?");

                    } else if (type.equals("event")) {
                        String[] splitStr2 = input.split("/at");
                        Events event = new Events(actualTask, splitStr2[1]);

                        arrayList.add(event);
                        System.out.println(lineBreak + "\n" + "What event could possibly be more important than the Moon Festival?:\n" + event + "\n" + lineBreak);
                        System.out.println("You now have " + arrayList.size() + " tasks added\n" + "How many moons would you need to complete them?");

                    } else if (type.equals("deadline")) {
                        String[] splitStr2 = input.split("/by");
                        Deadlines deadline = new Deadlines(actualTask, splitStr2[1]);

                        arrayList.add(deadline);
                        System.out.println(lineBreak + "\n" + "The only deadline LUNA has is when the Moon dies!:\n" + deadline + "\n" + lineBreak);
                        System.out.println("You now have " + arrayList.size() + " tasks added\n" + "How many moons would you need to complete them?");

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
