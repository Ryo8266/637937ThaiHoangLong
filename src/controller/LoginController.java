package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connect.AccessConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class LoginController extends AccessConnection{
	
	private Parent parent;
	private Stage stage;
	
	@FXML
	private TextField txtUser;
	
	@FXML
	private PasswordField txtPass;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnExit;
	
	@FXML
	private Label labelMessageLogin;
	
	public void btnLoginOnAction() {
		if (txtUser.getText().isEmpty() == false && txtPass.getText().isEmpty() == false) {
			validateLogin();
		} else {
			labelMessageLogin.setText("Vui lòng nhập Tài khoản và Mật khẩu!");
		}
	}
	
	public void validateLogin() {
		connection = AccessConnection.getConnection();
	
		String sql = "SELECT * FROM user WHERE username='" + txtUser.getText()
				+ "' AND password = '" + txtPass.getText() + "'";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			int isLoginSuccess = 0;
			while (rs.next()) {
				if (rs.getString(1) != null) {
					isLoginSuccess = 1;
					String username = rs.getString(2);
					String password = rs.getString(3);
					String role = rs.getString(4);
					goHomeAdmin();
				}
				break;
			}
			if (isLoginSuccess == 0) {
				labelMessageLogin.setText("Sai thông tin đăng nhập. Vui lòng thử lại!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void btnExitOnAction(ActionEvent event) {
		stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}

	public void goHomeAdmin() throws IOException {
		parent = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
		stage = (Stage) btnLogin.getScene().getWindow();
		stage.resizableProperty().setValue(false);
		stage.setScene(new Scene(parent));
		stage.setX(stage.getY() + 170);
		stage.show();
	}

}
