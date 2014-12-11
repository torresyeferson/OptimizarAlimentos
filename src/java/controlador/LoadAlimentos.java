/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import algoritmogeneticos.Alimentos;
import algoritmogeneticos.TestAlimentosFitness;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.openmarkov.core.exception.IncompatibleEvidenceException;
import org.openmarkov.core.exception.InvalidStateException;
import org.openmarkov.core.exception.NonProjectablePotentialException;
import org.openmarkov.core.exception.NotEvaluableNetworkException;
import org.openmarkov.core.exception.ParserException;
import org.openmarkov.core.exception.ProbNodeNotFoundException;
import org.openmarkov.core.exception.UnexpectedInferenceException;
import org.openmarkov.core.exception.WrongCriterionException;
import redbaseyiana.LeerRed;

@ManagedBean
@SessionScoped
public class LoadAlimentos {

    private List<Alimentos> list1 = new ArrayList();
    private Alimentos a;
    private String tipo;
    private String resultadoAlgoritmo;
    private String resultadoBayes;

    public LoadAlimentos() {
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

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //setter and getter
    public List<Alimentos> getList1() {
        return list1;
    }

    public void setList1(List<Alimentos> list1) {
        this.list1 = list1;
    }

    public Alimentos getA() {
        return a;
    }

    public void setA(Alimentos a) {
        this.a = a;
    }

    //metodos 
    public String accionNuevo() {
        a = new Alimentos();
        return "editar?faces-redirect=true";
    }

    public void llamarRed() throws Exception {
        TestAlimentosFitness test = new TestAlimentosFitness();
        test.initialize(tipo);
        resultadoAlgoritmo = test.testSelectFittestMovies();
    }

    public String cargarAlimento() {
        System.out.println(a.getName());
        list1.add(a);
        return "index?faces-redirect=true";

    }

    public String getResultadoAlgoritmo() {
        return resultadoAlgoritmo;
    }

    public void setResultadoAlgoritmo(String resultadoAlgoritmo) {
        this.resultadoAlgoritmo = resultadoAlgoritmo;
    }

    public void llamarBayes() {
        LeerRed l = new LeerRed();
        try {
            if (tipo == "verdura") {
                resultadoBayes=l.LeerArchivo("verduras.pgmx","Col");
            } else {
                System.out.println(l.LeerArchivo("frutas.pgmx","Arandanos"));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadAlimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserException ex) {
            Logger.getLogger(LoadAlimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonProjectablePotentialException ex) {
            Logger.getLogger(LoadAlimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongCriterionException ex) {
            Logger.getLogger(LoadAlimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProbNodeNotFoundException ex) {
            Logger.getLogger(LoadAlimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotEvaluableNetworkException ex) {
            Logger.getLogger(LoadAlimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncompatibleEvidenceException ex) {
            Logger.getLogger(LoadAlimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnexpectedInferenceException ex) {
            Logger.getLogger(LoadAlimentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidStateException ex) {
            Logger.getLogger(LoadAlimentos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getResultadoBayes() {
        return resultadoBayes;
    }

    public void setResultadoBayes(String resultadoBayes) {
        this.resultadoBayes = resultadoBayes;
    }

}
