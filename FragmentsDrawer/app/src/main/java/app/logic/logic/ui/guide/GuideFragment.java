package app.logic.logic.ui.guide;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import app.logic.logic.R;

public class GuideFragment extends Fragment {

    private int position;

    public GuideFragment(int position) {
        this.position = position;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guide_pager_item, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        final VideoView mVideoView = view.findViewById(R.id.guide_item_video);

        StringBuilder uri = new StringBuilder("android.resource://" + getActivity().getPackageName() + "/");

        switch (position) {
            case 1:
                uri.append(R.raw.tutorial_0_mp4);
                break;
            case 2:
                uri.append(R.raw.tutorial_1_mp4);
                break;
            case 3:
                uri.append(R.raw.tutorial_2_mp4);
                break;
            case 4:
                uri.append(R.raw.tutorial_3_mp4);
                break;
            case 5:
                uri.append(R.raw.tutorial_4_mp4);
                break;
            case 6:
                uri.append(R.raw.tutorial_5_mp4);
                break;
            case 7:
                uri.append(R.raw.tutorial_6_mp4);
                break;
            default:
                mVideoView.setVisibility(View.GONE);
        }

        if (position > 0 && position < 8) {
            mVideoView.setVideoURI(Uri.parse(uri.toString()));

            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);

                    /*

                        final float scale = mp.getVideoWidth() / mVideoView.getWidth();

                        mVideoView.setScaleX(scale >= 1 ? scale : 1 / scale);
                        mVideoView.setScaleY(scale >= 1 ? scale : 1 / scale);

                     */

                    if (!mVideoView.isPlaying())
                        mVideoView.start();
                }
            });
        }
    }

}
