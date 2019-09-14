package discord.clone.com.clonediscord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ChannelRecyclerView extends RecyclerView {
    private ChannelListAdapter channelListAdapter;
    public ChannelRecyclerView(Context context) {
        super(context);
        this.init(context);
    }

    public ChannelRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public ChannelRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init(context);
    }

    private void init(Context context){
        this.setLayoutManager(new LinearLayoutManager(context));
        this.channelListAdapter = new ChannelListAdapter(context,AllData.serverData[2]);

        this.setAdapter(this.channelListAdapter);
    }

    public void setServerData(ServerData serverData) {
        this.channelListAdapter.setServerData(serverData);
    }

    public void setMessageRecycleView(MessagesRecyclerView messageRecycleView){
        this.channelListAdapter.setMessagesRecyclerView(messageRecycleView);
    }

    public void setCloseDrawerAction(ChannelListAdapter.CloseDrawerAction closeDrawerAction){
        channelListAdapter.setCloseDrawerAction(closeDrawerAction);
    }

}

class ChannelListAdapter extends RecyclerView.Adapter<ChannelListAdapter.ChannelViewHolder>{

    private Context ctx;
    private MessagesRecyclerView messagesRecyclerView;
    private ServerData serverData;
    private CloseDrawerAction closeDrawerAction = (s)->{};
    ChannelListAdapter(Context ctx,ServerData serverData){
        this.ctx = ctx;
        this.serverData = serverData;

    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChannelGroupView channelGroupView = new ChannelGroupView(this.ctx);
        channelGroupView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT));
        return new ChannelViewHolder(channelGroupView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        holder.channelGroupView.setChannelNameTextOnClick((g,c)->{
            serverData.selectedCGIndex=g;
            serverData.selectedCIndex=c;
            this.notifyDataSetChanged();
            this.triggerMessageListView();
            this.closeDrawerAction.close(serverData.getChannelGroup()[position].getChannels()[c].getName());
        });
        holder.channelGroupView.setAllIndex(serverData.selectedCGIndex,serverData.selectedCIndex,position);
        holder.channelGroupView.setChannelGroup(serverData.getChannelGroup()[position]);
        holder.channelGroupView.setOnClick(()->{
            serverData.setChannelSelected(position,!serverData.getChannelSelected(position));
        });

    }

    void setServerData(ServerData serverData) {
        this.serverData = serverData;
        this.notifyDataSetChanged();
    }

    private void triggerMessageListView(){
        int g = this.serverData.selectedCGIndex;
        int c = this.serverData.selectedCIndex;
        this.messagesRecyclerView.setChannel(serverData.getChannelGroup()[g].getChannels()[c]);
    }


    @Override
    public int getItemCount() {
        return serverData.getChannelGroup().length;
    }

    void setMessagesRecyclerView(MessagesRecyclerView messagesRecyclerView) {
        this.messagesRecyclerView = messagesRecyclerView;
        int g = this.serverData.selectedCGIndex;
        int c = this.serverData.selectedCIndex;

        this.messagesRecyclerView.init(this.ctx,serverData.getChannelGroup()[g].getChannels()[c]);
    }

    public void setCloseDrawerAction(CloseDrawerAction closeDrawerAction) {
        this.closeDrawerAction = closeDrawerAction;
    }

    static class ChannelViewHolder extends RecyclerView.ViewHolder{
        ChannelGroupView channelGroupView;
        ChannelViewHolder(ChannelGroupView itemView) {
            super(itemView);
            channelGroupView = itemView;
        }
    }


    interface CloseDrawerAction{
        void close(String s);
    }
}


