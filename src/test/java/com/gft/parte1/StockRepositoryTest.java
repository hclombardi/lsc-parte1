package com.gft.parte1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.gft.parte1.model.Stock;
import com.gft.parte1.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
public class StockRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StockRepository repository;

    @Test
    public void testFindByProduct() {

    	Stock stock = new Stock();    	
    	stock.setProduct("PROD");
    	stock.setQuantity(1);
    	
        entityManager.persist(stock);

        List<Stock> stocks = repository.findByProduct("PROD");
        assertEquals(1, stocks.size());

        assertThat(stocks).extracting(Stock::getProduct).containsOnly("PROD");

    }

}
