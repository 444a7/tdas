package ar.edu.uns.cs.ed.tdas.tdalista;

import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.nodoenlazado.DNode;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
public class TDAlistaDE<E> implements PositionList<E>{
    
    protected DNode<E> inicio;
    protected DNode<E> fin;
    protected int cant;
    public TDAlistaDE(){
        inicio = new DNode<E>(null);
        fin = new DNode<E>(null);
        inicio.setSiguiente(fin);
        fin.setAnterior(inicio);
        cant=0;
    }
    @Override
    public int size() {
        return cant;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public Position<E> first() {
        if (isEmpty()){
            throw new EmptyListException("se intento hacer first en una lista vacia");
        }
        return inicio.getSiguiente();
    }

    @Override
    public Position<E> last() {
        if (isEmpty()){
            throw new EmptyListException("se intento hacer last en una lista vacia");
        }
        return fin.getAnterior();
    }

    @Override
    public Position<E> next(Position<E> p) {
        DNode<E> act =checkPosition(p);
        if (act.getSiguiente()==fin){
            throw new BoundaryViolationException("se intento next en el ultimo elemento de la lista");
        }
        return act.getSiguiente();
    }

    @Override
    public Position<E> prev(Position<E> p) {
        DNode<E> act = checkPosition(p);
        if (act.getAnterior()==inicio){
            throw new BoundaryViolationException("se intento prev en el primer elemento de la lista");
        }
        return act.getAnterior();
    }

    @Override
    public void addFirst(E element) {
        añadirEntre(inicio, inicio.getSiguiente(), element);
        cant++;      
    }

    @Override
    public void addLast(E element) {
        añadirEntre(fin.getAnterior(), fin, element);
        cant++;
    }

    @Override
    public void addAfter(Position<E> p, E element) {
        if (isEmpty()){
            throw new InvalidPositionException("se intento addAfter en una lista vacia");
        }
        DNode<E> nodo = checkPosition(p);
        añadirEntre(nodo, nodo.getSiguiente(), element);
        cant++;
    }

    @Override
    public void addBefore(Position<E> p, E element) {
        if (isEmpty()){
            throw new InvalidPositionException("se intento addBefore en una lista vacia");
        }
        DNode<E> nodo = checkPosition(p);
        añadirEntre(nodo.getAnterior(),nodo,element);
        cant++;
    }

    @Override
    public E remove(Position<E> p) {
        if (isEmpty()){
            throw new InvalidPositionException("se intento remove en una lista vacia");
        }
        DNode<E> remover=checkPosition(p);
        remover.getAnterior().setSiguiente(remover.getSiguiente());
        remover.getSiguiente().setAnterior(remover.getAnterior());
        cant--;
        return remover.element();
        
    }

    @Override
    public E set(Position<E> p, E element) {
        if (isEmpty()){
            throw new InvalidPositionException("se intento remove en una lista vacia");
        }
        DNode<E> aCambiar = checkPosition(p);
        E anterior= aCambiar.element();
        aCambiar.setElemento(element);
        return anterior;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public Iterable<Position<E>> positions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'positions'");
    }

    private DNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
		DNode<E> n;
		if( p == null ) {
			throw new InvalidPositionException("Posición Nula. Posición Inválida");
		}
		if(this.isEmpty()) {
			throw new EmptyListException("No puede operar sobre una lista vacía");
		}
		try {
		     n= (DNode<E>) p;
		} catch( ClassCastException e ) { 
		throw new InvalidPositionException("Posicion invalida");  }
		return n;
		}
        
    private void añadirEntre(DNode<E> anterior, DNode<E> siguiente,E element){
        DNode<E> nNode = new DNode<E>(element);
        anterior.setSiguiente(nNode);
        nNode.setAnterior(anterior);
        nNode.setSiguiente(siguiente);
        siguiente.setAnterior(nNode);
    }
}