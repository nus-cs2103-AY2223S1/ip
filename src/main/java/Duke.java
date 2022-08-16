import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        try {
            startChat();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void startChat() throws DukeException {
        String horizontalLine = "______________________________________ \n";
        String firstText = horizontalLine
                + "hi... I'm Karen \n"
                + "What do you want this time? \n"
                + horizontalLine;
        System.out.println(firstText);

        ArrayList<Task> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String echoText = sc.nextLine();
            //terminate chat
            if (echoText.equals("bye")) {
                System.out.println(horizontalLine + "K finally, good riddance! \n" + horizontalLine);
                break;
                //view list of items
            } else if (echoText.equals("list")) {
                System.out.println(horizontalLine);
                if (list.size() == 0) {
                    System.out.println("pff there is nothing in your list");
                } else {
                    System.out.println("Here are your dumb tasks in your list:");
                    for (int i = 1; i < list.size() + 1; i++) {
                        Task item = list.get(i - 1);
                        System.out.println(i + ". " + item.toString());
                    }
                }
                System.out.println(horizontalLine);
                //mark an item as done
            } else if (echoText.split(" ")[0].equals("mark")) {
                int markValue = Integer.parseInt(echoText.split(" ")[1]);
                Task item = list.get(markValue - 1);
                item.markAs(true);
                System.out.println(horizontalLine + "Took you long enough to complete this task:");
                System.out.println(item.toString() + "\n" + horizontalLine);
                //mark an item as not done
            } else if (echoText.split(" ")[0].equals("unmark")) {
                int markValue = Integer.parseInt(echoText.split(" ")[1]);
                Task item = list.get(markValue - 1);
                item.markAs(false);
                System.out.println(horizontalLine + "Another task marked as not done?? Slow indeed");
                System.out.println(item.toString() + "\n" + horizontalLine);
                //add a to-do item
            } else if (echoText.split(" ")[0].equals("todo")) {
                int firstSpaceIndex = echoText.indexOf(" ");
                if (firstSpaceIndex == -1) {
//                    throw new DukeException("Description of a todo cannot be empty dummy!");
                    System.out.println(horizontalLine + "Description of a todo cannot be empty dummy! \n" + horizontalLine);
                    continue;
                }
                String text = echoText.substring(firstSpaceIndex + 1);
                if (text.trim().equals("")) {
//                    throw new DukeException("Description of a todo cannot be empty dummy!");
                    System.out.println(horizontalLine + "Description of a todo cannot be empty dummy! \n" + horizontalLine);
                    continue;
                }
                Todo t = new Todo(text);
                list.add(t);
                System.out.println(horizontalLine + "Fine, I'll add this task:");
                System.out.println("\t" + t.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list... \n" + horizontalLine );
                // add a deadline item
            } else if (echoText.split(" ")[0].equals("deadline")) {
                int firstSpaceIndex = echoText.indexOf(" ");
                if (firstSpaceIndex == -1) {
                    System.out.println(horizontalLine + "Description of a deadline cannot be empty dummy! \n" + horizontalLine);
                    continue;
                }
                int byIndex = echoText.indexOf("/by");
                if (byIndex == -1) {
                    System.out.println(horizontalLine + "A deadline must have a by clause dummy! \n" + horizontalLine);
                    continue;
                }
                String desc = echoText.substring(firstSpaceIndex + 1, byIndex);
                //4 because 3 of "/by" and 1 for the additional space
                String by = echoText.substring(byIndex + 4);
                Deadline d = new Deadline(desc, by);
                list.add(d);
                System.out.println(horizontalLine + "Fine, I'll add this task:");
                System.out.println("\t" + d.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list... \n" + horizontalLine );
                //add an event item
            } else if (echoText.split(" ")[0].equals("event")) {
                int firstSpaceIndex = echoText.indexOf(" ");
                if (firstSpaceIndex == -1) {
                    System.out.println(horizontalLine + "Description of an event cannot be empty dummy! \n" + horizontalLine);
                    continue;
                }
                int atIndex = echoText.indexOf("/at");
                if (atIndex == -1) {
                    System.out.println(horizontalLine + "An event must have a at clause dummy! \n" + horizontalLine);
                    continue;
                }
                String desc = echoText.substring(firstSpaceIndex + 1, atIndex);
                String at = echoText.substring(atIndex + 4);
                Event e = new Event(desc, at);
                list.add(e);
                System.out.println(horizontalLine + "Fine, I'll add this task:");
                System.out.println("\t" + e.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list... \n" + horizontalLine );
            } else if (echoText.split(" ")[0].equals("delete")) {
                int delValue = Integer.parseInt(echoText.split(" ")[1]);
                Task item = list.get(delValue - 1);
                System.out.println(horizontalLine + "Ughh I'll remove this task: \n \t" + item.toString());
                list.remove(delValue - 1);
                System.out.println("Now you have " + list.size() + " tasks in the list... \n" + horizontalLine );
            } else {
                System.out.println(horizontalLine + "What are you saying??? Try again" + "\n" + horizontalLine);
            }

        }

    }

}
