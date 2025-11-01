# Java and SQL 

## Week 1

### OOP (Object Oriented Programming) vs OOD (Object oriented Design): 

       OOPs and OODs are closely related topics, tough they represent different stages in the software developement process.

### Object Oriented Design

        This is a planning face that focuses on structuring your software using Object-oriented principles. During OOD, you define objects, their properties (data), behavior (methods), and relationship between them. 
        Its like creating a blueprint for your code.
### Object Oriented Programming

        These is a implementation stage where you use programming languages like java, kotlin or swift to translate your OOD (Object oriented deisgn) into actual code. 
        You create classes based on your design, write methods to define the object behavior, and establish relationship betweem objects. Its like building a house based on a blueprint.

Here is an analogy to further clarify the difference. 

Imagine you are building a car. In OODs, you'd decide what the car's parts are - engine, wheels, seats - what information each part needs
(engine type, wheel size). and these parts will interact (engine powers wheels). In OOPs, you'd translate this design into code. You would define Engine, Wheel, and Seat class with properties
and methods specific to each part. You'd then write code to connect these objects and make a car function as expected.  

### Class 

        Class is a template from which individual objects are created. A class contains fields and methods.

### Object

        An Object is an intance of a class and it has three characteristics (State, Behavior, and Identity). State represents the date if an object.
        Behavior represents functionality. Identity is assigned by JVM to each objects. 

### Constructor

        A constructor is a special method of a class in the OOP that initializes a new created object, either to default or it is used to define values. 
        Although it resembles a method, a construtor is not a method since it doesn't have any return type. 

### Type of constructor

1. Default
2. Copy
3. Chained
4. Parameterized

        Note: This keyword it refers to the current object in the constructor or a method.

### Parameterized Constructor

This type of constructor has a specific number of parameters. It is used to provide diff values at the initialization. Checks for
correctness during object creation. 

### Copy Constructor

A copy constructor is a special type of constructor that creates an object using another object of the same Java class.
It returns a deep duplicate copy of the existing object of the class. 

```Java

public Student(Student s) {
    this.name = s.name;
    this.age = s.age;
}   

```

### Chained Constructor

Constructor chaining is a process of invoking ibe constructor from another constructor within the same class or parent class. 
This is achieved using this() keyword to call constructor of the same class and the super() keyword to call constructor of the parent class.

Constructor chaining helps in reusing constructor logic, avoiding code duplication, and ensuring consistent initialization of the objects. 
Here for example, using this keyword - this("Maggi") - is calling another parameterized constructor from the default constructor. 
```Java

public class DerivedClass {
    String firstName;
    String country;
    int age;

    public DerivedClass() {
        // calling one argument constructor
        this("Maggie");
    }

    public DerivedClass(String firstName) {
        // calling two argument constructor
        this(firstName, 15);
    }

    public DerivedClass(String firstName, int age) {
        // calling three argument constructor
        this(firstName, age, "Australia");
    }

    public DerivedClass(String firstName, int age, String country) {
        this.firstName = firstName;
        this.age = age;
        this.country = country;
    }
}   

```

In the above example each constructor is calling the next constructor in the chain using this() keyword. 

When dealing with inheritance, super() call ensures that the parent class constructor is executed first.

### Access Specifiers 

There are four types of access specifier 

1. Default: Cannot be access outside the package.
2. Public: Can be accessed outside the package. 
3. Private: Only access inside the class. Not even child class can access it. If you want your child class to access it. You need to set it as protected.
4. Protected: If member is marked as protected, it can be accessed by the members of its own class, its subclass and classes in the same package. However, it cannot be accessed by anyone outside the package.


### Streams and File Handling

Streams and file handling in java are fundamental concepts for performing input and output operations. Java uses streams to handle data flow which can be
categorized into two main types. 

1. Byte stream: for binary data or files like images or audio files 
2. Character streams: for text Data.

