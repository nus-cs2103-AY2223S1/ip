# Amanda Lite

> "If im nice to you, you will never finish your work.! - Amanda"

Amanda makes you feel bad about yourself, so you'll be better. She's

- Passive Agressive
- Hate people
- ~~LOVES TO~~ _DO_ HER JOB

All you need to do is,

1. download her from [here]().
2. double-click.
3. add your tasks.
4. let her manage your tasks for you 😉

And it is __FREE__!
###Features

- [x] Managing tasks
- [ ] Managing deadlines
- [ ] Reminders (coming soon)
---
If you Java programmer, you can use it to practice Java too. Here's the main method:
```Java
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
```

## USERGUIDE

---

#### FEATURES

---

##### Creating a To-Do Task
Command: todo [task]

Example: todo drink milk

---

##### Creating a Deadline Task
Command: deadline [task] /by [deadline]

__Deadline format: DD-MM-YYYY-HH:MM__

Example: deadline eat dinner /by 01-01-2023-23:59

---

##### Creating a Event Task
Command: event [task] /at [deadline]

__Deadline format: DD-MM-YYYY-HH:MM__

Example: event concert /at 01-01-2023-23:59

---

##### Adding a Tag to the Task
Command: tag [description] [index]
__Description format: no spaces__
Example: tag this_is_fun 2

---

##### Mark Task as Done
Command: mark [index]
Example: mark 3

---

##### Un-mark Task as not done
Command: unmark [index]
Example: unmark 3

---

##### Delete task
Command: delete [index]
Example: delete 3

---

##### List all tasks
Command: list

---

##### List all tags associated with a Task
Command: listtag [index]
Example: listtag 3
