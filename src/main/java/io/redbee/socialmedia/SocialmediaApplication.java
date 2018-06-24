package io.redbee.socialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SocialmediaApplication {

	@Bean(name = "postUpdaterTaskExecutor")
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(32);
		executor.setMaxPoolSize(9999);
		executor.setQueueCapacity(9999);
		executor.setThreadNamePrefix("InterestUpdater-");
		executor.initialize();
		return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(SocialmediaApplication.class, args);
	}
}