#### Binary Streams

Its handles data in raw binary format and are used for working with binary files. They include classes like InputStream and Output Stream, which are superclasses for all byte input and output streams. 
For example, FileInputStream and FileOutputStream are concrete subclasses used to read from and write from files. 


```Java

import java.io.FileInputStream;

public class ByteReadExample {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("example.txt");
        int i;
        while ((i = fis.read()) != -1) {
            System.out.print((char)i);
        }
        fis.close();
    }
}

```

Writing with FileOutputStream

```Java

import java.io.FileOutputStream;

public class ByteWriteExample {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("example.txt");
        String data = "Hello, Java!";
        fos.write(data.getBytes());
        fos.close();
    }
}


```
#### Character Streams

It handles data in form of Characters and are used for Text data. They ensure proper character encoding and decoding. Classes like Reader and Writer are superclasses for all Character input and output stream. 
FileReader and FileWriter are commonly used fir reading and writing. 

Reading using FileReader

```Java

import java.io.FileReader;

public class CharReadExample {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("example.txt");
        int i;
        while ((i = fr.read()) != -1) {
            System.out.print((char)i);
        }
        fr.close();
    }
}

```
Writing with FileWriter

```Java
import java.io.FileWriter;

public class CharWriteExample {
    public static void main(String[] args) throws Exception {
        FileWriter fw = new FileWriter("example.txt");
        fw.write("Hello from FileWriter!");
        fw.close();
    }
}


```

For Better performance we used BufferedReader, BufferedWriter, BufferedInputStream, BufferedOutputStream.

```Java

import java.io.*;

public class BufferedReaderExample {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("example.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}

```

### File Class

It is a part for java.io package and is used to represent file and directories. It includes methods for creating, deleting, and retrieving information 
from files. 


```Java

import java.io.File;

public class FileInfo {
    public static void main(String[] args) {
        File file = new File("example.txt");
        if (file.exists()) {
            System.out.println("File Name: " + file.getName());
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Writable: " + file.canWrite());
            System.out.println("Readable: " + file.canRead());
            System.out.println("File Size: " + file.length());
        } else {
            System.out.println("File does not exist.");
        }
    }
}



```

Exception handling is also very important in File handling

#### Try-catch-finally

```Java

import java.io.FileReader;
import java.io.IOException;

public class TryCatchExample {
    public static void main(String[] args) {
        FileReader reader = null;
        try {
            reader = new FileReader("example.txt");
            int i;
            while ((i = reader.read()) != -1) {
                System.out.print((char)i);
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Failed to close file: " + e.getMessage());
            }
        }
    }
}


```
### Try with resources

Here the Reader and Writer will be closed automatically. 

```Java

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesExample {
   public static void main(String[] args) {
      try (BufferedReader br = new BufferedReader(new FileReader("example.txt"))) {
         String line;
         while ((line = br.readLine()) != null) {
            System.out.println(line);
         }
      } catch (IOException e) {
         System.out.println("File read error: " + e.getMessage());
      }
   }
}


```

### Four pillars of OOPs
### Encapsulation 

         Reduces complexity + Data security: It helps to bind the data and related methods into single unit. It also keeps the data and methods safe from any
         external interface also known as Data hiding. There are no side effects  of this code  on the rest of the application.

Encapsulation and Data hiding are not same. Encapsulation unable data hiding. Data hiding can be achieved using Access modifiers. 

#### Steps for proper encapsulation 

1. Restrict Access 
2. know the bound of values (e.g in case of a bank account you cannot allow negative balance).
3. Initialize data elements to right initial values. 
4. Choose data types wisely
5. Validate the input while modifying/setting the data.

```Java

public class BankAccount {
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        }
    }

    // Getter for accountHolder
    public String getAccountHolder() {
        return accountHolder;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Deposit method with validation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method with validation
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Invalid or insufficient balance.");
        }
    }
}


```



 








