# CORBA-project

###### Understanding the structure

This project is divided in some maven modules:

* The ui modules:
    - gate-ui: This is the module representing the gate of the museum
    - watchman-ui: This is the module representing the watchman/guardian of the museum
    - bell-ui: This is the module representing the bell/alarm

* The CORBA interfaces modules:
    - bell: This module have the bell interface (Bell.idl)
        - bell-client: Module used to generate only the client classes from the CORBA interface.
        - bell-server: Module used to generate all classes from the CORBA interface.

    - watchman: This module have the watchman interface (Watchman.idl)
         - watchman-client: Module used to generate only the client classes from the CORBA interface.
         - watchman-server: Module used to generate all classes from the CORBA interface.

###### Dependencies

    - Java 8
    - Maven 3
    - CORBA (the project uses the idlj compiler that comes with jdk)

###### Hands on

To make it works works, you need to install the module **corba-interfaces**:
    `cd corba-interfaces && mvn install`

After this command the CORBA interfaces, inside bell and watchman folders, will be compiled and the java classes
will be generated and installed as a java library.
Now just refresh the UI modules in your IDE to make sure the the classes are correctly imported by them.

After that, initiate the Naming Service with: `tnameserv`

Now you can run the UI in this order:

    1. bell-ui:
        * Run the class *App.java* inside the *br.edu.ifce.corba.main*
    2. watchman-ui:
        * Run the class *App.java* inside the *br.edu.ifce.corba.main*
    3. gate-ui:
        * Run the class *App.java* inside the *br.edu.ifce.corba.main*

You will need to follow this order because the watchman is dependent on the bell, and the gate is dependent on the
watchman, so make sure you follow the order.

If you want to run the naming service in another port, just run: `tnameserv -ORBInitialPort 9001`

and make sure you run the App.java with the arguments `-ORBInitialPort` and `-ORBInitialHost`

