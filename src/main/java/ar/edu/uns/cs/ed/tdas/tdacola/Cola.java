package ar.edu.uns.cs.ed.tdas.tdacola;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;

public class Cola<E> implements Queue<E> {
    protected java.util.Queue<E> Cola;
    public Cola(){
        Cola = new java.util.LinkedList<E>(); 
    }
    @Override
    public int size() {
        return Cola.size();
    }

    @Override
    public boolean isEmpty() {
        return Cola.isEmpty();
    }

    @Override
    public E front() {
        if (Cola.isEmpty()){
            EmptyQueueException exc = new EmptyQueueException("se intento hacer front en una cola vacia");
            throw exc;
        }
        return Cola.peek();
    }

    @Override
    public void enqueue(E element) {
        Cola.add(element);
    }

    @Override
    public E dequeue() {
        if (Cola.isEmpty()){
            EmptyQueueException exc = new EmptyQueueException("Se intento dequeue en cola vacia");
            throw exc;
        }
        return Cola.remove();
    }

}
