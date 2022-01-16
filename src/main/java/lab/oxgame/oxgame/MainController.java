package lab.oxgame.oxgame;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lab.oxgame.dao.RozgrywkaDAO;
import lab.oxgame.dao.RozgrywkaDAOImpl;
import lab.oxgame.engine.OXGame;
import lab.oxgame.engine.OXGameImpl;
import lab.oxgame.model.OXEnum;
import lab.oxgame.model.Rozgrywka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private RozgrywkaDAO rozgrywkaDAO;
    private ExecutorService executor;
    private OXGame oxgame;

    @FXML
    private TableView<Rozgrywka> rozgrywkaTable;
    @FXML
    private TableColumn<Rozgrywka, Integer> rozgrywkaIdColumn;
    @FXML
    private TableColumn<Rozgrywka, String> graczXColumn;
    @FXML
    private TableColumn<Rozgrywka, String> graczOColumn;
    @FXML
    private TableColumn<Rozgrywka, OXEnum> zwyciezcaColumn;
    @FXML
    private TableColumn<Rozgrywka, LocalDateTime> dataczasRozgrywkiColumn;

    private ObservableList<Rozgrywka> history;

    public MainController(){
        rozgrywkaDAO = new RozgrywkaDAOImpl();
        executor.Executors.newSingleThreadExecutor();
        oxGame = new OXGameImpl();
    }

    @FXML
    private void initialize() {
        // 1. POWIAZANIE KOLUMN Z POLAMI KLASY ROZGRYWKA
        rozgrywkaIdColumn
                .setCellValueFactory(new PropertyValueFactory<Rozgrywka, Integer>("rozgrywkaId"));
        graczOColumn
                .setCellValueFactory(new PropertyValueFactory<Rozgrywka, String>("graczO"));
        graczXColumn
                .setCellValueFactory(new PropertyValueFactory<Rozgrywka, String>("graczX"));
        zwyciezcaColumn
                .setCellValueFactory(new PropertyValueFactory<Rozgrywka, OXEnum>("zwyciezca"));
        dataczasRozgrywkiColumn
                .setCellValueFactory(new PropertyValueFactory<Rozgrywka, LocalDateTime>("dataczasRozgrywki"));
        // 2. UTWORZENIE LISTY OBSERWOWALNEJ I JEJ POWIĄZANIE TABELA
        history = FXCollections.observableArrayList();
        rozgrywkaTable.setItems(history);
        // 3. POBRANIE Z BAZY HISTORII I ZAŁADOWANIE WYNIKÓW
        executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<Rozgrywka> rozgrywki = new ArrayList<>();
            try{
                rozgrywki.addAll(rozgrywkaDAO.findAll());
            } catch (Exception e) {
                String errMsg = "Błąd podczas inicjalizacji historii rozgrywek!";
                logger.error(errMsg, e);
                String errDetails = e.getCause() != null ?
                        e.getMessage() + "\n" + e.getCause().getMessage() :
                        e.getMessage();
                Platform.runLater(() -> showError(errMsg, errDetails));
            }
            Platform.runLater(() -> history.addAll(rozgrywki));
        });
    }
    public void shutdown() {
        executor.shutdownNow();
    }

    private void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }



    @FXML
    private void onActionBtnReset(ActionEvent event) {
        testDB();
    }

    private void testDB() {
        try {
            Rozgrywka rozgrywka = new Rozgrywka("Bartosz", "Radek", OXEnum.O, LocalDateTime.now());
            rozgrywkaDAO.save(rozgrywka);
            logger.info("ID utworzonej rozgrywki {}", rozgrywka.getRozgrywkaId());
            List<Rozgrywka> rozgrywki = rozgrywkaDAO.findAll();
            for (Rozgrywka r : rozgrywki) {
                logger.info("Projekt - Id: {}, Gracz O: {}, Gracz X: {}", r.getRozgrywkaId(), r.getGraczO(), r.getGraczX());
            }
            //rozgrywkaDAO.deleteAll();
        } catch (Exception e) {
            logger.error("Błąd podczas testowania operacji bazodanowych!", e);
        }
    }

    @FXML
    public void onActionBtn1(ActionEvent event){
        ruch((Button)event.getSource(),1);
    }

    private void ruch(Button btn, int index) {
        OXEnum kolej = oxGame.getKolej();
        if (!OXEnum.BRAK.equals(oxGame.getKolej())){
            if (OXEnum.BRAK.equals(oxGame.getPole())){
                btn.setText(kolej.toString());
                oxGame.setPole(index);
                kolej = oxGame.getKolej();
                //TODO
                // Jeśli koniec rozgrywki - zapisanie do bazy i aktualizacja listy history
                // lub tylko aktualizacja komunikatu, np. Ruch gracza X
            }
        }
    }
}
