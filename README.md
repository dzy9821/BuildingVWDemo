## Demo for Building Violation Witnesses from Error-Triggering Test Suites
This is a demo version of a command-line tool that I created based on my understanding of the topic. Currently, this tool supports only very limited inputs. For example, you can find sample inputs and outputs in the `example` folder.
Hardcoding is used in many places.

Currently,the tool has been developed and tested in a Windows environment.
## The workflow of the program
Currently, the tool requires three inputs: `metadata.xml`, `testcase.xml`, and the C program to be executed, such as `example.c.`


In the `main` method, the program first calls the `parseMetadata()` method from the `XMLParser` class. This method parses the properties for building the witness from the `metadata.xml` file and returns a `Metadata` entity.


The program then calls the `parseTestCase()` method from the `XMLParser` class, which parses the sequence of inputs provided to the program.


Next, the program uses the `CCodeHandler` class to insert code lines into the C program that needs to be verified, in order to record the program's execution process. At this stage, a modified file named `modifiedFile.c` is output in the current directory. 
The program then compiles `modifiedFile.c`, producing an executable file named `executableFile`.


Now that we have all the necessary information to construct the witness, the program builds the witness in the `WitnessHandler` and outputs it as `witness.yml`.

## How to Use This Tool

You can find an executable JAR file named `BuildingVWDemo.jar` and use the following command: 

`java -jar BuildingVWDemo.jar -c path\to\example.c -m path\to\metadata.xml -t path\to\testcase-1.xml`
