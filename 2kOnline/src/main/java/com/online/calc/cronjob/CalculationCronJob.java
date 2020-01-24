package com.online.calc.cronjob;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.online.calc.dto.CalculationDTO;
import com.online.calc.service.CalculationService;

@Configuration
@EnableScheduling
public class CalculationCronJob {
	
	private final CalculationService calculationService;
	
	public CalculationCronJob(CalculationService calculationService) {
		this.calculationService = calculationService;
	}

	/*
	 * Treba da se aktivira na svakih 7 dana (brise kalkulacije starije od 7 dana, jedan od nacina da
	 * se baza malo rastereti)
	 * RADI, ali nije dobar pattern u cron-u
	 */
	@Scheduled(cron = "0 0 12 */7 * ?")
	public void deleteOutdatedCalculations() {
		System.out.println("Old calculations deleted..");
		LocalDateTime currentDate = LocalDateTime.now();
		
		List<CalculationDTO> calculations = calculationService.getAllCalculations();
		
		for(CalculationDTO calculation : calculations) {
			LocalDateTime date = this.convertToLocalDateTime(calculation.getDateOfCalculation());
			if(date.plusDays(7).isBefore(currentDate)) {
				System.out.println("TRENUTNI DATUM: " + currentDate);
				System.out.println("DATUM KALKULAC: " + date);
				calculationService.deleteCalculationById(calculation.getId());
			}
		}
	}
	
	private LocalDateTime convertToLocalDateTime(Date date) {
	    return date.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
}
