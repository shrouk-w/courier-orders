import java.util.Collections;

public class Klient {

    Koszyk LastTransaction;

    Koszyk koszyk;

    ListaZamowien wishlist;

    String identyfikator;

    double saldo;

    boolean abonament;

    public Klient(String identyfikator, double saldo, boolean abonament) {

        this.identyfikator = identyfikator;

        this.saldo = saldo;

        this.abonament = abonament;

        koszyk = new Koszyk();

        wishlist = new ListaZamowien();

    }

    public void dodaj(Buyable buyable)

    {

        if(abonament)

            buyable.abonament();

        wishlist.dodaj(buyable);

    }

    public Koszyk getLastTransaction() {

        return LastTransaction;

    }

    public Koszyk pobierzKoszyk() {

        return koszyk;

    }

    public ListaZamowien pobierzListeZamowien() {

        return wishlist;

    }

    public double pobierzPortfel() {

        return saldo;

    }

    public void przepakuj()

    {

        wishlist.przepakuj(koszyk);

    }

    public void zwroc() //domyslnie zwracam cala ostatnia transkacje

    {

        int calacena=LastTransaction.calculatePrice();

        saldo+=calacena;

        while(!LastTransaction.lista.isEmpty())

            LastTransaction.przepakuj(koszyk);

    }

    public void zwroc(rozmiary rozmiar, String typ, int ilosc, opcjedostawy dostawa)

    {

        Collections.sort(LastTransaction.lista);

        Buyable a = new Buyable(rozmiar, typ, dostawa, ilosc);

        int i=LastTransaction.lista.indexOf(a);

        if(i!=-1)

        {

            Collections.swap(LastTransaction.lista, 0, i); //przepakuj działa na 1 elemencie listy

            LastTransaction.przepakuj(koszyk, ilosc);

            double cena=koszyk.lista.getLast().getCena();

            saldo += (cena*ilosc);

            Collections.sort(LastTransaction.lista);

        }

        else throw new RuntimeException("obiekt, ktory nie zostal zakupiony nie moze byc zwrocony");

    }

    public double calculateFirstPrice(metodyplatnosci b)

    {

        double cena=koszyk.lista.getFirst().getCena();

        if(b==metodyplatnosci.KARTA) {

            cena *= (101d/100);

        }

        int ilosc=koszyk.lista.getFirst().getIlosc();

        return cena*ilosc;

    }

    public void zaplac(metodyplatnosci b)

    {

        Collections.sort(koszyk.lista);

        LastTransaction = new Koszyk();

        double calacena=koszyk.calculatePrice();

        if(b==metodyplatnosci.KARTA) {

            calacena *= (101d/100);

            calacena=Math.round(calacena*100)/100d;

        }

        if(calacena<=saldo) {

            while(!koszyk.lista.isEmpty())

                koszyk.przepakuj(LastTransaction);

            saldo-=calacena;

            saldo=Math.round(saldo*100)/100d; //musi byc bo wcześniej wg javy 70.0 - 60.64 = 5.359999999

        }

        else

        {

            while(calculateFirstPrice(b)<=saldo)

            {

                saldo-=calculateFirstPrice(b);

                koszyk.przepakuj(LastTransaction);

            }

            int ile1produktumoznakupic=0;

            while(koszyk.lista.getFirst().getCena()<=saldo) {

                saldo-=koszyk.lista.getFirst().getCena();

                ile1produktumoznakupic+=1;

            }

            if(ile1produktumoznakupic!=0)

                koszyk.przepakuj(LastTransaction,ile1produktumoznakupic);

        }

    }

}

