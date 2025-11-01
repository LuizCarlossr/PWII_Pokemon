package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class PokemonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocal;
    private int idPokeApi;
    private String name;
    private int height;
    private int weight;
    private String ability;
    private String types;
    private LocalDateTime cachedAt;
    private Boolean favorite = false;
    private String note;

    public Long getIdLocal() { return idLocal; }

    public void setIdLocal(Long idLocal) { this.idLocal = idLocal; }

    public int getIdPokeApi() { return idPokeApi; }

    public void setIdPokeApi(int idPokeApi) { this.idPokeApi = idPokeApi; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }

    public String getAbility() { return ability; }

    public void setAbility(String ability) { this.ability = ability; }

    public String getTypes() { return types; }

    public void setTypes(String types) { this.types = types; }

    public LocalDateTime getCachedAt() { return cachedAt; }

    public void setCachedAt(LocalDateTime cachedAt) { this.cachedAt = cachedAt; }

    public Boolean getFavorite() { return favorite; }

    public void setFavorite(Boolean favorite) { this.favorite = favorite; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }
}
