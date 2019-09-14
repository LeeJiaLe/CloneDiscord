package discord.clone.com.clonediscord;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saber.stickyheader.stickyData.HeaderData;
import com.saber.stickyheader.stickyData.StickyMainData;
import com.saber.stickyheader.stickyView.StickHeaderRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ServerUserListAdapter extends StickHeaderRecyclerView<ServerUserItemData,ServerUserHeaderData>{

    private Context ctx;

    private User[] users={
            AllData.users[0],
            AllData.users[0],
            AllData.users[1],
            AllData.users[2],
            AllData.users[3],
            AllData.users[4],
            AllData.users[4],
            AllData.users[5],
            AllData.users[6],
            AllData.users[7],
            AllData.users[8],
            AllData.users[8],
            AllData.users[9],
            AllData.users[10],
            AllData.users[11],
            AllData.users[12],
            AllData.users[12],
            AllData.users[13],
            AllData.users[14],
            AllData.users[15],
            AllData.users[16],
            AllData.users[16],
            AllData.users[17],
            AllData.users[18],
            AllData.users[19],

    };

    ServerUserListAdapter(Context context){
        this.ctx = context;
        List<ServerUserItemData> items;

        for(int i=0;i<5;i++){
            items = new ArrayList<>();
            items.add(new ServerUserItemData());
            items.add(new ServerUserItemData());
            items.add(new ServerUserItemData());
            items.add(new ServerUserItemData());
            this.setHeaderAndData(items, new ServerUserHeaderData());
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==1){
            return new HeaderViewHolder( (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.server_user_list_header, parent, false));
        }else{
            return new ViewHolder( (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.server_user_list_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).tv.setText(users[position].getUserName());
            ((ViewHolder) holder).tv.setTextColor(Color.parseColor(users[position].getTextColor()));
            ((ViewHolder) holder).rpp.setImage(ctx.getDrawable(users[position].getProfilePic()));
        } else if (holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).tv.setText("sample user group "+String.valueOf(position/5 + 1)+" - 4");
        }
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {
        TextView tx = (TextView) header;
        tx.setText("sample user group "+String.valueOf(headerPosition/5 + 1)+" - 4");
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout cl;
        RoundProfilePic rpp;
        TextView tv;
        ViewHolder(ConstraintLayout itemView) {
            super(itemView);
            cl=itemView;
            rpp = cl.findViewById(R.id.user_profile_pic);
            tv = cl.findViewById(R.id.user_name);
        }
    }

    static class HeaderViewHolder extends  RecyclerView.ViewHolder{
        TextView tv;
        HeaderViewHolder(TextView itemView) {
            super(itemView);
            tv=itemView;
        }
    }
}


class ServerUserHeaderData implements HeaderData{

    @Override
    public int getHeaderLayout() {
        return R.layout.server_user_list_header;
    }

    @Override
    public int getHeaderType() {
        return 1;
    }
}

class ServerUserItemData implements StickyMainData{

}
