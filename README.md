# Tutorial 4 – Interface (Section B: Apps with Swing GUI)

Java programs on interfaces and polymorphism, each with a Swing GUI.

| Folder | Topic | GUI class | Console class |
|---|---|---|---|
| `Q1_DataSet` | `Measurable` interface, `DataSet`, `BankAccount`, `Coin`, `Student` | `DataSetGUI` | `DataSetApp` |
| `Q2_Edible` | `Edible` interface, `Animal`, `Fruit`, `Apple`, `Orange`, `Person` | `EdibleGUI` | `EdibleApp` |
| `Q3_MobileDevice` | `RadioTuner` interface, `MobileDevice`, `SmartPhone`, `BasicPhone` | `MobileGUI` | `MobileApp` |
| `Q4_Price` | `Price` interface, `Product`, `SmartPhone`, `Laptop`, `Car`, `Customer` | `PriceGUI` | `PriceApp` |

## Requirements

- JDK 8 or newer (tested with JDK 21). Check with `javac -version`.
- A desktop environment (Swing windows need a display).

## How to run

Each folder is a separate program. Open a terminal in the project root, then:

```bash
# Example: Question 1
cd Q1_DataSet
javac *.java
java DataSetGUI        # GUI version
java DataSetApp        # console version
```

Replace the folder and class names for the other questions:

```bash
cd Q2_Edible && javac *.java && java EdibleGUI
cd Q3_MobileDevice && javac *.java && java MobileGUI
cd Q4_Price && javac *.java && java PriceGUI
```

Each GUI has a **Run Sample** (or **Load Sample**) button that reproduces the sample output from the tutorial sheet.

## Running in an IDE

Open the folder of one question as a project in IntelliJ IDEA, Eclipse or VS Code (Java extension), then run the `main` method in the `...GUI` class.
Do not open the whole project root as a single Java project: `Q3_MobileDevice` and `Q4_Price` both contain a class called `SmartPhone`, which would clash.
