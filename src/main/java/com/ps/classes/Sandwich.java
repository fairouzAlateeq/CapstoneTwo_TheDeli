package com.ps.classes;

import java.util.List;

public class Sandwich implements Product{
    private enum BreadTypes {
        WHITE, WHEAT, RYE, WRAP
    }
    private enum Size {
        SMALL(4), MEDIUM(8), LARGE(12);
        private int price;

        // Constructor
        Size(int price) {
            this.price = price;
        }
        public int getPrice() {
            return price;
        }
    }
    private List<Topping> topping;
    private boolean isToasted;

    //Consturctor
    public Sandwich(List<Topping> topping, boolean isToasted) {
        this.topping = topping;
        this.isToasted = isToasted;
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
