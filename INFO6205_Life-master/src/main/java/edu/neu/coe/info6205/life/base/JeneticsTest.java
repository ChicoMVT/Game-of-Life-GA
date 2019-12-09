package edu.neu.coe.info6205.life.base;
import io.jenetics.*;
import io.jenetics.engine.*;
import io.jenetics.util.IntRange;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Timer;

import static io.jenetics.engine.Codecs.ofVector;

public class JeneticsTest {
    private static int run;
    public static HashMap<Long,Group> groupHashMap=new HashMap<>();
    private static int eval(int[] cells) {

        List<Point> points = new ArrayList<>();
        for (int position = 0; position < 100; position++) {
            if (cells[position] != 1) continue;

            int x = position % 10;
            int y = position / 10;
            Point point = new Point(x, y);
            points.add(point);
        }


        Game game = new Game();
        final Game.Behavior generations = game.run(0L, points, Game.MaxGenerations);

        run++;
        return (int) generations.generation;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Engine<IntegerGene, Integer> engine = Engine
                .builder(JeneticsTest::eval, ofVector(IntRange.of(0,1), 100))
                .populationSize(1000)
                .alterers(new Mutator<>(0.1),new  SinglePointCrossover(0.2))
                .survivorsSelector(new TournamentSelector<>(100))
                .offspringSelector(new RouletteWheelSelector<>())
                .optimize(Optimize.MAXIMUM)
                .build();




        Genotype<IntegerGene> result = engine.stream()
                //.limit(Limits.bySteadyFitness(7))
                .limit(1000)
                .collect(EvolutionResult.toBestGenotype());

        System.out.println("RUN:     "+run);



        List<Point> pos=getStartingPattern(result);

        System.out.println(pos.size());


//        List<Point> points=new ArrayList<>();
//        for(int i=0;i<array.length;i++){
//            for(int j=0;j<array[i].length;j++){
//                if(array[i][j]==1) points.add(new Point(j,i));
//            }
//        }

        JFrame frame = new JFrame();
        Draw draw = new Draw();
        draw.setPoints(pos);
        frame.add(draw);
        frame.setVisible(true);
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Label label = new Label();
        label.setText("Generation: origin" + "\n" + " size: " + pos.size());
        label.setBounds(5,5,30,30);
        draw.add(label);
        draw.repaint();

        Game game=new Game();
        game.setFlag(true);
        final Game.Behavior generations=game.run(0L, pos, Game.MaxGenerations);

        System.out.println("Group size:"+groupHashMap.size());

        for(Group group:groupHashMap.values()){
            System.out.println(group.getCount());
        }

        while (true) {
            int i = 1;
            for (Group group : groupHashMap.values()) {
                //draw.getGraphics().clearRect(0,0,400,400);
                label.setText("Generation: " + i++ + "\n" + " size: " + group.getCount());
                draw.setPoints(group.getPoints());
                draw.repaint();
                System.out.println(i);
                Thread.sleep(1000);
            }
            label.setText("Generation: origin" + "\n" + "size: " + pos.size());
            draw.setPoints(pos);
            draw.repaint();
            Thread.sleep(1000);
        }

    }

    public static List<Point> getStartingPattern(Genotype<IntegerGene> result){

        List<Point> list=new ArrayList<>();

        IntegerChromosome chromosome=(IntegerChromosome) result.getChromosome();
        int[] nodes=chromosome.toArray();

        for(int i = 0; i < nodes.length; i++){
            if(nodes[i]==1){
                Point point = new Point(i % 10, i / 10);
                list.add(point);
            }
        }

        return list;
    }
}
