package com.db.tradestore.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.tradestore.exception.TradeValidationException;
import com.db.tradestore.model.Trade;
import com.db.tradestore.repository.TradeRepository;

@Service
@Transactional
public class TradeServiceImpl implements TradeService {

	@Autowired
	public TradeRepository tradeRepository;

	@Override
	public List<Trade> findAllTrades() {
		return tradeRepository.findAll();
	}

	@Override
	public Trade findByTradeId(String tradeId) {
		return tradeRepository.findById(tradeId).orElse(null);
	}

	@Override
	public void validateAndSaveTrade(Trade trade) {
		LocalDate todaysDate = LocalDate.now();
		Trade existingTrade = findByTradeId(trade.getTradeId());

		if(existingTrade != null) {
			if(trade.getVersion() < existingTrade.getVersion()) {
				throw new TradeValidationException("Invalid trade version!");
			} else if(trade.getTradeId() == existingTrade.getTradeId()) {
				trade.setTradeId(existingTrade.getTradeId());
			}
			if(trade.getMaturityDate().isBefore(todaysDate)) {
				trade.setExpired("Y");
			} else {
				trade.setExpired("N");
			}
			tradeRepository.save(existingTrade);
		}
		tradeRepository.save(trade);
	}

	@Override
	public void updateTrade(String tradeId, Trade trade) {
		try {
			Trade existingTrade = findByTradeId(tradeId);
			existingTrade.setVersion(trade.getVersion());
			existingTrade.setCounterPartyId(trade.getCounterPartyId());
			existingTrade.setBookId(trade.getBookId());
			existingTrade.setMaturityDate(trade.getMaturityDate());
			existingTrade.setCreatedDate(trade.getCreatedDate());
			existingTrade.setExpired(trade.getExpired());
			this.validateAndSaveTrade(existingTrade);
		} catch (TradeValidationException e) {
			throw new TradeValidationException("Error updating trade");
		}
	}
}
