package ar.edu.uns.cs.ed.tdas.tdaarbol;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdalista.TDAlistaDE;


public class Arbol<E> implements Tree<E>{
    protected TNode<E> root;
    protected int size;
    public Arbol(){
        root=null;
        size=0;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
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

    @Override
    public E replace(Position<E> v, E e) {
        TNode<E> nodo= checkPosition(v);
        E resultado = nodo.element();
        nodo.setElement(e);
        return resultado;
    }

    
    @Override
    public Position<E> root() {
        if (isEmpty()){
            throw new EmptyTreeException("el arbol esta vacio");
        }
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        TNode<E> nodo= checkPosition(v);
        return nodo.padre;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> v) {
        TNode<E> nodo = checkPosition(v);
        PositionList<Position<E>> resultado = new TDAlistaDE<Position<E>>();
        for (TNode<E> n : nodo.hijos()){
            resultado.addLast(n);
        }
        return null;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        TNode<E> nodo = checkPosition(v);       
        return !nodo.hijos().isEmpty();
    }

    @Override
    public boolean isExternal(Position<E> v) {
        TNode<E> nodo = checkPosition(v);       
        return nodo.hijos().isEmpty();
    }

    @Override
    public boolean isRoot(Position v) {
        TNode<E> nodo = checkPosition(v);
        return nodo==root;
    }

    @Override
    public void createRoot(E e) {
        if (root!=null){
            throw new InvalidOperationException("el arbol ya tiene raiz");
        }
        root = new TNode<E>(e, null);
        size++;
    }

    @Override
    public Position<E> addFirstChild(Position<E> p, E e) {
        if (isEmpty()){
            throw new InvalidPositionException("el arbol esta vacio");
        }
        TNode<E> nodo=checkPosition(p);
        TNode<E> hijo = new TNode<E>(e,nodo);
        nodo.hijos().addFirst(hijo);
        return hijo;
    }

    @Override
    public Position<E> addLastChild(Position<E> p, E e) {
        if (isEmpty()){
            throw new InvalidPositionException("el arbol esta vacio");
        }
        TNode<E> nodo=checkPosition(p);
        TNode<E> hijo = new TNode<E>(e,nodo);
        nodo.hijos().addFirst(hijo);
        return hijo;
    }

    @Override
    public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
        if (isEmpty()){
            throw new InvalidPositionException("el arbol esta vacio");
        }
    
        TNode<E> sup = checkPosition(p);
        TNode<E> hDer = checkPosition(rb);
        if(hDer.padre()!=sup){
            throw new InvalidPositionException("el padre no es el verdadero");
        }
        TNode<E> nuevo = new TNode<E>(e, sup);
        Iterator<Position<TNode<E>>> ite = sup.hijos().positions().iterator();  //???
        Position<TNode<E>> posB =null;
        while (ite.hasNext()&&posB==null){
            Position<TNode<E>> pos = ite.next();
            if (pos.element()==hDer){
                posB=pos;
            }
        }
        sup.hijos().addBefore(posB, nuevo);
        size++;
        return nuevo;
    }

    @Override
    public Position<E> addAfter(Position p, Position lb, E e) {
        if (isEmpty()){
            throw new InvalidPositionException("el arbol esta vacio");
        }
    
        TNode<E> sup = checkPosition(p);
        TNode<E> hIzq = checkPosition(lb);
        if(hIzq.padre()!=sup){
            throw new InvalidPositionException("el padre no es el verdadero");
        }
        TNode<E> nuevo = new TNode<E>(e, sup);
        Iterator<Position<TNode<E>>> ite = sup.hijos().positions().iterator();  //???
        Position<TNode<E>> posB =null;
        while (ite.hasNext()&&posB==null){
            Position<TNode<E>> pos = ite.next();
            if (pos.element()==hIzq){
                posB=pos;
            }
        }
        sup.hijos().addAfter(posB, nuevo);
        size++;
        return nuevo;
    }

    @Override
    public void removeExternalNode(Position<E> p) {
        if (isInternal(p)){
            throw new InvalidPositionException("el nodo no es externo");
        }
        TNode<E> nodo = checkPosition(p);
        if (isRoot(p)){
            root=null;
            size=0;
        }
        else{   
            Position<TNode<E>> posB = null;
            Iterator<Position<TNode<E>>> ite = nodo.padre().hijos().positions().iterator();
            while (posB==null && ite.hasNext()){
                Position<TNode<E>> pos = ite.next();
                if (pos.element()==nodo){
                    posB= pos;
                }
            }
            nodo.padre().hijos().remove(posB);
            size--;
        }
    }

    @Override
    public void removeInternalNode(Position<E> p) {
        if(isExternal(p)){
            throw new InvalidPositionException("el nodo no es interno");
        }
        TNode<E> nodo = checkPosition(p);
        if(isRoot(p)){
            if (nodo.hijos().size()>1)
            throw new InvalidPositionException("el nodo es la raiz y tiene mas de un hijo");
        }
        
    }

    @Override
    public void removeNode(Position p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeNode'");
    }
    private TNode<E> checkPosition(Position v) {
        TNode<E> nodo= null;
        if (v==null){
            throw new InvalidPositionException("la posicion es nula");
        }
        if (isEmpty()){
            throw new InvalidPositionException("el arbol esta vacio");
        }
        try{
            nodo= (TNode<E>)v;
        }catch (ClassCastException e){
            throw new InvalidPositionException("la posicion es de un tipo incorrecto");
        }
        return nodo;
        //asd
    }
}
