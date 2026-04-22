package models.entities;

import java.time.LocalDateTime;

public class History implements Identifiable{
	
	private int id;
	private HistoryType historyType;
	private String details;
	private LocalDateTime createdAt;
	
    public History(){
    		
    }
    

	public History(HistoryType historyType, LocalDateTime createdAt) {
    		this.setHistoryType(historyType);
    		this.setCreatedAt(createdAt);
    }

	@Override
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public HistoryType getHistoryType() {
		return historyType;
	}

	public void setHistoryType(HistoryType historyType) {
		this.historyType = historyType;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	 @Override
	public String toString() {
		return "History [id=" + id + ", historyType=" + historyType + ", details=" + details + ", createdAt="
				+ createdAt + "]";
	}
    
    
}
