# Assignment1_OOP
Written by:
1. Michal Elisha
2. Shlomit Ashkenazi

In this assignment, we extended the abilities and applications of UndoableStringBuilder (which we have built in the previous assignment):
  1. At the first part we implemented the Observer design as we learned in class
  2. At the second part we debuged and analysed our program using JVM

# Getting Started
*Rerquirements - IntelliJ enviorment*

1.Download our project as ZIP file
2.extract it localy
3.open IntelliJ
3.on the top right press File->new->project from existing sources:

![image](https://user-images.githubusercontent.com/42152443/210076094-10179040-735f-4af5-b15b-28d1fcc26a4c.png)

4.nevigate to the path to our project folder:

![image](https://user-images.githubusercontent.com/42152443/210076190-91b23a11-7518-4eb5-83f3-46af2ae0122f.png)

5. press Next on the following:

![image](https://user-images.githubusercontent.com/42152443/210076270-66c83781-2034-40ef-a999-8efd742b617b.png)
![image](https://user-images.githubusercontent.com/42152443/210076284-9f50497e-68f2-4864-aef5-104d4bb8169c.png)
![image](https://user-images.githubusercontent.com/42152443/210076306-5451d701-95f5-41c0-8df4-233328095f9a.png)
![image](https://user-images.githubusercontent.com/42152443/210076323-259ab30e-2aca-47d8-88ff-eacd509e1a4c.png)
![image](https://user-images.githubusercontent.com/42152443/210076372-97673730-f2e4-418d-960f-6a2a0661ed21.png)
![image](https://user-images.githubusercontent.com/42152443/210076405-567b3f5f-d1af-47fc-a646-7cd2025672ea.png)
![image](https://user-images.githubusercontent.com/42152443/210076424-dc937216-9967-415a-a94e-8969e0137b3a.png)

6. then finally the extraction will end:

![image](https://user-images.githubusercontent.com/42152443/210076519-417a21fa-73b7-4ee6-a65e-346514906ef3.png)

you can see the project now. (should look like this):

![image](https://user-images.githubusercontent.com/42152443/210077082-9681d7d0-1090-44ca-8f82-cae61d609669.png)


Run&Debug:
if you are not familiar with IntelliJ: 
DEBUG - https://www.jetbrains.com/help/idea/debugging-your-first-java-application.html
RUN - https://www.jetbrains.com/help/idea/running-applications.html
COMPILE - https://www.jetbrains.com/help/idea/compiling-applications.html

### Run and Debug Tests:
when you get in the Tests.java class, if you are interested to see the "behind the scences" of a test function , click on the left of the first row of the method and press "debug".
in this example, we debugged the concreteMemberTest() method:

* initialization of two concrete members
  i![initialize members](https://user-images.githubusercontent.com/91603335/210090936-9ee81b00-bc19-4564-b75b-ab18d1a488bc.png)
  
* initialization of g - object  of type groupAdmin 

  ![initialize groupAdmin'](https://user-images.githubusercontent.com/91603335/210091974-f8977392-5555-4458-9553-1ebc4c072991.png)
    
* groupAdmin after registration on the first concrete member
  
  ![groupAdmin after registration of one member](https://user-images.githubusercontent.com/91603335/210090745-7c28612f-3f21-46ee-a401-6305635c14c2.png)


* assert the modify() and update() functions - checking the value of usb of the members
  
  ![checking the value of usb of memebers](https://user-images.githubusercontent.com/91603335/210090478-a2bff75b-6fe2-4e63-b422-3565fc32b7b4.png)
  
* assert  the modify() and update() functions and also the undo() function from UndoableStringBuilder class:
 
  ![list of members after undo() and modify](https://user-images.githubusercontent.com/91603335/210091602-90a70ce5-ab0a-4ae2-b053-8047f4c40c9a.png)


# Part 1 - Extend UndoableStringBuilder with Observable Design
we have created a UML to explain how we implemented this part:

![image](https://user-images.githubusercontent.com/42152443/210070552-c0ca8429-d950-4b72-ac26-9b3ea0d4c20e.png)

1. as you can see the 'observer' == 'Member' an interface that's being implemented by ConcreteMember (and therefore the 'update' method is implemented at ConcreteMember - although we didn't state it explicitly in the UML).
2. by this analogy, 'observable' == 'Sender' an interface that's being  implemented by GroupAdmin (also here all methods mentioned at 'Sender' are actually being implemented at 'GroupAdmin').

# Part 2 - Testing using JVM

the goal of JVM is to show us how much memory the data structures we use takes, so we can track it and try to optimize the code's efficiency.

the totalsize method on initialized concrete  members and the groupAdmin class  before the registration shows us the following data:

![info_totalsize_initialize](https://user-images.githubusercontent.com/91603335/210088729-68bd7158-70a9-4e07-a3ea-00830fa65c85.png)


the Footprint method on the groupAdmin class  before the registration shows us the following data:

![footprint_groupAdmin_pre](https://user-images.githubusercontent.com/91603335/210088849-44413b1f-d1c7-435b-ad04-84cef6c10a3f.png)


the Footprint method on the concrete members after the update (modify() method in groupAdmin )  shows us the following data:

![footprint_member](https://user-images.githubusercontent.com/91603335/210086393-bed18c5c-4936-417f-bd56-a1de0d6c3e13.png)


the Footprint method on the groupAdmin class after the update (modify() method in groupAdmin )  shows us the following data:

![info_totalsize_foot_groupAdmin](https://user-images.githubusercontent.com/91603335/210087291-26d4e821-3942-4b63-b752-97858952e371.png)

we can see that the list now contains the members, and so the memory allocated for the list and for groupAdmin has increased


*note:
in order to use the JVM capabilities, we used Logger as Natan (the teacher) explained to us at his SHEUT KABALA (Link - https://ariel-ac-il.zoom.us/rec/play/DISm8hWeT72rigLJrvBO7qyvMXBhaZOoXEMBSOLd4jmxMoqpF1srWUnfS-MlHVGOznSDSOlv7aA9E44C.8yjo9No50jJwHQWW?continueMode=true&_x_zm_rtaid=2FfKobIHSwW9RPlxRtmiCA.1671577797488.a88248ea7530a8531db2fdeeb2f51028&_x_zm_rhtaid=569 )
>>>>>>> main
