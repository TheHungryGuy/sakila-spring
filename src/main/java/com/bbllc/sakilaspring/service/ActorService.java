package com.bbllc.sakilaspring.service;

import com.bbllc.sakilaspring.repository.ActorRepository;
import com.bbllc.sakilaspring.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Map<String, Object>> getTopActors() {
        List<Object[]> results = actorRepository.findTopActors();
        List<Map<String, Object>> topActors = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> actor = new HashMap<>();
            actor.put("actor_id", row[0]);
            actor.put("first_name", row[1]);
            actor.put("last_name", row[2]);
            actor.put("full_name", row[3]);
            actor.put("film_count", row[4]);
            topActors.add(actor);
        }

        return topActors;
    }
}
