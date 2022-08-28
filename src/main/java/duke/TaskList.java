package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

//    public void list() {
//        System.out.printf("Here are the tasks in your list:\n");
//        for (int i = 0; i < duke.Task.taskCount; i++) {
//            System.out.printf("%d.%s\n", i + 1, this.tasks.get(i).toString());
//        }
//    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void delete(int taskNo) {
        tasks.remove(tasks.get(taskNo));
    }

//    public void todo(Scanner scanner) throws duke.DukeException, IOException {
//        try {
//            duke.ToDo todo = new duke.ToDo(scanner.nextLine());
//            tasks.add(todo);
//            System.out.println(todo.added());
//            //saveData(this);
//
//        } catch (NoSuchElementException e) {
//            throw new duke.DukeException("OOPS!!! The description of a todo cannot be empty.");
//        }
//    }

//    public void deadline(Scanner scanner) throws duke.DukeException{
//        String description = "";
//        while (!scanner.hasNext("/by")) {
//            description += scanner.next();
//        }
//        scanner.next();
//        String date = scanner.nextLine();
//        duke.Deadline deadline = new duke.Deadline(description, date);
//        tasks.add(deadline);
//        System.out.println(deadline.added());
//        // saveData(array);
//    }
//
//
//    public void event(Scanner scanner) throws duke.DukeException {
//        String description1 = "";
//        while (!scanner.hasNext("/at")) {
//            description1 += scanner.next() + " ";
//        }
//        scanner.next(); // skips /a
//        String date1 = scanner.nextLine();
//        duke.Event event = new duke.Event(description1, date1);
//        tasks.add(event);
//        System.out.println(event.added());
//        // saveData(array);
//    }

//    public void mark(Scanner scanner) throws duke.DukeException, IOException{
//        int taskNo = scanner.nextInt();
//        tasks.get(taskNo).markAsDone();
//        System.out.printf("Nice! I've marked this task as done: \n" +
//                "  [X] %s\n", tasks.get(taskNo).description);
//    }

//    public void unmark(Scanner scanner) throws duke.DukeException, IOException{
//        int taskNo = scanner.nextInt();
//        tasks.get(taskNo).markAsUndone();
//        System.out.printf("OK, I've marked this task as not done yet: \n" +
//                "  [ ] %s\n", tasks.get(taskNo).description);
//        //duke.Storage.saveData(taskList);
//    }

//    public void delete(Scanner scanner) throws duke.DukeException, IOException {
//        int taskNo2 = scanner.nextInt();
//        duke.Task t = tasks.get(taskNo2);
//        tasks.remove(taskNo2);
//        System.out.printf("Noted. I've removed this task:\n" +
//                "  %s\n" +
//                "Now you have %d tasks in the list.\n", t, duke.Task.taskCount);
//        //saveData(array);
//    }

}
