package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class TaskList {

    private final List<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(Stream<String> lines) {
        tasks = populateList(lines);
    }

    List<Task> populateList(Stream<String> lines) {
        ArrayList<Task> arrayList = new ArrayList<>();
        lines.forEach(x -> {
            int id = Integer.parseInt(x.substring(0, 1));
            char type = x.charAt(3);
            boolean done = x.charAt(6) == 'X';
            String task;
            if (type == 'D' || type == 'E') {
                task = x.substring(9, x.indexOf("(") - 1);
                String additional = x.substring(x.indexOf("(") + 6, x.indexOf(')'));
                if (type == 'D') {
                    arrayList.add(new Deadline(id, task, additional, done));
                } else {
                    arrayList.add(new Event(id, task, additional, done));
                }
            } else {
                task = x.substring(9);
                arrayList.add(new Todo(id, task, done));
            }
        });
        return arrayList;
    }

    String find(String query) {
        String[] response = new String[]{"Here are the matching tasks in your list:\n"};
        tasks.stream().filter(x -> x.getName().contains(query))
                .forEach(x -> response[0] += x + "\n");
        return response[0];
    }

    List<Task> getTasks() {
        return tasks;
    }

    String list() {
        String[] response = new String[]{"Here are the tasks in your list: \n"};
        tasks.forEach(x -> response[0] += x + "\n");
        return response[0];
    }

    String mark(int id, boolean done) {
        String response = "";
        Task task = tasks.get(id - 1);
        if (done) {
            task.setDone();
            response += "Nice! I've marked this task as done: \n";
        } else {
            task.setNotDone();
            response += "OK, I've marked this task as not done yet: \n";
        }
        response += task;
        return response;
    }

    String event(String name, String eventDate) {
        String response = "";
        Event event = new Event(tasks.size() + 1, name, eventDate);
        tasks.add(event);
        response += "Got it. I've added this task: \n";
        response += event + "\n";
        response += "Now you have " + tasks.size() + " tasks in the list.";
        return response;
    }

    String deadline(String name, String deadline) {
        String response = "";
        Deadline deadlineEvent = new Deadline(tasks.size() + 1, name, deadline);
        tasks.add(deadlineEvent);
        response += "Got it. I've added this task: \n";
        response += deadlineEvent + "\n";
        response += "Now you have " + tasks.size() + " tasks in the list.";
        return response;
    }

    String todo(String task) {
        String response = "";
        Todo todo = new Todo(tasks.size() + 1, task);
        tasks.add(todo);
        response += "Got it. I've added this task: \n";
        response += todo + "\n";
        response += "Now you have " + tasks.size() + " tasks in the list.";
        return response;
    }

    String delete(int id) {
        String response = "";
        Task toRemove = tasks.remove(id - 1);
        response += "Noted. I've removed this task: \n";
        response += toRemove + "\n";
        response += "Now you have " + tasks.size() + " tasks in the list.";
        return response;
    }

}
