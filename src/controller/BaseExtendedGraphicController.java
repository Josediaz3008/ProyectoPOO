package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import util.SceneManager;

public abstract class BaseExtendedGraphicController extends BaseMenuController{
	
	// Atributtes
	@FXML
	protected Button buttonBack;
	
	// Constructor
	public BaseExtendedGraphicController() {
		super();
	}
	
	// Methods
	protected void initializeBackButton() {
		this.buttonBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "Graphic.fxml", "Graphics", 900, 700);
			}
		});
	}
	
}
