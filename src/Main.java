public class Main {
    // cena przesyłek danego typu z koszyka
    static int cena(Koszyk k, String typ) {
        int licznik=0;
        for(Buyable a: k.lista)
        {
            if(a.getTyp().equals(typ))
                licznik+=(a.getCena()*a.getIlosc());
        }
        return licznik;
    }

    public static void main(String[] args) {

        // cennik
        Cennik cennik = Cennik.pobierzCennik();

        // dodawanie nowych cen do cennika
        cennik.dodaj(rozmiary.MINI, "zwykly", 12, 10, 8); 	// jeśli klient odbierze w punkcie: 10 zł/przesyłkę
        // jeśli klient odbierze w automacie: 8 zł/przesyłkę
        // jeśli kurier dostarczy przesyłkę: 12 zł/przesyłkę

        cennik.dodaj(rozmiary.MINI, "ekspres", 16, 14, 12);	// jak wyżej

        cennik.dodaj(rozmiary.SREDNI, "ubezp", 20, 15);		// jeśli kurier dostarczy przesyłkę: 20 zł/przesyłkę
        // jeśli klient odbierze w punkcie: 15 zł/przesyłkę
        // nie ma możliwości odbioru w automacie

        cennik.dodaj(rozmiary.MAXI, "ekspres", 20);		// przesyłka dostarczona tylko przez kuriera: 20 zł/przesyłkę

        // Klient Blyskawica deklaruje kwotę 70 zł na zamówienia przesyłek
        // true oznacza, że klient posiada abonament
        Klient blyskawica = new Klient("Błyskawica", 70, true);

        // Klient Błyskawica dodaje do listy zamówień różne przesyłki:
        // Przesyłka mini - zwykły, ilość: 4, sposób odbioru: w punkcie odbioru
        // Przesyłka średni - zwykly, ilość: 3, sposób odbioru: przez kuriera
        // Przesyłka maxi - ekspres, ilość: 2, sposób odbioru: w automacie
        // Przesyłka mini - ekspres, ilość: 4, sposób odbioru: przez kuriera
        blyskawica.dodaj(new Mini("zwykly", 4, opcjedostawy.PUNKT));
        blyskawica.dodaj(new Sredni("zwykly", 3, opcjedostawy.KURIER));
        blyskawica.dodaj(new Maxi("ekspres", 2, opcjedostawy.KURIER));
        blyskawica.dodaj(new Mini("ekspres", 4, opcjedostawy.AUTOMAT));

        // Lista zamówień klienta Błyskawica
        ListaZamowien listaBlyskawicy = blyskawica.pobierzListeZamowien();

        System.out.println("Lista zamówień klienta " + listaBlyskawicy);

        // Przed płaceniem, klient przepakuje przesyłki z listy zamówień do koszyka.
        // Możliwe, że na liście zamówień są rodzaje przesyłek niemające ceny w cenniku,
        // w takim przypadku nie trafiłyby do koszyka
        Koszyk koszykBlyskawicy = blyskawica.pobierzKoszyk();
        blyskawica.przepakuj();

        // Co jest na liście zamówień klienta Błyskawica
        System.out.println("Po przepakowaniu, lista zamówień klienta " + blyskawica.pobierzListeZamowien());

        // Co jest w koszyku klienta Błyskawica
        System.out.println("Po przepakowaniu, koszyk klienta " + koszykBlyskawicy);

        // Ile wynosi cena wszystkich przesyłek typu ekspresowego w koszyku klienta Błyskawica
        System.out.println("Przesyłki ekspresowe w koszyku klienta Błyskawica kosztowały:  " + cena(koszykBlyskawicy, "ekspres"));

        // Klient zapłaci...
        // w przypadku posiadania abonamentu klient dostanie 50% rabatu od każdej przesyłki
        blyskawica.zaplac(metodyplatnosci.KARTA);	// płaci kartą płatniczą, prowizja 1%

        // Ile klientowi Błyskawica zostało pieniędzy?
        System.out.println("Po zapłaceniu, klientowi Błyskawica zostało: " + blyskawica.pobierzPortfel() + " zł");

        // Mogło klientowi zabraknąć środków, wtedy aplikacja odłoży niektóre przesyłki w koszyku
        // wpp. koszyk jest pusty po zapłaceniu
        System.out.println("Po zapłaceniu, koszyk klienta " + blyskawica.pobierzKoszyk());
        System.out.println("Po zapłaceniu, koszyk klienta " + koszykBlyskawicy);

        // Teraz przychodzi klient Żółwik,
        // deklaruje 130 zł na zamówienia
        Klient zolwik = new Klient("Żółwik", 130, false);

        // Zamówił za dużo jak na tę kwotę
        zolwik.dodaj(new Mini("zwykly", 4, opcjedostawy.KURIER));
        zolwik.dodaj(new Maxi("ekspres", 5, opcjedostawy.KURIER));

        // Co klient Żółwik ma na swojej liście zamówień
        System.out.println("Lista zamówień klienta " + zolwik.pobierzListeZamowien());

        Koszyk koszykZolwika = zolwik.pobierzKoszyk();
        zolwik.przepakuj();

        // Co jest na liście zamówień klienta Żółwik
        System.out.println("Po przepakowaniu, lista zamówień klienta " + zolwik.pobierzListeZamowien());

        // A co jest w koszyku klienta Żółwik
        System.out.println("Po przepakowaniu, koszyk klienta " + zolwik.pobierzKoszyk());

        // klient Żółwik płaci
        zolwik.zaplac(metodyplatnosci.PRZELEW);	// płaci przelewem, bez prowizji

        // Ile klientowi Żółwik zostało pieniędzy?
        System.out.println("Po zapłaceniu, klientowi Żółwik zostało: " + zolwik.pobierzPortfel() + " zł");

        // Co zostało w koszyku klienta Żółwik (za mało pieniędzy miał)
        System.out.println("Po zapłaceniu, koszyk klienta " + koszykZolwika);

        zolwik.zwroc(rozmiary.MINI, "zwykly", 1, opcjedostawy.KURIER);	// zwrot (do koszyka) 1 przesyłki mini zwykłej (z dostawą od kuriera) z ostatniej transakcji

        // Ile klientowi Zółwik zostało pieniędzy?
        System.out.println("Po zwrocie, klientowi Zółwik zostało: " + zolwik.pobierzPortfel() + " zł");

        // Co zostało w koszyku klienta Żółwik
        System.out.println("Po zwrocie, koszyk klienta " + koszykZolwika);
    }
}