package com.harishtcx.project1.player;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping
    public List<Player> getPlayers(@RequestParam(required = false) String grade) {
        if (grade != null) {
            return playerService.getPlayersByGrade(grade);
        } else {
            return playerService.getPlayers();
        }
    }

    // Add a new player
    @PostMapping
    public ResponseEntity<Player> postPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    // Update an existing player
    @PutMapping
    public ResponseEntity<Player> putPlayer(@RequestBody Player player) {
        Player resultPlayer = playerService.updatePlayer(player);
        if (resultPlayer != null) {
            return new ResponseEntity<>(resultPlayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a player by name
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deletePlayer(@PathVariable String name) {
        playerService.deletePlayer(name);
        return new ResponseEntity<>("Player deleted successfully", HttpStatus.OK);
    }
}
