/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LamportSinHilos;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yohovani
 */
public class Lamport {
	private ArrayList<Integer> tiempos1;
	private ArrayList<Integer> tiempos2;
	private ArrayList<Integer> tiempos3;
	private int incrementos[];
	private boolean SR;
	private int indice,actual;
	
	public Lamport(ArrayList<Integer> tiempos1, ArrayList<Integer> tiempos2, ArrayList<Integer> tiempos3, int[] incrementos) {
		this.tiempos1 = tiempos1;
		this.tiempos2 = tiempos2;
		this.tiempos3 = tiempos3;
		this.incrementos = incrementos;
	}

	public void enviar(){
		if((indice+5) > this.tiempos1.size()){
		//	addTiempos();
		}
		for(int i=0;i<5;i++){
			Algoritmo();
		}
	}
	//SR = true => enviar
	public void Algoritmo(){
		
		switch(actual){
			case 1:{
				if(SR){
					if(tiempos1.get(indice) > tiempos2.get(indice+1)){
						ajustar(2,indice+1,tiempos1.get(indice));
						actual=2;
					}else
						if(tiempos1.get(indice) < tiempos2.get(indice+1))
							actual = 2;
				}	
				indice++;
				break;
			}
			case 2:{
				if(SR){
					if(tiempos2.get(indice) > tiempos3.get(indice+1)){
						ajustar(3,indice+1,tiempos2.get(indice));
						actual = 3;
					}else{
						if(tiempos2.get(indice) < tiempos3.get(indice+1))
							actual = 3;
					}
				}
				else{
					if(tiempos2.get(indice) > tiempos1.get(indice+1)){
						ajustar(1,indice+1,tiempos2.get(indice));
						actual = 1;
					}else{
						if(tiempos2.get(indice) < tiempos1.get(indice+1))
							actual = 1;
					}
				}
				indice++;
				break;
			}
			case 3:{
				if(tiempos3.get(indice) > tiempos2.get(indice+1)){
					ajustar(2,indice+1,tiempos3.get(indice));
				}
				actual = 2;
				SR = false;
				indice++;
			}
		}
	}
	
	public void ajustar(int x, int indice,int valor) {
		switch(x){
			case 1:{
				this.tiempos1.set(indice, valor+1);
				for(int i=indice+1;i<this.tiempos1.size();i++){
					this.tiempos1.set(i, this.tiempos1.get(i-1)+this.incrementos[0]);
				}
				break;
			}
			case 2:{
				this.tiempos2.set(indice, valor+1);
				for(int i=indice+1;i<this.tiempos2.size();i++){
					this.tiempos2.set(i, this.tiempos2.get(i-1)+this.incrementos[1]);
				}
				break;
			}
			default:{
				this.tiempos3.set(indice, valor+1);
				for(int i=indice+1;i<this.tiempos3.size();i++){
					this.tiempos3.set(i, this.tiempos3.get(i-1)+this.incrementos[2]);
				}
				break;
			}
		}
	}

	public String formatoLista1(){
		String aux="";
		for(int i=0;i<this.tiempos1.size();i++){
			aux+=tiempos1.get(i)+"\n";
		}
		return aux;
	}
	
	public String formatoLista2(){
		String aux="";
		for(int i=0;i<this.tiempos2.size();i++){
			aux+=tiempos2.get(i)+"\n";
		}
		return aux;
	}
	
	public String formatoLista3(){
		String aux="";
		for(int i=0;i<this.tiempos3.size();i++){
			aux+=tiempos3.get(i)+"\n";
		}
		return aux;
	}
	
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getActual() {
		return actual;
	}

	public void setActual(int actual) {
		this.actual = actual;
	}

	public ArrayList<Integer> getTiempos1() {
		return tiempos1;
	}

	public void setTiempos1(ArrayList<Integer> tiempos1) {
		this.tiempos1 = tiempos1;
	}

	public ArrayList<Integer> getTiempos2() {
		return tiempos2;
	}

	public void setTiempos2(ArrayList<Integer> tiempos2) {
		this.tiempos2 = tiempos2;
	}

	public ArrayList<Integer> getTiempos3() {
		return tiempos3;
	}

	public void setTiempos3(ArrayList<Integer> tiempos3) {
		this.tiempos3 = tiempos3;
	}

	public int[] getIncrementos() {
		return incrementos;
	}

	public void setIncrementos(int[] incrementos) {
		this.incrementos = incrementos;
	}

	public boolean isSR() {
		return SR;
	}

	public void setSR(boolean SR) {
		this.SR = SR;
	}

	public void addTiempos() {
		int x = indice+5;	
		for(int i=this.tiempos1.size();i<x;i++){
			this.tiempos1.add(this.tiempos1.get(i-1)+this.incrementos[0]);
			this.tiempos2.add(this.tiempos2.get(i-1)+this.incrementos[1]);
			this.tiempos3.add(this.tiempos3.get(i-1)+this.incrementos[2]);
		}
	}
	
	
	
}
