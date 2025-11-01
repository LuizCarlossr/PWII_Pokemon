package com.example.demo.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FavoriteRequest {

    @NotNull(message = "O campo 'favorite' é obrigatório")
    private Boolean favorite;

    @Size(max = 255, message = "A nota deve ter no máximo 255 caracteres")
    private String note;

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
