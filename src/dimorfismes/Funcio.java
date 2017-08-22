/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dimorfismes;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
/**
 *
 * @author carles
 */
public class Funcio {
    String definicio;
    

    public Funcio(String def) {
        definicio = def;
    }

    public double eval(double x) {
        Expression e = new ExpressionBuilder(definicio)
        .variable("x")
        .build()
        .setVariable("x",x);
        return e.evaluate();
    }
    
    public double[] eval(double[] x) {
        int n = x.length;
        double[] r = new double[n];
        for (int i = 0; i < n; ++i){
            r[i] = eval(x[i]);
        }
        return r;
    }   
    public double[] rang(double x0, double xn, double d){
        int n = (int)(Math.abs(xn - x0)/d);
        double[] r = new double[n + 1];
        for(int i = 0; i <= n; ++i){
            r[i] = x0;
            x0 += d;
        }
        return r;
        
    }


}