package com.ps.classes;

import java.util.List;

public class Sandwich implements Product{
    public enum BreadTypes {
        WHITE, WHEAT, RYE, WRAP
    }
    public enum Size {
        SMALL(4), MEDIUM(8), LARGE(12);
        private double price;

        // Constructor
        Size(int size) {
            if(size == 4)
                this.price = 5.50;
            if(size ==8)
                this.price = 7.00;
            if(size==12)
                this.price = 8.50;
        }

        public double getPrice() {
            return this.price;
        }
    }
    private List<Topping> topping;
    private boolean isToasted;
    private BreadTypes breadType;
    //Consturctor
    public Sandwich(List<Topping> topping, boolean isToasted, BreadTypes breadType) {
        this.topping = topping;
        this.isToasted = isToasted;
        this.breadType = breadType;
    }

    public List<Topping> getTopping() {
        return topping;
    }

    public void setTopping(List<Topping> topping) {
        this.topping = topping;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    @Override
    public double calculatePrice(int numberOfSandwiches) {
        return 0;
    }


}
