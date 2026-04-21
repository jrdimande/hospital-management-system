package services.history;

import java.time.LocalDateTime;

import models.data_structures.Stack.Stack;
import models.entities.History;
import models.entities.HistoryType;
import repositories.HistoryRepository;

public class HistoryService {
	
	private Stack history;
	private HistoryRepository historyRepository;
	
	public HistoryService() {
		this.historyRepository = new HistoryRepository();
		this.history = this.historyRepository.findAll();
	}
	
	/**
	 * List the history of the system
	 * @return the system history
	 */
	public Stack listHistory() {
		return this.history;
	}
	
	public void addToHistory(String operation, HistoryType historyType) {
		History history = new History();
		history.setDetails(operation);
		history.setCreatedAt(LocalDateTime.now());
		history.setHistoryType(historyType);
		
		this.history.push(history);
		this.historyRepository.save(history);
	}
	
}
