package app.logic.logic.util;

import android.animation.Animator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.IdRes;

public class TitleAnimation {

    final TextView title;
    final int way;
    final String newTitle;

    public TitleAnimation(View view, @IdRes int res, int way, String newTitle) {
        this.title = view.findViewById(res);
        this.way = way;
        this.newTitle = newTitle;
    }

    public void animate() {
        title.animate()
                .translationX(-title.getWidth() * way)
                .alpha(0.0f)
                .setDuration(1000)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        final int prevWidth = title.getWidth();

                        title.setText(newTitle);

                        title.setTranslationX((prevWidth + title.getWidth()) * way);
                        title.animate()
                                .translationX(-title.getWidth() * way)
                                .alpha(1.0f)
                                .setDuration(1000)
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .setStartDelay(500)
                                .start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                })
                .start();
    }

}
