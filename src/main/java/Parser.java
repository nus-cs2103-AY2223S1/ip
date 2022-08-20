import java.time.LocalDate;

public class Parser {
    private String[] words;

    public String checkResponse(String input) {
        words = input.split(" ");
        return words[0];
    }

    public int getTaskNumber() {
        return Integer.parseInt(words[1]);
    }

    public String getDeadlineDescription() {
        int a = 2;
        StringBuilder task = new StringBuilder(words[1]);
        while (!words[a].equals("/by")) {
            task.append(" ");
            task.append(words[a]);
            a++;
        }
        return task.toString();
    }

    public LocalDate getDeadlineTime() {
        int a = 2;
        while (!words[a].equals("/by")) {
            a++;
        }
        a++;
        StringBuilder deadline = new StringBuilder(words[a]);
        for (int b = a + 1; b < words.length; b++) {
            deadline.append(" ");
            deadline.append(words[b]);
        }
        return LocalDate.parse(deadline);
    }

    public String getEventDescription() {
        int a = 2;
        StringBuilder task = new StringBuilder(words[1]);
        while (!words[a].equals("/at")) {
            task.append(" ");
            task.append(words[a]);
            a++;
        }
        return task.toString();
    }

    public String getEventTime() {
        int a = 2;
        while (!words[a].equals("/at")) {
            a++;
        }
        a++;
        StringBuilder event = new StringBuilder(words[a]);
        for (int b = a + 1; b < words.length; b++) {
            event.append(" ");
            event.append(words[b]);
        }
        return event.toString();
    }

    public String getTodoDescription() throws EmptyTodoListException {
        if (words.length <= 1) {
            throw new EmptyTodoListException();
        }
        StringBuilder task = new StringBuilder(words[1]);
        for (int a = 2; a < words.length; a++) {
            task.append(" ");
            task.append(words[a]);
        }
        return task.toString();
    }
}
