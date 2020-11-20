package app.example.henriqueribeirodealmeida.nutre.Entities;

import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

public class Nutrient{

    private ArrayList<DailyMeal> dailyMeals; //teste


    private String name;
    private double value;
    private double suggestedValue;
    private String measure;
    private int human;
    private int addValue;
    private String Date;
    private double porcent;

    public Nutrient(){}


    public Nutrient(String name, int value, String measure, int human) {
        this.name = name;
        this.value = value;
        this.measure = measure;
        this.human =human;

    }
    //Nome do alimento
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Valor Principal
    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    //Unidade
    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    //valor sugerido
    public double getSuggestedValue() {
        return suggestedValue;
    }

    public void setSuggestedValue(double suggestedValue) {
        this.suggestedValue = suggestedValue;
    }

    //Silhueta
    public int getHuman() {
        return human;
    }

    public void setHuman(){

        double result =  (value/ suggestedValue)*100;



        if (result <= 0.0 ) {

           this.human = (R.mipmap.human_0);

       } else if(result > 0.0 & result < 12.5) {

           this.human = (R.mipmap.human_1);

        }else if(result >= 12.5 & result < 25.0){

           this.human = (R.mipmap.human_3);

       } else if (result >= 25.0 & result < 37.5) {

          this.human = (R.mipmap.human_4);

       } else if (result >= 37.5 & result < 50.0) {

          this.human = (R.mipmap.human_5);

        } else if (result >= 50.0 & result < 72.5) {

          this.human = (R.mipmap.human_6);

        } else if(result >= 72.5 & result <85.0){

           this.human = (R.mipmap.human_7);

        }else if(result >= 85.0 & result < 100 ){

          this.human = (R.mipmap.human_8);

        }else if(result >= 100){
           this.human= (R.mipmap.human_9);
        }

    }

   //porcentagem
    public double getPorcent() {
        return porcent;
    }

    public void setPorcent() {
        double result =  (value/ suggestedValue)*100;

        this.porcent = result;
    }

    //Resultado do Edit
    public int getAddValue() {
        return addValue;
    }

    public void setAddValue(int addValue) {
        this.addValue = addValue;
    }

    //pega data
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


    public int Values(int x){
        switch(x){
            case 0:
                setName("Energia");
                break;
            case 1:
               setName("Água");
                break;
            case 2:
                setName("Carboidrato");
                break;
            case 3:
                setName("Proteína");
                break;
            case 4:
                setName( "Gorduras\n totais");
                break;
            case 5:
                setName( "Gorduras \nsaturadas");
                break;
            case 6:
                setName("Fibras");
                break;
            case 7:
                setName( "Sódio");
                break;
            case 8:
                setName("Vitamina C");
                break;
            case 9:
                setName( "Cálcio");
                break;
            case 10:
                setName( "Ferro");
                break;
            case 11:
                setName("Vitamina A");
                break;
            case 12:
                setName( "Potássio");
                break;
            case 13:
                setName( "Magnésio");
                break;
            case 14:
                setName( "Tiamina");
                break;
            case 15:
                setName( "Riboflavina");
                break;
            case 16:
                setName( "Niacina");
                break;

        }

        return 0;
    }

    public int Measure(int x){
        switch(x){
            case 0:
                setMeasure("kcal");
                break;
            case 1:
                setMeasure("ml");
                break;
            case 2:
                setMeasure("g");
                break;
            case 3:
                setMeasure("g");
                break;
            case 4:
                setMeasure("g");
                break;
            case 5:
                setMeasure("g");
                break;
            case 6:
                setMeasure("g");
                break;
            case 7:
                setMeasure("mg");
                break;
            case 8:
                setMeasure("mg");
                break;
            case 9:
                setMeasure("mg");
                break;
            case 10:
                setMeasure("mg");
                break;
            case 11:
                setMeasure("µg");
                break;
            case 12:
                setMeasure("mg");
                break;
            case 13:
                setMeasure("mg");
                break;
            case 14:
                setMeasure("mg");
                break;
            case 15:
                setMeasure("mg");
                break;
            case 16:
                setMeasure("mg");
                break;

        }

        return 0;
    }

}
