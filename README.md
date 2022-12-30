# Assignment1_OOP
Written by:
1. Michal Elisha
2. Shlomit Ashkenazi

In this Matala we extended the abilities of UndoableStringBuilder (which we have built in the previous Matala):
  1. At the first part we implemented the Observer design as we learned in calss
  2. At the second part we debuged and analysed our program using JVM

# Getting Started
*make sure you have an java workspace like IntelliJ*

First download our progect as ZIP file
then extract it localy
right click on the folder "" -> open in Eclipse/IntelliJ ###

you can see the project now. (should look like this):

# Part 1 - Extend UndoableStringBuilder with Observable Design
we have created a UML to explain how we implemented this part:

![image](https://user-images.githubusercontent.com/42152443/210070552-c0ca8429-d950-4b72-ac26-9b3ea0d4c20e.png)

1. as you can see the 'observer' == 'Member' an interface that's being implemented by ConcreteMember (and therefor the 'update' method is implemented at ConcreteMember - even if we didn't state it explicitly).
2. by this analogy, 'observable' == 'Sender' an interface that's being  implemented by GroupAdmin (also here all methods mentioned at 'Sender' are actually being implemented at 'GroupAdmin').

# Part 2 - Testing using JVM
