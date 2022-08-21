public class Tasklist {
    private Task[] taskArray;
    private int count = 0;

    public Tasklist(int size){
        this.taskArray = new Task[size];
    }

    public void add(Task task){
        this.taskArray[this.count] = task;
        this.count += 1;
    }

    public Task getTask(int position) throws ArrayIndexOutOfBoundsException{
        return taskArray[position - 1];
    }

    public boolean markTaskAtPos(int position){
        try {
            Task currTask = getTask(position);
            currTask.markAsDone();
            return true;
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Task does not exist. Try another number between 0 and " + count + ".");
            return false;
        }
    }

    public boolean unmarkTaskAtPos(int position){
        try {
            Task currTask = getTask(position);
            currTask.unmark();
            return true;
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Task does not exist. Try another number between 0 and " + count + ".");
            return false;
        }
    }

    @Override
    public String toString(){
        String stringedList = "";
        for (int i = 0; i < this.count; i++) {
            stringedList += (i + 1) + ". " + this.taskArray[i].toString() + "\n";
        }
        return "Here are the tasks in your list:\n" + stringedList;
    }
}
