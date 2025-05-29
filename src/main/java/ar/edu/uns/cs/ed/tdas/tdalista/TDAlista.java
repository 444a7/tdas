package ar.edu.uns.cs.ed.tdas.tdalista;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.nodoenlazado.DNode;
import ar.edu.uns.cs.ed.tdas.nodoenlazado.Node;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
public class TDAlista<E> implements PositionList<E>{
    protected Node<E> lista;
    protected int cant;

    public TDAlista(){
        lista=null;
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
            EmptyListException exc= new EmptyListException("se intento hacer first en una lista vacia");
            throw exc;
        }
        return lista;
    }

    @Override
    public Position<E> last() {
        if (isEmpty()){
            EmptyListException exc= new EmptyListException("se intento hacer last en una lista vacia");
            throw exc;
        }
        Node<E> ultimo = checkPosition(first());
        while (ultimo.getSiguiente()!=null){
            ultimo=ultimo.getSiguiente();
        }
        return ultimo;
    }

    @Override
    public Position<E> next(Position<E> p) {
        Node<E> act =checkPosition(p);  //si la posicion es invalida o la lista esta vacia
        if (act.getSiguiente()==null){
            BoundaryViolationException exc = new BoundaryViolationException("se intento next en el ultimo elemento de una lista");
            throw exc;
        }
        return act.getSiguiente();
    }

    @Override
    public Position<E> prev(Position<E> p) {
        Node<E> act = checkPosition(p);
        Node<E> anterior=lista;
        if (act==lista){
            BoundaryViolationException exc = new BoundaryViolationException("se intento prev en la primera posicion de la lista");
            throw exc;
        }
        while (anterior!=null&&anterior.getSiguiente()!=act){
            anterior= anterior.getSiguiente();
        }
        if (anterior==null){
            throw new InvalidPositionException("la posicion p no se encuentra en la lista");
        }
        return anterior;
    }

    @Override
    public void addFirst(E element) {
        Node<E> nodo = new Node<E>(element);
        nodo.setSiguiente(lista);
        lista = nodo;
        cant++;
    }

    @Override
    public void addLast(E element) {
        Node<E> nNodo= new Node<E>(element);
        if (isEmpty()){
            lista=nNodo;
        }
        else{
            Node<E> ult= checkPosition(last());
            ult.setSiguiente(nNodo);
        }
        
        
    }

    @Override
    public void addAfter(Position<E> p, E element) {
        if (isEmpty()){
            InvalidPositionException exc = new InvalidPositionException("se intento addAfter en una lista vacia");
            throw exc;
        }
        Node<E> nodo = checkPosition(p);
        Node<E> nNodo = new Node<E>(element, nodo.getSiguiente());
        nodo.setSiguiente(nNodo);
        cant++;
    }

    @Override
    public void addBefore(Position<E> p, E element) {
        if (isEmpty()){
            InvalidPositionException exc = new InvalidPositionException("se intento addBefore en una lista vacia");
            throw exc;
        }
        
        Node<E> nodo = checkPosition(p);
        Node<E> nNodo = new Node<E>(element,nodo);

        if (nodo==lista){
        lista= nNodo;
        }
        else{   
        Node<E> anteriorP=checkPosition(prev(p));
        anteriorP.setSiguiente(nNodo);
        }
        cant++;
    }

    @Override
    public E remove(Position<E> p) {
        if (isEmpty()){
            InvalidPositionException exc = new InvalidPositionException("se intento eliminar de una lista vacia");
            throw exc;
        }        
        Node<E> borrado= checkPosition(p);
        if(borrado==lista){
            lista= borrado.getSiguiente();
        }
        else{
            Node<E> anteriorB=checkPosition(prev(borrado));
            anteriorB.setSiguiente(borrado.getSiguiente());
        }
        cant--;
        return borrado.element();       
    }

    @Override
    public E set(Position<E> p, E element) {
        Node<E> nodo= checkPosition(p);
        E viejo=nodo.getElemento();
        nodo.setElemento(element);
        return viejo;
    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public Iterable positions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'positions'");
    }
    private Node<E> checkPosition(Position<E> p) throws InvalidPositionException {
		Node<E> n;
		if( p == null ) {
			throw new InvalidPositionException("Posición Nula. Posición Inválida");
		}
		if(this.isEmpty()) {
			throw new EmptyListException("No puede operar sobre una lista vacía");
		}
		try {
		     n= (Node<E>) p;
		} catch( ClassCastException e ) { 
		throw new InvalidPositionException("Posicion invalida");  }
		return n;
		}

}
