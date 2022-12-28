package observer;

import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;

public class GroupAdmin implements Sender {

    List<Member> Members;
    UndoableStringBuilder usb;

    public GroupAdmin() {
        this.Members = new LinkedList<Member>();
        this.usb= new UndoableStringBuilder();
    }


    @Override
    public void register(Member obj) {
        this.Members.add(obj);
        System.out.println("new member has been added to the list ");


    }

    @Override
    public void unregister(Member obj) {
        this.Members.remove(obj);
        System.out.println("the member has been deleted from the list");

    }


    public void modify(){
        for (Member member: this.Members){
            member.update(usb);
        }
    }


    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset,obj);
    }

    @Override
    public void append(String obj) {
        usb.append(obj);
    }

    @Override
    public void delete(int start, int end) {
        usb.delete(start,end);
    }

    @Override
    public void undo() {
        usb.undo();
    }


}