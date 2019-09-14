package discord.clone.com.clonediscord;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.StyleableRes;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import java.util.Objects;


public class RoundProfilePic extends ConstraintLayout{

    @StyleableRes
    int[] srIndex = {0,1,2};

    private com.mikhaellopez.circularimageview.CircularImageView mainImg;
    private com.mikhaellopez.circularimageview.CircularImageView stateImage;

    public RoundProfilePic(Context context) {
        super(context);
    }

    public RoundProfilePic(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundProfilePic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context, AttributeSet attrs){
        inflate(context, R.layout.ct_round_profile_pic, this);
        int[] sets = {R.attr.rpp_img,R.attr.rpp_state};

        mainImg = findViewById(R.id.round_img_view);
        stateImage = findViewById(R.id.round_status);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        this.setImage(typedArray.getDrawable(srIndex[0]));

        this.setState(typedArray.getText(srIndex[1]).toString());
        typedArray.recycle();

    }

    public void setState(String s){

        if(s.equals("0")){
            stateImage.setVisibility(View.VISIBLE);
            stateImage.setColorFilter(Color.argb(255, 255, 0, 0));
        }else if(s.equals("1")){
            stateImage.setVisibility(View.VISIBLE);
            stateImage.setColorFilter(Color.argb(255, 0, 0, 0));
        }else if(s.equals("2")){
            stateImage.setVisibility(View.INVISIBLE);
        }else if(s.equals("3")){
            stateImage.setVisibility(View.VISIBLE);
            stateImage.setColorFilter(Color.argb(255, 0, 255, 0));
        }
    }
    public void setImage(Drawable d){
        mainImg.setImageDrawable(d);
    }
}
