package com.snail.track.samples.custom_view.demos.pie_chart;

public class PieData {

    private String name;
    private float value;

    private float percentage;
    private int color = -1;
    private float angle;

    public PieData(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setupPercentage(float valueSum) {
        this.percentage = value / valueSum;
        this.angle = percentage * 360;
    }

    public float getPercentage() {
        return percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

}
