# User Guide to Duke

Duke is a chatbot which allows you to manage tasks, namely:
- Note down new todos, deadlines and events
- Mark off completed tasks one by one
- Get quick reminders for the next week ahead

### Task Types

There are three different types of tasks, namely **Todo**, **Deadline** and **Event**.

Examples of tasks look like this:

`[T][ ] Return book`

`[D][X] Finish assignment by: 17 Aug 2023`

`[E][ ] Attend lecture at: 9 Jan 2022`

From left to right:

- `[T]`, `[D]`, `[E]` denote a **Todo**, **Deadline**, or **Event** task respectively
- `[ ]`, `[X]` denote an **Active** or **Completed** task
- The text in the middle denotes the **Description** of the task
- **Deadline** and **Event** tasks additionally have a `by` or `at` date respectively

## Features

### Add Todo
Format: `todo` `<description>`

This will create a **Todo** Task with the supplied description.

### Add Deadline
Format: `deadline` `<description>` `/by` `<date>`

This will create a **Deadline** Task with the supplied description.

***Date must be supplied in the format YYYY-MM-DD***

### Add Event
Format: `event` `<description>` `/at` `<date>`

This will create an **Event** Task with the supplied description.

***Date must be supplied in the format YYYY-MM-DD***

### List
Format: `list`

List will cause Duke to list your currently saved Tasks numbered from 1 onward.

### Mark Task Completion
Format: `mark` `<index>`

This will toggle the completed status of the task at the given index,
the order of which is determined in `list`.

### Delete Task
Format: `mark` `<index>`

This will delete the task at the given index,
the order of which is determined in `list`.

### Find Tasks with Regex
Format: `find` `<regex>`

This will cause Duke to list the Tasks which match the supplied regex.

### Remind of Upcoming Tasks
Format: `remind`

This will cause Duke to list the Tasks whose **by**/**at** dates fall
within a fortnight of the current day.

### Exiting the App
Format: `bye`

This will close the app.

