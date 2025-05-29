package ar.edu.uns.cs.ed.tdas.nodoenlazado;

import ar.edu.uns.cs.ed.tdas.Position;

public class Node<E> implements Position<E>{
	protected E elemento;
	protected Node<E> next;
	
	public Node( E _elemento ) {
		this.elemento = _elemento;
		this.next = null;
	}
	
	public Node( E _elemento, Node<E> _next ) {
		this.elemento = _elemento;
		this.next = _next;
	}
	
	public E getElemento() {
		return this.elemento;
	}
	
	public void setElemento( E _elemento ) {
		this.elemento = _elemento;
	}
	
	public Node<E> getSiguiente() {
		return this.next;
	}
	
	public void setSiguiente( Node<E> _next ) {
		this.next = _next;
	}

	@Override
	public E element() {
		return elemento; 
	}
}
