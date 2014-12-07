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
import org.openmarkov.core.model.network.ProbNode;
import org.openmarkov.core.model.network.Util;
import org.openmarkov.core.model.network.Variable;
import org.openmarkov.core.model.network.potential.TablePotential;
import org.openmarkov.inference.variableElimination.VariableElimination;
import org.openmarkov.io.probmodel.PGMXReader;

/**
 *
 * @author Yeferson
 */
public class LeerRed {

    final private String nombrered = "frutass.pgmx";
    final public static List<String> s = new ArrayList<String>();

    public void LeerArchivo() throws FileNotFoundException, ParserException, NonProjectablePotentialException, WrongCriterionException, ProbNodeNotFoundException, NotEvaluableNetworkException, IncompatibleEvidenceException, UnexpectedInferenceException, InvalidStateException {
        //abrir archivo
        InputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "/src/java/archivo/"+nombrered));
        //cargar la red
        PGMXReader reader = new PGMXReader();
        ProbNet prob = reader.loadProbNet(file, nombrered).getProbNet();
        System.out.println("Numero de nodos= " + prob.getNumNodes());
        List<ProbNode> listPro = prob.getProbNodes();
        for (int i = 0; i < listPro.size(); i++) {
            System.out.println("o---- " + listPro.size());
            ProbNode probNode = listPro.get(i);
            //obtenemos el nombre de cada nodo
            System.out.println("1---- " + probNode.getUtilityFunction());
            System.out.println("1---- " + probNode.getName());
            System.out.println("2---- " + probNode.getProbNet());
            System.out.println("3---- " + probNode.getRelevance());
            System.out.println("4---- " + probNode.getNodeType().toString());
            System.out.println("6---- " + probNode.getNode().toString());
            System.out.println("7---- " + probNode.getUtilityParents());
            System.out.println("8---- " + probNode.getPolicyType().name());

        }
        EvidenceCase evidence = new EvidenceCase();

        try {
            //se introduce la presencia del estado y si pasa o no
            evidence.addFinding(prob, "desayuno", "presente");
            System.out.println("........................--------");
            //evidence.addFinding(prob, "Enfermedad", "presente");
        } catch (InvalidStateException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncompatibleEvidenceException ex) {
            Logger.getLogger(LeerRed.class.getName()).log(Level.SEVERE, null, ex);
        }
        InferenceAlgorithm variableElimination = new VariableElimination(prob);
        variableElimination.setPreResolutionEvidence(evidence);
        Variable estado = prob.getVariable("desayuno");
        //Variable estado1 = prob.getVariable("Sintomas");
        ArrayList<Variable> variablesOfInterest = new ArrayList<Variable>();
        //variablesOfInterest.add(estado);
        variablesOfInterest.add(estado);
        // Calcular las probabilidades posteriores
        HashMap<Variable, TablePotential> posteriorProbabilities = variableElimination.getProbsAndUtilities();
        printResults(evidence, variablesOfInterest, posteriorProbabilities);
//        evidence.addFinding(prob, "Enfermedad", "presente");
//        printResults(evidence, variablesOfInterest, posteriorProbabilities);
    }

    public void printResults(EvidenceCase evidence, ArrayList<Variable> variablesOfInterest,
            HashMap<Variable, TablePotential> posteriorProbabilities) {
        // Print the findings
        System.out.println("Evidencia:");
        for (Finding finding : evidence.getFindings()) {
            System.out.print("1:  " + finding.getVariable() + ": ");
            s.add(String.valueOf(finding.getVariable()));
            s.add(finding.getState());
            System.out.println(finding.getState());
        }
        // Imprimir la probabilidad posterior del estado "pasa" de cada variable de interés
        System.out.println("Probabilidade posteriores: ");
        for (Variable variable : variablesOfInterest) {
            double value;
            TablePotential posteriorProbabilitiesPotential = posteriorProbabilities.get(variable);
            System.out.print(" 2:  " + variable + ": ");
            int stateIndex = -1;
            try {
                stateIndex = variable.getStateIndex("presente");
                value = posteriorProbabilitiesPotential.values[stateIndex];
                s.add(String.valueOf(Util.roundedString(value, "0.00001")));
                // s.add(sss);
                System.out.println(Util.roundedString(value, "0.0001"));
            } catch (InvalidStateException e) {
                System.err.println("State \"present\" not found for variable \""
                        + variable.getName() + "\".");
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        LeerRed l= new LeerRed();
        try {
            l.LeerArchivo();
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
