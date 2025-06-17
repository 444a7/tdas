package ar.edu.uns.cs.ed.tdas.tdalista;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;

public class IteradorListaDE<E> implements Iterator<E>{
protected PositionList<E> lista;
protected Position<E> cursor;
    public IteradorListaDE(PositionList<E> _lista){
        lista=_lista;
        if (lista.isEmpty()){
            cursor=null;
        }
        else{
            cursor=lista.first();
        }
    }
    @Override
    public boolean hasNext() {       
        return cursor!=null;
    }

    @Override
    public E next() {
        if (cursor==null){
            throw new BoundaryViolationException("no hay siguiente elemento");
        }
        E elemento= cursor.element();
        if (cursor==lista.last()){
            cursor=null;
        }
        else{
            cursor= lista.next(cursor);
        } 
        return elemento;
    }

}