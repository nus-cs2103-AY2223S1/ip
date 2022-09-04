package duke;

import duke.task.Priority;

import java.time.LocalDate;
import java.time.LocalTime;

public class test {

    private String appendDescription(String ... components) {
        StringBuilder str = new StringBuilder();
        for (String component : components) {
            str.append(component).append(" ");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        test t = new test();
        String time = "event meeting /by 2019-12-10         16:30-20:30";
        String[] str = time.split("\\s+", 2);
        String[] str1 = str[1].split("\\s+", 2);
        String description = t.appendDescription(str1[0], str1[1]);
        String[] components = description.split("/by ", 2);
        String[] timeComponents = components[1].split("\\s+", 2);
        String[] c = timeComponents[1].split("-", 2);
        System.out.println(LocalTime.parse(c[1].trim()));
    }
}

