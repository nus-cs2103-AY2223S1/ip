import java.util.Scanner;

public class Duke {

    private static Task[] lst = new Task[100];
    private static int currEmpty = 0;
    private final static Scanner myScanner = new Scanner(System.in);
    private final static String SEPARATOR = "------------------------------------";

    private void add(Task task) {

        if (currEmpty == 100) {
            System.out.println("List is Already Full, Cannot add anymore item");
            return;
        }
        lst[currEmpty] = task;
        System.out.println("added: " + task.toString());
        currEmpty++;
    }

    private void read() {
        if (currEmpty == 0) {
            System.out.println("You have no task");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < currEmpty; i++) {
            Task curr = lst[i];
            System.out.println(i + 1 + "." + curr.toString());
        }
    }

    private void mark(int index) {
        if (index >= currEmpty) {
            System.out.println("There is no task with that index");
            return;
        }
        lst[index].setDone();
    }

    private void unMark(int index) {
        if (index >= currEmpty) {
            System.out.println("There is no task with that index");
            return;
        }
        lst[index].setNotDone();
    }

    public static void main(String[] args) {

        Duke duke = new Duke();

        System.out.println("Hello! i am Duke");



        while (true) {
            System.out.println(SEPARATOR);
            System.out.println("What do you want me to do?");
            String command = myScanner.next();

            switch (command) {
                case "list":
                    if (!myScanner.nextLine().isBlank()) {
                        System.out.println("Too many Arguments");
                        break;
                    }

                    System.out.println(SEPARATOR);
                    duke.read();
                    break;
                case "mark":
                    int index = myScanner.nextInt();

                    if (!myScanner.nextLine().isBlank()) {
                        System.out.println("Too many Arguments");
                        break;
                    }

                    System.out.println(SEPARATOR);

                    if (index <= 0) {
                        System.out.println("index cannot be zero or negative");
                        break;
                    } else {
                        duke.mark(index - 1);
                    }

                    break;
                case "unmark":
                    int index1 = myScanner.nextInt();

                    if (!myScanner.nextLine().isBlank()) {
                        System.out.println("Too many Arguments");
                        break;
                    }

                    System.out.println(SEPARATOR);

                    if (index1 < 0) {
                        System.out.println("index cannot be negative");
                        break;
                    } else {
                        duke.unMark(index1 - 1);
                    }

                    break;
                case "deadline":
                    String unParsed = myScanner.nextLine();
                    String[] descriptionAndBy =  unParsed.split("/by", 2);

                    if (descriptionAndBy.length != 2) {
                        System.out.println("Invalid Commands");
                        break;
                    }

                    System.out.println(SEPARATOR);

                    Deadline newDeadLine = new Deadline(descriptionAndBy[0], descriptionAndBy[1]);
                    duke.add(newDeadLine);

                    break;
                case "todo":
                    String description = myScanner.nextLine();

                    if (description.isBlank()) {
                        System.out.println("Empty Arguments");
                    }

                    System.out.println(SEPARATOR);

                    ToDo newToDo = new ToDo(description);
                    duke.add(newToDo);

                    break;
                case "event":
                    String unParsed1 = myScanner.nextLine();
                    String[] descriptionAndBy1 =  unParsed1.split("/at", 2);

                    if (descriptionAndBy1.length != 2) {
                        System.out.println("Invalid Commands");
                        break;
                    }

                    System.out.println(SEPARATOR);

                    Event newDeadLine1 = new Event(descriptionAndBy1[0], descriptionAndBy1[1]);
                    duke.add(newDeadLine1);

                    break;
                case "bye":
                    System.out.println(SEPARATOR);

                    System.out.println("See you later :)");
                    System.exit(0);

                    break;
                default:
                    System.out.println("Unknown Command");
                    myScanner.nextLine();
            }
        }


    }

}
