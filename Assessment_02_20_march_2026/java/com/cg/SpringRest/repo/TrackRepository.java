package com.cg.SpringRest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.SpringRest.entity.Track;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {

    // Custom query method
    List<Track> findByTitle(String title);
}