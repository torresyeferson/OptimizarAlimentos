/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algoritmogeneticos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import org.jgap.impl.IntegerGene;

/**
 *
 * @author silvy
 */
public class AlimentosFitnessFuncion extends FitnessFunction {

    List alimentos = new ArrayList();
    List genRes = new ArrayList();

    public AlimentosFitnessFuncion(List alim, List genres) {
        this.alimentos = alim;
        this.genRes = genres;
    }
    @Override
    protected double evaluate(IChromosome chromosome) {
        double score = 0;
        double imdbScore = 0;

        List dups = new ArrayList();
        int badSolution = 1;

        for (int i = 0; i < chromosome.size(); i++) {

            IntegerGene agene = (IntegerGene) chromosome.getGene(i);
            int index = (Integer) chromosome.getGene(i).getAllele();

            if (dups.contains(index)) {
                badSolution = 0;
            } else {
                dups.add(index);
            }

            Alimentos aliment = (Alimentos) alimentos.get(index);
            double genreScore = getGenreScore(aliment);
            if (genreScore == 0) {
                badSolution = 0;
            }

            score = (score + aliment.getValNutricional()) + (genreScore);
//            System.out.println(movie + " socore: " + score);
        }
        return (score * badSolution);
    }
     private double getGenreScore(Alimentos alim) {
        double genreScore = 0;
        Iterator it = this.genRes.iterator();

        while (it.hasNext()) {
            String genre = (String) it.next();
            if (alim.getTipoAlimento().equals(genre)) {
                genreScore = genreScore + 1;
            }
        }
        return genreScore;
    }
    
}

