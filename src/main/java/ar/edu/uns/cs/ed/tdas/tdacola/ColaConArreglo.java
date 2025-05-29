package ar.edu.uns.cs.ed.tdas.tdacola;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;
public class ColaConArreglo<E> implements Queue<E>{
    private static int cap=1000;
    protected E[] cola;
    protected int cantidad;
    @SuppressWarnings("unchecked")
    public ColaConArreglo(int capacidad){
        cola = (E[]) new Object[capacidad];
    }
    @SuppressWarnings("unchecked")
    public ColaConArreglo(){
        cola = (E[]) new Object[cap];
    }

    @Override
    public int size() {
        return cola.length;
    }

    @Override
    public boolean isEmpty() {
        return cantidad==0;
    }

    @Override
    public E front() {
        if (cantidad==0){
            EmptyQueueException exc = new EmptyQueueException("se intento front en una cola vacia");
            throw exc;
        }
        return cola[0];
    }

    @Override
    public void enqueue(E element) {
        if (cantidad==cola.length){
            E[] cola2 = (E[]) new Object[cola.length*2];
            for (int i=0;i<cola.length;i++){
                
            }
        }
        cola[cantidad] = element;
    }

    @Override
    public E dequeue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dequeue'");
    }
}
