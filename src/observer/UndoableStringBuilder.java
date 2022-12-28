package observer;

/*
Use the class you've implemented in previous assignment
 */
import java.util.Stack;
public class UndoableStringBuilder {


    /**
     * this class is for the first assignment in OOP course , second year , semester A.
     * @author Michal Elisha, Shlomit Ashkenazi
     */
    private Stack<UndoableStringBuilder> stk = new Stack<>();
    private StringBuilder strbld = new StringBuilder();

    public UndoableStringBuilder()
    {
        strbld=new StringBuilder();
    } // default constructor

    public UndoableStringBuilder(String str) throws NullPointerException  {
        try
        {
            strbld=new StringBuilder(str);
        }
        catch (NullPointerException e){
            System.out.print("NullPointerException Caught"+e);
        }
    }

    /**
     * This method appends the specified string to the character sequence.
     * Additionally, will save on stk the object, by value.
     *
     * @param str - a string to be appended to strbld
     * @return UndoDstringBuilder - we will return this (the object by reference), after execution of appending
     */
    public UndoableStringBuilder append(String str) {
        this.strbld.append(str);
        stk.push(new UndoableStringBuilder(this.strbld.toString()));
        return this;
    }

    /**
     * This method Removes the characters in a substring of this sequence.If start is equal to end, no changes are made.
     * Additionally, will save on stk the object, by value.
     *
     * @param start - The beginning index, inclusive.
     * @param end   - The ending index, exclusive.
     * @return UndoDstringBuilder - we will return this(the object by reference), after execution of removing
     **/
    public UndoableStringBuilder delete(int start, int end) throws StringIndexOutOfBoundsException{
        try{
            strbld.delete(start, end).toString();
            stk.push(new UndoableStringBuilder(this.strbld.toString()));
        }
        catch(StringIndexOutOfBoundsException e){ //TESTING prints error, continue
            System.out.println("StringIndexOutOfBoundsException"+ e);
        }
        return this;
    }

    /**
     * This method inserts the string into this character sequence.
     * Additionally, will save on stk the object, by value.
     *
     * @param offset - the offset, it must be greater than or equal to 0, and less than or equal to the length of this sequence.
     * @param str - a string, if gets the value null, would consider this as the string "null"
     * @return UndoDstringBuilder - we will return this(the object by reference), after execution of inserting
     */
    public UndoableStringBuilder insert(int offset, String str) throws IndexOutOfBoundsException {
        try {
            this.strbld.insert(offset, str);
            stk.push(new UndoableStringBuilder(this.strbld.toString()));
        }
        catch(StringIndexOutOfBoundsException e) {
            System.out.println("StringIndexOutOfBoundsException" + e);
        }
        return this;
    }

    /**
     * This method replaces the characters in a substring of this sequence with characters in the specified String.
     * Additionally, will save on stk the object, by value.
     *
     * @param start - The beginning index, inclusive.
     * @param end - The ending index, exclusive.
     * @param str - String that will replace previous contents.
     * @return UndoDstringBuilder - we will return this(the object by reference), after execution of replacing
     */
    public UndoableStringBuilder replace(int start, int end, String str)  throws NullPointerException,IndexOutOfBoundsException{
        try
        {
            this.strbld.replace(start, end, str);
            stk.push(new UndoableStringBuilder(this.strbld.toString()));
        }
        catch(NullPointerException e1){
            System.out.println("NullPointerException has found"+e1);
        }
        catch(IndexOutOfBoundsException e2){
            System.out.println("IndexOutOfBoundsException"+e2);
        }
        return this;
    }

    /**
     * This method Causes this character sequence to be replaced by the reverse of the sequence.
     * Additionally, will save on stk the object, by value.
     *
     * @param - no parameters.
     * @return UndoDstringBuilder - we will return this(the object by reference), after execution of reversing
     */
    public UndoableStringBuilder reverse() {
        if (!(this.strbld.toString()).equals(""))
        {
            this.strbld.reverse();
            stk.push(new UndoableStringBuilder(this.strbld.toString()));
        }
        return this;
    }

    /**
     * This method will do the UNDO operation, canceling the last function operation that have been made
     *
     * @param - no parameters.
     * @return - do not return, void
     */
    public void undo() {
        if (!stk.isEmpty()) {
            if(stk.size()!=1) {
                stk.pop();
                if (!stk.isEmpty()) {
                    this.strbld = stk.peek().strbld;
                }
            }
        }
    }

    /**
     * This method is "parsing" the object to a String
     *
     * @param  - no parameters.
     * @return String - strbld.toString()
     */
    public String toString() {

        // return null;
        return strbld.toString();
    }


}
