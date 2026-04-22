package controllers;

import models.data_structures.Stack.Stack;
import services.history.HistoryService;

public class HistoryController {
	
	private HistoryService historyService;
	
	public Stack listHistory() {
		return this.historyService.listHistory();
	}

}
