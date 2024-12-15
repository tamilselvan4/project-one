package com.first.application.views.home;

import com.first.application.model.Stock;
import com.first.application.service.YahooService;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Home")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
@JsModule("https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js")
public class HomeView extends VerticalLayout {

    private final YahooService yahooService;

    public HomeView(YahooService yahooService) {
        this.yahooService = yahooService;
        // Main container for the grids
        Div gridContainer = new Div();
        gridContainer.getStyle().set("display", "grid");
        gridContainer.getStyle().set("grid-template-columns", "repeat(2, 1fr)");
        gridContainer.getStyle().set("gap", "20px");
        gridContainer.setWidthFull();
        gridContainer.setId("grid-container");

        // Add grids for each category
        gridContainer.add(createStockGrid("Top Gainers", yahooService.getTopGainers()));
        gridContainer.add(createStockGrid("Top Losers", getTopLosers()));
        gridContainer.add(createStockGrid("High Volume", getHighVolume()));
        gridContainer.add(createStockGrid("Most Bought", getMostBought()));

        // Add the container to the layout
        add(gridContainer);

        // Add SortableJS functionality using JS
        getElement().executeJs(
                "new Sortable($0, { animation: 150, swapThreshold: 1 });",
                gridContainer.getElement()
        );

        // Set layout spacing and padding
        setSpacing(true);
        setPadding(true);
    }

    private Div createStockGrid(String title, List<Stock> stocks) {
        Grid<Stock> stockGrid = new Grid<>(Stock.class, false);
        stockGrid.addColumn(Stock::getName).setHeader("Name");
        stockGrid.addColumn(Stock::getSymbol).setHeader("Symbol");
        stockGrid.addColumn(stock -> String.format("%.2f", stock.getCurrentPrice())).setHeader("Price");
        stockGrid.addColumn(new ComponentRenderer<>(stock -> {
            Div div = new Div();
            div.setText(stock.getTodayReturnPrice() > 0
                    ? String.format("▲ %.2f%%", stock.getTodayReturnPercent())
                    : String.format("▼ %.2f%%", stock.getTodayReturnPercent()));
            div.getStyle().set("color", stock.getTodayReturnPrice() > 0 ? "green" : "red");
            return div;
        })).setHeader("Today's Return");
        stockGrid.setItems(stocks);

        Div container = new Div();
        container.add(new Div(new Span(title)));
        container.add(stockGrid);
        container.getStyle().set("border", "1px solid #ccc");
        container.getStyle().set("border-radius", "8px");
        container.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        container.getStyle().set("padding", "10px");
        container.getStyle().set("background-color", "white");
        container.getStyle().set("cursor", "move"); // Indicate draggable

        return container;
    }

    private List<Stock> getTopGainers() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("TCS", "TCS", 3500.00, 50.00, 1.45));
        stocks.add(new Stock("Infosys", "INFY", 1470.00, 30.00, 2.08));
        return stocks;
    }

    private List<Stock> getTopLosers() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("Reliance", "RELIANCE", 2400.00, -25.00, -1.03));
        stocks.add(new Stock("HDFC Bank", "HDFC", 1600.00, -20.00, -1.25));
        return stocks;
    }

    private List<Stock> getHighVolume() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("Yes Bank", "YESBANK", 15.00, 0.50, 3.45));
        stocks.add(new Stock("Suzlon", "SUZLON", 25.00, 0.30, 1.22));
        return stocks;
    }

    private List<Stock> getMostBought() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("ITC", "ITC", 450.00, 5.00, 1.12));
        stocks.add(new Stock("Coal India", "COAL", 230.00, 4.00, 1.75));
        return stocks;
    }
}