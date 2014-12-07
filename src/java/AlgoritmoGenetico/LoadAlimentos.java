/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmoGenetico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author silvy
 */
@ManagedBean
@ViewScoped
public class LoadAlimentos implements Serializable {

    List list = new ArrayList();
    private List <Alimentos>list1=new ArrayList();
    private Alimentos a;

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
    public void accionNuevo() {
        a = new Alimentos();
    }

    public void cargarAlimento(Alimentos a) {
        list1.add(a);

    }

    public List cargarAlimento1() {
        List tipoAlimRe1 = new ArrayList();
        tipoAlimRe1.add("fruta");
        list.add(new Alimentos("ARANDANOS", 1, 10, 0.4, 10, 75, 19.28, tipoAlimRe1));
        list.add(new Alimentos("CEREZAS", 0.4, 25, 0.4, 20, 200, 49.16, tipoAlimRe1));
        list.add(new Alimentos("DURAZNOS", 1, 10, 1, 20, 180, 42.4, tipoAlimRe1));
        list.add(new Alimentos("FRUTILLA", 1, 22, 1, 22, 160, 41.2, tipoAlimRe1));
        list.add(new Alimentos("HIGO", 2, 40, 0.5, 30, 200, 54.5, tipoAlimRe1));
        list.add(new Alimentos("KIWI", 4, 30, 0.4, 41, 300, 75.08, tipoAlimRe1));
        list.add(new Alimentos("MANZANA", 1, 7, 0.3, 12, 110, 26.06, tipoAlimRe1));

        List tipoAlimRe2 = new ArrayList();
        tipoAlimRe2.add("verdura");
        list.add(new Alimentos("BROCOLI", 16, 105, 1.3, 78, 400, 120.06, tipoAlimRe2));
        list.add(new Alimentos("COLIFOR", 18, 27, 1, 56, 300, 80.4, tipoAlimRe2));
        list.add(new Alimentos("LECHUGA", 9, 20, 0.5, 23, 175, 45.5, tipoAlimRe2));
        list.add(new Alimentos("COL", 11, 22, 1.5, 80, 400, 102.9, tipoAlimRe2));
        list.add(new Alimentos("ACELGA", 140, 90, 3.5, 39, 400, 134.5, tipoAlimRe2));

        return list;
    }
}
