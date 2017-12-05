import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Test {
    public static void main(String[] args) throws FileNotFoundException{
        PrintWriter pw = new PrintWriter("output.txt");
        long start = System.currentTimeMillis();
        Generation generation = new Generation(10000);
        System.out.println(generation.optimum);
        for(int j = 0; j<generation.optimum.classrooms.length; j++)
        {
            System.out.println(j);
            System.out.println(generation.optimum.classrooms[j]);
        }
        for(int j = 0; j<generation.optimum.professors.length; j++) {
            System.out.println(j+": "+generation.optimum.professors[j].overlaptimes());
        }
        //for(int j = 0; j<generation.optimum.classrooms.length; j++)
        {
            System.out.println(generation.optimum.overlapClassroom());
        }
        int i = 1;
        while (true) {
            System.out.println("evolution " + i);
            System.out.println(generation.optimum.fit);
            System.out.println();

            generation.evolve(0.7, 0.01);
            //pw.println();
            //pw.println("evolution " + i);
            //pw.println(generation.meanFit);
            pw.println(generation.optimum.fit);
            i++;

            if(i > 100)
                break;

        }

        long end = System.currentTimeMillis();
        System.out.println("evolution end");
        System.out.println(end-start);
        System.out.println(generation.optimum);
        for(int j = 0; j<generation.optimum.classrooms.length; j++)
        {
            System.out.println(j);
            System.out.println(generation.optimum.classrooms[j]);
        }
        for(int j = 0; j<generation.optimum.professors.length; j++) {
            System.out.println(j+": "+generation.optimum.professors[j].overlaptimes());
        }
        {
            System.out.println(generation.optimum.overlapClassroom());
        }

        pw.close();
    }

}
