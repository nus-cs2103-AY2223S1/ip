# Duke
> “Your mind is for having ideas, not holding them.” – David Allen

A project which frees your mind of holding to the tasks you need to do. Duke is
- Text-based: Suitable for people who type fast
- User friendly: easy to remember commands
- **FREE** to use

All you need to do is:
1. Download lastest version of Duke from [here](https://github.com/ngquyduc/ip/releases).
2. Run Duke.
3. Add your tasks and you are good to go.


Try to get yourself familiar with Sheep
- [ ] `todo Do something` to add a todo task 
- [ ] `deadline Deadline /by 2022-08-31` to add add deadline 
- [ ] `event Event /at 2022-08-31` to add an event 
- [ ] `list` to list out all the tasks 
- [ ] `mark 1` to mark the 1st task as done 
- [ ] `unmark 1` to unmark the 1st task 
- [ ] `delete 1` to delete the 1st task 
- [ ] `bye` to close Duke and store all ongoing tasks :wave:

If you want to load a list of tasks, you can load it by replacing ./data/tasks.txt with your file path in main() of file Duke.java

```
public static void main(String[] args) {
    new Duke("./data/tasks.txt").run();
}
```

Hope you have good experience with Duke!!!
