import java.util.Objects;

public class PodmiotWCenniku {

    private rozmiary rozmiar;

    private String typ;

    private int cena_kurier;

    private int cena_punkt;

    private int cena_automat;

    public PodmiotWCenniku(rozmiary rozmiar, String typ, int cena_kurier, int cena_punkt, int cena_automat) {

        this.rozmiar = rozmiar;

        this.typ = typ;

        this.cena_kurier = cena_kurier;

        this.cena_punkt =  cena_punkt;

        this.cena_automat =  cena_automat;

    }

    public PodmiotWCenniku(rozmiary rozmiar, String typ, int cena_kurier, int cena_punkt) {

        this(rozmiar, typ, cena_kurier, cena_punkt,0);

    }

    public PodmiotWCenniku(rozmiary rozmiar, String typ, int cena_kurier) {

        this(rozmiar, typ, cena_kurier,0,0);

    }

    public PodmiotWCenniku(rozmiary rozmiar, String typ) {

        this(rozmiar, typ, 0,0,0);

    }

    public int getCena_kurier() {

        return (cena_kurier);

    }

    public int getCena_punkt() {

        return cena_punkt;

    }

    public int getCena_automat() {

        return cena_automat;

    }

    @Override

    public int hashCode() { // po to żeby cennik jako set nie chciał trzymać 2 takich samych rozmiar-typów

        return Objects.hashCode(rozmiar)*Objects.hashCode(typ);

    }

    @Override

    public boolean equals(Object obj) { // po to żeby można było znaleźć cene dla getPrice() (taki sam rozmaiar i typ)

        return this.hashCode() == obj.hashCode();

    }

}

