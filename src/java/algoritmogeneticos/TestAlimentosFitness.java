/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algoritmogeneticos;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

/**
 *
 * @author silvy
 */
public class TestAlimentosFitness {

    private Configuration conf;
    private AlimentosFitnessFuncion fitnessFunction = null;
    public List alimento = new ArrayList();
    public List genRes = new ArrayList();

    private static final int MAX_ALLOWED_EVOLUTIONS = 1500;
    private Chromosome alimentoChromosome = null;

    public void initialize(String tipoAlimento) throws Exception {
        StringTokenizer st = new StringTokenizer(tipoAlimento);

        while (st.hasMoreElements()) {
            String genre = st.nextToken();
            genRes.add(genre);
        }

        alimento = this.loadAlimento();

        conf = new DefaultConfiguration();
        conf.setPreservFittestIndividual(true);
        conf.setPopulationSize(1000);
        conf.setKeepPopulationSizeConstant(false);

        fitnessFunction = new AlimentosFitnessFuncion(alimento, genRes);

        conf.setFitnessFunction(fitnessFunction);

        Gene[] alimentoGenes = new Gene[3];

        alimentoGenes[0] = new IntegerGene(conf, 0, alimento.size() - 1);
        alimentoGenes[1] = new IntegerGene(conf, 0, alimento.size() - 1);
        alimentoGenes[2] = new IntegerGene(conf, 0, alimento.size() - 1);

        alimentoChromosome = new Chromosome(conf, alimentoGenes);
        alimentoGenes[0].setAllele(new Integer(0));
        alimentoGenes[1].setAllele(new Integer(1));
        alimentoGenes[2].setAllele(new Integer(3));

        conf.setSampleChromosome(alimentoChromosome);

    }

    private List loadAlimento() {
        List list1 = new ArrayList();
        list1.add(new Alimentos("ARANDANOS", 1, 10, 0.4, 10, 75, 19.28, "fruta"));
        list1.add(new Alimentos("CEREZAS", 0.4, 25, 0.4, 20, 200, 49.16, "fruta"));
        list1.add(new Alimentos("DURAZNOS", 1, 10, 1, 20, 180, 42.4, "fruta"));
        list1.add(new Alimentos("FRUTILLA", 1, 22, 1, 22, 160, 41.2, "fruta"));
        list1.add(new Alimentos("HIGO", 2, 40, 0.5, 30, 200, 54.5, "fruta"));
        list1.add(new Alimentos("KIWI", 4, 30, 0.4, 41, 300, 75.08, "fruta"));
        list1.add(new Alimentos("MANZANA", 1, 7, 0.3, 12, 110, 26.06, "fruta"));
        list1.add(new Alimentos("BROCOLI", 16, 105, 1.3, 78, 400, 120.06, "verdura"));
        list1.add(new Alimentos("COLIFOR", 18, 27, 1, 56, 300, 80.4, "verdura"));
        list1.add(new Alimentos("LECHUGA", 9, 20, 0.5, 23, 175, 45.5, "verdura"));
        list1.add(new Alimentos("COL", 11, 22, 1.5, 80, 400, 102.9, "verdura"));
        list1.add(new Alimentos("ACELGA", 140, 90, 3.5, 39, 400, 134.5, "verdura"));
        return list1;

    }

    public String testSelectFittestMovies() throws Exception {

        alimento = this.loadAlimento();

        Genotype population = Genotype.randomInitialGenotype(conf);

        IChromosome bestSolutionSoFar = alimentoChromosome;

        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            population.evolve();
            IChromosome candidateBestSolution = population.getFittestChromosome();

            if (candidateBestSolution.getFitnessValue() > bestSolutionSoFar.getFitnessValue()) {
                bestSolutionSoFar = candidateBestSolution;

            }
        }
        return printSolution(bestSolutionSoFar, alimento);
    }

    public String printSolution(IChromosome solution, List aliment) {
        String resultado="";
        for (int i = 0; i < solution.size(); i++) {
            int index = (Integer) solution.getGene(i).getAllele();
            Alimentos alimento = (Alimentos) aliment.get(index);
            resultado=resultado+alimento.toString()+"\n";
        }
        return resultado;
    }
}
