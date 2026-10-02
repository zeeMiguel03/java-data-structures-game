# Tactical Mission Simulator — Data Structures Project

Academic Java project developed for the **Data Structures** course in the Computer Engineering degree at ESTG / Polytechnic of Porto.

The project combines custom implementations of fundamental data structures with a console-based tactical mission simulation. The game models a building as a graph, loads scenarios from JSON files, and uses graph algorithms to support navigation and route calculation.

## Highlights

- Custom implementations of fundamental data structures
- Graph and weighted network representations using adjacency matrices
- Breadth-First Search (BFS) and Depth-First Search (DFS)
- Shortest-path calculation based on Dijkstra's algorithm
- Manual and automatic game modes
- JSON-based scenario loading
- Object-oriented design using interfaces, implementations, enums, and custom exceptions
- Javadoc documentation across the project

## Data Structures

The project includes implementations and abstractions for:

- Lists
- Queues
- Stacks
- Trees
- Priority queues
- Graphs
- Weighted networks

The main data-structure code is located under `src/Collections`.

## Application Structure

```text
src/
├── Collections/
│   ├── Exceptions/
│   ├── Graph/
│   ├── Lists/
│   ├── Queues/
│   ├── Stacks/
│   └── Trees/
├── Game/
│   ├── Enums/
│   ├── Exceptions/
│   ├── GameImpl/
│   ├── Interfaces/
│   ├── Json/
│   └── Reports/
└── GameMenus/
```

## Technologies

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![JSON](https://img.shields.io/badge/JSON-000000?style=flat-square&logo=json&logoColor=white)
![Apache Ant](https://img.shields.io/badge/Apache_Ant-A81C7D?style=flat-square&logo=apacheant&logoColor=white)

- Java
- Apache Ant / NetBeans project structure
- JSON Simple 1.1.1

## Running the Project

The repository contains the NetBeans / Apache Ant project configuration and the required `json-simple` dependency under `libs/`.

With Apache Ant installed, the project can be built from the repository root using:

```bash
ant clean
ant jar
```

It can also be opened directly as a Java project in Apache NetBeans.

## Academic Context

This project was developed collaboratively by:

- **António Miguel Cunha Monteiro** — 8230230
- **José Miguel Monteiro da Rocha** — 8230238

It was created as an academic project for the Data Structures course at ESTG.

## Shortest Path References

The shortest-path implementation was developed with support from course materials and the following learning resources:

- W3Schools — Graphs and Dijkstra's algorithm
- GeeksforGeeks — Dijkstra's shortest-path algorithm in Java
- Software Testing Help — Dijkstra's algorithm in Java

These references were used as learning material while implementing and adapting the algorithm to the project's own graph/network structures.
