package edu.neu.coe.info6205.life.base;

import io.jenetics.*;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SelectorTest {

    private static int eval(Genotype<BitGene> gt) {
        return gt.getChromosome().as(BitChromosome.class).bitCount();
    }
    @Test
    public void selectorTest(){


        Genotype<BitGene> gtf = Genotype.of(BitChromosome.of(5, 0.5));

        Engine<BitGene, Integer> engine = Engine
                .builder(SelectorTest::eval, gtf)
                .populationSize(100)
                .selector(new TournamentSelector<>(2))
                .build();

        Genotype<BitGene> best = engine.stream()
                .limit(1)
                .collect(EvolutionResult.toBestGenotype());

        assertEquals("[00011111]", best.toString());
    }
}
