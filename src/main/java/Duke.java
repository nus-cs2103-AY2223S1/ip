import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String INDENTATION = "     ";
    private static final String EXTRA_INDENTATION = "  ";
    enum Work_type {
        TODO,
        DEADLINE,
        EVENT
    }
    private static String reply(String message) {
        return "    ____________________________________________________________\n" +
                INDENTATION + message + "\n" +
                "    ____________________________________________________________";
    }

    private static String craftList(ArrayList<String> array, ArrayList<Boolean> bArray, ArrayList<Work_type> workTypeArray) {
        int length = array.size();
        String result = "Here are the task(s) in your list:";
        for (int x = 0; x < length; x++) {
            if (array.get(x) == null) {
                break;
            } else {
                result += "\n" + INDENTATION + (x + 1) + "." + workTypeBox(workTypeArray.get(x)) + checkBox(bArray.get(x)) + " " + array.get(x);
            }
        }
        return result;
    }

    private static String craftTDEMessage(Work_type work_type, boolean bool, String todo, int work_number) {
        return "Got it. I've added this task:\n" +
                INDENTATION + EXTRA_INDENTATION + workTypeBox(work_type) + checkBox(bool) +  " " + todo + "\n" +
                INDENTATION + "Now you have " + work_number + (work_number < 2 ? " task" : " tasks") + " in the list.";
    }

    private static String checkBox(boolean bool) {
        if (bool) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    private static String workTypeBox(Work_type type) {
        if (type == Work_type.TODO) {
            return "[T]";
        } else if (type == Work_type.DEADLINE) {
            return "[D]";
        } else { // Has to be Work_type.EVENT
            return "[E]";
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(reply("Hello! I'm Duke\n" +
                "     What can I do for you?"));
        boolean conversation = true;
        ArrayList<String> work = new ArrayList<>(100);
        ArrayList<Boolean> workCompleted = new ArrayList<>(100);
        ArrayList<Work_type> workType = new ArrayList<>(100);
        int workCounter = 0;
        while (conversation) {
            String response = scan.nextLine();
            String[] splitResponse = response.split(" ");
            String keyword = splitResponse[0];
            if (splitResponse.length == 2 && (keyword.equals("mark") || keyword.equals("unmark"))) {
                try {
                    int number = Integer.parseInt(splitResponse[1]) - 1;
                    if (splitResponse[0].equals("mark")) {
                        workCompleted.set(number, true);
//                    workCompleted[number] = true;
                        System.out.println(reply("Nice! I've marked this task as done:\n" +
                                INDENTATION + EXTRA_INDENTATION + workTypeBox(workType.get(number)) + checkBox(true) + " " + work.get(number)));
                    } else {
                        workCompleted.set(number, false);
//                    workCompleted[number] = false;
                        System.out.println(reply("OK, I've marked this task as not done yet:\n" +
                                INDENTATION + EXTRA_INDENTATION + workTypeBox(workType.get(number))+ checkBox(false) + " " + work.get(number)));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(reply(new DukeException("non integer input when marking").toString()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(reply(new DukeException("index out of bounds").toString()));
                }
            } else if (splitResponse.length == 2 && keyword.equals("delete")) {
                try {
                    int location = Integer.parseInt(splitResponse[1]) - 1;
                    Work_type type = workType.get(location);
                    boolean completed = workCompleted.get(location);
                    String task = work.get(location);
                    workType.remove(location);
                    workCompleted.remove(location);
                    work.remove(location);
                    workCounter--;
                    System.out.println(reply("Noted. I've removed the task:\n" +
                            INDENTATION + EXTRA_INDENTATION + workTypeBox(type) + checkBox(completed) + " " + task + "\n" +
                            INDENTATION + "Now you have " + workCounter + (workCounter < 2 ? " task" : " tasks") + " in the list."));
                } catch (NumberFormatException e) {
                    System.out.println(reply(new DukeException("non integer input when deleting").toString()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(reply(new DukeException("index out of bounds").toString()));
                }
            } else if (keyword.equals("todo")) {
                if (splitResponse.length == 1) {
                    System.out.println(reply(new DukeException("todo").toString()));
                } else {
                    String todo = response.substring(5);
                    work.add(todo);
                    workType.add(Work_type.TODO);
                    workCompleted.add(false);
                    workCounter++;
                    System.out.println(reply(craftTDEMessage(Work_type.TODO, false, todo, workCounter)));
                }
            } else if (keyword.equals("deadline")) {
                if (splitResponse.length == 1) {
                    System.out.println(reply(new DukeException("deadline").toString()));
                } else {
                    String[] newSplit = response.split(" /by ");
                    if (newSplit.length == 1) {
                        System.out.println(reply(new DukeException("deadline format").toString()));
                    } else {
                        String todo = newSplit[0].substring(9);
                        String by = newSplit[1];
                        String newTodo = todo + " (by: " + by + ")";
                        work.add(newTodo);
                        workType.add(Work_type.DEADLINE);
                        workCompleted.add(false);
                        workCounter++;
                        System.out.println(reply(craftTDEMessage(Work_type.DEADLINE, false, newTodo, workCounter)));
                    }
                }
            } else if (keyword.equals("event")) {
                if (splitResponse.length == 1) {
                    System.out.println(reply(new DukeException("event").toString()));
                } else {
                    String[] newSplit = response.split(" /at ");
                    if (newSplit.length == 1) {
                        System.out.println(reply(new DukeException("event format").toString()));
                    } else {
                        String todo = newSplit[0].substring(6);
                        String at = newSplit[1];
                        String newTodo = todo + " (at: " + at + ")";
                        work.add(newTodo);
                        workType.add(Work_type.EVENT);
                        workCompleted.add(false);
                        workCounter++;
                        System.out.println(reply(craftTDEMessage(Work_type.EVENT, false, newTodo, workCounter)));
                    }
                }
            } else if (response.equals("bye")) {
                System.out.println(reply("Bye. Hope to see you again soon!"));
                conversation = false;
            } else if (response.equals("list")) {
                System.out.println(reply(craftList(work, workCompleted, workType)));
            } else {
                System.out.println(reply(new DukeException("unknown").toString()));
            }
        }
    }
}
