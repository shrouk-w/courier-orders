import java.util.Collections;

public class ListaZamowien extends ListaRzeczy{

    public ListaZamowien()

    {

        super();

    }

    public void dodaj(Buyable a) {

        super.add(a);

    }

    @Override

    void przepakuj(ListaRzeczy a) {

        if(a.getClass()!=Koszyk.class)

            throw new RuntimeException("liste zakupów można przepakować tylko do koszyka");

        Koszyk b=(Koszyk)a;

        int i=0;

        while(i<lista.size())

        {

            if(b.dodaj(lista.get(i)))

                lista.remove(i);

            else

                i++;

        }

    }

}

