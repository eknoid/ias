package pl.ias.provider.first.model;

public class Announcement {

	private String topic;
	private String content;
	
	private String address;
	
	private Long roomQuantity;
	private Long surface;
	
	private Long price;
	
	private Agency agency;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(Long roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public Long getSurface() {
		return surface;
	}

	public void setSurface(Long surface) {
		this.surface = surface;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	
}
