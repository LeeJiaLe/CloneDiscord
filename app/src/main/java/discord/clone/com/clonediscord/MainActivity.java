package discord.clone.com.clonediscord;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ServerRecyclerView serverListView;
    private ChannelRecyclerView channelListView;
    private MessagesRecyclerView messegesListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AllData();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("channel220");

        NavigationView navEnd = findViewById(R.id.nav_view_end);
        toolbar.setLogo(R.drawable.ic_hash);
        toolbar.setOnMenuItemClickListener((v)->{
            int id = v.getItemId();

            if(id==R.id.action_friend){
                drawer.openDrawer(navEnd);
            }else if(id==R.id.action_search){
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
            }
            return true;
        });
        Toolbar ctoolbar = findViewById(R.id.channel_toolbar);

        LinearLayout bottomSheet = findViewById(R.id.server_bottom_sheet);
        BottomSheetBehavior sheetBehavior= BottomSheetBehavior.from(bottomSheet);
        TextView bottomSheetTitle = bottomSheet.findViewById(R.id.bottom_sheet_title);
        RoundProfilePic bottomIcon= bottomSheet.findViewById(R.id.bottom_icon);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        ctoolbar.inflateMenu(R.menu.channel);
        ctoolbar.setTitle("server3");
        ctoolbar.setOnMenuItemClickListener((v)->{
            if(sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }else{
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
            return true;
        });
        View backShadow = findViewById(R.id.back_shadow);
        backShadow.setOnClickListener((v)->{
            if(sheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED){
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState==BottomSheetBehavior.STATE_COLLAPSED){
                    backShadow.setVisibility(View.GONE);
                }else{
                    backShadow.setVisibility(View.VISIBLE);
                    backShadow.setLayoutParams(new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.MATCH_PARENT));
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        messegesListView = findViewById(R.id.re_message_list);

        channelListView = findViewById(R.id.re_channel_list);
        channelListView.setMessageRecycleView(messegesListView);
        channelListView.setCloseDrawerAction((s)->{
            drawer.closeDrawers();
            setTitle(s);
        });

        serverListView = findViewById(R.id.re_server_list);
        serverListView.setChannelRecyclerView(channelListView);
        serverListView.setOnServerIconClick((i,s)->{
            ctoolbar.setTitle(s);
            bottomSheetTitle.setText(s);
            bottomIcon.setImage(getDrawable(i));

        });

        RecyclerView serverUserList = findViewById(R.id.server_user_list);
        serverUserList.setLayoutManager(new LinearLayoutManager(this));
        serverUserList.setAdapter(new ServerUserListAdapter(this));

        ImageButton setting = findViewById(R.id.btn_acc_setting);
        setting.setOnClickListener((v)->{
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//         Handle action bar item clicks here. The action bar will
//         automatically handle clicks on the Home/Up button, so long
//         as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }else if(id == R.id.action_friend) {
            return true;
        }else if(id == R.id.action_point) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
