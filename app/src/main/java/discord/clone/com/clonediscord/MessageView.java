package discord.clone.com.clonediscord;


import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageView extends ConstraintLayout{
    private TextView messageMainContent;
    private TextView messageUserName;
    private TextView messageDateTop;
    private LinearLayout messageDateTopView;
    private RoundProfilePic messageUserProfile;
    private TextView messageDate;
    private SimpleDateFormat simpleDateFormat;
    private SimpleDateFormat simpleDateFormat2;


    public MessageView(Context context) {
        super(context);
        this.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT));
        this.init(context);
    }

    public MessageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public MessageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context);
    }

    public void init(Context context){
        inflate(context,R.layout.message_view,this);
        messageDate = findViewById(R.id.message_date);
        messageDateTop = findViewById(R.id.message_date_top);
        messageDateTopView = findViewById(R.id.message_date_top_view);
        messageUserProfile = findViewById(R.id.message_profile_pic);
        messageUserName = findViewById(R.id.message_user_name);
        messageMainContent = findViewById(R.id.message_main_content);
        simpleDateFormat=new SimpleDateFormat("MMM dd, yyyy HH:mm");
        simpleDateFormat2=new SimpleDateFormat("MMM dd, yyyy");
    }

    public void setProfilePic(int i){
        messageUserProfile.setImage(getContext().getDrawable(i));
    }

    public void setMainContent(String[] s){
        String n = "";
        for(String cs:s){
            n=n.concat(cs.concat("\n"));
        }
        messageMainContent.setText(n);
    }

    public void setUserName(String s,String color){
        messageUserName.setTextColor(Color.parseColor(color));
        messageUserName.setText(s);
    }

    public void setDate(Date d){
        this.messageDate.setText(this.simpleDateFormat.format(d));
    }

    public void setDateTop(int visible, Date date){
        Log.i("visible",String.valueOf(visible));
        if(visible!=0){
            messageDateTopView.setVisibility(GONE);
        }else{
            messageDateTopView.setVisibility(VISIBLE);
            messageDateTop.setText(this.simpleDateFormat2.format(date));
        }
    }
}
