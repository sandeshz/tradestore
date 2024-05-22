package com.db.tradestore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.tradestore.model.Trade;
import com.db.tradestore.service.TradeService;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api")
public class TradeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);

	@Autowired
	public TradeService tradeService;

	@CrossOrigin
	@GetMapping("/trades/hello")
	public String sayHello() {
		return "Hello, Trade Store";
	}

	@CrossOrigin
	@GetMapping("/trades/{tradeId}")
	public Trade findByTradeId(@PathVariable String tradeId) {
		Trade trade = tradeService.findByTradeId(tradeId);
		System.out.println("trade : "+trade);
		return trade;
	}

	@CrossOrigin
	@GetMapping("/trades/all")
	public List<Trade> findAllTrades() {
		return tradeService.findAllTrades();
	}

	@CrossOrigin
	@PostMapping("/trades/create")
	public void validateAndSaveTrade(@RequestBody Trade trade) {
		tradeService.validateAndSaveTrade(trade);
		LOGGER.info("Trade record validated and saved : " + trade);
	}

	@CrossOrigin
	@PutMapping("/trades/update/{tradeId}")
	public void updateTrade(@PathVariable String tradeId, @RequestBody Trade trade) {
		tradeService.updateTrade(tradeId, trade);
		LOGGER.info("Trade record with ID : " + tradeId + " updated");
	}

	@CrossOrigin
	@PostMapping("/trades/delete")
	public void deleteTrade(@RequestBody Trade trade) {
		tradeService.validateAndSaveTrade(trade);
		LOGGER.info("Trade record validated and saved : " + trade);
	}
}
