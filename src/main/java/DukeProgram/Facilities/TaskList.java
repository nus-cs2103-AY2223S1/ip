package DukeProgram.Facilities;

import DukeProgram.*;
import DukeProgram.Storage.SaveManager;
import Exceptions.JobNameException;
import Exceptions.KeyNotFoundException;

import java.io.Serializable;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

public class TaskList implements List<Task>, Serializable {
    private static HashMap<String, TaskList> taskListsMapping;

    private static TaskList current;

    private final ArrayList<Task> taskArrayList = new ArrayList<>(100);
    private String name;

    private TaskList(String name) {
        this.name = name;

        taskListsMapping.put(name, this);
    }

    public static void initialise() {
        if (taskListsMapping == null) {
            try {
                taskListsMapping = SaveManager.load("taskListsMapping");
            } catch (KeyNotFoundException e) {
                taskListsMapping = new HashMap<>();
            }
        } else {
            System.out.println("TRIED TO INITIALISE TASKLIST WHEN IT ALREADY EXISTS");
        }
    }

    public static TaskList current() {
        return current;
    }

    public static TaskList getTaskList(String name) throws KeyNotFoundException {
        if (!taskListsMapping.containsKey(name)) {
            throw new KeyNotFoundException(name, "taskListMapping");
        }
        return taskListsMapping.get(name);
    }

    public static void changeTaskList(String name) throws KeyNotFoundException{
        if (current != null) {
            SaveManager.save("taskListsMapping", taskListsMapping);
        }

        current = getTaskList(name);
    }


    public static int getNumberOfTaskLists() {
        return taskListsMapping.size();
    }

    public static String[] getAllTaskListNames() {
        return taskListsMapping.keySet().toArray(new String[0]);
    }

    public static TaskList addNewTaskList(String name) {
        TaskList taskList = new TaskList(name);
        taskListsMapping.put(taskList.name, taskList);

        SaveManager.save("taskListsMapping", taskListsMapping);

        return taskList;
    }

    public static void removeTaskList(String name) throws KeyNotFoundException{
        if (!taskListsMapping.containsKey(name)) {
            throw new KeyNotFoundException(name, "taskListsMapping");
        }

        taskListsMapping.remove(name);
        SaveManager.save("taskListsMapping", taskListsMapping);
    }

    public String getName() {
        return name;
    }

    public void changeName(String newName) {
        taskListsMapping.remove(name);
        this.name = newName;
        taskListsMapping.put(name, this);

        SaveManager.save("taskListsMapping", taskListsMapping);
    }

    /***
     * Provides functionality to use a checklist in Duke.
     */
    /*public static void use() {
        String[] input = askForInput("").split(" ");

        while (!input[0].toLowerCase(Locale.ROOT).equals("bye")) {
            switch (input[0]) {
                case "list":
                    listChecklist(input);
                    break;

                case "mark": case "unmark":
                    markChecklist(input);
                    break;

                case "delete":
                    deleteFromChecklist(input);
                    break;

                default:
                    try {
                        addToChecklist(input);
                    } catch (InvalidJobException ex) {
                        printInStyle(ex.getMessage());
                    }
            }
            input = askForInput("").split(" ");
        }
    }*/


