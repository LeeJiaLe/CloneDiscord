package discord.clone.com.clonediscord;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;



public class ChannelGroupView extends ConstraintLayout{

    private TextView channelGroupName;
    private Button addChannel;
    private LinearLayout channelGroupList;
    private Boolean showChannel=false;
    private ChannelGroup channelGroup;
    private ChannelGroupView.OnClickListener onClick;
    //Group index and selected index
    private int selectedCGIndex;
    private int selectedCIndex;
    private int g;
    private ChannelNameText.OnClick channelNameTextOnClick;

    public ChannelGroupView(Context context) {
        super(context);
        init(context);
    }

    public ChannelGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChannelGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setAllIndex(int sg, int sc, int g){
        this.selectedCGIndex = sg;
        this.selectedCIndex = sc;
        this.g=g;
    }

    private void init(Context context){
        inflate(context, R.layout.channel_group_view, this);
        channelGroupName = findViewById(R.id.channel_group_name);
        channelGroupList = findViewById(R.id.channel_group_list);
        channelGroupName.setOnClickListener(v->{
            this.toggleChannelView();
            this.onClick.onClick();
        });

//        this.setShowChannel(true);
//        this.toggleChannelView();
//        this.setChannelNameVisibility(false);
    }

    public void setOnClick(OnClickListener onClick){
        this.onClick = onClick;
    }
    public void setChannelNameVisibility(Boolean b){
        if(b){
            for(int i=0;i<channelGroupList.getChildCount();i++){
                channelGroupList.getChildAt(i).setVisibility(VISIBLE);
                channelGroupList.getChildAt(i).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            }

        }else{
            for(int i=0;i<channelGroupList.getChildCount();i++){
                if(i!=this.selectedCIndex||this.g!=this.selectedCGIndex){
                    channelGroupList.getChildAt(i).setVisibility(INVISIBLE);
                    channelGroupList.getChildAt(i).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0));
                }
            }
        }
    }

    public void setChannelGroup(ChannelGroup channelGroup) {
        this.channelGroup = channelGroup;
        channelGroupName.setText(channelGroup.getGroupName());
        channelGroupList.removeAllViews();
        int i=0;
        for (Channel channel : this.channelGroup.getChannels()) {
            ChannelNameText channelNameText = new ChannelNameText(this.getContext());
            channelNameText.setType(channel.getType());
            channelNameText.setChannelName(channel.getName());
            channelNameText.setGC(this.g,i);
            if(this.selectedCIndex==i && this.selectedCGIndex==this.g){
                channelNameText.setSelected("1");
            }else{
                channelNameText.setSelected("0");
            }

            channelNameText.setOnClick(this.channelNameTextOnClick);
            channelGroupList.addView(channelNameText);
            i++;
        }

        this.showChannel=channelGroup.getSelected();
        this.setChannelNameVisibility(this.showChannel);
    }

    public void setShowChannel(Boolean b){
        this.showChannel = b;
    }

    private void toggleChannelView(){
        this.showChannel=(!this.showChannel);
        this.setChannelNameVisibility(this.showChannel);
    }

    public void setChannelNameTextOnClick(ChannelNameText.OnClick oc){
        this.channelNameTextOnClick = oc;
    }

    interface OnClickListener{
        void onClick();
    }
}
