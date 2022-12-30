package observer;

/**
 * concreteMember class implements member and represents each member that needs to be notified by the groupAdmin .
 * like observer as we studied in class.
 */
public class ConcreteMember implements Member{


    // field variables
    private final String name;
    private UndoableStringBuilder usb;


    /**
     * constructor
     * @param name  (String that represents the name of the concreteMember)
     */
    public ConcreteMember(String name){
        this.name=name;
        this.usb=null;
    }

    public String getName(){
        return this.name;
    }

    public UndoableStringBuilder getUSB(){
        return this.usb;
    }

    /**
     * this method updates the usb (shallow copy) to the value of the groupAdmin's usb.
     * @param usb (UndoableStringBuilder), sent by groupAdmin.
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb=usb;
        System.out.println("value of usb of member  "+this.name +"is : "+ this.usb);
    }
}
