package test.java.be.pxl.ja.exercise_7_5;
import main.java.be.pxl.ja.exercise_7_5.Exercise_7_5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Exercise_7_5Tests {
    private List<List<Integer>> list;

    @Test
    public void sumLargestShouldReturn36() {
        list = Arrays.asList(
                Arrays.asList(1, 7, 5, 3),
                Arrays.asList(20),
                Arrays.asList(6, 9)
                );

        assertEquals(36, Exercise_7_5.sumLargest(list));
    }
}
