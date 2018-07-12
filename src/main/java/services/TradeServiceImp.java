package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.TradeDAO;
import model.Trade;

@Service
@Transactional(propagation = Propagation.SUPPORTS ,readOnly=true)
public class TradeServiceImp implements TradeService{

	@Autowired
	private TradeDAO tradeDao;
	
	@Transactional
	@Override
	public void tradeBook(Trade trade) {
		tradeDao.tradeBook(trade);
	}

	@Transactional
	@Override
	public void addTrade(Trade trade) {
		tradeDao.addTrade(trade);
	}

	@Override
	public Trade getSingleTrade(Trade trade) {
		return tradeDao.getSingleTrade(trade);
	}

}
