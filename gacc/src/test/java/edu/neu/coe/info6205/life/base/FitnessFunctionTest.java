package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.life.library.Library;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FitnessFunctionTest {

    @Test
    public void testFitnessFunction0() {
        final String pattern = "1 2, 2 2, 1 4, 2 4, 4 3, 2 5, 4 5";
        final Game.Behavior generations = Game.run(0L, pattern);
        assertEquals(1, generations.generation);
    }

    @Test
    public void testFitnessFunction() {
        String patternName = "Blip";
        final String pattern = Library.get(patternName);
        final Game.Behavior generations = Game.run(0L, pattern);
        assertEquals(0, generations.generation);
    }

    @Test
    public void testFitnessFunction1() {
        String patternName = "Beehive";
        final String pattern = Library.get(patternName);
        final Game.Behavior generations = Game.run(0L, pattern);
        assertEquals(2, generations.generation);
    }

    @Test
    public void testFitnessFunction2() {
        String patternName = "Glider3";
        final String pattern = Library.get(patternName);
        final Game.Behavior generations = Game.run(0L, pattern);
        assertEquals(9, generations.generation);
    }

    @Test
    public void testFitnessFunction3() {
        String patternName = "Glider2";
        final String pattern = Library.get(patternName);
        final Game.Behavior generations = Game.run(0L, pattern);
        assertEquals(12, generations.generation);
    }

    @Test
    public void testFitnessFunction4() {
        String patternName = "Glider1";
        final String pattern = Library.get(patternName);
        final Game.Behavior generations = Game.run(0L, pattern);
        assertEquals(14, generations.generation);
    }

    @Test
    public void testFitnessFunction5() {
        String patternName = "Blinker";
        final String pattern = Library.get(patternName);
        final Game.Behavior generations = Game.run(0L, pattern);
        assertEquals(2, generations.generation);
    }
}
