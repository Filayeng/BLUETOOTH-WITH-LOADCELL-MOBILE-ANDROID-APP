package com.example.suttartmaca;

public class animationClass {
    public static int[] imageList = {R.drawable.truck0, R.drawable.truck1, R.drawable.truck2, R.drawable.truck3, R.drawable.truck4, R.drawable.truck5, R.drawable.truck6, R.drawable.truck7, R.drawable.truck8, R.drawable.truck9, R.drawable.truck10, R.drawable.truck11, R.drawable.truck12, R.drawable.truck13, R.drawable.truck14};
    private static int selectImage = R.drawable.truck0;

    public static int imageHolder() {
        int dN = calculatorMethods.imageDividerNumber();
        Integer tD = MainActivity.totalData;
        int i = 0;
        while (true) {
            if (i >= 15) {
                break;
            }
            boolean z = false;
            boolean z2 = tD.intValue() > dN * i;
            if (tD.intValue() < (i + 1) * dN) {
                z = true;
            }
            if (z2 && z) {
                selectImage = imageList[i + 1];
                break;
            }
            i++;
        }
        if (tD.intValue() == 0) {
            selectImage = R.drawable.truck0;
        }
        return selectImage;
    }
}
