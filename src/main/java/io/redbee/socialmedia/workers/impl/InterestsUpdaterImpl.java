package io.redbee.socialmedia.workers.impl;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;
import io.redbee.socialmedia.repositories.InterestRepository;
import io.redbee.socialmedia.services.InterestService;
import io.redbee.socialmedia.workers.InterestsUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class InterestsUpdaterImpl implements InterestsUpdater {

    private static final Logger log = LoggerFactory.getLogger(InterestsUpdaterImpl.class);

    private final InterestRepository interestRepository;
    private final InterestService interestService;


    @Autowired
    public InterestsUpdaterImpl(InterestRepository interestRepository, InterestService interestService) {
        this.interestRepository = interestRepository;
        this.interestService = interestService;
    }

    @Override
    @Scheduled(cron = "${io.redbee.socialmedia.worker.cron}")
    public void updateAllInterests() throws ExecutionException, InterruptedException {

        log.info("Updating all interests...");

        List<Interest> allInterests = interestRepository.findAll();
        List<CompletableFuture<List<Post>>> listCompletableFuture = new ArrayList<>();

        for(Interest interest: allInterests) {
            listCompletableFuture.add(interestService.updateInterest(interest));
        }

        for (CompletableFuture<List<Post>> completableFuture : listCompletableFuture) {
            completableFuture.get();
        }

    }

}

