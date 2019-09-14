package discord.clone.com.clonediscord;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class ServerRecyclerView extends RecyclerView{

    private ServerListAdapter serverListAdapter;
    private ChannelRecyclerView channelRecyclerView;
    public ServerRecyclerView(Context context) {
        super(context);
        this.init(context);
    }

    public ServerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public ServerRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init(context);
    }

    private void init(Context context){
        this.setLayoutManager(new LinearLayoutManager(context));

        this.serverListAdapter = new ServerListAdapter(context);
        this.setAdapter(serverListAdapter);
    }

    public void setOnServerIconClick(OnServerIconClick on){
        this.serverListAdapter.setOnServerIconClick(on);

    }

    public void setChannelRecyclerView(ChannelRecyclerView channelRecyclerView) {
        this.serverListAdapter.setChannelRecyclerView(channelRecyclerView);
    }

    interface OnServerIconClick{

        void onClick(int icon,String s);

    }
}

class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.ServerListHolder> {
    private ServerData[] mDataset;
    private Context ctx;
    private int selectedIndex=2;
    private ServerRecyclerView.OnServerIconClick onServerIconClick;
    private ChannelRecyclerView channelRecyclerView;
    ServerListAdapter(Context ctx){
        this.ctx=ctx;
        onServerIconClick = (v,s)->{};
        this.setmDataset();
    }

    private void setmDataset(){
        mDataset = AllData.serverData;
    }

    private void setChannelData(){
        this.channelRecyclerView.setServerData(mDataset[selectedIndex]);
    }

    void setChannelRecyclerView(ChannelRecyclerView channelRecyclerView) {
        this.channelRecyclerView = channelRecyclerView;
        this.setChannelData();
    }

    void setOnServerIconClick(ServerRecyclerView.OnServerIconClick onServerIconClick){
        this.onServerIconClick=onServerIconClick;
    }

    @NonNull
    @Override
    public ServerListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoundServerPic roundServerPic = new RoundServerPic(ctx);

        return new ServerListHolder(roundServerPic);
    }

    @Override
    public void onBindViewHolder(@NonNull ServerListHolder holder, int position) {
        holder.rsc.setData(mDataset[position].getSelected());
        holder.rsc.setImage(mDataset[position].getImgResource());
        holder.rsc.setServerIconOnClick((v,s)->{
            mDataset[selectedIndex].setSelected(false);
            mDataset[position].setSelected(true);
            this.channelRecyclerView.setServerData(mDataset[position]);
            selectedIndex=holder.getAdapterPosition();
            onServerIconClick.onClick(mDataset[position].getImgResource(),mDataset[position].getServerName());
            this.notifyDataSetChanged();
        });
    }


    @Override
    public int getItemCount() {
        return mDataset.length;
    }


    static class ServerListHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        RoundServerPic rsc;

        ServerListHolder(RoundServerPic v) {
            super(v);
            rsc = v;
        }
    }


}


