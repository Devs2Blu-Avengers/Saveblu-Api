package br.com.savebluapi.enums;

public enum Category {
    INCENDIO("Incêndio"),
    ENCHENTE("Enchente"),
    ALAGAMENTO("Alagamento"),
    RISCOELETRICO("Risco Elétrico"),
    AFOGAMENTO("Afogamento"),
    DESLIZAMENTO("Delizamento"),
    OUTROS("Outros"),
    POLUICAOAMBIENTAL("Poliação Ambiental"),
    TRAFICOANIMAIS("Tráfico de Animais"),
    DESMATAMENTOILEGAL("Desmatamento"),
    CONTAMINACAO("Contaminação");

    private String category = "";

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return  this.category;
    }
}
