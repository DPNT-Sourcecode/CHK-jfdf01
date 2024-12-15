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
}


