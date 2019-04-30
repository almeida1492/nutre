package com.example.henriqueribeirodealmeida.nutre.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

@Entity(tableName = "meals")
public class Meal implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    /*private String date;*/
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
    private float selenium;
    private float potassium;
    private float magnesium;
    private float vitaminE;
    private float thiamine;


    public Meal() {
    }


    public Meal(String name, float energy, float carbohydrate, float protein, float totalFat, float saturatedFat, float transFat, float fibers, float sodium, float vitaminC, float calcium, float iron, float vitaminA, float selenium, float potassium, float magnesium, float vitaminE, float thiamine) {
        this.name = name;
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

    public Float getSelenium() {
        return selenium;
    }

    public void setSelenium(Float selenium) {
        this.selenium = selenium;
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
        fibers = in.readFloat();
        sodium = in.readFloat();
        vitaminC = in.readFloat();
        calcium = in.readFloat();
        iron = in.readFloat();
        vitaminA = in.readFloat();
        selenium = in.readFloat();
        potassium = in.readFloat();
        magnesium = in.readFloat();
        vitaminE = in.readFloat();
        thiamine = in.readFloat();
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
        dest.writeFloat(fibers);
        dest.writeFloat(sodium);
        dest.writeFloat(vitaminC);
        dest.writeFloat(calcium);
        dest.writeFloat(iron);
        dest.writeFloat(vitaminA);
        dest.writeFloat(selenium);
        dest.writeFloat(potassium);
        dest.writeFloat(magnesium);
        dest.writeFloat(vitaminE);
        dest.writeFloat(thiamine);
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

}