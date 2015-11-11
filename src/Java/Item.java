package Java;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name="itens")
public class Item {
	@Id
	private String marca;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String tipo;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String cor;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String modelo;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String outrasCaracteristicas;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String bairro;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String pontoDeReferencia;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String horario;

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getOutrasCaracteristicas() {
		return outrasCaracteristicas;
	}
	public void setOutrasCaracteristicas(String outrasCaracteristicas) {
		this.outrasCaracteristicas = outrasCaracteristicas;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getPontoDeReferencia() {
		return pontoDeReferencia;
	}
	public void setPontoDeReferencia(String pontoDeReferencia) {
		this.pontoDeReferencia = pontoDeReferencia;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
}
