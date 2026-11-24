import java.util.HashSet;

import java.util.Set;

public class Cennik {

    private static Cennik instance=null;

    public Set<PodmiotWCenniku> cennik; // set zamiast listy żeby łatwiej sobie radzić z podmianą elementów i na pewno nie miec duplikatów

    private Cennik()

    {

        cennik= new HashSet<>();

    }

    public static Cennik pobierzCennik()

    {

        if(instance==null)

            instance=new Cennik();

        return instance;

    }

    public int getPrice(Buyable szukana, opcjedostawy dostawa)

    {

        for(PodmiotWCenniku a: this.cennik)

        {

            if(a.equals(szukana))

            {

                return switch (dostawa)

                {

                    case PUNKT -> a.getCena_punkt();

                    case KURIER -> a.getCena_kurier();

                    case AUTOMAT -> a.getCena_automat();

                };

            }

        }

        return 0;

    }

    public void dodaj(rozmiary rozmiar, String typ, int cena_kurier, int cena_punkt, int cena_automat)

    {

        PodmiotWCenniku a = new PodmiotWCenniku(rozmiar,typ,cena_kurier,cena_punkt,cena_automat);

        if(!cennik.add(a))  //jeżeli nie da się dodać a (istnieje inny obiekt który ma ten sam rozmiar i typ)

        {

            cennik.remove(a); // to usuwam ten obiekt (działa ponieważ a i ten obiekt mają taki sam hashcode)

        }                     // to jest po to jeżeli chce zmienić cene dla jakiegoś rozmiaru i typu który już jest w cenniku

        cennik.add(a);

    }

    public void dodaj(rozmiary rozmiar, String typ, int cena_kurier, int cena_punkt)

    {

        this.dodaj(rozmiar, typ, cena_kurier, cena_punkt, 0);

    }

    public void dodaj(rozmiary rozmiar, String typ, int cena_kurier)

    {

        this.dodaj(rozmiar, typ, cena_kurier, 0, 0);

    }

    public void dodaj(PodmiotWCenniku a) // jeżeli chcemy zrobic sobie w mainie gotowy podmiot do cennika i go wrzucić

    {

        if(!cennik.add(a))

        {

            cennik.remove(a);

        }

        cennik.add(a);

    }

}

