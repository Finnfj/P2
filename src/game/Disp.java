package game;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ws1718_a4.assets.Assets;
import ws1718_a4.basis.Konstanten.Befehl;
import ws1718_a4.basis.Konstanten.SpielStatus;
import ws1718_a4.basis.Level;
import ws1718_a4.basis.LevelIO;
import ws1718_a4.basis.Neuzeichnen;
import ws1718_a4.basis.SpielZustand;
import ws1718_a4.controller.Controller;
import ws1718_a4.darstellung.SpielfeldRenderer;

public class Disp extends Application implements Neuzeichnen {
	private static Neuzeichnen nz;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("PMP2 Spiel");
		Pane root = new GridPane();
		Scene scene = new Scene(root, 500, 300);
		
		Insets small = new Insets(5,5,5,5);
		Insets big = new Insets(10,10,10,10);
		
		//restart button
		ToggleButton restart = new ToggleButton("Spiel neu starten");
		GridPane.setHalignment(restart, HPos.CENTER);
		GridPane.setMargin(restart, big);
		
		//command labels
		Pane labelgrid = new GridPane();
		GridPane.setConstraints(labelgrid, 0, 1);
		List<String> cmds = getCommands();
		List<Label> labels = new ArrayList<Label>();
		
		int i = 0;
		for (String s : cmds) {
			Label label = new Label(s);
			GridPane.setConstraints(label, i%3, (i-i%3)/3);
			GridPane.setHalignment(label, HPos.CENTER);
			GridPane.setMargin(label, small);
			labels.add(label);
			labelgrid.getChildren().add(label);
			i++;
		}
		
		//Textfield
		TextField input = new TextField();
		input.setAlignment(Pos.CENTER);
		GridPane.setConstraints(input, 0, 2);
		GridPane.setMargin(input, small);
		
		//Confirm-buttons
		Pane buttongrid = new GridPane();
		GridPane.setConstraints(buttongrid, 0, 3);
		ToggleButton ok = new ToggleButton("OK");
		ToggleButton delete = new ToggleButton("Löschen");
		GridPane.setConstraints(ok, 0, 0);
		GridPane.setConstraints(delete, 1, 0);
		GridPane.setMargin(ok, small);
		GridPane.setMargin(delete, small);
		buttongrid.getChildren().addAll(ok,delete);
		
		//Console output
		Label output = new Label();
		GridPane.setConstraints(output, 0, 4);
		
		root.getChildren().addAll(restart, labelgrid, input, buttongrid);
		
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
		Level level = LevelIO
			.levelLaden(Assets.class.getResourceAsStream("level01.json"));
		SpielZustand.getInstance().setAktuellerLevel(level);
		SpielZustand.getInstance().setSpielStatus(SpielStatus.SPIELER_ZUG);
		SpielfeldRenderer render = new SpielfeldRenderer();
		Controller controller = new Controller(nz);
		launch(args);
	}

	@Override
	public void neuzeichnen() {
		// TODO Auto-generated method stub
		
	}
	
}
