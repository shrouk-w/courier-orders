import java.util.Objects;

public class Buyable implements Comparable<Buyable>{

    private rozmiary rozmiar;

    private String typ;

    private opcjedostawy dostawa;

    private int ilosc;

    private double cena;

    public void abonament() {

        this.cena /=2;

    }

    public Buyable(rozmiary rozmiar, String typ, opcjedostawy dostawa, int ilosc) {

        this.rozmiar = rozmiar;

        this.typ = typ;

        this.dostawa = dostawa;

        this.ilosc=ilosc;

        cena = Cennik.pobierzCennik().getPrice(this, dostawa);

    }

    public Buyable getcopy()

    {

        return new Buyable(this.rozmiar,this.typ,this.dostawa,this.ilosc);

    }

    public rozmiary getRozmiar() {

        return rozmiar;

    }

    public String getTyp() {

        return typ;

    }

    public opcjedostawy getDostawa() {

        return dostawa;

    }

    public double getCena() {

        return cena;

    }

    public int getIlosc() {

        return ilosc;

    }

    public void setIlosc(int ilosc) {

        this.ilosc = ilosc;

    }

    @Override

    public int hashCode() { // po to żeby equals działał na rzecz podmiotów w cenniku

        return Objects.hashCode(rozmiar)*Objects.hashCode(typ);

    }

    @Override

    public boolean equals(Object obj) {

        Buyable a = (Buyable) obj;

        return (this.hashCode()==obj.hashCode() && this.dostawa==a.dostawa);

    }

    @Override

    public int compareTo(Buyable o) {

        if(this.cena<o.cena)

            return -1;

        else if(this.cena>o.cena)

            return 1;

        return 0;

    }

    @Override

    public String toString() {

        return  rozmiar +

                ", typ: " + typ +

                ", ile: "+ilosc+

                ", dostawa=" + dostawa +

                ", cena=" + cena;

    }

}

