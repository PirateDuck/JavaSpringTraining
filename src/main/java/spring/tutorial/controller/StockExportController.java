package spring.tutorial.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.web.bind.annotation.*;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Location;
import spring.tutorial.model.Stock;
import spring.tutorial.model.pojo.StockPojo;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.service.ExportStockService;

import java.util.List;

@RestController
public class StockExportController {

    @RequestMapping(method = RequestMethod.GET, value = "/export")
    @ResponseBody
    public String export(@RequestParam("location") int locationId, ExportStockService stockExporter, StockRepository stockRepo) throws JsonProcessingException {
        try {
            Location location = new Location();
            location.setId(locationId);
            List<Stock> stocks = stockExporter.exportAllStocksFromLocation(location, stockRepo);
            List<StockPojo> stockPojos = StockPojo.fromStockList(stocks);
            final CsvMapper mapper = new CsvMapper();
            final CsvSchema schema = mapper.schemaFor(StockPojo.class);
            final String csv = mapper.writer(schema.withUseHeader(true)).writeValueAsString(stockPojos);
            return csv;
        } catch (NoStockFoundException ex) {
            return "nothing found";
        }

    }
}
