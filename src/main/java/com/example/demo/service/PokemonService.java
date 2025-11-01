package com.example.demo.service;

import com.example.demo.dto.PokemonDto;
import com.example.demo.model.PokemonEntity;
import com.example.demo.repository.PokemonRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final RestTemplate restTemplate;
    private final PokemonRepository repository;

    public PokemonService(RestTemplate restTemplate, PokemonRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    @CacheEvict(value = "pokemon", key = "#nameOrId")
    public PokemonEntity cachePokemon(String nameOrId) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + nameOrId;
        try {
            PokemonDto dto = restTemplate.getForObject(url, PokemonDto.class);
            if (dto == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resposta da PokeAPI vazia");
            }

            PokemonEntity entity = repository.findByIdPokeApi(dto.getId()).orElse(new PokemonEntity());
            entity.setIdPokeApi(dto.getId());
            entity.setName(dto.getName());
            entity.setHeight(dto.getHeight());
            entity.setWeight(dto.getWeight());
            entity.setAbility(dto.getAbilities().get(0).getAbility().getName());
            entity.setTypes(dto.getTypes().stream()
                    .map(t -> t.getType().getName())
                    .collect(Collectors.joining(",")));
            entity.setCachedAt(LocalDateTime.now());

            return repository.save(entity);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokémon não encontrado na PokeAPI");
        }
    }

    public Page<PokemonEntity> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Cacheable(value = "pokemon", key = "#id")
    public PokemonEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID local não encontrado"));
    }

    public List<PokemonEntity> searchByType(String type) {
        return repository.findByTypesContainingIgnoreCase(type);
    }

    @CacheEvict(value = "pokemon", key = "#id")
    public PokemonEntity updateFavorite(Long id, Boolean favorite, String note) {
        PokemonEntity entity = getById(id);
        entity.setFavorite(favorite);
        entity.setNote(note);
        return repository.save(entity);
    }
}
