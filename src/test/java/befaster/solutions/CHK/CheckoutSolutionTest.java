package befaster.solutions.CHK;

import befaster.solutions.HLO.HelloSolution;
import befaster.solutions.SUM.SumSolution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {
    private CheckoutSolution checkoutMain;

    @BeforeEach
    public void setUp() {
        checkoutMain = new CheckoutSolution();
    }

    @Test
    public void testValidInputWithoutDiscount(){
        int expected = 115;
        int actual = checkoutMain.checkout("ABCD");
        assertEquals(expected, actual, "Expected Price should be 115");
    }

    @Test
    public void testInvalidInputWithoutDiscount(){
        int expected = 105;
        int actual = checkoutMain.checkout("ABCD");
        assertEquals(expected, actual, "Expected Price should be 115");
    }

    @Test
    public void testValidInputWithDiscount(){
        int expected = 245;
        int actual = checkoutMain.checkout("AAAABB");
        assertEquals(expected, actual, "Expected Price should be 245");
    }

    @Test
    public void testInvalidInputs(){
        assertEquals(-1, checkoutMain.checkout("null"), "Expected Price should be -1");
        assertEquals(0, checkoutMain.checkout(""), "Expected Price should be -1");
        assertEquals(-1, checkoutMain.checkout("XYZ"), "Expected Price should be -1");
        assertEquals(-1, checkoutMain.checkout("12AB"), "Expected Price should be -1");
    }

    @Test
    public void testNewOfferE(){
        int expected = 80;
        int actual = checkoutMain.checkout("EE");
        assertEquals(expected, actual, "Expected value of 80");
    }

    @Test
    public void testInputForE(){
        assertEquals(40, checkoutMain.checkout("E"), "Expected Price should be 115");
    }

    @Test
    public void testInputForEEB(){
        assertEquals(80, checkoutMain.checkout("EEB"), "Expected Price should be 80");
    }

    @Test
    public void testInputForBB(){
        assertEquals(45, checkoutMain.checkout("BB"), "Expected Price should be 45");
    }

    @Test
    public void testInputForBBB(){
        assertEquals(75, checkoutMain.checkout("BBB"), "Expected Price should be 75");
    }

    @Test
    public void testInputForEEBB(){
        assertEquals(110, checkoutMain.checkout("EEBB"), "Expected Price should be 80");
    }

    @Test
    public void testInputForFF(){
        assertEquals(20, checkoutMain.checkout("FF"), "Expected Price should be 20");
    }

    @Test
    public void testInputForFFF(){
        assertEquals(20, checkoutMain.checkout("FFF"), "Expected Price should be 20");
    }

    @Test
    public void testInputForFFFF(){
        assertEquals(30, checkoutMain.checkout("FFFF"), "Expected Price should be 30");
    }

    @Test
    public void testInputForAFFFF(){
        assertEquals(80, checkoutMain.checkout("AFFFF"), "Expected Price should be 80");
    }

    @Test
    public void testInputForG(){
        assertEquals(20, checkoutMain.checkout("G"), "Expected Price should be 20");
    }

    @Test
    public void testPositivesMultiples(){
        assertEquals(45, checkoutMain.checkout("HHHHH"), "Expected Price should be 45");
        assertEquals(55, checkoutMain.checkout("HHHHHH"), "Expected Price should be 55");
        assertEquals(150, checkoutMain.checkout("KK"), "Expected Price should be 150");
        assertEquals(120, checkoutMain.checkout("NNNM"), "Expected Price should be 120");
        assertEquals(120, checkoutMain.checkout("UUUU"), "Expected Price should be 120");
        assertEquals(120, checkoutMain.checkout("UUU"), "Expected Price should be 120");
        assertEquals(965, checkoutMain.checkout("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        assertEquals(150, checkoutMain.checkout("RRR"));

    }
}


