/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LamportSinHilos;

import java.util.ArrayList;

/**
 *
 * @author Yohovani
 */
public class test {
	public static void main(String asd[]){
		ArrayList<Integer> x1=new ArrayList(),x2=new ArrayList(),x3=new ArrayList();
		
		for(int i=0,a=3,v=5,c=7;i<10;i++,a+=3,v+=5,c+=7){
			x1.add(a);
			x2.add(v);
			x3.add(c);
		}
		
		int incrementos[] = {3,5,7};
		
		Lamport l = new Lamport(x1,x2,x3,incrementos);
		
		
		l.setIndice(0);
		l.setActual(1);
		l.setSR(true);
		l.enviar();
		System.out.println(l.getTiempos1());
		System.out.println(l.getTiempos2());
		System.out.println(l.getTiempos3());
		System.out.println();
		
	}
}
