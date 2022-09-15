# Conan bot

![Manage your task as smart as Conan](https://www.nicepng.com/png/full/689-6894004_topic-screen-la-fin-de-dtective-conan.png)

## Feature
:rotating_light: Fields in [square bracket] are parameters supplied by the user.

| Command                            | Description                                                      |
|------------------------------------|------------------------------------------------------------------|
| `todo [text]`                      | Add a todo task                                                  |
| `deadline [text] /by [yyyy-mm-dd]` | Add a deadline task                                              |
| `event [text] /at [yyyy-mm-dd]`    | Add a event task                                                 |
| `mark [number]`                    | Mark a task as done, replace number by its index in the list     |
| `unmark [number]`                  | Mark a task as not done, replace number by its index in the list |
| `delete [number]`                  | Delete a task, replace number by its index in the list           |
| `find [keyword]`                   | Find tasks that have the description matching the keyword        |

:star::star::star: Flexible search! 
The `find` command will show all task that match at least 3 characters of your keyword.