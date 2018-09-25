package com.example.henriqueribeirodealmeida.nutre.Entities;

public class SummaryValues {

    private int energy;
    private int carbohydrate;
    private int protein;
    private int totalFat;
    private int transFat;
    private int saturatedFat;
    private int water;
    private int vitaminA;
    private int vitaminC;
    private int vitaminE;
    private int fibers;
    private int sodium;
    private int iron;
    private int omega3;
    private int omega6;
    private int omega9;
    private int calcium;
    private int zinc;

    //TODO might erase this constructor when integration is over
    public SummaryValues(int energy, int carbohydrate, int protein, int totalFat, int transFat, int saturatedFat, int water, int vitaminA, int vitaminC, int vitaminE, int fibers, int sodium, int iron, int omega3, int omega6, int omega9, int calcium, int zinc) {
        this.energy = energy;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.totalFat = totalFat;
        this.transFat = transFat;
        this.saturatedFat = saturatedFat;
        this.water = water;
        this.vitaminA = vitaminA;
        this.vitaminC = vitaminC;
        this.vitaminE = vitaminE;
        this.fibers = fibers;
        this.sodium = sodium;
        this.iron = iron;
        this.omega3 = omega3;
        this.omega6 = omega6;
        this.omega9 = omega9;
        this.calcium = calcium;
        this.zinc = zinc;
    }

    public SummaryValues(){}

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(int totalFat) {
        this.totalFat = totalFat;
    }

    public int getTransFat() {
        return transFat;
    }

    public void setTransFat(int transFat) {
        this.transFat = transFat;
    }

    public int getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(int saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(int vitaminA) {
        this.vitaminA = vitaminA;
    }

    public int getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(int vitaminC) {
        this.vitaminC = vitaminC;
    }

    public int getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(int vitaminE) {
        this.vitaminE = vitaminE;
    }

    public int getFibers() {
        return fibers;
    }

    public void setFibers(int fibers) {
        this.fibers = fibers;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getOmega3() {
        return omega3;
    }

    public void setOmega3(int omega3) {
        this.omega3 = omega3;
    }

    public int getOmega6() {
        return omega6;
    }

    public void setOmega6(int omega6) {
        this.omega6 = omega6;
    }

    public int getOmega9() {
        return omega9;
    }

    public void setOmega9(int omega9) {
        this.omega9 = omega9;
    }

    public int getCalcium() {
        return calcium;
    }

    public void setCalcium(int calcium) {
        this.calcium = calcium;
    }

    public int getZinc() {
        return zinc;
    }

    public void setZinc(int zinc) {
        this.zinc = zinc;
    }
}
