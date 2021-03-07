package com.example.demo.services;
import com.example.demo.models.*;


import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
@Component
public class CoronaVirusDataService {

	private static String virusData = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	private List<LocationStats> allstats = new ArrayList<>();
	
	public  List<LocationStats> getAllstats() {
		return allstats;
	}

	public void setAllstats(List<LocationStats> allstats) {
		this.allstats = allstats;
	}

	@PostConstruct 
	@Scheduled(cron = "* * 1 * * *")
	
	// * * 1 * * * runs 1st hour of every day
	public void fetchData() throws IOException,InterruptedException
	{
		List<LocationStats> newStats = new ArrayList<>();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder().uri(URI.create(virusData)).build();
		HttpResponse<String> res = client.send(req,HttpResponse.BodyHandlers.ofString());

		 StringReader csvBodyReader = new StringReader(res.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {

			LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
     
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            locationStat.setLatestTotalcases(latestCases);
            locationStat.setDifffrompreviousday(latestCases - prevDayCases);
            System.out.println(locationStat);
            newStats.add(locationStat);
			
		}
		this.allstats = newStats;
		   
	}
}
