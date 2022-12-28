package test.java;

import static org.junit.jupiter.api.Assertions.*;

import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility

    public void concreteMemberTest(){
        UndoableStringBuilder usb= new UndoableStringBuilder();
        GroupAdmin groupAdmin = new GroupAdmin();
        ConcreteMember member= new ConcreteMember();
        groupAdmin.register(member);
        member.update(usb);


    }



    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";
        UndoableStringBuilder usb=new UndoableStringBuilder("hello darkness my old friend");


        logger.info(()-> JvmUtilities.objectFootprint(usb));

        logger.info(()->JvmUtilities.objectFootprint(usb));

        logger.info(()->JvmUtilities.objectTotalSize(usb));

        logger.info(() -> JvmUtilities.jvmInfo());
    }
}
