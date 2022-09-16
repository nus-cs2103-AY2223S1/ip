# Duke: A Quick Simple Task Manager

## Features

Three types of Tasks: Todos, Deadlines, Events.
All tasks have a description and a marker.
Deadlines and Events have a time indicating the due date and event date respectively.

Usage of the marker is up to you!
Possible uses are to mark completed tasks or important tasks.

### Storage
All tasks are automatically saved at `/data/duke.txt` and loaded from the same file on startup.

Advanced users may directly edit the savefile.

**Caution!** If the edited data is of a different format, the file will be **overwritten** and data lost.

## Usage

### `todo <description>` Adds a Todo task

Usage:
`todo practice piano`


```
1.[T][ ] practice piano
```

### `deadline <description> /by <time>` Adds a deadline task
`<time>` can be arbitrarily specified or preferably
in the form `YYYY-MM-DD HHMM` or `YYYY-MM-DD`.
The latter defaults to a time of midnight (`0000`).


Usage:
`deadline calculus assignment /by 2022-09-27 0800`

```
2.[D][ ] calculus assignment (by: Sep 27 2022 0800)
```

`deadline algebra homework /by Thursday`

```
3.[D][ ] algebra homework (by: Thursday)
```

### `event <description> /at <time>` Adds an event task
`<time>` can be arbitrarily specified or preferably 
in the form `YYYY-MM-DD HHMM` or `YYYY-MM-DD`.
The latter defaults to a time of midnight (`0000`).



Usage:
`event AI Seminar /at 2022-10-13 1900`

```
4.[E][ ] AI Seminar (at: Oct 13 2022 1900)
```

`event AI Test /at Friday 2pm`

```
5.[E][ ] AI Test (at: Friday 2pm)
```

### `mark <index>`, `unmark <index>` Marks or Unmarks the task specified by the index

Usage:
`mark 1` : Marks the first task in the list as completed

```
1.[T][X] practice piano
```

### `list` Displays a list of the current tasks

Usage:
`list`

```
1.[T][X] practice piano
2.[D][ ] calculus assignment (by: Sep 27 2022 0800)
3.[D][ ] algebra homework (by: Thursday)
4.[E][ ] AI Seminar (at: Oct 13 2022 1900)
5.[E][ ] AI Test (at: Friday 2pm)
```

### `find` Searches and returns tasks that contain a keyword

Usage:
`find piano`

```
1.[T][x] practice piano
```

### `remove <indices...>` Removes the tasks specified by one or more indices

Usage:
`remove 1 2 4`
`list`

```
1.[E][ ] AI Seminar (at: Oct 13 2022 1900)
2.[E][ ] AI Test (at: Friday 2pm)
```