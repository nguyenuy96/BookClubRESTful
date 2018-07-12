package services;

import model.Trade;

public interface TradeService {
	void tradeBook(Trade trade);
	void addTrade(Trade trade);
	Trade getSingleTrade(Trade trade);
}
