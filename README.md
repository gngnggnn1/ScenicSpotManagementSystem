Scenic Spot Management System
Overview
The Scenic Spot Management System is a Java-based project developed as a final project for a Data Structure training course. It implements various data structures and algorithms to manage scenic spots, such as tourist attractions, efficiently.
Features

Graph-based Navigation: Utilizes graph data structures to represent scenic spots and their connections, enabling efficient pathfinding.
Data Management: Stores and manages information about scenic spots using arrays, stacks, and queues.
Pathfinding Algorithms: Implements Dijkstra's algorithm for finding the shortest path between scenic spots.
Tour Planning: Uses the Hamilton algorithm to create optimal tour routes visiting multiple scenic spots.
Search Functionality: Incorporates Depth-First Search (DFS) for exploring scenic spot connections.

Data Structures Used

Graph: Represents scenic spots as nodes and paths as edges.
Array: Stores lists of scenic spots and their attributes.
Stack: Supports backtracking for search and pathfinding.
Queue: Manages tasks like processing scenic spot visits in order.

Algorithms Implemented

Dijkstra's Algorithm: Finds the shortest path between two scenic spots.
Hamilton Algorithm: Determines an optimal route that visits each scenic spot exactly once.
Depth-First Search (DFS): Explores all possible paths through the scenic spot network.

Prerequisites

Java Development Kit (JDK) 8 or higher
An IDE such as IntelliJ IDEA, Eclipse, or NetBeans (optional but recommended)

Installation

Clone the repository:git clone https://github.com/gngnggnn1/ScenicSpotManagementSystem.git


Navigate to the project directory:cd ScenicSpotManagementSystem


Compile and run the project using your preferred IDE or via the command line:javac *.java
java Main



Usage

Add Scenic Spots: Input details about scenic spots, such as name, location, and description.
Connect Spots: Define paths between scenic spots with distances or travel times.
Find Shortest Path: Use Dijkstra's algorithm to find the shortest route between two spots.
Plan a Tour: Generate a tour that visits all scenic spots using the Hamilton algorithm.
Explore Connections: Use DFS to explore all possible paths from a given scenic spot.

Contributing
Contributions are welcome! Please follow these steps:

Fork the repository.
Create a new branch (git checkout -b feature-branch).
Make your changes and commit (git commit -m 'Add new feature').
Push to the branch (git push origin feature-branch).
Create a pull request.

License
This project is licensed under the MIT License. See the LICENSE file for details.
Contact
For questions or suggestions, please open an issue on the GitHub repository or contact the project maintainer at guangning.li@outlook.com.
