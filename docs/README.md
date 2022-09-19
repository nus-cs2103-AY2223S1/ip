# User Guide for `Dukie` 🐥

Your very own task tracker!  ✏️

## Features 
1. Add tasks to your to-do list easily 
2. Separate tasks into `todo`, `deadline`, and `event` 
3. `Mark` tasks when they are done 
4. See your `Schedule` for the day!
5. `Find` tasks 
6. `Snooze` tasks if they need a little longer to complete  ⏰

## Commands

### ✏️ `Todo`

Adds a to-do task to your task list. 

Format: `todo <taskName>` 

Example: `todo ip` 

Remarks: 
- `todo` items do not have a date tied to them! 
- for items with specified dates, use `deadline` or `event` 

### 💣 `Deadline`

Adds a deadline task to your task list. 

Format: `deadline <taskName> /by <date>`

Example: `deadline ip /by 2022-09-16`

Remarks: 
- date must be in the format **YYYY-MM-DD**

### 📆 `Event`

Adds an event task to your task list.

Format: `event <taskName> /at <date>`

Example: `event holiday /at 2022-12-20`

Remarks:
- date must be in the format **YYYY-MM-DD**

### ✅ `Mark` / ❎ `Unmark`

Marks a task as done/undone. 

Format: `mark <taskName>` / `unmark <taskName>`

Examples: 
- `mark ip`
- `unmark hw`

Remarks: 
- tasks are unmarked by default upon creation 

### 📃 `List` 

Shows the complete list of tasks in the task list. 

Format: `list`

Expected outcome:

```
1. [E][ ] holiday 2022-12-20
2. [D][X] ip 2022-09-16
3. [T][ ] study for midterms
```

### 🗑️ `Delete` 

Deletes a task that is in the task list. 

Format: `delete <taskNumber>` 

Example: `delete 1`

Remarks: 
- first item in the task list will be deleted 
- remaining tasks will be updated with the new index number 

### 🔍 `Find` 

Searches task list for tasks that contain given keyword. 

Format: `Find <taskName>` 

Example' `Find hw`

### 📅 `Schedule` 

Generates all `deadline`s and `event`s for that given date.

Format: `schedule <date>`

Example: `schedule 2022-09-18`

### ⏰ `Snooze` 

Updates the date of a `deadline` or `event` without changing other properties. 

Format: `snooze <taskName> /to <date>`

Example: `snooze ip /to 2022-09-19`

### 👋 `Bye`

Exits Dukie. 

Format: 'bye'

--- 

byebye!