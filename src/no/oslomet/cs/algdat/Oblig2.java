package no.oslomet.cs.algdat;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
public class DobbeltLenketListe<T> implements Liste<T> {
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

        private Node(T verdi)  // konstruktør
        {
            this(verdi, null, null);
        }

    } // Node

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;   // antall endringer i listen


    // konstruktør
    public DobbeltLenketListe(T[] a) {
        this();

        int i = 0;
        for (; i < a.length; i++) ;

        if (i < a.length) {
            Node<T> p = hode = hale = new Node<>(a[i], null, null);  // den første noden
            antall = 1;                                 // vi har minst en node

            for (i++; i < a.length; i++) {
                if (a[i] != null) {
                    p = p.neste = new Node<>(a[i], p, null);   // en ny node

                    antall++;
                }
            }
            if (antall == 0) hode = hale = null;
            else hode = hode.neste;
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


}