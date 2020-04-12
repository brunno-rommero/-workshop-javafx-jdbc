package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {
	
	@FXML
	private MenuItem menuItemFuncionario;
	@FXML
	private MenuItem menuItemDepartamento;
	
	@FXML
	public void onMenuItemFuncionario() {
		System.out.println("onMenuItemFuncionario");
	}
	
	@FXML
	public void onMenuItemDepartamento() {
		System.out.println("onMenuItemDepartamento");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}
	

}
