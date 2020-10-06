package no.oslomet.cs.algdat;
import java.util.Comparator;
import java.util.Iterator;
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
    //Oppgave 3-a
    //Hjelpemetode
    private Node<T> finnNode(int indeks) {
        Node<T> p;     //p: returnNode her
        if (indeks < antall / 2) {
            p = hode;
            for (int i = 1; i < indeks; i++) {
                p = p.neste;
            }
        } else {
            p = hale;
            for (int i = 1; i < (antall - indeks); i++) {
                p = p.forrige;
            }
        }
        return p;
    }
    // legge til en konstruktør
    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }
    // Oppgave 1
    // konstruktør
    public DobbeltLenketListe(T[] a) {
        this();
        int i = 0;
        for (; i < a.length && a[i] == null; i++) ; // fikset her
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
    // Oppgave 3-b
    public Liste<T> subliste(int fra, int til) {
        endringer=0;   // Vilkår står i obligen
        fratilKontroll(antall, fra, til);
        Node<T> p = finnNode(fra);
        Liste<T> h = new DobbeltLenketListe<T>();
        for(int i=fra; i<til;i++){
            h.leggInn(p.verdi);
            p=p.neste;
        }
        return h;
    }
    private void fratilKontroll(int antall, int fra, int til) {
        if(fra<0)
            throw new IndexOutOfBoundsException("fra(" +fra+ ") er negativ");
        if(til>antall)       // 'til' her er utenfor tabellen
            throw new IndexOutOfBoundsException("til(" +til+ " )>antall("+ antall +")");
        if (fra> til)
            throw new IllegalArgumentException("fra(" +fra+ ")>til(" +til+ ")- illegalt intervall");
    }
    // Oppgave 1
    @Override
    public int antall() {
        return antall;
    }
    @Override
    public boolean tom() {
        return antall == 0;    // listen blir tom hvis antall er 0
    }
    @Override
    public void nullstill() {
    }
    @Override
    public Iterator< T > iterator() {
        return null;
    }
    //Oppgave 2-a
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append('[');
        if (!tom()) {
            Node< T > p = hode;
            s.append(p.verdi);
            p = p.neste;
            while (p != null) {
                s.append(',').append(' ').append(p.verdi);
                p = p.neste;
            }
        }
        s.append(']');
        return s.toString();
    }
    // Oppgave 2-a
    public String omvendtString()
    {
        StringBuilder s = new StringBuilder();
        s.append('[');
        if(!tom()) {
            Node< T > p = hale;
            s.append(p.verdi);
            p = p.forrige;
            while (p != null) {
                s.append(',').append(' ').append(p.verdi);
                p = p.forrige;
            }
        }
        s.append(']');
        return s.toString();
    }
    // Oppgave 2-b
    @Override
    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi);
        if(tom()) hode = hale = new Node<>(verdi, null, null);
        else hale = hale.neste = new Node<>(verdi,hale, null);
        antall++;
        endringer++;
        return true;
    }
    @Override
    public void leggInn(int indeks, T verdi) {
    }
    // oppgave 4
    @Override
    public boolean inneholder(T verdi) {
        /*if(indeksTil(verdi)==-1) // Den er riktig
            return false;
        return true;*/
        return indeksTil(verdi) !=-1;   // Den er best og enkelt!
    }
    // Oppgave 3-a
    @Override
    public T hent(int indeks) {
        indekskontroll(indeks,false);
        return finnNode(indeks).verdi;
    }

    private void indekskontroll(int indeks, boolean b) {
    }
    // oppgave 4
    @Override
    public int indeksTil(T verdi) {
        if(verdi==null)
            return -1;
        Node<T> p=hode;
        for(int i=0; i<antall;i++){
            if(p.verdi.equals((verdi))) return i;
            p=p.neste;
        }
        return -1;
    }
    // Oppgave 3-a
    @Override
    public T oppdater(int indeks, T nyverdi) {
        indekskontroll(indeks,false);
        Objects.requireNonNull(nyverdi);
        Node<T> p=finnNode(indeks);
        T gammelverdi=p.verdi;
        p.verdi=nyverdi;
        endringer++;
        return gammelverdi;
    }
    @Override
    public boolean fjern(T verdi) {
        return false;
    }
    @Override
    public T fjern(int indeks) {
        return null;
    }

}