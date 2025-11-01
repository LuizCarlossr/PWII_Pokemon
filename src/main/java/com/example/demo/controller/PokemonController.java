package com.example.demo.controller;

import com.example.demo.dto.FavoriteRequest;
import com.example.demo.model.PokemonEntity;
import com.example.demo.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @PostMapping("/cache/{nameOrId}")
    public ResponseEntity<PokemonEntity> cache(@PathVariable String nameOrId) {
        return ResponseEntity.ok(service.cachePokemon(nameOrId));
    }

    @GetMapping
    public ResponseEntity<Page<PokemonEntity>> list(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.listAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonEntity> detail(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PokemonEntity>> search(@RequestParam String type) {
        return ResponseEntity.ok(service.searchByType(type));
    }

    @PatchMapping("/{id}/favorite")
    public ResponseEntity<PokemonEntity> favorite(@PathVariable Long id, @Valid @RequestBody FavoriteRequest body) {
        return ResponseEntity.ok(service.updateFavorite(id, body.getFavorite(), body.getNote()));
    }
}
