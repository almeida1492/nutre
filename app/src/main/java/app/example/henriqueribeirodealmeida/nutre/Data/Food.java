package app.example.henriqueribeirodealmeida.nutre.Data;

public class Food {
    private int id;
    private String name;
    private Float energy;
    private Float carbohydrate;
    private Float protein;
    private Float totalFat;
    private Float saturatedFat;
    private Float water;
    private Float fibers;
    private Float sodium;
    private Float vitaminC;
    private Float calcium;
    private Float iron;
    private Float vitaminA;
    private Float thiamine;
    private Float potassium;
    private Float magnesium;
    private Float niacin;
    private Float riboflavin;

    public  Food(int id, String name, float energy, float water, float carbohydrate, float protein, float totalFat, float saturatedFat, float fibers, float sodium, float vitaminC, float calcium, float iron, float vitaminA, float potassium, float magnesium, float thiamine, float riboflavin, float niacin) {
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.water = water;
        this.fibers = fibers;
        this.sodium = sodium;
        this.vitaminC = vitaminC;
        this.calcium = calcium;
        this.iron = iron;
        this.vitaminA = vitaminA;
        this.potassium = potassium;
        this.magnesium = magnesium;
        this.riboflavin = riboflavin;
        this.niacin= niacin;
        this.thiamine=thiamine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getEnergy() {
        return energy;
    }

    public void setEnergy(Float energy) {
        this.energy = energy;
    }

    public Float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(Float totalFat) {
        this.totalFat = totalFat;
    }

    public Float getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(Float saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public Float getWater() {
        return water;
    }

    public void setWater(Float water) {
        this.water = water;
    }

    public Float getFibers() {
        return fibers;
    }

    public void setFibers(Float fibers) {
        this.fibers = fibers;
    }

    public Float getSodium() {
        return sodium;
    }

    public void setSodium(Float sodium) {
        this.sodium = sodium;
    }

    public Float getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(Float vitaminC) {
        this.vitaminC = vitaminC;
    }

    public Float getCalcium() {
        return calcium;
    }

    public void setCalcium(Float calcium) {
        this.calcium = calcium;
    }

    public Float getIron() {
        return iron;
    }

    public void setIron(Float iron) {
        this.iron = iron;
    }

    public Float getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(Float vitaminA) {
        this.vitaminA = vitaminA;
    }

    public Float getNiacin() {
        return niacin;
    }

    public void setNiaicn(Float niacin) {
        this.niacin = niacin;
    }

    public Float getPotassium() {
        return potassium;
    }

    public void setPotassium(Float potassium) {
        this.potassium = potassium;
    }

    public Float getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(Float magnesium) {
        this.magnesium = magnesium;
    }

    public Float getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(Float riboflavin) {
        this.riboflavin = riboflavin;
    }

    public Float getThiamine() {
        return thiamine;
    }

    public void setThiamine(Float thiamine) {
        this.thiamine = thiamine;
    }
}
