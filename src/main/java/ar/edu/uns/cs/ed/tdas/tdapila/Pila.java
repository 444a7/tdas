package ar.edu.uns.cs.ed.tdas.tdapila;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyStackException;

public class Pila<E> implements Stack<E>{
    protected java.util.Stack<E> Pila;
    
    public Pila(){
        Pila = new java.util.Stack<E>();
    }
    @Override
    public int size() {
        return Pila.size();
    }

    @Override
    public boolean isEmpty() {
        return Pila.isEmpty();
    }

    @Override
    public E top() {
        if (Pila.isEmpty()){
            EmptyStackException exc = new EmptyStackException("se intento top en pila vacia");
            throw exc;
        }
        return Pila.peek();
    }

    @Override
    public void push(E element) {
        Pila.add(element);
    }

    @Override
    public E pop() {
        if (Pila.isEmpty()){
            EmptyStackException exc = new EmptyStackException("se intento pop en pila vacia");
            throw exc;
        }
        return Pila.pop();
    }

}
