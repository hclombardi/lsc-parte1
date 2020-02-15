package com.gft.parte1.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gft.parte1.Parte1Application;
import com.gft.parte1.model.Stock;
import com.gft.parte1.repository.StockRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

@Service
public class FileService {
	
	private static final Logger log = LoggerFactory.getLogger(FileService.class);

	@Autowired
	private StockRepository stockRepo;

	@Value("${path.dir.json.files}")
	private String pathJsonFiles;	

	public String processFile(String filename) {

		List<Stock> stockList;

		log.info(filename + " - Processing file...");

		stockList = convertJsonFileToStock(filename);
		log.info(filename + " - Stock records size: " + stockList.size());
		
		// Update file name
		stockList.forEach(x -> x.setFile(filename));

		// Remove duplicates (distinct)
		stockList = stockList.stream().distinct().collect(Collectors.toList());
		log.info(filename + " - Distinct records: " + stockList.size());

		// Save record to database (if necessary)
		log.info(filename + " - DB Records: " + stockList.parallelStream().map(n -> addStock(n)).count());
		
		return filename;
	}
	

	public Stock addStock(Stock stock) {

		Stock newStock = stockRepo.findByFileAndProductAndQuantityAndPrice(stock.getFile(), stock.getProduct(),
				stock.getQuantity(), stock.getPrice());

		if (newStock == null)
			return stockRepo.save(stock);
		else
			return newStock;
	}
	

	public List<Stock> convertJsonFileToStock(String filename) {

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		
		try {
			jsonObject = gson.fromJson(new FileReader(pathJsonFiles + filename), JsonObject.class);
		} catch (JsonSyntaxException e) {			 
			log.error(e.toString());
		} catch (JsonIOException e) {		
			log.error(e.toString());
		} catch (FileNotFoundException e) {
			log.error(e.toString());
		}
		
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");

		return Arrays.asList(gson.fromJson(jsonArray, Stock[].class));
	}

}
