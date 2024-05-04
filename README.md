##  Small frontend application for managing a todo list.
######   Test task description: a candidate should implement a small frontend application for managing a todo list.

# [Click here to tackle website](https://dimastepul.github.io/supreme-palm-tree/)

### Functionality:
1) Creation of todo items
2) Marking todo items as completed
3) Removing todo items
4) Filtering the todo list by completed or incomplete items

### Technologies:
1) Language: Kotlin
2) Rendering library - Compose Multiplatform for web


### How to run 

#### Docker 
execute from the root folder of repo
```bash
docker build -t todolist  . 
```
```bash
docker run -p 8080:8080 todolist
```

#### Alternative 
You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task
or type in terminal `./gradlew :composeApp:wasmJsRun`
