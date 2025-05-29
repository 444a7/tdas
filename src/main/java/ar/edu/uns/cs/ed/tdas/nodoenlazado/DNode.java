package ar.edu.uns.cs.ed.tdas.nodoenlazado;

import ar.edu.uns.cs.ed.tdas.Position;

public class DNode<E> implements Position<E>{
	protected E elemento;
	protected DNode<E> next;
    protected DNode<E> prev;
	
	public DNode( E _elemento ) {
		this.elemento = _elemento;
		this.next = null;
        this.prev = null;
	}

	
	public E getElemento() {
		return this.elemento;
	}
	
	public void setElemento( E _elemento ) {
		this.elemento = _elemento;
	}
	
	public DNode<E> getSiguiente() {
		return this.next;
	}
    public DNode<E> getAnterior(){
        return this.prev;
    }
	
	public void setSiguiente( DNode<E> _next ) {
		this.next = _next;
	}
    public void setAnterior(DNode<E> _prev){
        this.prev=_prev;
    }
	@Override
	public E element() {
		return elemento; 
	}
}
