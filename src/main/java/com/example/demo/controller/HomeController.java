package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.models.*;
import com.example.demo.services.CoronaVirusDataService;
import java.util.*;
@Controller
public class HomeController {
	@Autowired
	CoronaVirusDataService cvds;

	@GetMapping("/")
	public String home(Model model)
	{
		  List<LocationStats> allStats = cvds.getAllstats();
	        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalcases()).sum();
	        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDifffrompreviousday()).sum();
	        model.addAttribute("locationStats", allStats);
	        model.addAttribute("totalReportedCases", totalReportedCases);
	        model.addAttribute("totalNewCases", totalNewCases);

		return "home";
	}
}
