package com.example.demo.repository;

import com.example.demo.model.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {
    Optional<PokemonEntity> findByIdPokeApi(int idPokeApi);
    List<PokemonEntity> findByTypesContainingIgnoreCase(String type);
}
