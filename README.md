# B3R Tree Implementation
## 📋 Description
This repository contains the implementation of a B3R Tree as the final task for the subject Data Structures.

## 🚀 Features
- **Insertion**: Add keys while maintaining balance.
- **Search**: Efficient key lookup.
- **Deletion**: Remove keys while preserving tree balance.
- **Maximum**: Get the highest key stored in the tree.
- **Minimum**: Get the lowest key stored in the tree.
- **Visualization**: Graphical representation of the tree.

## 🛠️ Technologies Used
- **Language**: Java.
- **Paradigm**: Object-Oriented Programming.

## 📂 Repository Structure
```
├──diagrams/
│   └── ...                        # Corresponding Images and pdfs
├── docs/                          # Javadoc documentation
│   ├── index.html                 # Main Javadoc entry point
│   └── ...                        # Other Javadoc-generated files
├── src/
│   ├── main/
│   │   ├── B3RTree.java           # Main B3R Tree class
│   │   └── Node.java              # Node representation class
│   ├─ App                         # Test class with usage examples
│   ├─ tree.txt                    # .txt containing operations to test the tree
│   └── tree2.txt                  # another test .txt
├── .gitignore                    
└── README.md                      # This file
```
## 📖 Installation and Usage
1. Clone the repository:
    ```bash
    git clone https://github.com/ByGurixD095/B3R-Tree.git
    ```

2. Compile the code:
    ```bash
    cd repository-name
    # Replace with your specific compilation command, e.g., if using Java:
    javac src/*.java
    ```

3. Run the main class:
    ```bash
    java App
    ```
4. Test Instructions:
   When running the App, it will ask for .txt files containted in the same source dir as the App.java, will test the tree while entering files,
   otherwise, will leave the app after blank filename.
5. Modify test:
   If want to try other operations here are explained how do they work and what you can do:
   ## Comandos

- `c` - Crea un árbol B3R vacío
- `i n` - Inserta el valor `n`
- `mn` - Obtiene y muestra el valor mínimo
- `mx` - Obtiene y muestra el valor máximo
- `s` - Representa el árbol como una cadena y lo muestra
- `nn` - Muestra el número de nodos
- `nk` - Muestra el número de claves
- `b n` - Indica si el valor de la clave `n` está en el árbol

## Ejemplo de uso

```text
c
i 5
i 15
i 25
i 30
nn
nk
i -5
i -15
s
b 15
b 7
i 123
mn
mx
s
```

## 📊 Project Status
- **Node Class**: Already implemented. Changed it in the last version, in order to handle the keys directly from the tree.
- **Tree Class**: All implemented but deletion method.
