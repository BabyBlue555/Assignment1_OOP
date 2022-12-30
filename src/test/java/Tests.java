//package test;

//import observer.ConcreteMember;
//import observer.GroupAdmin;
//import observer.UndoableStringBuilder;
import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import observer.UndoableStringBuilder;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

        import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);


    @Test
    public void TestConcreteMember(){
        // test constructor
        ConcreteMember m1 = new ConcreteMember("Manny");
        ConcreteMember m2 = new ConcreteMember("Benny");
        assertEquals(m1.getName(),"Manny");
        assertEquals(m2.getName(), "Benny");
        // # check with JVM change in place in memory !
        logger.info(()-> test.java.JvmUtilities.objectFootprint(m1));
        logger.info(()-> test.java.JvmUtilities.objectFootprint(m2));
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(m1));
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(m2));


        // test update
        GroupAdmin g= new GroupAdmin();
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(g));
        logger.info(()-> test.java.JvmUtilities.objectFootprint(g));
        g.register(m1);
        g.register(m2);
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(g));
        logger.info(()-> test.java.JvmUtilities.objectFootprint(g));


        UndoableStringBuilder t = new UndoableStringBuilder();
        t.append("to be or not to be");
        g.getUSB().append(t.toString());
        assertEquals(m1.getUSB(),null);
        assertEquals(m2.getUSB(),null);
        g.modify();
        assertNotEquals(m1.getUSB().toString(),null);
        assertNotEquals(m2.getUSB().toString(),null);
        assertEquals(m1.getUSB().toString(),t.toString());
        assertEquals(m2.getUSB().toString(),t.toString());
        assertEquals(m1.getUSB().toString(),m2.getUSB().toString()); // check that all members has the same usb value
        // # CHECK JVM MEMEORY for groupAdmin
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(g)); // check size of groupAdmin in memory
        logger.info(()-> test.java.JvmUtilities.objectFootprint(g));
        //check size of members after update
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(m1));
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(m2));
        logger.info(()-> test.java.JvmUtilities.objectFootprint(m1));
        logger.info(()-> test.java.JvmUtilities.objectFootprint(m2));

        g.getUSB().reverse();
        g.modify();
        assertEquals( g.getUSB().toString(),t.reverse().toString());
        assertEquals(m1.getUSB().toString(),t.toString());
        assertEquals(m2.getUSB().toString(),t.toString());
        System.out.println(m1.getUSB().toString());
        System.out.println(m2.getUSB().toString());

        g.getUSB().delete(0,100);
        g.modify();
        assertNotEquals(m1.getUSB().toString(), t.toString()); // nothing vs. to be or not to be
        assertNotEquals(m2.getUSB().toString(),t.toString());
