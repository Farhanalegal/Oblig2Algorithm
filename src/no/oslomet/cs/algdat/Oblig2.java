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

        public Node(T verdi, Node< T > hode) {
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
    // Oppgave 7
    @Override
    public void nullstill() {
        Node<T> node=hode;
        for(int i=0;i<antall-1;i++){
            node=node.neste;
            node.forrige.neste=null;
            node.forrige.verdi=null;
        }
        this.hode=null;  // Vi kan skrive (hode=hale=null;)
        this.hale=null;  // Vi kan skrive (hode=hale=null;)
        antall=0;
        endringer++;
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
    //oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi,"null er ikke tillatt");
        indekskontroll(indeks,true);
        if(indeks==0){
            hode= new Node<T>(verdi,hode);
        }
        else if (indeks==antall){
            hale=hale.neste=new Node<>(verdi);
        }
        else {
            Node<T> p =finnNode(indeks-1);
            p.neste= new Node<>(verdi,p.neste);
        }
        endringer++;
        antall++;
    }
    // oppgave 4
    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi)==-1)
            return false;
        return true;
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

    //oppgave 6
    @Override
    public boolean fjern(T verdi) {
        if(verdi==null)
            return false;
        Node<T> node= hode;
        while (node!=null){
            if (node.verdi.equals(verdi)){
                break;
            }
            node=node.neste;
        }
        if(node==null)
            return false;
        if (node==hode){   // Verdien her er hode!
            hode=hode.neste;
            if(hode!=null){
                hode.forrige=null;
            }
            else {
                hale=null;
            }
        }
        else if(node==hale){   // Verdien her er hale!
            hale=hale.forrige;
            hale.neste=null;
        } else {             // Verdien her er midten!
            node.forrige.neste=node.neste;
            node.neste.forrige=node.forrige;
        }
        antall--;
        endringer++;

        return true;
    }
    @Override
    public T fjern(int indeks) {
        indekskontroll(indeks,false);
        if(tom())
            return null;
        Node<T>node;
        T verdi;
        if(antall==1){
            verdi=hode.verdi;
            hode=hale=null;

        }
        else if (indeks==0){
            if (antall==2){
                hode=hale;
                hale.neste=null;
                hode.forrige=null;
            }
            else {
                node=hode.neste;
                node.forrige=null;
                hode=node;
            }
            verdi= hode.verdi;
        }
        else if(indeks==antall-1){
            if(antall==2){
                hale=hode;
                hale.neste=null;
                hode.forrige=null;
            }
            else{
                node=hale.forrige;
                node.neste=null;
                hale=node;

            }
            verdi= hale.verdi;;
        }
        else {
            node=finnNode(indeks);
            verdi=node.verdi;
            node.neste.forrige=node.forrige;
            node.forrige.neste=node.neste;
        }
        antall--;
        endringer++;


        return verdi;
    }

}