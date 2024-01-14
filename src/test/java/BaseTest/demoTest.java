package BaseTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class demoTest {

    @BeforeTest
    public void print1(){
        System.out.println("this 2");
    }

    @Test
    public void print2(){
        System.out.println("this is 3");

    }

    @AfterTest
    public void print3(){
        System.out.println("this is 4");
    }
}
