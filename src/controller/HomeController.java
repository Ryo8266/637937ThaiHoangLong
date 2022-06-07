package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import connect.ProductDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.SanPham;

public class HomeController implements Initializable{
	private Parent parent;
	private Stage stage;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField textSearch;

	@FXML
	private Button btnSearch;

	@FXML
	private TextField textTT;

	@FXML
	private TextField textTenBanh;

	@FXML
	private TextField textSoLuong;

	@FXML
	private TextField textGia;

	@FXML
	private TextField textNgaySX;

	@FXML
	private TextField textHanSD;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDelete;
	
	@FXML
	private TableView<SanPham> table;

	@FXML
	private TableColumn<SanPham, String> colTT;

	@FXML
	private TableColumn<SanPham, String> colTenBanh;

	@FXML
	private TableColumn<SanPham, String> colSoLuong;

	@FXML
	private TableColumn<SanPham, String> colGia;

	@FXML
	private TableColumn<SanPham, String> colNgaySX;

	@FXML
	private TableColumn<SanPham, String> colHanSD;

	ObservableList<SanPham> list_product = FXCollections.observableArrayList();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	SanPham product = null;
	int index = -1;

	ProductDB productDb = new ProductDB();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initAndReloadTable();
		colTT.setCellValueFactory(new PropertyValueFactory<>("tt"));
		colTenBanh.setCellValueFactory(new PropertyValueFactory<>("tenBanh"));
		colSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
		colGia.setCellValueFactory(new PropertyValueFactory<>("gia"));
		colNgaySX.setCellValueFactory(new PropertyValueFactory<>("ngaySX"));
		colHanSD.setCellValueFactory(new PropertyValueFactory<>("hanSD"));
		table.setItems(list_product);
	}
	
	public void searchProduct(ActionEvent event) {
		String temp_search = textSearch.getText();
		list_product.clear();
		List<SanPham> productList = productDb.searchProductList(temp_search);
		for (SanPham product : productList) {
			list_product.add(product);
		}
	}

	public void addOnAction(ActionEvent event) throws SQLException {
		String temp_tt = textTT.getText();
		String temp_tenBanh = textTenBanh.getText();
		String temp_soLuong = textSoLuong.getText();
		String temp_gia = textGia.getText();
		String temp_ngaySX = textNgaySX.getText();
		String temp_hanSD = textHanSD.getText();

		if (temp_tt.equals("") || temp_tenBanh.equals("") || temp_soLuong.equals("") || temp_gia.equals("")
				|| temp_ngaySX.equals("") || temp_hanSD.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Lỗi dữ liệu nhập vào");
			alert.setContentText("Không được bỏ trống!");
			alert.show();
		}

		SanPham product = new SanPham(temp_tt, temp_tenBanh, temp_soLuong, temp_gia, temp_ngaySX, temp_hanSD);
		productDb.addProduct(product);
		reset();
		list_product.clear();
		initAndReloadTable();
	}

	public void editOnAction(ActionEvent event) throws SQLException {
		String temp_tt = textTT.getText();
		String temp_tenBanh = textTenBanh.getText();
		String temp_soLuong = textSoLuong.getText();
		String temp_gia = textGia.getText();
		String temp_ngaySX = textNgaySX.getText();
		String temp_hanSD = textHanSD.getText();

		if (temp_tt.equals("") || temp_tenBanh.equals("") || temp_soLuong.equals("") || temp_gia.equals("")
				|| temp_ngaySX.equals("") || temp_hanSD.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Lỗi dữ liệu nhập vào");
			alert.setContentText("Không được bỏ trống!");
			alert.show();
		}

		SanPham product = new SanPham(temp_tt, temp_tenBanh, temp_soLuong, temp_gia, temp_ngaySX, temp_hanSD);
		productDb.updateProduct(product);
		reset();
		list_product.clear();
		initAndReloadTable();
	}

	public void deleteOnAction(ActionEvent event) throws SQLException {
		String temp_tt = textTT.getText();
		productDb.deleteProduct(temp_tt);
		reset();
		list_product.clear();
		initAndReloadTable();
	}
	
	public void selectData(MouseEvent event) {
		index = table.getSelectionModel().getSelectedIndex();
		System.out.println(index);

		if (index <= -1)
			return;
		
		textTT.setText(colTT.getCellData(index).toString());
		textTenBanh.setText(colTenBanh.getCellData(index).toString());
		textSoLuong.setText(colSoLuong.getCellData(index).toString());
		textGia.setText(colGia.getCellData(index).toString());
		textNgaySX.setText(colNgaySX.getCellData(index).toString());
		textHanSD.setText(colHanSD.getCellData(index).toString());
	}

	void initAndReloadTable() {
		List<SanPham> productList = productDb.getProductList();
		for (SanPham product : productList) {
			list_product.add(product);
		}
	}
	
	void reset() {
		textTT.setText("");
		textTenBanh.setText("");
		textSoLuong.setText("");
		textGia.setText("");
		textNgaySX.setText("");
		textHanSD.setText("");
	}
}
