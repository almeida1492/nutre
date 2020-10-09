package app.example.henriqueribeirodealmeida.nutre.Entities;

public class SummaryValues {
    private float energy;
    private float carbohydrate;
    private float protein;
    private float totalFat;
    private float saturatedFat;
    private float fibers;
    private float sodium;
    private float vitaminC;
    private float calcium;
    private float iron;
    private float vitaminA;
    private float potassium;
    private float magnesium;
    private float thiamine;
    private float water;
    private float riboflavin;
    private float niacin;


    //TODO might erase this constructor when integration is over
    public SummaryValues (float energy, float water, float carbohydrate, float protein, float totalFat, float saturatedFat, float fibers, float sodium, float vitaminC, float calcium, float iron, float vitaminA, float potassium, float magnesium, float thiamine, float riboflavin, float niacin) {
        this.energy=energy;
        this.water=water;
        this.carbohydrate=carbohydrate;
        this.protein=protein;
        this.totalFat=totalFat;
        this.saturatedFat=saturatedFat;
        this.fibers=fibers;
        this.sodium=sodium;
        this.vitaminC=vitaminC;
        this.calcium=calcium;
        this.iron=iron;
        this.vitaminA=vitaminA;
        this.potassium=potassium;
        this.magnesium=magnesium;
        this.thiamine=thiamine;
        this.riboflavin=riboflavin;
        this.niacin=niacin;
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

    public float getWater() {
        return water;
    }

    public void setWater(float water) {
        this.water = water;
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

    public float getThiamine() {
        return thiamine;
    }

    public void setThiamine(float thiamine) {
        this.thiamine = thiamine;
    }

    public float getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(float riboflavin) {
        this.riboflavin = riboflavin;
    }

    public float getNiacin() {
        return niacin;
    }

    public void setNiacin(float niacin) {
        this.niacin = niacin;
    }


    public int Values(float x){
        switch((int) x){

            case 0:
            return (int) energy;

            case 1:
                return (int) water;

            case 2:
            return (int) carbohydrate;

            case 3:
                return (int) protein;

            case 4:
                return (int) totalFat;

            case 5:
                return (int) saturatedFat;

            case 6:
                return (int)fibers;

            case 7:
                return (int) sodium;

            case 8:
                return (int) vitaminC;

            case 9:
                return (int) calcium;

            case 10:
                return (int)iron;

            case 11:
                return (int) vitaminA;

            case 12:
                return (int) potassium;

            case 13:
                return (int) magnesium;

            case 14:
                return (int) thiamine;

            case 15:
                return (int) vitaminA;

            case 16:
                return (int) riboflavin;
                case 17:
                return (int) niacin;
        }

        return 0;
    }

}
