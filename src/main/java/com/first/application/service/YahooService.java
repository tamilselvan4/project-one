package com.first.application.service;

import com.first.application.model.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface YahooService {

    public List<Stock> getTopGainers();

}
