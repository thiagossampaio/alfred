package br.com.twsoftware.alfred.object;

public class Bean {
	public Integer inicio;
	public Integer fim;
	
	public Bean(Integer inicio, Integer fim) {
		super();
		this.inicio = inicio;
		this.fim = fim;
	}
	public Bean() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getInicio() {
		return inicio;
	}
	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}
	public Integer getFim() {
		return fim;
	}
	public void setFim(Integer fim) {
		this.fim = fim;
	}
	
	public String toString(){
		return inicio + " - " + fim;
	}
	
	
}
