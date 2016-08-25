package zhaobf.viewpagerdemo;

import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * viewpager练习
 */
public class MainActivity extends AppCompatActivity {
    int[] colors = new int[]{android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_orange_light};
    List<ImageView> list = new ArrayList<ImageView>();
    private ViewPager viewpager;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for (int i = 0; i < colors.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundColor(getResources().getColor(colors[i]));
            list.add(image);
        }

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new MyAdapter();
        viewpager.setAdapter(adapter);


    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * Determines whether a page View is associated with a specific key object
         * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
         * required for a PagerAdapter to function properly.
         *
         * @param view   Page View to check for association with <code>object</code>
         * @param object Object to check for association with <code>view</code>
         * @return true if <code>view</code> is associated with the key object <code>object</code>
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}
