package main.java.be.pxl.ja.exercise_7_5;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Exercise_7_5 {
    public static int sumLargest(List<List<Integer>> list) {
        return list.stream()
            .mapToInt(Collections::max)
            .sum();
    }
}
