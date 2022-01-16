package lab.oxgame.engine;

import lab.oxgame.model.OXEnum;

public interface OXGame {
    //plansze można odwzorować za pomocą tablicy
    // jednowymiarowej 0,1,2 3,4,5 6,7,8

    //Losowanie kolejności tj. X lub O i resetowanie gry
    void inicjalizuj();

    // Ustawienie pola i aktualizacja stanu gry
    // (zmiana kolejności, sprawdzanie czy jest zwycięzca)
    void setPole(int indeks);

    OXEnum getPole(int indeks);

    //OXEnum.O lub OXEnum.X - wskazuje czyja kolej,
    // po zakończeniu gry OXEnum.Brak
    OXEnum getKolej();

    OXEnum getZwyciezca();
}
