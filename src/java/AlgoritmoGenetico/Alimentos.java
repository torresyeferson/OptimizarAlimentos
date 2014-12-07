/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmoGenetico;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author silvy
 */
@ManagedBean
@ViewScoped
public class Alimentos implements Serializable {

    private String name;
    private String name1;
    private double sodio;
    private double calcio;
    private double hierro;
    private double fostoro;
    private double potacio;
    private double valNutricional;
    private List tipoAlimento;
   
    public Alimentos() {
    }

    public Alimentos(String name, double sodio, double calcio, double hierro, double fostoro, double potacio, double valNutricional, List tipoAlimento) {
        this.name = name;
        this.sodio = sodio;
        this.calcio = calcio;
        this.hierro = hierro;
        this.fostoro = fostoro;
        this.potacio = potacio;
        this.valNutricional = valNutricional;
        this.tipoAlimento = tipoAlimento;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSodio() {
        return sodio;
    }

    public void setSodio(double sodio) {
        this.sodio = sodio;
    }

    public double getCalcio() {
        return calcio;
    }

    public void setCalcio(double calcio) {
        this.calcio = calcio;
    }

    public double getHierro() {
        return hierro;
    }

    public void setHierro(double hierro) {
        this.hierro = hierro;
    }

    public double getFostoro() {
        return fostoro;
    }

    public void setFostoro(double fostoro) {
        this.fostoro = fostoro;
    }

    public double getPotacio() {
        return potacio;
    }

    public void setPotacio(double potacio) {
        this.potacio = potacio;
    }

    public double getValNutricional() {
        return valNutricional;
    }

    public void setValNutricional(double valNutricional) {
        this.valNutricional = valNutricional;
    }

    public List getTipoAlimento() {
        return tipoAlimento;
    }

//    public void setTipoAlimento(List tipoAlimento) {
//    
//        this.tipoAlimento = tipoAlimento;
//    }
    public void setTipoAlimento(String tipoAlimento) {
        this.tipoAlimento.add(tipoAlimento);
    }

    @Override
    public String toString() {
        return "Alimento [Nombre=" + name + ", sodio (mg)=" + sodio
                + ", calcio=" + calcio + ", hierro=" + hierro
                + ", fostoro=" + fostoro + ", potacio=" + potacio
                + ", Valor Nutricional=" + valNutricional
                + "]";
    }
    public void presenta(){
        this.name1=this.name;
    
    }
}
