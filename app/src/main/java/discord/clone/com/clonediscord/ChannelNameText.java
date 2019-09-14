package discord.clone.com.clonediscord;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StyleableRes;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;

public class ChannelNameText extends ConstraintLayout {
    private String channelName;
    private String type;
    private String selected="0";
    private TextView txtChannelName;
    private OnClick oc;
    private int c;
    private int g;
    @StyleableRes
    int[] srcIndex = {0,1,2};
    public ChannelNameText(Context context) {
        super(context);
        init(context);
    }

    public ChannelNameText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChannelNameText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public void init(Context context){
        inflate(context, R.layout.channel_name_view, this);
        this.txtChannelName = findViewById(R.id.channel_name_text_view);
    }

    public void init(Context context, AttributeSet attrs){
        inflate(context, R.layout.channel_name_view, this);
        int[] sets = {R.attr.cnt_name, R.attr.cnt_type};
        this.txtChannelName = findViewById(R.id.channel_name_text_view);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);

        this.setChannelName(typedArray.getText(srcIndex[0]).toString());
        this.setType(typedArray.getText(srcIndex[1]).toString());

        typedArray.recycle();


    }

    public void setGC(int g, int c){
        this.g=g;
        this.c=c;
    }

    public void setOnClick(OnClick oc){
        this.oc = oc;
        this.setOnClickListener(v->this.oc.onClick(this.g,this.c));
    }

    public void setChannelName(String channelName){
        this.channelName  = channelName;
        this.txtChannelName.setText(channelName);
    }

    public void setSelected(String selected){
        this.selected = selected;
        if(selected.equals("1")){
            this.setBackgroundResource(R.drawable.bg_channel_name);
        }else{
            this.setBackgroundResource(0);
        }
    }

    public void setType(String type) {
        this.type = type;
        if(this.type.equals("voice")){
            this.txtChannelName.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_speaker_grey, 0, 0, 0);

        }else{
            this.txtChannelName.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_hash_channel_text, 0, 0, 0);
        }
    }

    interface OnClick{
        void onClick(int g,int c);
    }
}
