package ec.edu.ups.ppw63.demo63.business;

import java.util.List;

import ec.edu.ups.ppw63.demo63.dao.UniversidadesDAO;
import ec.edu.ups.ppw63.demo63.model.Universidades;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUniversidad {
	@Inject
	private UniversidadesDAO daoUniversidad;
	
	private final Tracer tracer = GlobalTracer.get();

	public void guardar(Universidades universidad) {
		Span span = tracer.buildSpan("guardar").start();
		try (Scope scope = tracer.scopeManager().activate(span)) {
			daoUniversidad.insert(universidad);
		} catch (Exception e) {
			span.log(e.getMessage());
			throw e;
		} finally {
			span.finish();
		}
	}
	
	public void actualizar(Universidades universidad) {
		Universidades universidades = daoUniversidad.getUniversidad(universidad.getCodigo());
	    Span span = tracer.buildSpan("actualizar").start();
	    try (Scope scope = tracer.scopeManager().activate(span)) {
	        if (universidades != null) {
	        	universidades.setNombre(universidad.getNombre());
	            daoUniversidad.update(universidad);
	        } else {
	            System.out.println("Cliente no encontrado");
	        }
	    } catch (Exception e) {
	        span.log(e.getMessage());
	        throw e;
	    } finally {
	        span.finish();
	    }
	}
	
	public void borrar(int codigo) {
		Universidades universidades = daoUniversidad.getUniversidad(codigo);
		Span span = tracer.buildSpan("eliminar").start();
		try {
			try (Scope scope = tracer.scopeManager().activate(span)) {
				if (universidades != null) {
					daoUniversidad.remove(universidades.getCodigo());
				} else {
					System.out.println("No existe");
				}
			} catch (Exception s) {
				s.printStackTrace();
			}
		} catch (Exception e) {
			span.log(e.getMessage());
			throw e;
		} finally {
			span.finish();
		}
	}
	
	public List<Universidades> getClientes() {
		Span span = tracer.buildSpan("listar").start();
		try {
			try (Scope scope = tracer.scopeManager().activate(span)) {
				return daoUniversidad.getAll();
			} catch (Exception ex) {
				span.log(ex.getMessage());
				throw ex;
			} finally {
				span.finish();
			}
		} catch (Exception e) {
			return null;
		}

	}
}
