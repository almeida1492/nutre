package com.example.henriqueribeirodealmeida.nutre;

import android.content.Context;
import android.util.Log;

import com.example.henriqueribeirodealmeida.nutre.Data.UserInfoContainer;

public class Helpers {

    public final static int MALE = 3;
    public final static int FEMALE = 4;

    public final static int LOW = 0;
    public final static int REGULAR = 1;
    public final static int INTENSE = 2;

    private Helpers() {
    }

    private static double getWeightToUse(double inputWeight, int height, int gender) {
        double maxBMI, minBMI;

        double dHeight = height;
        double heightInMeters = dHeight / 100;

        switch (gender) {
            case MALE:
                minBMI = 20.1;
                maxBMI = 25.0;
                break;
            case FEMALE:
                minBMI = 18.7;
                maxBMI = 23.8;
                break;
            default:
                minBMI = 0;
                maxBMI = 0;
                break;
        }

        double minWeight = (heightInMeters * heightInMeters) * minBMI;
        double maxWeight = (heightInMeters * heightInMeters) * maxBMI;

        if (inputWeight < minWeight) {
            return minWeight;
        } else if (inputWeight > maxWeight) {
            return maxWeight;
        }

        return inputWeight;
    }

    private static double getPhysicalActivityFactor(int gender, int intensity) {
        switch (gender) {
            case MALE:
                switch (intensity){
                    case LOW:
                        return 1.55;
                    case REGULAR:
                        return 1.78;
                    case INTENSE:
                        return 2.10;
                }
                break;
            case FEMALE:
                switch (intensity){
                    case LOW:
                        return 1.56;
                    case REGULAR:
                        return 1.64;
                    case INTENSE:
                        return 1.82;
                }
                break;
            default:
                break;
        }
        return 0;
    }

    public static double calculateRequiredEnergy(Context context) {
        double energy = 0;

        double age = UserInfoContainer.getAge(context);
        int height = UserInfoContainer.getHeight(context);
        double weight = UserInfoContainer.getWeight(context);
        //TODO implement gender input
        int gender = MALE;
        int physicalActivityIntensity = UserInfoContainer.getPhysicalActivityIntensity(context);

        double weightToUse = getWeightToUse(weight, height, gender);
        double physicalActivityFactor = getPhysicalActivityFactor(gender, physicalActivityIntensity);

        if (age < 18){
            switch (gender) {
                case MALE:
                    return (17.68 * weightToUse + 658.2) * physicalActivityFactor;
                case FEMALE:
                    return (13.38 * weightToUse + 692.6) * physicalActivityFactor;
                default:
                    break;
            }
        } else if (age < 30){
            switch (gender) {
                case MALE:
                    return ((15.05 * weightToUse) + 629.2) * physicalActivityFactor;
                case FEMALE:
                    return (14.81 * weightToUse + 486.6) * physicalActivityFactor;
                default:
                    break;
            }
        } else if (age < 60) {
            switch (gender) {
                case MALE:
                    return (11.47 * weightToUse + 873.1) * physicalActivityFactor;
                case FEMALE:
                    return (8.12 * weightToUse + 845.6) * physicalActivityFactor;
                default:
                    break;
            }
        } else {
            switch (gender) {
                case MALE:
                    return (11.71 * weightToUse + 587.7) * physicalActivityFactor;
                case FEMALE:
                    return (9.08 * weightToUse + 658.5) * physicalActivityFactor;
                default:
                    break;
            }
        }

        return energy;
    }
}

