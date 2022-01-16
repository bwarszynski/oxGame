package lab.oxgame.engine;

import lab.oxgame.model.OXEnum;

public class OXGameImpl implements OXGame {
    private OXEnum kolej;
    private OXEnum zwyciezca;
    private OXEnum[] plansza;
    private int krok;

    @Override
    public void inicjalizuj(){
        krok = 0;
        zwyciezca = OXEnum.BRAK;
        kolej = Math.random() < 0.5 ? OXEnum.X : OXEnum.O;
        plansza = new OXEnum[] {OXEnum.BRAK, OXEnum.BRAK, OXEnum.BRAK,
                                OXEnum.BRAK, OXEnum.BRAK, OXEnum.BRAK,
                                OXEnum.BRAK, OXEnum.BRAK, OXEnum.BRAK};
    }

    @Override
    public void setPole(int indeks) {
        plansza[indeks] = kolej;

        krok += 1;
        boolean koniec = krok > 8;
        boolean wygrana = (kolej.equals(plansza[0]) && kolej.equals(plansza[4]) && kolej.equals(plansza[8]))
                || (kolej.equals(plansza[2]) && kolej.equals(plansza[4]) && kolej.equals(plansza[6]))
                || (kolej.equals(plansza[0]) && kolej.equals(plansza[1]) && kolej.equals(plansza[2]))
                || (kolej.equals(plansza[3]) && kolej.equals(plansza[4]) && kolej.equals(plansza[5]))
                || (kolej.equals(plansza[6]) && kolej.equals(plansza[7]) && kolej.equals(plansza[8]));
    }
}
