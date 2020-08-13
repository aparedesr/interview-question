package com.example.demo.config;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.persistence.RecordNotFoundException;
import com.example.demo.service.UrlService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableScheduling
@Slf4j
public class PurgeDataJob {
	@Autowired
	UrlService service;

	// The purge job is expected to run every 20 minutes with an initial delay of 2 minutes
	@Scheduled(initialDelay = 2 * 60 * 1000, fixedDelay = 20 * 60 * 1000)
	public void purgeOldUrls() throws RecordNotFoundException {
		log.debug("Executing data purge: " + Instant.now());
		service.purgeOldUrls();
	}
}
