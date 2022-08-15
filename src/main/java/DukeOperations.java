public class DukeOperations {
    public void exit() {
        System.out.println("_________________________________________________________________________");
        System.out.println("        Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________________________________");
    }

    public void displayList(Task[] array) {
        System.out.println("_________________________________________________________________________");
        for (int i = 0; i < 100; i++) {
            if (array[i] != null) {
                int j = i + 1;
                System.out.println(j + ". " + array[i].toString());
            }
        }
        System.out.println("_________________________________________________________________________");
    }

    public void todo(Task[] array, String item, int counter) {
        array[counter] = new Todo(item);
        System.out.println("Got it. I've added this task:");
        System.out.println(array[counter].toString());
        counter++;
        System.out.println("Now you have " + counter + " tasks in the list.");
    }

    public void deadline(Task[] array,String item, String deadline, int counter){

        array[counter] = new Deadline(item, deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(array[counter].toString());
        counter ++;
        System.out.println("Now you have " + counter + " tasks in the list.");
    }

    public void event(Task[] array,String item, String deadline, int counter) {
        array[counter] = new Event(item,deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(array[counter].toString());
        counter ++;
        System.out.println("Now you have " + counter + " tasks in the list.");

    }

    public void mark(Task[] array, int number) {
        array[number - 1].markAsDone();
        System.out.println("_________________________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(array[number-1].toString());
        System.out.println("_________________________________________________________________________");
    }

    public void unmark(Task[] array, int number) {
        array[number - 1].markAsNotDone();
        System.out.println("_________________________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(array[number-1].toString());
        System.out.println("_________________________________________________________________________");
    }

    public void randomword(String word) {

    }

}
