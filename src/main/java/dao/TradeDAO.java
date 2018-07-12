package dao;

import java.util.List;

import model.Trade;

public interface TradeDAO {
	void tradeBook(Trade trade);
	void addTrade(Trade trade);
	List<Trade> getAllTrade();
	List<Trade> getWaitingTrade();
	List<Trade> getHistoryTrade();
	Trade getSingleTrade(Trade trade);
}
