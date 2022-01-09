package lab.oxgame.model;

import java.time.LocalDateTime;

public class Rozgrywka {
    private Integer rozgrywkaId;
    private String graczX;
    private String graczO;
    private OXEnum zwyciezca;
    private LocalDateTime dataczasRozgrywki;

    public Rozgrywka() {}

    public Rozgrywka(Integer rozgrywkaId, String graczX, String graczO, OXEnum zwyciezca, LocalDateTime dataczasRozgrywki) {
        this.rozgrywkaId = rozgrywkaId;
        this.graczX = graczX;
        this.graczO = graczO;
        this.zwyciezca = zwyciezca;
        this.dataczasRozgrywki = dataczasRozgrywki;
    }

    public Rozgrywka(String graczX, String graczO, OXEnum zwyciezca, LocalDateTime dataczasRozgrywki) {
        this.graczX = graczX;
        this.graczO = graczO;
        this.zwyciezca = zwyciezca;
        this.dataczasRozgrywki = dataczasRozgrywki;
    }

    public Integer getRozgrywkaId() {
        return rozgrywkaId;
    }

    public void setRozgrywkaId(Integer rozgrywkaId) {
        this.rozgrywkaId = rozgrywkaId;
    }

    public String getGraczX() {
        return graczX;
    }

    public void setGraczX(String graczX) {
        this.graczX = graczX;
    }

    public String getGraczO() {
        return graczO;
    }

    public void setGraczO(String graczO) {
        this.graczO = graczO;
    }

    public OXEnum getZwyciezca() {
        return zwyciezca;
    }

    public void setZwyciezca(OXEnum zwyciezca) {
        this.zwyciezca = zwyciezca;
    }

    public LocalDateTime getDataczasRozgrywki() {
        return dataczasRozgrywki;
    }

    public void setDataczasRozgrywki(LocalDateTime dataczasRozgrywki) {
        this.dataczasRozgrywki = dataczasRozgrywki;
    }
}
