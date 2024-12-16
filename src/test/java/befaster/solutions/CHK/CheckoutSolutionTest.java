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

    @Test
    public void testInputForLongValue(){
        assertEquals(1640, checkoutMain.checkout("AAAAAPPPPPUUUUEEBRRRQAAAHHHHHHHHHHVVVBBNNNMFFFKKQQQVVHHHHH"));
    }

    @Test
    public void testInputForRandom(){
        assertEquals(740, checkoutMain.checkout("PPPPQRUVPQRUVPQRUVSU"));
    }

    @Test
    public void testInputForRandomSmall(){
        assertEquals(510, checkoutMain.checkout("QRUVPQRUVQRUVU"));;
    }

    @Test
    public void testInputForRandom2(){
        assertEquals(300, checkoutMain.checkout("QRUVPQR"));
    }

    @Test
    public void testInputForRandom4(){
        assertEquals(400, checkoutMain.checkout("QRUVPQRUVQ"));
    }

    @Test
    public void testInputForRandom5(){
        assertEquals(600, checkoutMain.checkout("PPPPQRUVPQRUVQ"));
    }

    @Test
    public void testInputForRandom6(){
        assertEquals(200, checkoutMain.checkout("PPPPP"));
    }

    @Test
    public void testInputForRandom7(){
        assertEquals(350, checkoutMain.checkout("QRUVQRUVQ"));
    }

    // PPPPQRUVPQRUVPQ RUVSU
    @Test
    public void testInputForRandom8(){
        assertEquals(600, checkoutMain.checkout("PPPPQRUVPQRUVPQ"));
    }

    // PPPPQRUVPQRUVPQ RUVSU
    @Test
    public void testInputForRandom9(){
        assertEquals(180, checkoutMain.checkout("QRQRQ"));
    }

    // PPPPQRUVPQRUVPQ RUVSU
    @Test
    public void testInputForRandom10(){
        assertEquals(210, checkoutMain.checkout("QRQRQR"));
    }

    // PPPPQRUVPQRUVPQ RUVSU
    @Test
    public void testInputForRandom11(){
        assertEquals(280, checkoutMain.checkout("QRUVQRUVQR"));
    }


    @Test
    public void testInputForRandom3(){
        assertEquals(220, checkoutMain.checkout("QRUVP"));
    }

    @Test
    public void testInputForPAndV(){
        assertEquals(340, checkoutMain.checkout("PPPPVPVP"));
    }
}








