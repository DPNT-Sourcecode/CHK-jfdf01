package befaster.solutions.HLO;

import befaster.solutions.SUM.SumSolution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.equalTo;

public class HelloSolutionTest {

    private HelloSolution hello;

    @BeforeEach
    public void setUp() {
        hello = new HelloSolution();
    }

    @Test
    public void testHelloPositive(){
        String s = "Hello World!";
        String actual = hello.hello("abc");
        assertEquals(s,actual, "Expected Hello World!");
    }

}
