import java.util.Scanner;

public class Duke {
    public static final String line = "____________________________________________________________";
    public Scanner sc = new Scanner(System.in);
    public static Task[] list = new Task[100];
    public static int count = 0;

    public Duke() {};

    public void greet() {
        System.out.println(
                line + "\n" +
                        "Hello! I'm Duke" + "\n" +
                        "What can I do for you?" + "\n" +
                        line + "\n"
        );
    }

    public void respond()  {
        try {
            String input = sc.nextLine();

            String[] arr = input.split(" ", 2);
            String command = arr[0];
            if (command.equals("mark") || command.equals("unmark")) {
                int index = Integer.parseInt(arr[1]);
                Task b = list[index - 1];
                if (command.equals("mark")) {
                    b.mark(b, index);
                } else if (command.equals("unmark")) {

                    b.unmark(b, index);
                }
                respond();

            }

            else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                if (arr.length == 1) {
                    throw new EmptyCommandException(command);
                }


                if (command.equals("todo")) {

                    list[count] = new Todo(arr[1]);
                    list[count++].print();
                    respond();
                }
                else if (command.equals("event")) {
                    String[] deets = arr[1].split("/at", 2);
                    list[count] = new Event(deets[0], deets[1]);
                    list[count++].print();
                    respond();
                }
                else if (command.equals("deadline")) {
                    String[] deets = arr[1].split("/by", 2);
                    list[count] = new Deadline(deets[0], deets[1]);
                    list[count++].print();
                    respond();
                }
            }

            else if (input.equals(("bye"))) {
                bye();
            }

            else if (input.equals("list")) {
                list();
                respond();
            }

            else {
                throw new InvalidCommandException(command);
            }

        }
        catch (EmptyCommandException e){
            System.out.println(e.getMessage());
            respond();
        }

        catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            respond();
        }

    }

    public void list() {
        System.out.println(
                line + "\n" +
                        "Here are the tasks in your list:");

        for (int i = 0, j = 1; i < count; i++, j++) {

            System.out.print(j + ". ");
            list[i].list();
        }
        System.out.println(
                line + "\n"
        );

    }

    public void bye() {
        System.out.println(
                line + "\n" +
                        "Bye. Hope to see you again soon!" + "\n" + line
        );

    }

    public static void main (String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.respond();
    }

}
