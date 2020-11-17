package app.example.henriqueribeirodealmeida.nutre;

import android.content.Context;

import java.text.DecimalFormat;

import app.example.henriqueribeirodealmeida.nutre.Data.UserInfoContainer;

public class Helpers {

    public final static int MALE = 1;
    public final static int FEMALE = 2;

    public final static int LOW = 1;
    public final static int REGULAR = 2;
    public final static int INTENSE = 3;

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
        int gender = UserInfoContainer.getGender(context);
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

    public static double calculateIMC(Context context){

        int height = UserInfoContainer.getHeight(context);
        double weight = UserInfoContainer.getWeight(context);
        double imcFinal = (weight/(height*height))*10000;
        return imcFinal;


    }

    public static String textIMC(Context context){

        int imc = (int) calculateIMC(context);
        if (imc < 18.5) {
            return "Valores de IMC de referência:\n Você está abaixo do peso";
        } if (imc > 18.5 && imc <24.0) {
            return "Valores de IMC de referência:\n Peso normal";
        }if (imc > 24.0 && imc <29.9) {
            return "Valores de IMC de referência:\n Sobrepeso";
        }if (imc > 29.9 && imc <34.9) {
            return "Valores de IMC de referência:\n Obesidade grau 1";
        }if (imc > 34.9 && imc <39.9) {
            return "Valores de IMC de referência:\n Obesidade grau 2";
        }if (imc > 39.9) {
            return "Valores de IMC de referência:\n Obesidade grau 3";
        }

        return "";
    }

    public static String formatDate(String raw, boolean isComplete){
        String date, day, numMonth, month, time;

        numMonth = raw.substring(5, 7);
        day = raw.substring(8, 10);
        time = raw.substring(11, 16);

        switch (numMonth){
            case "01":
                month = " janeiro ";
                break;
            case "02":
                month = " fevereiro ";
                break;
            case "03":
                month = " março ";
                break;
            case "04":
                month = " abril ";
                break;
            case "05":
                month = " maio ";
                break;
            case "06":
                month = " junho ";
                break;
            case "07":
                month = " julho ";
                break;
            case "08":
                month = " agosto ";
                break;
            case "09":
                month = " setembro ";
                break;
            case "10":
                month = " outubro ";
                break;
            case "11":
                month = " novembro ";
                break;
            case "12":
                month = " dezembro ";
                break;
            default:
                month = "";
        }

        if (day.equals("01")){
            day = "1º";
        }

        if (isComplete){
            date = day + " de" + month + "às " + time;
        } else {
            date = day + " de" + month;
        }

        return date;
    }

    public static double otherRequiredEnergy(Context context){


        int helper = (int) calculateRequiredEnergy(context);

        return helper;
    }


}

