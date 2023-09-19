package model;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class CartBean implements Serializable {
	private ProductDAO pD = new ProductDAO();
	
	private Map<Integer, Integer> quantity = new HashMap<>();
	private Map<Integer, ProductBean> products = new HashMap<>();
	
	public CartBean() {
		
	}
	
	public boolean isEmpty() {
		return products.size() == 0 ? true : false;
	}
	
	public void remove(int id) {
		quantity.remove(id);
		products.remove(id);
		System.out.print(getQuantities());
	}
	
	public ProductBean getProduct(int id) {
		return products.get(id);
	}
	
	public Integer getQuantity(int id) {
		return quantity.get(id);
	}
	
	public void addProduct(int id, int quantity) {
		if (getQuantity(id) == null)
			insertProduct(id, quantity);
		else
			updateQuantity(id, quantity);
	}
	
	private void insertProduct(int id, int quantity) {
		this.quantity.put(id, quantity);
		this.products.put(id, pD.doRetrieveById(id));
	}
		
	private void updateQuantity(int id, int quantity) {
		setQuantity(id, this.quantity.get(id) + quantity);
	}
	
	public void setQuantity(int id, int quantity) {
		this.quantity.put(id, quantity);
	}
	
	public int getCartSize() {
		return products.size();
	}
	
	public void setProductPrice(int id, float price) {
		this.products.get(id).setPrezzo(price);
	}
	
	public Map<Integer, ProductBean> getProducts(){
		return this.products;
	}
	
	public Map <Integer, Integer> getQuantities(){
		return this.quantity;
	}
}