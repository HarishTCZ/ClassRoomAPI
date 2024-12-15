package com.harishtcx.project1.player;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Retrieve all players
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    // Retrieve players filtered by grade
    public List<Player> getPlayersByGrade(String grade) {
        return playerRepository.findAll().stream()
                .filter(player -> grade.equals(player.getGrade()))
                .collect(Collectors.toList());
    }

    // Add a new player
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    // Update an existing player
    public Player updatePlayer(Player updatedPlayer) {
        Optional<Player> existingPlayer = playerRepository.findById(updatedPlayer.getName());
        if (existingPlayer.isPresent()) {
            Player playerToUpdate = existingPlayer.get();
            playerToUpdate.setName(updatedPlayer.getName());
            playerToUpdate.setGrade(updatedPlayer.getGrade());
            return playerRepository.save(playerToUpdate);
        }
        return null;
    }

    // Delete a player by name
    @Transactional
    public void deletePlayer(String name) {
        playerRepository.deleteById(name);
    }
}
