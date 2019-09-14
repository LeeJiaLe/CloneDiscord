package discord.clone.com.clonediscord;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.makeramen.roundedimageview.RoundedImageView;

public class RoundServerPic extends ConstraintLayout {
    private CircularImageView notSelectedIcon;
    private RoundedImageView selectedIcon;
    private View shadeSelected;
    private View shadeNotSelected;

    private ServerRecyclerView.OnServerIconClick onServerIconClick = (v,s)->{};
    public RoundServerPic(Context context) {
        super(context);
        inflate(context, R.layout.ct_round_server_icon, this);
        initComponent();
    }

    public RoundServerPic(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.ct_round_server_icon, this);
        initComponent();
    }

    public RoundServerPic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.ct_round_server_icon, this);
        initComponent();
    }

    public void initComponent(){
        notSelectedIcon = findViewById(R.id.round_icon_not_selected);
        selectedIcon = findViewById(R.id.round_icon_selected);
        shadeNotSelected = findViewById(R.id.server_round_shade_not);
        shadeSelected = findViewById(R.id.server_round_shade_selected);
        this.setOnClickListener(v->{
            this.onServerIconClick.onClick(1,"");
        });
    }

    public void setData(Boolean selected){
        if(selected){
            selectedIcon.setVisibility(VISIBLE);
            notSelectedIcon.setVisibility(INVISIBLE);
            shadeSelected.setVisibility(VISIBLE);
            shadeNotSelected.setVisibility(INVISIBLE);
        }else{
            selectedIcon.setVisibility(INVISIBLE);
            notSelectedIcon.setVisibility(VISIBLE);
            shadeNotSelected.setVisibility(VISIBLE);
            shadeSelected.setVisibility(INVISIBLE);
        }

    }

    public void setImage(int re){
        notSelectedIcon.setImageResource(re);
        selectedIcon.setImageResource(re);
    }

    public void setServerIconOnClick(ServerRecyclerView.OnServerIconClick v){
        onServerIconClick=v;
    }


}
