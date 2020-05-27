package pl.atos.exercise.model;

public class Calculator{

    private float Amount;

    private String currency;

    public Calculator(float amount, String currency){
        Amount=amount;
        this.currency=currency;
    }

    public float getAmount(){
        return Amount;
    }

    public void setAmount(float amount){
        Amount=amount;
    }

    public String getCurrency(){
        return currency;
    }

    public void setCurrency(String currency){
        this.currency=currency;
    }

}
