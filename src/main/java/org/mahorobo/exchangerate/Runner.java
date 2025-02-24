package org.mahorobo.exchangerate;

import org.mahorobo.exchangerate.domain.component.task.FetchExchangeRateScheduledTask;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner  {
	private final FetchExchangeRateScheduledTask task;

	@Override
	public void run(String... args) throws Exception {
		task.fetchExchangeRate();
		
	}

}
