package pluto;

import pluto.task.Task;

import java.util.ArrayList;
import java.util.Locale;

public class TaskList {
    private ArrayList<Task> missions;

    public TaskList() {
        this.missions = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> missions) {
        this.missions = missions;
    }

    public void addTask(Task t) {
        missions.add(t);
    }

    public void addTask(int idx, Task t) throws PlutoException {
        try {
            missions.add(idx, t);
        } catch (IndexOutOfBoundsException e) {
            throw new PlutoException("\tOOPS!!! Valid index required.");
        }
    }

    public Task deleteTask(int idx) throws PlutoException {
        try {
            return missions.remove(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new PlutoException("\tOOPS!!! Valid index required.");
        }
    }

    public Task getTask(int idx) throws PlutoException {
        try {
            return missions.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new PlutoException("\tOOPS!!! Valid index required.");
        }
    }

    public void markTask(int idx, boolean markStatus) throws PlutoException {
        try {
            if (markStatus) {
                missions.get(idx).markAsDone();
            } else {
                missions.get(idx).markAsUndone();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new PlutoException("\tOOPS!!! Valid index required.");
        }
    }

    public int nTasks() {
        return missions.size();
    }

    public TaskList filter(String keyword) {
        TaskList filtered = new TaskList();
        String[] keywords = keyword.split("\\s+");
        for (Task t: missions) {
            boolean allFound = true;
            for (String s: keywords) {
                if (!t.getDescription().toUpperCase().contains(s.toUpperCase())) {
                    allFound = false;
                    break;
                }
            }
            if (allFound) {
                filtered.addTask(t);
            }
        }
        return filtered;
    }

    @Override
    public String toString() {
        StringBuilder printTasks = new StringBuilder();
        for (int i = 0; i < missions.size(); i++) {
            String output = String.format("\t\t%d. %s\n",i + 1, missions.get(i).toString());
            printTasks.append(output);
        }
        return printTasks.toString();
    }

}
