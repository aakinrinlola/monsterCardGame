package org.example.models;

// TODO: Erweiterung der Cards in Monster und Zauberkarten
public abstract class Cards {

    private String cardName;
    private int cardDamage;
    private String elementTyp;

    //Konstruktor
    public Cards (String cardName, int cardDamage, String typ) {
        this.cardName = cardName;
        this.cardDamage = cardDamage;
        this.elementTyp = elementTyp;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardDamage() {
        return cardDamage;
    }

    public void setCardDamage(int cardDamage) {
        this.cardDamage = cardDamage;
    }

    public String getElementTyp() {
        return elementTyp;
    }

    public void setElementTyp(String elementTyp) {
        this.elementTyp = elementTyp;
    }
}
