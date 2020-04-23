package gui;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemFuncionario;
	@FXML
	private MenuItem menuItemDepartamento;
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemFuncionario() {
		System.out.println("onMenuItemFuncionario");
	}

	@FXML
	public void onMenuItemDepartamento(ActionEvent event) {
		//System.out.println("onMenuItemDepartamento");
		
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemAbout() {
		loadView("/gui/About.fxml", x -> {});
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);


		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}

	}
	

}
