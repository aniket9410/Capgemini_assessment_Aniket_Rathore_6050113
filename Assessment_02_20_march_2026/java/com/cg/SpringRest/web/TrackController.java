package com.cg.SpringRest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.SpringRest.entity.Track;
import com.cg.SpringRest.repo.TrackRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class TrackController {

    @Autowired
    private TrackRepository repo;

    @PostMapping("/tracks")
    public ResponseEntity<String> addTrack(@RequestBody Track track) {
        repo.save(track);
        return ResponseEntity.ok("Track added successfully");
    }

    @GetMapping("/tracks")
    public ResponseEntity<List<Track>> getTracks() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/tracks/title/{title}")
    public ResponseEntity<List<Track>> getTracksByTitle(@PathVariable String title) {
        return ResponseEntity.ok(repo.findByTitle(title));
    }

    @GetMapping("/tracks/{id}")
    public ResponseEntity<Object> getTrack(@PathVariable Long id) {

        Optional<Track> track = repo.findById(id);

        if (track.isPresent()) {
            return ResponseEntity.ok(track.get());
        } else {
            return ResponseEntity.status(404).body("Track not found");
        }
    }
}