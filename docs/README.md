# User Guide

## Overview

Jude the chatbot is a task tracker which helps keep track of todos, deadlines and events. It can be used as a reminder tool to remind you of upcoming or overdue deadlines, as well as upcoming events.

## Features

### Three types of tasks

There are three types of tasks which Jude the chatbot supports.
- Todo tasks: Tasks without a deadline or a start time and end time
- Deadline tasks: Tasks with a deadline
- Event tasks: Tasks with a start and end time

### Track completion of tasks

Tasks can be marked, which means to indicate that the task is complete. Likewise, tasks can be 
unmarked, which means to indicate that the task is incomplete.

### Remind deadlines and events

Upon startup, Jude the chatbot displays a list which includes the following:
- upcoming or overdue deadlines which are yet 
- upcoming events
to ensure that the user does not miss them. Reminders can be 
subsequently triggered by using the command `remindme`.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```


### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

## Supported Date/Time Formats

The supported date formats are:
- 21 Aug 2022 
- Aug 21 2022 2:00PM
- 2022-08-21

The supported time formats are:
- 14:00
- 2:00PM
- 2:00 PM

Note that the format 2PM is not supported.

Date and time inputs are case-insensitive.

In addition, ISO 8601-like dates in the format of 2022-08-21T14:00 are also supported.
