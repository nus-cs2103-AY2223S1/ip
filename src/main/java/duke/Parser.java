package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;


public class Parser {


    int count = 0;
    TaskList tasks = new TaskList();
    boolean checker = false;
    Ui ui = new Ui();

    Storage storage = new Storage();
    String s = storage.reader();

    public Parser() {
        storageParse();
    }

    /**
     * Processes the input given by the user of Duke
     *
     * @param input what the user typed
     */
    public String parse(String input) {

        StringBuilder temp = new StringBuilder();

        if (input.equals("bye")) {
            storage.writer(tasks.getArr());
            return ui.print(1);
        } else if (input.equals("list")) {
            temp.append(ui.print(2)).append("\n");
            for (int i = 0; i < count; i ++) {
                temp.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
            }
        } else if (input.contains("mark")) {
            int index = -1;
            try {
                if (input.equals("mark")) {
                    throw new EmptyDescriptionException("Please select a task to mark");
                } else {
                    input = input.replaceAll("mark ", "");
                    index = Integer.parseInt(input) - 1;
                }

                if (index > count - 1 || index < 0) {
                    throw new OutOfRangeException(index + 1);
                } else {
                    tasks.get(index).setDone();
                }

                if (checker) {
                    temp = new StringBuilder(ui.print(3) + tasks.get(index).toString());
                    return temp.toString();
                }

            } catch (EmptyDescriptionException | OutOfRangeException e) {
                return e.getMessage();
            }
        } else if (input.contains("todo")) {
            try {
                if (input.equals("todo")) {
                    throw new EmptyDescriptionException();
                }
                input = input.replaceFirst("todo ", "");
                tasks.add(new Todo(input));

                //output
                if (checker) {
                    temp.append(ui.print(4))
                            .append("\n")
                            .append(tasks.get(count).toString())
                            .append("\n")
                            .append("Now you have ")
                            .append(count + 1)
                            .append(" tasks in the list.");
                    count++;
                    return temp.toString();
                }
                count++;

            } catch (EmptyDescriptionException e) {
                return e.getMessage();
            }
        } else if (input.contains("deadline")) {

            input = input.replaceFirst("deadline ", "");

            if (!checker) {
                String[] s_arr = input.split("/", 2); //split array
                s_arr[1] = s_arr[1].replaceAll("by ", "");
                tasks.add(new Deadline(s_arr[0], s_arr[1]));
            } else {
                //string manipulation
                String[] s_arr = input.split("/", 2); //split array
                s_arr[1] = s_arr[1].replaceFirst("by ", "");
                tasks.add(new Deadline(s_arr[0], s_arr[1]));
            }

            //output
            if (checker) {
                temp.append("Got it. I've added this task:\n")
                        .append(tasks.get(count).toString())
                        .append("\n")
                        .append("Now you have ")
                        .append(count + 1)
                        .append( " tasks in the list.");
                count++;
                return temp.toString();
            }
            count++;

        } else if (input.contains("event")) {

            //string manipulation
            input = input.replaceFirst("event ", "");
            String[] s_arr = input.split("/", -1); //split array
            s_arr[1] = s_arr[1].replaceFirst("at ", "");

            tasks.add(new Event(s_arr[0], s_arr[1]));


            //output
            if (checker) {
                temp.append("\n")
                        .append(ui.print(4))
                        .append("\n")
                        .append(tasks.get(count).toString())
                        .append("\n")
                        .append("Now you have ")
                        .append(count + 1)
                        .append(" tasks in the list.");
                count++;
                return temp.toString();
            }
            count++;

        } else if (input.contains("delete")) {
            int index = -1;

            //string manipulation
            input = input.replaceAll("delete ", "");
            index = Integer.parseInt(input) - 1;

            //output
            temp.append(ui.print(7) + "\n")
                    .append(tasks.get(index).toString() + "\n")
                    .append("Now you have ")
                    .append(count - 1)
                    .append(" tasks in the list.");

            tasks.remove(index);
            count--;

        } else if (input.contains("find")) {
            //string manipulation
            int count = 1;
            input = input.replaceFirst("find ", "");
            ArrayList<Task> filtered_List = new ArrayList<>();
            for (Task item: tasks.getArr()) {
                if (item.description.contains(input)) {
                    filtered_List.add(item);
                }
            }
            temp.append("Here are the matching tasks in your list:\n" );
            for (Task item: filtered_List) {
                temp.append(count + ". " + item.toString());
                count++;
            }
        } else if (input.contains("snooze")) {
            input = input.replaceFirst("snooze ", "");
            String[] split_num = input.split(" ",2);

            if (tasks.getArr().get(Integer.parseInt(split_num[0]) - 1) instanceof Deadline) {
                Deadline curr_task = (Deadline) tasks.getArr().get(Integer.parseInt(split_num[0]) - 1);
                curr_task.date_increment(Integer.parseInt(split_num[1]));
                temp.append("The following task has been snoozed by " + split_num[1] + " days!\n")
                        .append(tasks.get(Integer.parseInt(split_num[0]) - 1).toString());
            } else {
                temp.append("Sorry! Only deadlines can be snoozed!");
            }
        } else {
            try {
                throw new UnknownCommandException();
            } catch (UnknownCommandException e) {
                return e.getMessage();
            }
        }
        return temp.toString();
    }

    public ArrayList<Task> getArr() {
        return tasks.getArr();
    }

    public void setChecker() {
        checker = true;
    }

    private void storageParse() {
        //process String
        s = s.replace("[T]", "todo");
        s = s.replace("[D]", "deadline");
        s = s.replace("[E]", "event");
        s = s.replace("[ ]", "");
        s = s.replace("[X]", "");
        if (s.contains(":")) {
            s = s.replace("(", "/");
            s = s.replace(":", "");
            s = s.replace(")", "");
        }
        if (s.contains("deadline")) {
            int len = s.length();

            String date = s.substring(len - 17, len - 6);
            if (date.charAt(0) == ' ') {
                date = date.replaceFirst(" ", "");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");;
            LocalDate d1 = LocalDate.parse(date, formatter);
            s = s.replaceAll(date ,d1.format(DateTimeFormatter.ofPattern("d/MM/yyyy")));

        }
        while (true) {
            if (s.equals("")) {
                setChecker();
                break;
            } else {
                int temp = s.indexOf("%");
                String temp2 = s.substring(0, temp);
                parse(s.substring(0, temp));
                s = s.replaceAll(temp2 + "%", ""); //remove old string
            }
        }
    }
}