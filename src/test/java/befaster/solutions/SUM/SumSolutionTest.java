package befaster.solutions.SUM;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.equalTo;

public class SumSolutionTest {
    private SumSolution sum;

    @BeforeEach
    public void setUp() {
        sum = new SumSolution();
    }

    @Test
    public void compute_sum() {
        assertThat(sum.compute(1, 1), equalTo(2));
    }

    @Test
    public void testSumPositive(){
        int x = 5;
        int y = 6;
        int actual = sum.compute(x,y);
        assertEquals(11,actual, "Expected 11 for the input 5 and 6");
    }

    @Test
    public void testSumNegative(){
        int x = -1;
        int y = 102;
        int actual = sum.compute(x,y);
        assertEquals(0, actual, "Expected 0 for the input -1 and 102");
    }
}
