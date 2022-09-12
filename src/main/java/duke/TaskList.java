package duke;

import java.time.LocalDate;
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

            assert(id > 0);
            assert(type == 'T' || type == 'D' || type == 'E');

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
        task.setStatus(done);
        if (done) {
            response += "Nice! I've marked this task as done: \n";
        } else {
            response += "OK, I've marked this task as not done yet: \n";
        }
        response += task;
        return response;
    }

    boolean checkIfDuplicate(String name, String type, String additional) {
        if (type.equals("T")) {
            return tasks.stream().anyMatch(x -> x.getName().equals(name) && x.getClass() == Todo.class);
        } else {
            return tasks.stream().anyMatch(x -> {
                LocalDate date = LocalDate.parse(additional);
                if (x.getClass() == Deadline.class) {
                    return x.getName().equals(name) && ((Deadline) x).deadline.equals(date);
                } else if (x.getClass() == Event.class) {
                    System.out.println(date);
                    System.out.println(((Event) x).eventTime);
                    return x.getName().equals(name) && ((Event) x).eventTime.equals(date);
                }
                return false;
            });
        }
    }

    String addTask(String name, String type, String additional) {
        if (checkIfDuplicate(name, type, additional)) {
            return "Duplicate task! Not added.";
        }
        String response = "";
        Task newTask;
        switch (type) {
        case "T":
            newTask = new Todo(tasks.size() + 1, name);
            break;
        case "D":
            newTask = new Deadline(tasks.size() + 1, name, additional);
            break;
        case "E":
            newTask = new Event(tasks.size() + 1, name, additional);
            break;
        default:
            return "Unknown task type";
        }
        tasks.add(newTask);
        response += "Got it. I've added this task: \n";
        response += newTask + "\n";
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
