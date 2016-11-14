package Data_collector.data_collector.DataCollector;

public class KafkaMessage {
	private String value;
	private String status;
	private String itemName;
	private String timestamp;
	
	public KafkaMessage(String v, String s, String i, String t){
		this.setValue(v);
		this.setStatus(s);
		this.setItemName(i);
		this.setTimestamp(t);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
