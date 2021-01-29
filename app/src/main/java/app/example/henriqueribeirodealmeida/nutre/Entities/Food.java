package app.example.henriqueribeirodealmeida.nutre.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "food",
        foreignKeys = {
            @ForeignKey(entity = DailyMeal.class,
                parentColumns = "id",
                childColumns = "dailyMealId",
                onDelete = CASCADE),
            @ForeignKey(entity = Meal.class,
                parentColumns = "id",
                childColumns = "mealId",
                onDelete = CASCADE),
        })
public class Food implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    private double quantityPerUnit;
    private String measure;
    private int dailyMealId;
    private Integer mealId;

    public Food() {
    }

    public Food(String name, double  quantity, String measure, Integer mealId){
        this.name = name;
        this.quantityPerUnit = quantity;
        this.measure = measure;
        this.mealId = mealId;

    }

    public Food(String name, double quantity, String measure){
        this.name = name;
        this.quantityPerUnit = quantity;
        this.measure = measure;

    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setQuantityPerUnit(double quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getQuantityPerUnit(){
        return quantityPerUnit;
    }

    public String getMeasure(){
        return measure;
    }

    public int getDailyMealId(){
        return this.dailyMealId;
    }

    public void setDailyMealId(int id){
        this.dailyMealId = id;
    }

    public Integer getMealId(){
        return this.mealId;
    }

    public void setMealId(Integer id){
        this.mealId = id;
    }

    protected Food(Parcel in){
        id = in.readInt();
        name = in.readString();
        quantityPerUnit = in.readDouble();
        measure = in.readString();
        dailyMealId = in.readInt();
        mealId = in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(quantityPerUnit);
        dest.writeString(measure);
        dest.writeInt(dailyMealId);
        dest.writeInt(mealId);

    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
