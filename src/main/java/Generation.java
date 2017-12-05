public class Generation {
    Gene [] genes;
    double meanFit;
    Gene optimum;
    int [] roulette;
    public Generation(int ngene) {
        genes = new Gene[ngene];
        for(int i = 0 ; i < ngene; i++) {
            genes[i] = new Gene();
        }
        calFits();
    }



    public void calFits()
    {
        int total = 0;
        int max = 0;
        int fit;
        for(int i = 0; i<genes.length; i++) {
            genes[i].fillList();
            genes[i].calFit();
            fit = genes[i].fit;
            if(fit > max)
            {
                max = fit;
                optimum = genes[i].clone();
            }
            total += fit;
        }
        meanFit = total/genes.length;

        roulette = new int [genes.length];
        int sum = 0;
        for(int i = 0; i<roulette.length; i++) {
            sum += genes[i].fit;
            roulette[i] = sum;
        }
    }
    public void evolve(double crossoverRate, double mutationRate)
    {
        Gene [] nextGeneration = new Gene[genes.length];
        int geneIndex = 0;

        // 복제
        double heritanceRate = 1 - crossoverRate - mutationRate;
        int heritance = (int)(heritanceRate * genes.length);
        nextGeneration[geneIndex++] = optimum.clone();
        while (geneIndex < heritance)
        {
            Gene gene = roulettePick();
            nextGeneration[geneIndex++] = gene.clone();
        }

        // 교차
        int crossover = (int)(genes.length*crossoverRate);
        while(geneIndex < crossover+heritance)
        {
            Gene g1 = roulettePick();
            Gene g2 = roulettePick();
            if(g1.crossover(g2)) {
                nextGeneration[geneIndex++] = g1.clone();
                nextGeneration[geneIndex++] = g2.clone();
            }
        }

        // 변이
        while(geneIndex < genes.length)
        {
            Gene gene = roulettePick();
            if(gene.mutate()) {
                nextGeneration[geneIndex++] = gene.clone();
            }
        }
        int nextMeanFit = 0;
        for(int i = 0; i<genes.length; i++)
            nextMeanFit+=genes[i].fit;

        if(meanFit < nextMeanFit) {
            genes = nextGeneration.clone();
            calFits();
        }
    }

    public Gene roulettePick()
    {
        int totalFit = roulette[roulette.length-1];
        int dart = (int)(Math.random() * totalFit);
        for(int i = 0; i<roulette.length; i++)
        {
            if(dart <= roulette[i])
                return genes[i].clone();
        }
        return genes[(int)(Math.random()*roulette.length)];
    }
}
