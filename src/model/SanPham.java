package model;

public class SanPham {
	private String tt;
	private String tenBanh;
	private String soLuong;
	private String gia;
	private String ngaySX;
	private String hanSD;
	
	public SanPham(String tt, String tenBanh, String soLuong, String gia, String ngaySX, String hanSD) {
		this.tt = tt;
		this.tenBanh = tenBanh;
		this.soLuong = soLuong;
		this.gia = gia;
		this.ngaySX = ngaySX;
		this.hanSD = hanSD;
	}
	
	public String getTt() {
		return tt;
	}

	public void setTt(String tt) {
		this.tt = tt;
	}

	public String getTenBanh() {
		return tenBanh;
	}
	
	public void setTenBanh(String tenBanh) {
		this.tenBanh = tenBanh;
	}
	
	public String getSoLuong() {
		return soLuong;
	}
	
	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}
	
	public String getGia() {
		return gia;
	}
	
	public void setGia(String gia) {
		this.gia = gia;
	}
	
	public String getNgaySX() {
		return ngaySX;
	}
	
	public void setNgaySX(String ngaySX) {
		this.ngaySX = ngaySX;
	}
	
	public String getHanSD() {
		return hanSD;
	}
	
	public void setHanSD(String hanSD) {
		this.hanSD = hanSD;
	}
}
