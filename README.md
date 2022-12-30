# Assignment1_OOP

explanation on the classes:

1. GroupAdmin - the observerable.  implements Sender
2. concreteMember - the observer. Implements Member
3. UndoableStringBuilder - is used in both of the classes as a source of situations - we update each member according to the usb , and each member has the same usb value , since each member gets a shallow copy of the usb
