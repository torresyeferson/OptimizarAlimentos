/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AlgoritmoGenetico;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silvy
 */
public class ejemploAG {
    public static void main(String[] args) {
       try {
            TestAlimentosFitness test = new TestAlimentosFitness();
            test.initialize("fruta");
            test.testSelectFittestMovies();
        } catch (Exception ex) {
            Logger.getLogger(ejemploAG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
