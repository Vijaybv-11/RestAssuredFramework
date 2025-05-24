package api.payLoad;

public class StorePayload {
	int id;
	int petId;
	int quantity;
	String shipdate;
	String status;
	boolean complete;
	
	public int getId() {
		return id;
	}
	public int getPetId() {
		return petId;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getShipdate() {
		return shipdate;
	}
	public String getStatus() {
		return status;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setShipdate(String shipdate) {
		this.shipdate = shipdate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

}
