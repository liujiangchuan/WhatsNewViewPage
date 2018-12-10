package nz.co.horsebean.whatsnewviewpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WhatsNewActivity extends AppCompatActivity implements View.OnClickListener
{
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private Button mBtnLeft;
    private Button mBtnRight;

    private List<View> mViewList;
    private List<View> mDots;
    private int previousPosition;
    private int mViewSize;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_new);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mLinearLayout = (LinearLayout) findViewById(R.id.lineLayout_dot);
        mBtnLeft = (Button) findViewById(R.id.left);
        mBtnRight = (Button) findViewById(R.id.right);
        mBtnLeft.setOnClickListener(this);
        mBtnRight.setOnClickListener(this);
        initData();
        initView();
    }

    public void initData()
    {
        String[] mImageTitles = new String[]{"title1", "title2", "title3", "title4"};
        int[] imageRess =
                new int[]{R.drawable.ic_1, R.drawable.ic_2, R.drawable.ic_3, R.drawable.ic_4};
        mViewSize = imageRess.length;

        mViewList = new ArrayList<>();
        for (int i = 0; i < mViewSize; i++)
        {
            View view = getLayoutInflater().inflate(R.layout.view_whatsnew, null);
            view.findViewById(R.id.img_whats_new).setBackgroundResource(imageRess[i]);
            ((TextView) view.findViewById(R.id.tv_title)).setText(mImageTitles[i]);
            mViewList.add(view);
        }

        mDots = new ArrayList<>();
        for (int i = 0; i < mViewSize; i++)
        {
            View dot = new View(this);
            if (0 == i)
            {
                dot.setBackgroundResource(R.drawable.ic_lens_selected);
            }
            else
            {
                dot.setBackgroundResource(R.drawable.ic_lens_unselected);
            }
            LinearLayout.LayoutParams dotParams =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            dotParams.width = 16;
            dotParams.height = 16;
            dotParams.setMargins(4, 0, 4, 0);
            dot.setLayoutParams(dotParams);
            mLinearLayout.addView(dot);
            mDots.add(dot);
        }
    }

    public void initView()
    {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mViewList);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override public void onPageSelected(int position)
            {
                mDots.get(position).setBackgroundResource(R.drawable.ic_lens_selected);
                mDots.get(previousPosition).setBackgroundResource(R.drawable.ic_lens_unselected);
                previousPosition = position;

                if(position == mViewSize - 1)
                {
                    mBtnLeft.setVisibility(View.INVISIBLE);
                    mBtnRight.setText(R.string.done);
                }
                else
                {
                    mBtnLeft.setVisibility(View.VISIBLE);
                    mBtnRight.setText(R.string.next);
                }
            }

            @Override public void onPageScrollStateChanged(int state)
            {

            }
        });
        mViewPager.setCurrentItem(0);
    }

    @Override public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.left:
                startMainActivity();
                break;
            case R.id.right:
                if(getString(R.string.done).equals(((Button)v).getText()))
                {
                    startMainActivity();
                }
                else
                {
                    mViewPager.setCurrentItem(previousPosition + 1);
                }
                break;
        }
    }

    private void startMainActivity()
    {
        finish();
        startActivity(new Intent(WhatsNewActivity.this, MainActivity.class));
    }
}