    /*private static void listChecklist(String[] input) {
        if (input.length == 1) {
            printInStyle(IntStream
                    .range(0, checklist.size())
                    .mapToObj(i -> String.format("%d: %s", i + 1, checklist.get(i).toString()))
            );
            return;
        }

        switch (input[1]) {
        case "-date":
            printInStyle(checklist
                    .stream()
                    .filter(e -> e instanceof DatedJob)
                    .sorted(Comparator.comparing(e -> ((DatedJob)e).getDate()))
            );
            break;

            *//**
        case "-on":
            printInStyle(checklist
                    .stream()
                    .filter(e -> e instanceof DatedJob && ((DatedJob) e).getDate().equals(DateTimeParser.parse())
            );
            break;
             **//*

        case "-alphabet":
            printInStyle(checklist
                    .stream()
                    .sorted(Comparator.comparing(Task::getName))
            );
            break;
        }
    }

    *//***
     * Takes in a command in the format from System.in and mark or unmarks
     * jobs on the checklist
     * @param input the command given from System.in
     *//*
    private static void markChecklist(String[] input) {
        if (input.length < 2) {
            printInStyle("Please specify which task you want to mark or unmark " +
                    "after the command. Otherwise, you may also specify all.");
            return;
        }

        if (input[1].equals("all")) {
            if (input[0].equals("mark")) {
                for (Task task : checklist) {
                    task.MarkJobState(true);
                }
                printInStyle("Ok, I've marked all items as completed!");
            } else if (input[0].equals("unmark")) {
                for (Task task : checklist) {
                    task.MarkJobState(false);
                }
                printInStyle("Ok, I've unmarked all items as undone.");
            }
            return;
        }

        int index;
        try {
            index = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException ex) {
            printInStyle(String.format("I cannot understand what %s means in this context.", input[1]));
            return;
        }

        if (index < checklist.size() && index >= 0) {
            boolean isMark = input[0].equals("mark");
            checklist.get(index).MarkJobState(isMark);

            String response = isMark ?
                    "Nice! I've marked this task as done:" :
                    "Ok, I've marked this task as not done yet:";

            printInStyle(
                    response,
                    checklist.get(index).toString()
            );

        } else if (checklist.isEmpty()) {
            printInStyle(String.format("%d is out of range, " +
                    "you need to first add some items into the To-Do List", index + 1));

        } else {
            printInStyle(
                    String.format("%d is out of range, please choose an integer between 1 to %d",
                            index + 1,
                            checklist.size()));
        }
    }

    *//**
     * Takes in a command in the format from System.in
     * and deletes the job with the associated index.
     * @param input command input from system
     *//*
    private static void deleteFromChecklist(String[] input) {
        if (input.length < 1) {
            printInStyle("Please specify the index of the job you want to delete.");
            return;
        }

        if (input[1].equals("all")) {
            checklist.clear();
            printInStyle("Alright. I've cleared all your tasks from the checklist");
            return;
        }

        int index;
        try {
            index = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException ex) {
            printInStyle("Please only specify numbers starting from 1!");
            return;
        }

        if (index <= -1) {
            printInStyle("Sorry, the number can only be more than or equals to 1!");
            return;
        }

        if (index >= checklist.size()) {
            printInStyle(String.format("You wanted to delete task %d but there were only %d jobs in the list.",
                    index + 1, checklist.size()));
            return;
        }

        Task taskRemoved = checklist.get(index);
        checklist.remove(index);
        printInStyle("Noted. I've removed this task:",
                taskRemoved.toString(),
                String.format("Now you have %d tasks in the list.", checklist.size()));
    }

    *//***
     * Takes in a command in the format from System.in and add
     * jobs on the checklist based on their type
     * @param input the command given from System.in
     *//*
    private static void addToChecklist(String[] input) throws InvalidJobException{
        Task task;
        String[][] nameAndDate;

        switch (input[0]) {
            case "todo":
                try {
                    task = new ToDo(concatName(input));
                } catch (JobNameException ex) {
                    printInStyle(ex.getMessage());
                    return;
                }
                break;

            case "deadline":
                nameAndDate = StringUtilities.splitStringArray(input, "/by");
                if (nameAndDate.length != 2) {
                    printInStyle("Please use /by to set a time");
                    return;
                }

                try {
                    task = new Deadline(concatName(nameAndDate[0]), String.join(" ", nameAndDate[1]));
                } catch (JobNameException ex) {
                    printInStyle(ex.getMessage());
                    return;
                }
                break;

            case "event":
                nameAndDate = StringUtilities.splitStringArray(input, "/at");
                if (nameAndDate.length != 2) {
                    printInStyle("Please use /at to set a time");
                    return;
                }

                try {
                    task = new Event(concatName(nameAndDate[0]), String.join(" ", nameAndDate[1]));
                } catch (JobNameException ex) {
                    printInStyle(ex.getMessage());
                    return;
                }
                break;
            default:
                throw new InvalidJobException();
        }
        checklist.add(task);
        printInStyle(
                "Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %d tasks in the list", checklist.size()));
    }*/


    /**
     * A helper method to concat a string array given in the format from System.in
     * @param input The string array given in the format from System.in
     * @return the actual name of the job to be created
     * @throws JobNameException if there is no name provided
     */
    private static String concatName(String[] input) throws JobNameException {
        String name = Arrays.stream(input).skip(1).collect(Collectors.joining(" "));
        if (name.equals("")) {
            throw new JobNameException(input[0]);
        }
        return name;
    }

    @Override
    public int size() {
        return taskArrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return taskArrayList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return taskArrayList.contains(o);
    }

    @Override
    public Iterator<Task> iterator() {
        return taskArrayList.iterator();
    }

    @Override
    public Object[] toArray() {
        return taskArrayList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return taskArrayList.toArray(a);
    }

    @Override
    public boolean add(Task task) {
        return taskArrayList.add(task);
    }

    @Override
    public boolean remove(Object o) {
        return taskArrayList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return taskArrayList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Task> c) {
        return taskArrayList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Task> c) {
        return taskArrayList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return taskArrayList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return taskArrayList.retainAll(c);
    }

    @Override
    public void clear() {
        taskArrayList.clear();
    }

    @Override
    public Task get(int index) {
        return taskArrayList.get(index);
    }

    @Override
    public Task set(int index, Task element) {
        return taskArrayList.set(index, element);
    }

    @Override
    public void add(int index, Task element) {
        taskArrayList.add(index, element);
    }

    @Override
    public Task remove(int index) {
        return taskArrayList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return taskArrayList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return taskArrayList.lastIndexOf(o);
    }

    @Override
    public ListIterator<Task> listIterator() {
        return taskArrayList.listIterator();
    }

    @Override
    public ListIterator<Task> listIterator(int index) {
        return taskArrayList.listIterator(index);
    }

    @Override
    public List<Task> subList(int fromIndex, int toIndex) {
        return taskArrayList.subList(fromIndex, toIndex);
    }
}
