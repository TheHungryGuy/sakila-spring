package com.bbllc.sakilaspring.controller;

import com.bbllc.sakilaspring.service.ActorService;
import com.bbllc.sakilaspring.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/top_actors")
    public ResponseEntity<Map<String, Object>> getTopActors(){
        Map<String, Object> response = new HashMap<>();
        response.put("top_actors", actorService.getTopActors());
        return ResponseEntity.ok(response);
    }
}
