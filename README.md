DobbeltLenkeListe,Obligatorisk 2:
Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
Hogi Garo Ali, S344094, S344094@oslomet.no.
Farhan Eizo Alegal, S341869, S341869@oslomet.no.

Oppgave 1:

Metoden her skal lage en dobbeltlenketliste med verdiene
fra tabellen a og de ligges i samme rekkefølge i listen.
Vi må passe på hvis a=0 her, tabellen blir null!
Hvis alle verdiene i a er null,får vi en tom liste.

Oppgaven 2:

Det er to metoder, en av dem til å returerne tegnstreng
fra listesverdier og her det er ikke tom liste.
Dette gjøres gjennom en ny    StringBuilder s = new StringBuilder();
Handlingsmekanismen begynner med å bygge tegnstreng eller
verdier fra Hode til Hale by neste-pekere.
Tvert imot OmvendtString fra Hale til Hode by forrige-pekere.
 boolean legginn metoden, det skal legge alle nye noder med
 oppgitt dem verdi bakerst i liste.
NB-Ikke glemme hvis Listen er tom (hode=hale=0) elle ikke!

Oppgaven 3:

Vi løser den avhengige av oppreter en metode finnNode, og gjennom indeks
for node og gjennom vikår som står i obligen 3-a(indeks<antal/2), søk 
blir gjort fra hode til hale hvis vilkår er oppfylt eller går motsatt.
jeg brukte også en metode til å hjelpe kalles 'hent' og så vi må sjekker indeks!
Ettre det lagd vi metoden til å erstatte verdien på plass indeks med ny verdi.
NB:
null-verdier er ikke tillatt.
endringers skal økes også.
Subliste metoden her skal returnere en liste som inneholder verdier fra intervallet fra:til.
Vi skal bruke også kontrollmetode, det er lov til å bli intervall tomt her.

Oppgave 4:

Metoden skal returnere indeksen eller posisjonen til verdei når 
den finnes den I listen!
Hvis den finnes ikke verdi skal returnere -1 og ingen problem Hvis
verdi er null her.Det betyr bruker ikke kastes unntak.
I tillegg bruker vi metode til å returnere true (hvis listen innholder verdi)/false(ikke innholder).

Oppgave 5:

Metoden i denne oppgave skal legge verdi inn i listen, men
den har vilkår at negative indeks og indeksen >antall er ulovlig her!
Vi må passe på noe slik at:
Listen er tom / verdien skal legges først,bakest og mellom to andre verdier.

Oppgave 6:

Det er to metoder i oppgave 6 som det fungerer:
-skal fjerne og returnere verdien på posisjon indeks (må sjekkes også).
-fjerne verdien fra listen og returnere true.
Hvis kommer flere forekomster av verdier skal fjernes første av dem fra venstre.
NB:
Hvis verdi=0 og ikke i liste skal returnere false ikke kastes unntak.
I begge metodene må den første fjernes/siste også/mellom to andre.
I tillegg til noe vilkår i teksten må man passe på dem.

Oppgave 7:

Metoden her skal tømme listen og nulle alt slik at søppeltommeren

kan hente alt som ikke lenger brukes.
Metoden starter fra hoden mot halen ved hjelp pekeren neste!

Oppgave 8:

Den skal først sjekke om iteratorendringer er lik endringer.Deretter "denne" flyttes til den neste node.
Metoden i oppgave b returnere en instans av iteratorklassen.
Metoden i oppgave c skal sette pekeren "denne" til den noden indeks.
Metoden i oppgave d det må sjekke at indeksen er lovlig!
