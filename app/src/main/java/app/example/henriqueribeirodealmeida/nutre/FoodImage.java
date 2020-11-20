package app.example.henriqueribeirodealmeida.nutre;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodImage implements Parcelable {
    private int mFoodImage;

    public FoodImage(int foodImage){
        mFoodImage=foodImage;
    }


    protected FoodImage(Parcel in) {
        mFoodImage = in.readInt();
    }

    public static final Creator<FoodImage> CREATOR = new Creator<FoodImage>() {
        @Override
        public FoodImage createFromParcel(Parcel in) {
            return new FoodImage(in);
        }

        @Override
        public FoodImage[] newArray(int size) {
            return new FoodImage[size];
        }
    };

    public int getFoodImage(){
        return mFoodImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mFoodImage);
    }
}
