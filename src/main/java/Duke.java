import java.util.Scanner;

public class Duke {
    /**
     * Main class used to handle inputs
     */
    public static void main(String[] args) {
        Messages.welcome();

        String in = "";
        TaskList list = new TaskList();
        Scanner sc = new Scanner(System.in);

        while (true) {
            in = sc.nextLine();

            if (in.equals("bye")) {
                break;
            } else if (in.equals("list")) {
                list.printTasks();
            } else if (in.startsWith("mark")) {
                int index = Integer.valueOf(in.split(" ")[1]) - 1;
                list.mark(index);
            } else if (in.startsWith("unmark")) {
                int index = Integer.valueOf(in.split(" ")[1]) - 1;
                list.unmark(index);
            } else if (in.startsWith("delete")) {
                int index = Integer.valueOf(in.split(" ")[1]) - 1;

                try {
                    if (index >= list.getSize()) {
                        throw new InvalidCommandException();
                    }

                    Task task = list.getTask(index);
                    Messages.delete(task);
                    list.delete(index);
                    Messages.countTasks(list);
                }

                catch (InvalidCommandException e) {
                    System.out.println(e.toString());
                }

            } else {
                try {
                    Task task = TaskCreator.CreateTask(in);
                    if (task == null) {
                        throw new InvalidCommandException();
                    } else if (task.getClass() == ErrorTask.class) {
                        throw new InvalidDateException();
                    } else if (task.description.length() < 1) {
                        throw new NoDescriptionException();
                    } else {
                        list.add(task);
                        Messages.add(task);
                    }
                }

                catch (InvalidCommandException e) {
                    System.out.println(e.toString());
                }

                catch (InvalidDateException e) {
                    System.out.println(e.toString());
                }

                catch (NoDescriptionException e){
                    System.out.println(e.toString());
                }
            }
            System.out.println("-------------------------------------------");
        }
        Messages.bye();
    }
}
