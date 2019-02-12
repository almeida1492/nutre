package com.example.henriqueribeirodealmeida.nutre.Entities;

public class SummaryValues {
    private float energy;
    private float carbohydrate;
    private float protein;
    private float totalFat;
    private float saturatedFat;
    private float transFat;
    private float fibers;
    private float sodium;
    private float vitaminC;
    private float calcium;
    private float iron;
    private float vitaminA;
    private float selenium;
    private float potassium;
    private float magnesium;
    private float vitaminE;
    private float thiamine;

    //TODO might erase this constructor when integration is over
    public SummaryValues (String name, float energy, float carbohydrate, float protein, float totalFat, float saturatedFat, float transFat, float fibers, float sodium, float vitaminC, float calcium, float iron, float vitaminA, float selenium, float potassium, float magnesium, float vitaminE, float thiamine) {
        this.energy = energy;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.transFat = transFat;
        this.fibers = fibers;
        this.sodium = sodium;
        this.vitaminC = vitaminC;
        this.calcium = calcium;
        this.iron = iron;
        this.vitaminA = vitaminA;
        this.selenium = selenium;
        this.potassium = potassium;
        this.magnesium = magnesium;
        this.vitaminE = vitaminE;
        this.thiamine = thiamine;
    }

    public SummaryValues(float i, float i1, float i2, float i3, float i4, float i5, float i6, float i7, float i8, float i9, float i10, float i11, float i12, float i13, float i14, float i15, float i16, float i17){}

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(float totalFat) {
        this.totalFat = totalFat;
    }

    public float getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(float saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public float getTransFat() {
        return transFat;
    }

    public void setTransFat(float transFat) {
        this.transFat = transFat;
    }

    public float getFibers() {
        return fibers;
    }

    public void setFibers(float fibers) {
        this.fibers = fibers;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public float getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(float vitaminC) {
        this.vitaminC = vitaminC;
    }

    public float getCalcium() {
        return calcium;
    }

    public void setCalcium(float calcium) {
        this.calcium = calcium;
    }

    public float getIron() {
        return iron;
    }

    public void setIron(float iron) {
        this.iron = iron;
    }

    public float getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(float vitaminA) {
        this.vitaminA = vitaminA;
    }

    public float getSelenium() {
        return selenium;
    }

    public void setSelenium(float selenium) {
        this.selenium = selenium;
    }

    public float getPotassium() {
        return potassium;
    }

    public void setPotassium(float potassium) {
        this.potassium = potassium;
    }

    public float getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(float magnesium) {
        this.magnesium = magnesium;
    }

    public float getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(float vitaminE) {
        this.vitaminE = vitaminE;
    }

    public float getThiamine() {
        return thiamine;
    }

    public void setThiamine(float thiamine) {
        this.thiamine = thiamine;
    }
}
