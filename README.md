# User Guide

Feel like your life is in a mess? Too many tasks to keep track of? Perhaps you're taking CS2103 this semester?

> "Yesterday is history, tomorrow is a mystery, but today is a gift. That's why it is called the present" - Master Oogway [(Source)](https://www.goodreads.com/author/quotes/7178454.Master_Oogway)

Fret not, Duke is an interactive chat bot that is going to organise your life.

- Text-based
- Easy to learn
- ~~Fast~~ ***SUPER FAST*** to use!

All you need to do is,

1. Download it here
2. Double click it
3. Done!

## Features 

### Keep track of different types of tasks.

Duke supports the following tasks:
- [x] Generic ```todo``` tasks;
- [x] A ```deadline``` to adhere to;
- [x] An ```event``` along with its time!

### Tag tasks to keep track of what's most important.

You can also ask Duke to tag certain tasks with as many tags as you need to keep your life organised. You can
- [x] Add tags to any task;
- [x] Delete a tag from a task;
- [x] Search for all tasks with a specific tag!

Isn't that cool!

> :bulb: Tip: Commands are not case sensitive. That means Duke interprets `hello` and `Hello` as the same command!

## Usage

### Greeting Duke - `hello`

Start off your day with a friendly greeting from Duke!

**Example of usage:**

```
hello
> Hello! I'm Duke.
> What can I do for you?
```

### Saving your tasks - `bye` 

You can have Duke remember all of your tasks at the end of a long day. Kick back, relax, and Duke allows you to pick up where you left off.

**Example of usage:**

```
bye
> Bye! I have saved your tasks. 
> Hope to see you again soon! :)
```

### Listing all your tasks - `list`

Use the command ```list``` to have Duke show you all the tasks you have.

```
list
> Here are the tasks I found:
> 1: [T] [ ] Look for internships
> 2: [D] [✔] Complete CS2103 iP tasks
> (by: 16 Sep 2022 11:59 PM)
> 3: [E] [ ] Project meeting for CS2103 tP
> (at: 17 Sep 2022 08:00 PM)
```

### Adding a todo task - `todo`

Adds a `todo` task to your task list.

Format: `todo TASK_NAME` to add a task with the specified `TASK_NAME` to your list.

**Example of usage:**

```
todo Look for internships
> Got it. I've added this task:
> [T] [ ] Look for internships
> Now you have 1 task in the list.
```

### Adding a deadline - `deadline`

Adds an `deadline` task to your task list.

Format: `deadline TASK_NAME /by DATE TIME` to add a deadline named `TASK_NAME` at a specified `DATE` and `TIME` to your list.

####Date format: `dd-MM-yyyy`
####Time format: `HHmm`

**Example of usage:**

```
deadline Complete CS2103 iP tasks /by 16-09-2022 2359
> Got it. I've added this task:
> [D] [ ] Complete CS2103 iP tasks
> (by: 16 Sep 2022 11:59 PM)
> Now you have 2 tasks in the list.
```

### Adding an event - `event`

Adds an `event` task to your task list.

Format: `event TASK_NAME /at DATE TIME` to add an event named `TASK_NAME` at a specified `DATE` and `TIME` to your list.

####Date format: `dd-MM-yyyy`
####Time format: `HHmm`

**Example of usage:**

```
event Project meeting for CS2103 tP /at 17-09-2022 2000
> Got it. I've added this task:
> [E] [ ] Project meeting for CS2103 tP
> (at: 17 Sep 2022 08:00 PM)
> Now you have 3 tasks in the list.
```

### Marking a task as done - `mark`

Marks a task at a given `INDEX` as done.

Format: `mark INDEX`

> :bulb: Tip: Use the `list` command to quickly find the index of any task!

**Example of usage:**

```
mark 1
> Nice! I've marked this task as done.
> [T] [✔] Look for internships
```

### Marking a task as not done - `unmark`

Marks a task at a given `INDEX` as not done.

Format: `unmark INDEX`

**Example of usage:**

```
unmark 1
> Alright! I've unmarked this task:
> [T] [ ] Look for internships
```

### Deleting a task - `delete`

Deletes a task at a given `INDEX`

Format: `delete INDEX`

> :warning: Warning: This action cannot be undone! Deleted tasks cannot be retrieved anymore, so be careful when using this command!


**Example of usage:**

```
delete 1
> Noted, I've removed this task:
> [T] [ ] Look for internships
> Now you have 2 tasks in your list.

list
> Here are the tasks I found
> 1: [D] [✔] Complete CS2103 iP tasks
> (by: 16 Sep 2022 11:59 PM)
> 2: [E] [ ] Project meeting for CS2103 tP
> (at: 17 Sep 2022 08:00 PM)
```

### Listing tasks with a fixed due date - `deadlines`

Finds all tasks that are due at a given `DATE`.

> :warning: Warning: Not to be confused with `deadline`!

Format: `deadlines DATE`

####Date format: `dd-MM-yyyy`

**Example of usage:**

```
deadlines 16-09-2022
> Here are the tasks I found:
> 2: [D] [✔] Complete CS2103 iP tasks
> (by: 16 Sep 2022 11:59 PM)
```

### Finding tasks by keyword - `find`

Finds all tasks that contain a given `KEYWORD`. The search is case-sensitive.

Format: `find KEYWORD`

**Example of usage:**

```
find CS2103
> Here are the tasks I found:
> 2. [D] [ ] Complete CS2103 iP tasks
> (by: 16 Sep 2022 11:59 PM)
> 3. [E] [ ] Project meeting for CS2103 tP
> (at: 17 Sep 2022 08:00 PM)
```

### Adding a tag to a task - `tag`

Tags a task at a given `INDEX` with a tag named `TAG_STRING`.

Format: `tag INDEX TAG_STRING`
> :exclamation: Note: Duke only accepts tags that are ***one word*** long. If more than one word is passed, Duke will retrieve the first word and ignore the rest.

**Example of usage:**

```
tag 1 Urgent!
> Noted, I've tagged the task:
> [T] [ ] Look for internships
> Tag: [Urgent!]
```

### Deleting a tag to a task - `untag`

Removes the tag named `TAG_STRING` from a task at a given `INDEX`.

Format: `untag INDEX TAG_STRING`

**Example of usage:**

```
untag 1 Urgent!
> Noted, I've removed the tag to this task:
> [T] [ ] Look for internships
```

### Finding tasks by tag - `findtag`

Finds all tasks that are tagged with a given `TAG_STRING`. The search is case-sensitive.

Format: `findtag TAG_STRING`

**Example of usage:**

```
list
> Here are the tasks I found:
> 1: [T] [ ] Look for internships
> Tag: [Urgent!]
> 2: [D] [✔] Complete CS2103 iP tasks
> (by: 16 Sep 2022 11:59 PM)
> 3: [E] [ ] Project meeting for CS2103 tP
> Tag: [Urgent!]
> (at: 17 Sep 2022 08:00 PM)

findtag Urgent!
> 1: [T] [ ] Look for internships
> Tag: [Urgent!]
> 3: [E] [ ] Project meeting for CS2103 tP
> Tag: [Urgent!]
> (at: 17 Sep 2022 08:00 PM)
```

And that is all! Look out at this page for any further exciting updates. Be a master of your time, use Duke today! :+1:
