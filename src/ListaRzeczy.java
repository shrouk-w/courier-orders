import java.util.ArrayList;

import java.util.List;

public abstract class ListaRzeczy {

    protected List<Buyable> lista;

    protected ListaRzeczy() {

        this.lista = new ArrayList<>();

    }

    public int calculatePrice()

    {

        int lacznacena=0;

        for(Buyable a: lista)

        {

            lacznacena += (a.getCena()*a.getIlosc());

        }

        return lacznacena;

    }

    public void add(Buyable a)

    {

        lista.add(a);

    }

    abstract void przepakuj(ListaRzeczy a);

    @Override

    public String toString() {

        if (!lista.isEmpty()) {

            StringBuilder stringBuilder = new StringBuilder();

            for (var a : lista) {

                stringBuilder.append("\n" + a);

            }

            return stringBuilder.toString() + "\n";

        }

        return "pusto ----";

    }

}

