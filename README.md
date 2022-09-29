# Renaissance Duke

> All new original Duke with a twist 

Renaissance Duke is a very **perculiar** chat bot that helps you keep track of your daily task! 🙃

Features:
- Differation of Task
- Search by date
- Search by Keyword
- Load & Save 
- And… more!

Commands:
1. date (search by date)
2. find (search by keyword)
3. deadline (add a dateline)
4. event (add a event)
5. todo (add a todo)
6. delete (delete a task)
7. list (list all the current task)
8. load (loads a task from file)
9. save (saves a task into file)
10. mark (marks the task as done)
11. unmark (unmarks the task as done)
12. exit (exits Duke) 

Download it [here](https://github.com/onepersonhere/ip/releases/tag/A-Jar)!

Future plans:

 - [x]  Managing more tasks
 - [ ] Reminder
 - [ ] Multiple language support
 - [ ] Voiceover
__________

To add more `Commands` to Renaissance Duke, do extend this `abstract` class:
```java
 public abstract class Command {
	protected boolean isExit = false;

    /**
     * Sets the exit flag.
     *
     * @return true if the exit flag is set, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command.
     *
     * @param ui the user interface
     * @param storageList the storage list
     * @throws DukeException if an error occurs
     */
    public abstract void execute(Ui ui, StorageList storageList) throws DukeException;
}
```
