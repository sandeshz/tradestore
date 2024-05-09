package com.db.tradestore.test.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.db.tradestore.model.Trade;
import com.db.tradestore.repository.TradeRepository;
import com.db.tradestore.service.TradeService;

@SpringBootTest
public class TradeServiceImplTest {

	@Autowired
	public TradeRepository tradeRepository;

	@Autowired
	public TradeService tradeService;

	@Test
	public void testFindByTradeId() {
		Trade trade = new Trade();
		trade.setTradeId("T22");
		trade.setVersion(7);
		trade.setCounterPartyId("CP-5");
		trade.setBookId("B8");
		trade.setMaturityDate(LocalDate.of(2024, 12, 31));
		trade.setCreatedDate(LocalDate.now());
		trade.setExpired("N");
		tradeRepository.save(trade);

		Trade foundTrade = tradeService.findByTradeId("T22");
		assertEquals("T22", foundTrade.getTradeId());
		assertEquals("B8", foundTrade.getBookId());
		assertEquals("CP-5", foundTrade.getCounterPartyId());
		assertEquals(7, foundTrade.getVersion());
		assertTrue(foundTrade.getMaturityDate() instanceof LocalDate, "Maturity date is not of type LocalDate");
		assertNotNull(foundTrade);
	}
}
