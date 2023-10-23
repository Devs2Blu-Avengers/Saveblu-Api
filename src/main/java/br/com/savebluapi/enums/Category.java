package br.com.savebluapi.enums;

public enum Category {
    INCENDIO("Incêndio"),
    ENCHENTE("Enchente"),
    ALAGAMENTO("Alagamento"),
    RISCO_ELETRICO("Risco Elétrico"),
    AFOGAMENTO("Afogamento"),
    DESLIZAMENTO("Delizamento"),
    OUTROS("Outros"),
    POLUICAO_AMBIENTAL("Poluição Ambiental"),
    TRAFICO_ANIMAIS("Tráfico de Animais"),
    DESMATAMENTO_ILEGAL("Desmatamento Ilegal"),
    CONTAMINACAO("Contaminação");

    private String category = "";

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return  this.category;
    }
}
