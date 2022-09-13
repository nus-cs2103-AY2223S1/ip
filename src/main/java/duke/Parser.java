package duke;

import java.util.Scanner;

//Solution below adapted from https://github.com/nus-cs2103-AY2223S1/ip/commit/da76395fec6c4ab68f6c849a0431d7297e9fb97f

public class Parser {

    private final String HORIZONTAL_LINE_BREAK = "-------------------------";
    private Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

    public void initialise() {
        Scanner sc = new Scanner(System.in);
        String command = sc.next();

        while (!command.equals("bye")) {
            try {

                switch (command) {

                case "list": {
                    duke.showList();
                    break;
                }

                case "mark": {
                    int index = sc.nextInt() - 1;
                    duke.mark(index);
                    break;
                }

                case "unmark": {
                    int index = sc.nextInt() - 1;
                    duke.unmark(index);
                    break;
                }

                case "todo": {
                    String description = sc.nextLine();
                    duke.setToDo(description);
                    break;
                }

                case "deadline": {
                    String input = sc.nextLine();
                    String description = input.substring(0, input.indexOf("/") - 1);
                    String by = input.substring(input.indexOf("/") + 4);
                    duke.setDeadLine(description, by);
                    break;
                }

                case "event": {
                    String input = sc.nextLine();
                    String description = input.substring(0, input.indexOf("/") - 1);
                    String at = input.substring(input.indexOf("/") + 4);
                    duke.setEvent(description, at);
                    break;
                }

                case "delete": {
                    int index = sc.nextInt() - 1;
                    duke.delete(index);
                    break;
                }

                case "find": {
                    String string = sc.next() + sc.nextLine();
                    duke.find(string);
                    break;
                }

                default:
                    sc.nextLine();
                    throw new DukeException("I'm sorry, but I don't know what that means.");
                }
            } catch (DukeException exception) {
                System.out.println(exception);
            }
            command = sc.next();
        }

    }




}
