package com.example.arsalankhan.malakandlevies.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Arsalan khan on 2/23/2017.
 */

public class AnimationUtils {

    public static void animator(RecyclerView.ViewHolder holder,boolean goesDown){

        AnimatorSet animatorSet=new AnimatorSet();
        ObjectAnimator translateY=ObjectAnimator.ofFloat(holder.itemView,"translationY",goesDown==true ?200:-200,0);
        ObjectAnimator translateX=ObjectAnimator.ofFloat(holder.itemView,"translationX",-25,25,-20,20,-15,15,-10,10,-5,5,0);
        translateX.setDuration(500);
        translateY.setDuration(500);

        animatorSet.playTogether(translateX,translateY);
        animatorSet.start();

    }
}
