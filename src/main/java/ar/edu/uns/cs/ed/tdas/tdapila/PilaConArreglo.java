package ar.edu.uns.cs.ed.tdas.tdapila;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyStackException;

public class PilaConArreglo<E> implements Stack<E>{
    protected static int Cap=1000;
    protected E[] Pila;
    protected int cantidad;
    
    @SuppressWarnings("unchecked")
    public PilaConArreglo(int capacidad){
        Pila = (E[]) new Object[capacidad];
        cantidad=0;
    }
    @SuppressWarnings("unchecked")
    public PilaConArreglo(){
        Pila = (E[]) new Object[Cap];
        cantidad=0;
    }

    @Override
    public int size() {
        return cantidad;
    }

    @Override
    public boolean isEmpty() {
        return cantidad==0;
    }

    @Override
    public E top() {
        if (cantidad==0){
            EmptyStackException exc = new EmptyStackException(("se intento hacer top en una pila vacia"));
            throw exc;
        }
        return Pila[cantidad-1];
    }

    @Override
    public void push(E element) {
        if (cantidad==Pila.length){
            @SuppressWarnings("unchecked")
            E[] Pila2 = (E[]) new Object[Pila.length*2];
            for (int i=0;i<Pila.length;i++){
                Pila2[i]=Pila[i];
            }
            Pila= Pila2;
        }
        Pila[cantidad]=element;
        cantidad++;
    }

    @Override
    public E pop() {
        if (cantidad==0){
            EmptyStackException exc = new EmptyStackException(("se intento hacer pop en una pila vacia"));
            throw exc;
        }
        E aux= Pila[cantidad-1];
        cantidad--;
        return aux; 
    }
    
}
