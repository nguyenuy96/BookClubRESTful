package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Components.HandleException;
import bookclub.ExceptionThrower;
import model.Trade;
import services.TradeService;

@RestController
@CrossOrigin
@RequestMapping(path = "/trade")
public class TradeController {
	@Autowired
	private TradeService tradeService;
	@RequestMapping(path = "/")
	public ResponseEntity<HttpStatus> takeTradeBook(@RequestBody Trade trade) throws HandleException{
		if(tradeService.getSingleTrade(trade) != null) {
			tradeService.tradeBook(trade);
		}else {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Invalid Trade");
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@RequestMapping(path = "/addtrade")
	public ResponseEntity<HttpStatus> addTradeBook(@RequestBody Trade trade){
		tradeService.addTrade(trade);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	@RequestMapping(path="/id")
	public ResponseEntity<Trade> getSingleTrade(@RequestBody Trade trade){
		return new ResponseEntity<>(tradeService.getSingleTrade(trade), HttpStatus.OK);
	}
}
