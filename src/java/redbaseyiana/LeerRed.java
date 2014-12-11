/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redbaseyiana;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openmarkov.core.exception.IncompatibleEvidenceException;
import org.openmarkov.core.exception.InvalidStateException;
import org.openmarkov.core.exception.NonProjectablePotentialException;
import org.openmarkov.core.exception.NotEvaluableNetworkException;
import org.openmarkov.core.exception.ParserException;
import org.openmarkov.core.exception.ProbNodeNotFoundException;
import org.openmarkov.core.exception.UnexpectedInferenceException;
import org.openmarkov.core.exception.WrongCriterionException;
import org.openmarkov.core.inference.InferenceAlgorithm;
import org.openmarkov.core.model.network.EvidenceCase;
import org.openmarkov.core.model.network.Finding;
import org.openmarkov.core.model.network.ProbNet;
import org.openmarkov.core.model.network.Util;
import org.openmarkov.core.model.network.Variable;
import org.openmarkov.core.model.network.potential.TablePotential;
import org.openmarkov.inference.variableElimination.VariableElimination;
import org.openmarkov.io.probmodel.PGMXReader;

/**
 *
 * @author Yeferson
 */
public class LeerRed implements Serializable{

    final public static List<String> s = new ArrayList<String>();
    final private String bayesNetworkName = "frutas.pgmx";
    public String LeerArchivo(String nombrered, String parametro) throws FileNotFoundException, ParserException, NonProjectablePotentialException, WrongCriterionException, ProbNodeNotFoundException, NotEvaluableNetworkException, IncompatibleEvidenceException, UnexpectedInferenceException, InvalidStateException {
       
        //InputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "/src/java/archivo/" + nombrered));
        InputStream file = new FileInputStream(new File("E:\\DOC\\NetBeansProjects\\RedesBayesianasAlgoritmosGeneticos\\src\\java\\archivo\\" + nombrered));
        PGMXReader reader = new PGMXReader();
        ProbNet prob = reader.loadProbNet(file,parametro).getProbNet();
        EvidenceCase evidence = new EvidenceCase();
        try {
            //se introduce la presencia del estado y si pasa o no
            evidence.addFinding(prob, parametro, "presente");
            //evidence.addFinding(prob, "Enfermedad", "presente");
        } catch (InvalidStateException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncompatibleEvidenceException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        }
        InferenceAlgorithm variableElimination = new VariableElimination(prob);
        variableElimination.setPreResolutionEvidence(evidence);
        Variable estado = prob.getVariable("desayuno");
        ArrayList<Variable> variablesOfInterest = new ArrayList<Variable>();
        variablesOfInterest.add(estado);
        HashMap<Variable, TablePotential> posteriorProbabilities = variableElimination.getProbsAndUtilities();
        return printResults(evidence, variablesOfInterest, posteriorProbabilities);

    }

    public String printResults(EvidenceCase evidence, ArrayList<Variable> variablesOfInterest,
            HashMap<Variable, TablePotential> posteriorProbabilities) {
        String resultados = "";
        for (Finding finding : evidence.getFindings()) {
            resultados += "1:  " + finding.getVariable() + ": ";
            s.add(String.valueOf(finding.getVariable()));
            s.add(finding.getState());
            resultados += finding.getState();
        }
        for (Variable variable : variablesOfInterest) {
            double value;
            TablePotential posteriorProbabilitiesPotential = posteriorProbabilities.get(variable);
            resultados += " 2:  " + variable + ": ";
            int stateIndex = -1;
            try {
                stateIndex = variable.getStateIndex("presente");
                value = posteriorProbabilitiesPotential.values[stateIndex];
                s.add(String.valueOf(Util.roundedString(value, "0.00001")));
                // s.add(sss);
                resultados += Util.roundedString(value, "0.0001");
            } catch (InvalidStateException e) {
                System.err.println("State \"present\" not found for variable \""
                        + variable.getName() + "\".");
                e.printStackTrace();
            }
        }
        return resultados;
    }

    public static void main(String[] args) {
        LeerRed l = new LeerRed();
        try {
            System.out.println("*****");
            System.out.println(l.LeerArchivo("frutas.pgmx", "Arandanos"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonProjectablePotentialException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongCriterionException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProbNodeNotFoundException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotEvaluableNetworkException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncompatibleEvidenceException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnexpectedInferenceException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidStateException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
