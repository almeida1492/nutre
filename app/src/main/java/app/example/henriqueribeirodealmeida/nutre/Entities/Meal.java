package app.example.henriqueribeirodealmeida.nutre.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "meals")
public class Meal implements Parcelable, Cloneable{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    /*private HashMap<Food, Integer> foods;*/

    //TODO move to Food

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
    private float potassium;
    private float magnesium;
    private float vitaminE;
    private float thiamine;
    private float riboflavin;
    private float niacin;
    private float water;

    private String measureLabel;
    private String unity;
    private float unityMultiplier;


    public Meal() {
    }


    public Meal(String name, String measureLabel, float unityMultiplier, String unity, float energy, float water, float carbohydrate, float protein, float totalFat, float saturatedFat, float fibers, float sodium, float vitaminC, float calcium, float iron, float vitaminA, float potassium, float magnesium, float thiamine, float riboflavin, float niacin) {
        this.name = name;
        this.measureLabel = measureLabel;
        this.unityMultiplier = unityMultiplier;
        this.unity = unity;
        this.energy=energy;
        this.water = water;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.fibers = fibers;
        this.sodium = sodium;
        this.vitaminC = vitaminC;
        this.calcium = calcium;
        this.iron = iron;
        this.vitaminA = vitaminA;
        this.potassium = potassium;
        this.magnesium = magnesium;
        this.thiamine = thiamine;
        this.riboflavin = riboflavin;
        this.niacin = niacin;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
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

    public Float getTransFat() {
        return transFat;
    }

    public void setTransFat(Float transFat) {
        this.transFat = transFat;
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

    public Float getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(Float vitaminE) {
        this.vitaminE = vitaminE;
    }

    public Float getThiamine() {
        return thiamine;
    }

    public void setThiamine(Float thiamine) {
        this.thiamine = thiamine;
    }

    public String getMeasureLabel() {
        return measureLabel;
    }

    public void setMeasureLabel(String measureLabel) {
        this.measureLabel = measureLabel;
    }

    public Float getUnityMultiplier() {
        return unityMultiplier;
    }

    public Float getRiboflavin() { return riboflavin; }

    public void setRiboflavin(float riboflavin) { this.riboflavin = riboflavin; }

    public void setUnityMultiplier(float unityMultiplier) { this.unityMultiplier = unityMultiplier; }

    public Float getNiacin() { return niacin; }

    public void setNiacin(float niacin) { this.niacin = niacin; }

    public String getUnity() { return unity; }

    public void setUnity(String unity) { this.unity = unity; }

    public Float getWater() { return water; }

    public void setWater(float water) { this.water = water; }

    @Override
    public String toString() {
        return this.getName();
    }

    protected Meal(Parcel in){
        id = in.readInt();
        name = in.readString();
        energy = in.readFloat();
        carbohydrate = in.readFloat();
        protein = in.readFloat();
        totalFat = in.readFloat();
        saturatedFat = in.readFloat();
        transFat = in.readFloat();
        water = in.readFloat();
        fibers = in.readFloat();
        sodium = in.readFloat();
        vitaminC = in.readFloat();
        calcium = in.readFloat();
        iron = in.readFloat();
        vitaminA = in.readFloat();
        potassium = in.readFloat();
        magnesium = in.readFloat();
        vitaminE = in.readFloat();
        thiamine = in.readFloat();
        measureLabel = in.readString();
        unityMultiplier = in.readFloat();
        unity = in.readString();
        niacin = in.readFloat();
        riboflavin = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeFloat(energy);
        dest.writeFloat(carbohydrate);
        dest.writeFloat(protein);
        dest.writeFloat(totalFat);
        dest.writeFloat(saturatedFat);
        dest.writeFloat(transFat);
        dest.writeFloat(water);
        dest.writeFloat(fibers);
        dest.writeFloat(sodium);
        dest.writeFloat(vitaminC);
        dest.writeFloat(calcium);
        dest.writeFloat(iron);
        dest.writeFloat(vitaminA);
        dest.writeFloat(potassium);
        dest.writeFloat(magnesium);
        dest.writeFloat(vitaminE);
        dest.writeFloat(thiamine);
        dest.writeString(measureLabel);
        dest.writeFloat(unityMultiplier);
        dest.writeFloat(riboflavin);
        dest.writeFloat(niacin);
        dest.writeString(unity);
    }

    public static final Parcelable.Creator<Meal> CREATOR = new Parcelable.Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public Meal getClone() {
        try {
            return (Meal) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println (" Cloning not allowed. " );
            return this;
        }
    }



}