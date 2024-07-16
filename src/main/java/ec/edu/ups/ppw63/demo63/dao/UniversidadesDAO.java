package ec.edu.ups.ppw63.demo63.dao;

import java.util.List;

import ec.edu.ups.ppw63.demo63.model.Universidades;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UniversidadesDAO {
	@PersistenceContext
	public EntityManager em;
	
	public void insert(Universidades universidad) {
		em.merge(universidad);
	}
	
	public void update(Universidades universidad) {
		em.merge(universidad);
	}
	
	public void remove(int codigo) {
		Universidades universidad = em.find(Universidades.class, codigo);
		em.remove(universidad);
	}
	
	public Universidades read(int codigo) {
		Universidades universidad = em.find(Universidades.class, codigo);
		return universidad;
	}
	
	public List<Universidades> getAll(){
		String jpql = "SELECT u FROM Universidades u";
		Query q = em.createQuery(jpql, Universidades.class);
		return q.getResultList();
	}
	
	public Universidades getUniversidad(int codigo){
		String jpql = "SELECT u FROM Universidades u WHERE u.codigo = :codigo";
		Query q = em.createQuery(jpql, Universidades.class);
		q.setParameter("codigo", codigo);
		List<Universidades> universiadades = q.getResultList();
		if(universiadades.size()>0)
			return universiadades.get(0);
		return null;
	}
}
