package com.cmt.base.dialog;

/**
 * Date: 2020/5/9
 * Time: 9:52
 * author: cmt
 * desc: 专门用来设置dialog属性的参数实体，比如是圆角，显示位置，对齐方式，dialog的宽高，dialog是否可以取消等等常用的属性
 */
public class DialogParams {
    private int roundRadius = 10;
    private int strokeWidth = 0;
    private int dialogWidth = 0;
    private int dialogHeight = 0;
    private int dialogGravity = 0;
    private int layoutX = 0;
    private int layoutY = 0;
    private int fillColor = 0;
    private float[] roundRadiusArray;

    public DialogParams(Builder builder){
        this.roundRadius = builder.roundRadius;
        this.strokeWidth = builder.strokeWidth;
        this.dialogWidth = builder.dialogWidth;
        this.dialogHeight = builder.dialogHeight;
        this.dialogGravity = builder.dialogGravity;
        this.layoutX = builder.layoutX;
        this.layoutY = builder.layoutY;
        this.fillColor = builder.fillColor;
    }

    public float[] getRoundRadiusArray() {
        return roundRadiusArray;
    }

    public int getRoundRadius() {
        return roundRadius;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public int getDialogWidth() {
        return dialogWidth;
    }

    public int getDialogHeight() {
        return dialogHeight;
    }

    public int getDialogGravity() {
        return dialogGravity;
    }

    public int getLayoutX() {
        return layoutX;
    }

    public int getLayoutY() {
        return layoutY;
    }

    public int getFillColor() {
        return fillColor;
    }

    public static class Builder{
        private int roundRadius = 10;
        private int strokeWidth = 0;
        private int dialogWidth = 0;
        private int dialogHeight = 0;
        private int dialogGravity = 0;
        private int layoutX = 0;
        private int layoutY = 0;
        private int fillColor = 0;
        private float[] roundRadiusArray;

        public Builder setRoundRadiusArray(float[] roundRadiusArray) {
            this.roundRadiusArray = roundRadiusArray;
            return this;
        }

        public Builder setRoundRadius(int roundRadius) {
            this.roundRadius = roundRadius;
            return this;
        }

        public Builder setStrokeWidth(int strokeWidth) {
            this.strokeWidth = strokeWidth;
            return this;

        }

        public Builder setDialogWidth(int dialogWidth) {
            this.dialogWidth = dialogWidth;
            return this;
        }

        public Builder setDialogHeight(int dialogHeight) {
            this.dialogHeight = dialogHeight;
            return this;

        }

        public Builder setDialogGravity(int dialogGravity) {
            this.dialogGravity = dialogGravity;
            return this;

        }

        public Builder setLayoutX(int layoutX) {
            this.layoutX = layoutX;
            return this;

        }

        public Builder setLayoutY(int layoutY) {
            this.layoutY = layoutY;
            return this;

        }

        public Builder setFillColor(int fillColor) {
            this.fillColor = fillColor;
            return this;
        }
        public DialogParams build(){
            return new DialogParams(this);
        }
    }
}
