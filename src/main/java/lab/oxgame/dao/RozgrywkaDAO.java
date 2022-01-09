package lab.oxgame.dao;

import lab.oxgame.model.Rozgrywka;

import java.util.List;

public interface RozgrywkaDAO { //data access object
    int save(Rozgrywka rozgrywka);

    List<Rozgrywka> findAll();

    int deleteAll();
}
