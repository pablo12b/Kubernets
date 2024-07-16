package ec.edu.ups.ppw63.demo63.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Universidades implements Serializable{
	@Id
	private int codigo;
	private String nombre;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Universidades [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	
}
