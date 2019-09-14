package discord.clone.com.clonediscord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Date;

public class MessagesRecyclerView extends RecyclerView{

    private MessageListAdapter messageListAdapter;
    public MessagesRecyclerView(Context context) {
        super(context);
    }

    public MessagesRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MessagesRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init(Context context, Channel channel){
        this.setLayoutManager(new LinearLayoutManager(context));
        this.messageListAdapter = new MessageListAdapter(context,channel);
        this.setAdapter(this.messageListAdapter);
    }

    public void setChannel(Channel channel){
        this.messageListAdapter.setChannel(channel);
        this.messageListAdapter.notifyDataSetChanged();
    }
}

class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessagesViewHolder>{

    private Channel channel;
    private Context ctx;
    MessageListAdapter(Context ctx, Channel channel){
        this.ctx = ctx;
        this.channel = channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageView messageView = new MessageView(this.ctx);

        return new MessagesViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        DiscordMessage dm = channel.getDiscordMessages()[position];
        int invi=0;
        if(position!=0){
            invi=this.getDay(channel.getDiscordMessages()[position].getDate())==this.getDay(this.channel.getDiscordMessages()[position-1].getDate())?
                    1:0;
        }
        holder.messageView.setDate(dm.getDate());
        holder.messageView.setDateTop(invi,dm.getDate());
        holder.messageView.setMainContent(dm.getMessage());
        holder.messageView.setProfilePic(dm.getUser().getProfilePic());
        holder.messageView.setUserName(dm.getUser().getUserName(),dm.getUser().getTextColor());
    }

    private int getDay(Date date){
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.DAY_OF_MONTH);
    }
    @Override
    public int getItemCount() {
        return channel.getDiscordMessages().length;
    }

    static class MessagesViewHolder extends RecyclerView.ViewHolder{

        MessageView messageView;
        MessagesViewHolder(MessageView itemView) {
            super(itemView);
            messageView = itemView;
        }
    }
}