//        assertEquals(m2.getUSB(),"");
        g.getUSB().undo();
        g.modify();
        assertEquals(m1.getUSB().toString(),t.toString());
        assertEquals(m2.getUSB().toString(),t.toString());
        // # JVM CHECK FOR ALL CHANGES
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(g));
        logger.info(()-> test.java.JvmUtilities.objectFootprint(g));
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(m1));
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(m2));
        logger.info(()-> test.java.JvmUtilities.objectFootprint(m1));
        logger.info(()-> test.java.JvmUtilities.objectFootprint(m2));
    }


    @Test
    public void TestGroupAdmin(){
        GroupAdmin g1= new GroupAdmin(); // default
        ConcreteMember c1= new ConcreteMember("Jenny");

        // test register
        assertEquals(c1 instanceof Member, true);
        g1.register(c1);
        for(Member m: g1.getMembers()){
            assertEquals(m instanceof ConcreteMember, true);
        }
        //JVM - CHECK THAT SIZE OF LIST GROWN
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(g1.getMembers()));

        // test unregister
        g1.unregister(c1);
        assertEquals(g1.getMembers().isEmpty(),true);
        g1.unregister(c1); // c1 has already been unregistered, expect no change
        assertEquals(g1.getMembers().isEmpty(),true);
        // # JVM
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(g1));
        logger.info(()-> test.java.JvmUtilities.objectTotalSize(g1.getMembers()));


    }


    /**
       the following tests belong to the usb test class from the previous assignment.
     */
   @Test
   public void AppendMethodTest() {
        UndoableStringBuilder st1 = new UndoableStringBuilder();
        UndoableStringBuilder st2 = new UndoableStringBuilder();

        /* 1-test : append "mom" */
        assertEquals(new UndoableStringBuilder("mom").toString(), (st1.append("mom")).toString());

        /* 2-test : append "null" to mom" */
        assertNotNull(st1.append(null).toString());

        /* 3-test : append "1234!@#$" - numbers and special letters */
        assertEquals(new UndoableStringBuilder("1234!@#$").toString(), st2.append("1234!@#$").toString());
    }

   @Test
   public void DeleteMethodTest() {
        UndoableStringBuilder st1 = new UndoableStringBuilder("abba");
        String ABA = new UndoableStringBuilder("aba").toString();

        /* 1-test : delete 'b' from 'abba' */
        assertEquals(ABA, st1.delete(1, 2).toString());

        /* 2-test : invalid input of start value, expected for no change */
        assertEquals(ABA, (st1.delete(5, 4)).toString());

        /* 3-test : invalid input of start value, expected for no change */
        assertEquals(ABA, (st1.delete(-66, 4)).toString());

        /* 4-test : invalid input of start value, expected for no change */
        assertFalse(ABA.equals(st1.delete(st1.getClass().getModifiers(), 4).toString()));
    }


   @Test
    public void InsertMethodTest() {
        UndoableStringBuilder st1 = new UndoableStringBuilder("a");
        String ABA = "b";

        /* 1-test : insert to 'b' at the beginning */
        assertEquals("ba", st1.insert(0, ABA).toString());

        /* 2-test : invalid input of start value, expected for no change */
        assertEquals("ba", (st1.insert(-6, "aconnamatata")).toString());

        /* 3-test : invalid input of start value, expected for no change */
        assertEquals("ba", (st1.insert(100, "mamamia")).toString());

        /* 4-test : checks the empty string "" */
        assertTrue("ba".equals(st1.insert(0,"").toString()));
    }

    @Test
    public void ReplaceMethodTest() {
        UndoableStringBuilder st1 = new UndoableStringBuilder("aconna banana");
        String ABA = "matata";

        /* 1-test : replace to the 'banana' to 'matata' */
        assertEquals("aconna matata", st1.replace(7,100, ABA).toString());

        /* 2-test : invalid input of start value, expected for no change */
        assertEquals("aconna matata", (st1.replace(-88, 1," ")).toString());

        /* 3-test : invalid input of start value, expected for no change */
        assertEquals("aconna matata", (st1.replace(100, 3,"baiana")).toString());

        /* 4-test : invalid input of start value, expected for no change */
        assertTrue("aconna matata".equals(st1.replace(0,2,null).toString()));
    }

    @Test
    public void ReverseMethodTest() {
        UndoableStringBuilder st1 = new UndoableStringBuilder("aba");

        /* 1-test : reverse palindrome */
        assertTrue("aba".equals(st1.reverse().toString()));

        /* 2-test : usual palindrome */
        st1.insert(0,"b");
        assertTrue("abab".equals(st1.reverse().toString()));
    }

   @Test
   public void UndoTest() {
        UndoableStringBuilder st1 = new UndoableStringBuilder("aconna banana");
        String ABA = "matata";

        /* 1-test : test undo with replace */
        st1.replace(7,100, ABA);
        st1.replace(100, 3,"baiana");
        st1.undo();
        assertEquals("aconna matata", (st1.toString()));

        st1.replace(7,100, "aaaaaaaaaaaa");
        st1.replace(-88, 1," ");
        st1.replace(0,2,null);
        st1.insert(0,"mamama");
        st1.undo();
        st1.undo();
        assertEquals("aconna matata", (st1.toString()));

        /* 2-test : checks a lot of undo to make sure it acts properly even if the stk is empty */

        st1.undo();
        st1.undo();
        st1.undo();
        st1.undo();
        st1.undo();
        st1.undo();
        st1.undo();
        st1.undo();
        st1.undo();
        st1.undo();
        st1.undo();
        st1.undo();
        st1.undo();
        st1.undo();

        /* 3-test : test undo with reverse */

        st1.reverse();
        String s = st1.reverse().toString();
        st1.reverse();

        st1.undo();
        assertTrue(s.equals(st1.toString()));

        st1.undo();
        assertFalse(s.equals(st1.toString()));

    }



}

