/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamportHilos;

import java.util.ArrayList;

/**
 *
 * @author Yohovani
 */
public class test {
	public static void main(String[] asdsa){
		boolean b = true;
		boolean fg = true;
		int x = 0;

		Lamport l1 = new Lamport("1",3,b,fg);
		Lamport l2 = new Lamport("2",5,b,fg);
		Lamport l3 = new Lamport("3",7,b,fg);

		ArrayList<Integer> x1=new ArrayList(),x2=new ArrayList(),x3=new ArrayList();

		for(int i=0,a=3,v=5,c=7;i<10;i++,a+=3,v+=5,c+=7){
			x1.add(a);
			x2.add(v);
			x3.add(c);
		}

		l1.setIndice(x);
		l2.setIndice(x);
		l3.setIndice(x);

		int a=1;
		
		l1.setActual(a);
		l2.setActual(a);
		l3.setActual(a);
		
		l1.setTiempos(x1, x2, x3);
		l2.setTiempos(x1, x2, x3);
		l3.setTiempos(x1, x2, x3);

		l1.start();
		l2.start();
		l3.start();

	}
}
