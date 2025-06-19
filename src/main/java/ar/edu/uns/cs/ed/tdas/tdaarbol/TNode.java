package ar.edu.uns.cs.ed.tdas.tdaarbol;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdalista.TDAlistaDE;
public class TNode<E> implements Position<E>{
    protected E elemento;
    protected TNode<E> padre;
    protected PositionList<TNode<E>> hijos;
    /**
	 * Constructor de TNodo.
	 * 
	 * Inicializa:
	 *  el elemento con el.
	 *  su padre, con p.
	 *  su lista de hijos, sin ningún elemento.
	 * @param el: elemento que almacenará.
	 * @param p: Nodo padre del nodo actual.
	 */
	public TNode(E el, TNode<E> p){
		elemento=el;
		padre=p;
		hijos= new TDAlistaDE<TNode<E>>();
	}
	
	
	
	/**
	 * @return padre del nodo actual.
	 */
	public TNode<E> padre(){
		return padre;
	}
	
	/**
	 * @return lista de hijos del nodo actual.
	 */
	public PositionList<TNode<E>> hijos(){
		return hijos;
	}
	
	/**
	 * Setea el padre del nodo actual.
	 * @param p: nodo padre.
	 */
	public void setPadre (TNode<E> p){
		padre = p;
	}
	
	/**
	 * Setea el elemento del nodo actual.
	 * @param e elemento.
	 */
	public void setElement( E e) {
		elemento = e;
	}
	
	/**
	 * @return elemento del nodo actual.
	 */
	public E element() {
		return elemento;
	}
}