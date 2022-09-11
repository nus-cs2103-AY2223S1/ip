package seedu.duke.Task;

import seedu.duke.Parser;

import java.time.LocalDate;

public class TaskSorter {

    public static Task sortTaskFromData(String data) {

        String splitter = "\\|";
        String[] temp = data.split(splitter); //0:type 1:description 2:status 3:date (optional)

        Task task = null;
        if ("todo".equals(temp[0])) {
            task = new Todo(temp[1]);
        } else if ("event".equals(temp[0])) {
            LocalDate date = LocalDate.parse(temp[3].strip());
            task = new Event(temp[1],date);
        } else if ("deadline".equals(temp[0])) {
            LocalDate date = LocalDate.parse(temp[3].strip());
            task = new Deadline(temp[1],date);
        } else {
            System.out.println("Invalid choice, please choose either todo, event or deadline");
        }

        boolean isDone = "1".equals(temp[2]);
        assert task != null;
        task.setStatusIcon(isDone);
        return task;
    }

    public static Task sortTaskFromInput(String input) {
        String[] temp = input.split(" ", 2);
        String type = temp[0];

        Task task = null;
        if ("todo".equals(temp[0])) {
            task = new Todo(temp[1]);
        } else if ("event".equals(temp[0])) {
            String[] info = temp[1].split("/at");
            assert info.length == 2;
            LocalDate date = LocalDate.parse(info[1].strip());
            task = new Event(info[0],date);
        } else if ("deadline".equals(temp[0])) {
            String[] info = temp[1].split("/by");
            assert info.length == 2;
            LocalDate date = LocalDate.parse(info[1].strip());
            task = new Deadline(info[0],date);
        } else {
            System.out.println("Invalid choice, please choose either todo, event or deadline");
        }

        return task;
    }
}
