package com.example.demo.dto;

import java.util.List;

public class PokemonDto {
    private int id;
    private String name;
    private int height;
    private int weight;
    private List<AbilityWrapper> abilities;
    private List<TypeWrapper> types;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }

    public List<AbilityWrapper> getAbilities() { return abilities; }

    public void setAbilities(List<AbilityWrapper> abilities) { this.abilities = abilities; }

    public List<TypeWrapper> getTypes() { return types; }

    public void setTypes(List<TypeWrapper> types) { this.types = types; }

    public static class AbilityWrapper {
        private Ability ability;

        public Ability getAbility() { return ability; }

        public void setAbility(Ability ability) { this.ability = ability; }

        public static class Ability {
            private String name;

            public String getName() { return name; }

            public void setName(String name) { this.name = name; }
        }
    }

    public static class TypeWrapper {
        private Type type;

        public Type getType() { return type; }

        public void setType(Type type) { this.type = type; }

        public static class Type {
            private String name;

            public String getName() { return name; }

            public void setName(String name) { this.name = name; }
        }
    }
}
