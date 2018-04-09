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
public class Lamport extends Thread{
	private ArrayList<Integer> tiempos1;
	private ArrayList<Integer> tiempos2;
	private ArrayList<Integer> tiempos3;
	private int incremento;
	private boolean bandera,fin,envio;
	private int indice,actual;

	public void setActual(int actual) {
		this.actual = actual;
	}

	
	
	public Lamport(String name,int x,boolean b,boolean c){
		super(name);
		this.incremento = x;
		this.bandera = b;
		this.fin = c;
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

	public int getIncremento() {
		return incremento;
	}

	public void setIncremento(int incremento) {
		this.incremento = incremento;
	}

	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public void setTiempos(ArrayList<Integer> a,ArrayList<Integer> b,ArrayList<Integer> c){
		this.tiempos1 = a;
		this.tiempos2 = b;
		this.tiempos3 = c;
	}
	
	@Override
	public void run() {
		int x = indice+4;
		while(fin){
			algoritmo();
		}
		System.out.println(imprimirTiempos()+"Indice: "+this.indice);
	}
	
	public synchronized void sad(){
		if(this.getName().equals(this.actual+"")){
			algoritmo();
		}else{
			try {
				wait();
			} catch (InterruptedException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	//bandera = true es para enviar
	//bandera = false es para recibir
	
	public synchronized void algoritmo(){
		System.out.println("Actual: "+this.actual+" Nombre: "+this.getName());
		switch(actual){
			case 1:{
				if(bandera){
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
				if(bandera){
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
					fin = false;
				}
				indice++;
				break;
			}
			case 3:{
				if(tiempos3.get(indice) > tiempos2.get(indice+1)){
					ajustar(2,indice+1,tiempos3.get(indice));
				}
				actual = 2;
				bandera = false;
				indice++;
			}
		}
	}

	public void ajustar(int x, int indice,int valor) {
		switch(x){
			case 1:{
				this.tiempos1.set(indice, valor+1);
				for(int i=indice+1;i<this.tiempos1.size();i++){
					this.tiempos1.set(i, this.tiempos1.get(i-1)+this.incremento);
				}
				break;
			}
			case 2:{
				this.tiempos2.set(indice, valor+1);
				for(int i=indice+1;i<this.tiempos2.size();i++){
					this.tiempos2.set(i, this.tiempos2.get(i-1)+this.incremento);
				}
				break;
			}
			default:{
				this.tiempos3.set(indice, valor+1);
				for(int i=indice+1;i<this.tiempos3.size();i++){
					this.tiempos3.set(i, this.tiempos3.get(i-1)+this.incremento);
				}
				break;
			}
		}

	}
	
	public String imprimirTiempos(){
		String aux="";
		switch(this.getName()){
			case "1":{
				for(int i=0;i<this.tiempos1.size();i++){
					aux+=tiempos1.get(i)+"\n";
				}
				break;
			}
			case "2":{
				for(int i=0;i<this.tiempos2.size();i++){
					aux+=tiempos2.get(i)+"\n";
				}
				break;
			}
			default:{
				for(int i=0;i<this.tiempos3.size();i++){
					aux+=tiempos3.get(i)+"\n";
				}
				break;
			}
		}
		return aux;
	}
	
	public String formatoLista1(){
		String aux="";
		for(int i=0;i<this.tiempos1.size();i++){
			if(envio && this.indice-4 == i || this.indice==i){
				aux+="-> "+tiempos1.get(i)+"\n";
			}else
			aux+=tiempos1.get(i)+"\n";
		}
		
		return aux;
	}
	
	public String formatoLista2(){
		String aux="";
		for(int i=0;i<this.tiempos2.size();i++){
			if(envio && this.indice-3 == i || this.indice-1 == i){
				aux+="-> "+tiempos2.get(i)+"\n";
			}else
			aux+=tiempos1.get(i)+"\n";
		}
		return aux;
	}
	
	public String formatoLista3(){
		String aux="";
		for(int i=0;i<this.tiempos3.size();i++){
			if(envio && this.indice-2 == i){
				aux+="-> "+tiempos3.get(i)+"\n";
			}else

			aux+=tiempos3.get(i)+"\n";
		}
		return aux;
	}

	public boolean isEnvio() {
		return envio;
	}

	public void setEnvio(boolean envio) {
		this.envio = envio;
	}
	
	
}
