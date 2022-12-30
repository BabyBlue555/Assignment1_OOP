package observer;

import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;


/**
 * groupAdmin class implements sender and notifies the usb changes to all registered members,
 * it represents observerable as we studied in class.
 */
public class GroupAdmin implements Sender {

    // field variables
    private List<Member> Members;
    private UndoableStringBuilder usb;


    /**
     * constructor (default)
     */
    public GroupAdmin() {
        this.Members = new LinkedList<Member>();
        this.usb= new UndoableStringBuilder();
    }


    /**
     * getters methods
     */

    /**
     * returns the list of concrete Members in the groupAdmin class
     * @return Members
     */
    public List<Member> getMembers(){
        return this.Members;
    }

    /**
     * returns the current usb of groupAdmin
     * @return usb
     */
    public UndoableStringBuilder getUSB(){
        return this.usb;
    }

    /**
     * this method add a new member to the list Members in groupAdmin,
     * the member will be notified on usb changes from now on.
     * @param obj  (Member - on runtime will be a concreteMember)
     */
    @Override
    public void register(Member obj) {
        this.Members.add(obj);
        System.out.println("new member has been added to the list ");

    }


    /**
     * this method removes a member that is currently in the list Members in groupAdmin,
     * the member won't be notified on usb changes from now on.
     * @param obj  (Member - on runtime will be a concreteMember)
     */
    @Override
    public void unregister(Member obj) {
        try{
            this.Members.remove(obj);
            System.out.println("the member has been deleted from the list");
        }
        catch (Exception e){
            System.out.println("Exception "+e);
            e.printStackTrace();
        }

    }

    /**
     * this method notifies all registered members in list Members in groupAdmin about changes of usb
     * like the notify method as we learned in class
     */
    public void modify(){
        for (Member member: this.Members){
            member.update(usb);
        }
    }


    /**
     * This method inserts the string into this character sequence.
     * Additionally, will save on stk the object, by value.
     *
     * @param offset - the offset, it must be greater than or equal to 0, and less than or equal to the length of this sequence.
     * @param obj - a string, if gets the value null, would consider this as the string "null"
     * @return UndoDstringBuilder - we will return this(the object by reference), after execution of inserting
     */
    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset,obj);
    }

    /**
     * This method appends the specified string to the character sequence.
     * Additionally, will save on stk the object, by value.
     *
     * @param obj - a string to be appended to strbld
     * @return UndoDstringBuilder - we will return this (the object by reference), after execution of appending
     */
    @Override
    public void append(String obj) {
        usb.append(obj);
    }


    /**
     * This method Removes the characters in a substring of this sequence.If start is equal to end, no changes are made.
     * Additionally, will save on stk the object, by value.
     *
     * @param start - The beginning index, inclusive.
     * @param end   - The ending index, exclusive.
     * @return UndoDstringBuilder - we will return this(the object by reference), after execution of removing
     **/
    @Override
    public void delete(int start, int end) {
        usb.delete(start,end);
    }

    /**
     * This method will do the UNDO operation, canceling the last function operation that have been made
     *
     * @param - no parameters.
     * @return - do not return, void
     */
    @Override
    public void undo() {
        usb.undo();
    }


}