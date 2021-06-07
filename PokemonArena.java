import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PokemonArena extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static List<Pokemoni> pokemoni = new ArrayList<>();
    private static Pokemoni pokemon1 = null;
    private static Pokemoni pokemon2 = null;

    private static void ucitaj(){
        try {
            List<String> linije = Files.readAllLines(Paths.get("pokemoni.txt"));
            for(String linija : linije){
                String[] tokeni = linija.split(",");
                String tip = tokeni[0].trim();
                String ime = tokeni[1].trim();
                int nivo = Integer.parseInt(tokeni[2].trim());
                String imeMagije1 = tokeni[3].trim();
                int jacinaMagije1 = Integer.parseInt(tokeni[4].trim());
                String imeMagije2 = tokeni[5].trim();
                int jacinaMagije2 = Integer.parseInt(tokeni[6].trim());
                if(tip.equals("p")){
                    pokemoni.add(new igracPokemon(ime, nivo, new Magija(jacinaMagije1, imeMagije1), new Magija(jacinaMagije2, imeMagije2)));
                }
                else if(tip.equals("n")){
                    pokemoni.add(new igracPokemon(ime, nivo, new Magija(jacinaMagije1, imeMagije1), new Magija(jacinaMagije2, imeMagije2)));
                }
                else{
                    System.out.println("ovo ne sme da se desi!");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10,10,10,10));

        Button btUcitaj = new Button("Ucitaj");
        TextArea ta1 = new TextArea();
        TextArea ta2 = new TextArea();

        RadioButton rdMagija1 = new RadioButton();
        RadioButton rdMagija2 = new RadioButton();
        rdMagija1.setSelected(true);
        ToggleGroup tg = new ToggleGroup();
        rdMagija1.setToggleGroup(tg);
        rdMagija2.setToggleGroup(tg);

        HBox dole = new HBox(10);
        Button btIzaberiMagiju = new Button("Izaberi Magiju");
        Button btSimuliraj = new Button("Simuliraj");

        dole.getChildren().addAll(btIzaberiMagiju,btSimuliraj);

        root.getChildren().addAll(btUcitaj,ta1,ta2,rdMagija1,rdMagija2,dole);

        btUcitaj.setOnAction(e -> {
            ucitaj();
            Collections.sort(pokemoni);
            for(Pokemoni p : pokemoni){
                ta1.appendText(p.toString() + "\n");
            }
            pokemon1 = pokemoni.get(0);

            for(Pokemoni p : pokemoni){
                if(p instanceof CpuPokemon){
                    pokemon2 = p;
                    break;
                }
            }
            rdMagija1.setText(pokemon1.getMagija1().toString());
            rdMagija2.setText(pokemon2.getMagija2().toString());

            ta1.appendText("Izabrani pokemoni: \n" + pokemon1 + "\n" );
            ta1.appendText(pokemon2 + "\n");
        });

        btIzaberiMagiju.setOnAction(e -> {
            if(rdMagija1.isSelected()){
                ((igracPokemon)pokemoni).setIzabranaMagija(1);
                ta2.appendText("[Igrac] bira magiju: " + pokemon1.getMagija1() + "\n");
            }
            else{
                ((igracPokemon)pokemoni).setIzabranaMagija(2);
                ta2.appendText("[Igrac] bira magiju: " + pokemon1.getMagija2() + "\n");
            }
            ta2.appendText("------------------------------");
        });

        btSimuliraj.setOnAction(e -> {
            if(pokemon1.getZivot() <= 0){
                ta2.appendText("Pobedio je CPU\n");
            }
            else if(pokemon2.getZivot() <= 0){
                ta2.appendText("Pobedio je Igrac\n");
            }
            else{
                pokemon1.baciMagiju(pokemon2);
                pokemon2.baciMagiju(pokemon1);
                ta2.appendText("Igrac " + pokemon1 + " je bacio " + pokemon1.getPoslednjaMagija());
                ta2.appendText("CPU " + pokemon2 + " je bacio " + pokemon2.getPoslednjaMagija());
                ta2.appendText("Igrac" + pokemon1.getZivot() + "\n");
                ta2.appendText("CPU" + pokemon2.getZivot() + "\n");
                ta2.appendText("--------------------------");
            }
        });

        Scene scene = new Scene(root,600,500);
        stage.setScene(scene);
        stage.setTitle("Pokemoni");
        stage.show();

    }

}
