package game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ws1718_a4.assets.Assets;
import ws1718_a4.basis.Konstanten;
import ws1718_a4.basis.Konstanten.Befehl;
import ws1718_a4.basis.Konstanten.SpielStatus;
import ws1718_a4.basis.Level;
import ws1718_a4.basis.LevelIO;
import ws1718_a4.basis.SpielZustand;
import ws1718_a4.darstellung.SpielfeldRenderer;



/**
 * User interface for predesigned game
 * @author Finn-Frederik Jannsen[2340054]
 * @author Philipp Syperrek
 * @version 1.0, 13.12.2017
 */
public class Disp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("PMP2 Spiel");
		Pane mainpan = new GridPane();
		Pane left = new GridPane();
		GridPane.setConstraints(left, 0, 0);
		Scene scene = new Scene(mainpan, Konstanten.FENSTER_BREITE,
				Konstanten.FENSTER_HOEHE);

		Insets small = new Insets(5, 5, 5, 5);
		Insets big = new Insets(10, 10, 10, 10);

		// restart button
		Button restart = new Button("Spiel neu starten");
		GridPane.setHalignment(restart, HPos.CENTER);
		GridPane.setMargin(restart, big);
		GridPane.setConstraints(restart, 0, 0);

		// command labels
		Pane labelgrid = new GridPane();
		GridPane.setConstraints(labelgrid, 0, 1);
		List<String> cmds = getCommands();
		List<Label> labels = new ArrayList<Label>();

		int i = 0;
		for (String s : cmds) {
			Label label = new Label(s);
			GridPane.setConstraints(label, i % 3, (i - i % 3) / 3);		//modulo calculation for dynamic grid positioning
			GridPane.setHalignment(label, HPos.CENTER);
			GridPane.setMargin(label, small);
			labels.add(label);
			labelgrid.getChildren().add(label);
			i++;
		}

		// Textfield
		TextField input = new TextField();
		input.setAlignment(Pos.CENTER);
		GridPane.setConstraints(input, 0, 2);
		GridPane.setMargin(input, small);

		// Confirm-buttons
		Pane buttongrid = new GridPane();
		GridPane.setConstraints(buttongrid, 0, 3);
		Button ok = new Button("OK");
		Button delete = new Button("Löschen");
		GridPane.setConstraints(ok, 0, 0);
		GridPane.setConstraints(delete, 1, 0);
		GridPane.setMargin(ok, small);
		GridPane.setMargin(delete, small);
		buttongrid.getChildren().addAll(ok, delete);

		// Console output
		StringProperty op = new SimpleStringProperty();
		Label output = new Label();
		GridPane.setMargin(output, small);
		GridPane.setConstraints(output, 0, 4);
		output.textProperty().bind(op);

		// delete button click
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Textfeld geleert");
				op.set("Textfeld geleert");
			}
		});

		// restart button click
		restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Spiel neu gestartet");
				op.set("Spiel neu gestartet");
			}
		});

		// Game initialization
		Level level = LevelIO
				.levelLaden(Assets.class.getResourceAsStream("level01.json"));
		SpielZustand.getInstance().setAktuellerLevel(level);
		SpielZustand.getInstance().setSpielStatus(SpielStatus.SPIELER_ZUG);

		SpielfeldRenderer render = new SpielfeldRenderer();
		GridPane.setConstraints(render, 1, 0);
		GridPane.setMargin(render, big);
		render.neuzeichnen();

		// Create hierarchy
		left.getChildren().addAll(restart, labelgrid, input, buttongrid,
				output);
		mainpan.getChildren().addAll(left, render);
		
		// Load Window
		stage.setScene(scene);
		stage.show();
	}

	private static List<String> getCommands() {
		List<String> commands = new ArrayList<String>();
		for (Befehl befehl : Befehl.values()) {
			commands.add(befehl.toString());
		}
		return commands;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
