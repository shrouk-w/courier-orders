public class Koszyk extends ListaRzeczy{

    public Koszyk()

    {

        super();

    }

    public boolean dodaj(Buyable a) { //boolean ponieważ chciałbym móc sprawdzić przy przepakowywaniu czy dany obiekt wszedł do koszyka czy nie

        if(a.getCena()!=0) {

            super.add(a);

            return true;

        }

        return false;

    }

    // obie metody przepakuj działają tylko dla aktualnie pierwszego obiektu z danej listy, dla wszystkich wywołuje je klient

    @Override

    void przepakuj(ListaRzeczy a) { // jeżeli stać nas na cały obiekt Buyable

        if(a.getClass()==Koszyk.class || a.getClass()==ListaZamowien.class) {

            int index = a.lista.indexOf(lista.getFirst()); //działą bo equals sprawdza rozmiar, typ i dostawa

            if (index != -1) { //jeżeli już mamy np Maxi 1 to chcemy zrobić Maxi 2, a nie Maxi 1 Maxi 1

                a.lista.get(index).setIlosc(a.lista.get(index).getIlosc() + this.lista.getFirst().getIlosc()); //this.lista to nasz koszyk

            } else {

                if (a.getClass() == ListaZamowien.class)

                    ((ListaZamowien) a).dodaj(lista.getFirst());

                else if (a.getClass() == Koszyk.class)

                    ((Koszyk) a).dodaj(lista.getFirst());

                lista.removeFirst();

            }

        }

        else

            throw new RuntimeException("koszyk mozna przepakowac tylko do koszyka lub listy zamowien");

    }

    void przepakuj(ListaRzeczy a, int ilosc) //ilosc to ile jestem w stanie kupic

    {

        if(a.getClass()==Koszyk.class || a.getClass()==ListaZamowien.class) {

            int index=a.lista.indexOf(lista.getFirst()); //działą bo equals sprawdza rozmiar, typ i dostawa

            if(index!=-1) { //jeżeli już mamy np Maxi 1 to chcemy zrobić Maxi 2, a nie Maxi 1 Maxi 1

                a.lista.get(index).setIlosc(a.lista.get(index).getIlosc()+ilosc);

            }

            else {

                Buyable el = lista.getFirst().getcopy();

                el.setIlosc(ilosc);

                if(a.getClass()==Koszyk.class)

                    ((Koszyk) a).dodaj(el);

                else if(a.getClass()==ListaZamowien.class)

                    ((ListaZamowien) a).dodaj(el);

                lista.getFirst().setIlosc(lista.getFirst().getIlosc() - ilosc);

            }

        }

        else

            throw new RuntimeException("koszyk mozna przepakowac tylko do koszyka lub listy zamowien");

    }

}

