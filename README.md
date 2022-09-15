# Duke Aemon Of Old
> “You can do anything, but not everything.” – David Allen ([source](https://dansilvestre.com/productivity-quotes/))

**Duke Aemon Of Old** :crown: has ~~broken out of~~ risen from the crypts _centuries_ after centuries of peace. No one knows why. Having come to the castle to investigate this mystery, you realize Aemon has excellent memory. As you fish for clues, use Duke Aemon to:

- [X] Jot down quick Todos
- [X] Pen events with dates and duration
- [X] Note important tasks with their deadlines
- [ ] Solve the puzzle of Aemon's return (Coming Soon!)

Run Duke by calling 


Here is how you can talk to Aemon:
1. Run Duke.java
2. Write a task description following `todo ` to save a Todo task
3. Write a task description and date with time (formatted as **description/yyyy-MM-dd HH:mm**) following `deadline ` to store a Deadline-based task
4. Write a task description and date with start and end times (formatted as **description/yyyy-MM-dd HH:mm HH:mm**) following `event ` to store an Event
5. Type `mark ` followed by the task number to mark a task as complete. Alternatively, use `unmark ` instead to mark it as incomplete
6. Type in `find ` followed by a keyword to see only the tasks containing the keyword
7. Type in `list ` to see all your saved tasks!

Play around with the main method to call other functions of Aemon like `echoAndExit` and learn more about your philosophical assistant born before Time.

```java
public static void main(String[] args) {
       Duke AemonT = new Duke();
       AemonT.interact();
    }
```
Why struggle with organizing a thousand tasks when you have a thousand-year-old friend in Aemon? With him, it is:
- **EASY** to plan your day
- **SIMPLE** to make changes
- **FUN** to live an organized life!
