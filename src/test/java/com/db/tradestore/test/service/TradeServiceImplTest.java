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
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TradeServiceImplTest {

	@Autowired
	public TradeRepository tradeRepositoryTest;

	@Autowired
	public TradeService tradeService;

	@Test
	public void testFindByTradeId() {
		Trade trade = new Trade();
		trade.setTradeId("T9");
		trade.setVersion(3);
		trade.setCounterPartyId("CP-5");
		trade.setBookId("B2");
		trade.setMaturityDate(LocalDate.of(2024, 12, 31));
		trade.setCreatedDate(LocalDate.now());
		trade.setExpired("N");
		tradeRepositoryTest.save(trade);

		Trade foundTrade = tradeService.findByTradeId("T9");
		assertEquals("T9", foundTrade.getTradeId());
		assertEquals("B2", foundTrade.getBookId());
		assertEquals("CP-5", foundTrade.getCounterPartyId());
		assertEquals(3, foundTrade.getVersion());
		assertTrue(foundTrade.getMaturityDate() instanceof LocalDate, "Maturity date is not of type LocalDate");
		assertNotNull(foundTrade);
	}

//	@Test
//	public void testValidateAndSaveTrade() {}

//	@AfterAll
//	public void cleanup() {
//		tradeRepositoryTest.deleteAll();
//	}
}
