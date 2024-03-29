package com.thinkpalm.covidtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.thinkpalm.covidtracker.models.LocationStats;
import com.thinkpalm.covidtracker.services.CovidDataService;

@Controller
public class HomeController {
	
	@Autowired
	CovidDataService covidDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<LocationStats> allStats = covidDataService.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
		
	}
}
