package no.oslomet.cs.algdat;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.Objects;

class DobbeltLenketListe<T> implements Liste<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        // instansvariabler
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste)  // konstruktør
        {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        protected Node(T verdi)  // konstruktør
        {
            this(verdi, null, null);
        }

    } // Node

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;   // antall endringer i listen

    // legge til en konstruktør
    public DobbeltLenketListe(){

        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    // konstruktør
    public DobbeltLenketListe(T[] a) {
        this();

        int i = 0;
        for (; i < a.length && a[i]==null; i++) ; // fikset her

        if (i < a.length) {
            Node<T> p = hode = hale = new Node<>(a[i], null, null);  // den første noden
            antall = 1;                                 // vi har minst en node

            for (i++; i < a.length; i++) {
                if (a[i] != null) {
                    p = p.neste = new Node<>(a[i], p, null);   // en ny node

                    antall++;
                }
            }
            hale = p;
        }


    }

    @Override
    public int antall() {
        return antall;
    }
    @Override
    public boolean tom() {
        return antall == 0;
    }
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append('[');
        Node<T> p = hode;
        s.append(p.verdi);
        p = p.neste;
        while (p!=null){
            s.append(',').append(' ').append(p.verdi);
            p = p.neste;
        }
        s.append(']');
        return s.toString();
    }
    public String omvendtString()
    {
        StringBuilder s = new StringBuilder();
        s.append('[');
        Node<T> p = hale;
        s.append(p.verdi);
        p = p.forrige;
        while (p!=null){
            s.append(',').append(' ').append(p.verdi);
            p = p.forrige;
        }
        s.append(']');
        return s.toString();
    }
    @Override
    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi);
        if(tom()) hode = hale = new Node<>(verdi, null, null);
        else hale = hale.neste = new Node<>(verdi,hale, null);
        return true;
    }

}