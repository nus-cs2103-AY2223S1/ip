import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Drake {

    public static void main(String[] args) throws DrakeException {


        System.out.println("------------------------------------------------------");
        System.out.println("You used to call me on my cellphone");
        StringBuilder logo = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            logo.append("DRAKE ".repeat(4));
            logo.append("DRAKE");
            if (i == 4) break;
            logo.append("\n");
        }
        System.out.println(logo);
        System.out.println("!@#$%^&*()-+!@#$%^&*()`~`!@#$");
        System.out.println("Drake's (me) the kind of guy to help you out uwu");
        System.out.println("Go ahead, make that hotline bling");
        System.out.println("------------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        ArrayList<Task> list = new ArrayList<>();

        while (!Objects.equals(command, "bye")) {
            int firstSpace = command.indexOf(' ');
            String firstWord;
            if (command.equals("list")) {
                firstWord = "list";
            } else if (firstSpace == -1) {
                throw new UnknownCommandException();
            } else {
                firstWord = command.substring(0, firstSpace);
            }
            System.out.println("------------------------------------------------------");
            switch (firstWord) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    int i = 1;
                    for (Task item : list)
                        System.out.println(i++ + ". " + item);
                    break;
                case "mark": {
                    String[] commands = command.split(" ");
                    try {
                        int taskNo = Integer.parseInt(commands[1]);
                        if (taskNo <= list.size()) {
                            System.out.println("I've marked this task as done!");
                            list.get(taskNo - 1).markAsDone();
                            System.out.println(list.get(taskNo - 1));
                        } else {
                            throw new IncompatibleCommandException("That task number doesn't exist!");
                        }
                    } catch (NumberFormatException e) {
                        throw new IncompatibleCommandException("Where's the number?");
                    }
                    break;
                }
                case "unmark": {
                    String[] commands = command.split(" ");
                    try {
                        int taskNo = Integer.parseInt(commands[1]);
                        if (taskNo <= list.size()) {
                            System.out.println("I've marked this task as not done (yet ;))");
                            list.get(taskNo - 1).unmarkAsDone();
                            System.out.println(list.get(taskNo - 1));
                        } else {
                            throw new IncompatibleCommandException("That task number doesn't exist!");
                        }
                    } catch (NumberFormatException e) {
                        throw new IncompatibleCommandException("Where's the number?");
                    }
                    break;
                }
                case "todo":
                    String description = command.substring("todo ".length());
                    if (description.length() == 0)
                        throw new EmptyDescriptionException();
                    System.out.println("I've added this task:");
                    list.add(new Todo(description));
                    System.out.println(list.get(list.size() - 1));
                    System.out.println("You now have " + list.size() + " tasks in the list");
                    break;
                case "deadline": {
                    String filtered = command.substring("deadline ".length());
                    String[] commands = filtered.split("/by");
                    if (commands.length == 1)
                        throw new IncompatibleCommandException("A deadline task without a deadline?");
                    System.out.println("I've added this task:");
                    list.add(new Deadline(commands[0], commands[1].trim()));
                    System.out.println(list.get(list.size() - 1));
                    System.out.println("You now have " + list.size() + " tasks in the list");
                    break;
                }
                case "event": {
                    String filtered = command.substring("event ".length());
                    String[] commands = filtered.split("/at");
                    if (commands.length == 1)
                        throw new IncompatibleCommandException("An event task without an event time?");
                    System.out.println("I've added this task:");
                    list.add(new Event(commands[0], commands[1].trim()));
                    System.out.println(list.get(list.size() - 1));
                    System.out.println("You now have " + list.size() + " tasks in the list");
                    break;
                }
                case "delete": {
                    String[] commands = command.split(" ");
                    try {
                        int taskNo = Integer.parseInt(commands[1]);
                        if (taskNo <= list.size()) {
                            System.out.println("I've removed this task: ");
                            System.out.println(list.remove(taskNo - 1));
                        } else {
                            throw new IncompatibleCommandException("That task number doesn't exist!");
                        }
                    } catch (NumberFormatException e) {
                        throw new IncompatibleCommandException("Where's the number?");
                    }
                    break;
                }
                default:
                    throw new UnknownCommandException();
            }
            System.out.println("------------------------------------------------------");
            command = sc.nextLine();
        }
        System.out.println("I'm down for you always. See you <3");
    }

}
