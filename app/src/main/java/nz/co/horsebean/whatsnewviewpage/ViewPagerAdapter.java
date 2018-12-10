package nz.co.horsebean.whatsnewviewpage;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Trent on 2018/12/8.
 * Email liujiangchuan@hotmail.com
 */

public class ViewPagerAdapter extends PagerAdapter
{
    private List<View> mViews;

    ViewPagerAdapter(List<View> mViews){
        this.mViews = mViews;
    }

    @Override public int getCount()
    {
        if(null == mViews)
            return 0;
        return mViews.size();
    }

    @Override public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override public Object instantiateItem(ViewGroup container, int position)
    {
        View iv = mViews.get(position);
        container.addView(iv);
        return iv;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView(mViews.get(position));
    }
}
