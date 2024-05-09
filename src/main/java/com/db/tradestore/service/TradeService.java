package com.db.tradestore.service;

import java.util.List;

import com.db.tradestore.model.Trade;

public interface TradeService {
	public List<Trade> findAllTrades();
	public Trade findByTradeId(String tradeId);
	public void validateAndSaveTrade(Trade trade);
}
