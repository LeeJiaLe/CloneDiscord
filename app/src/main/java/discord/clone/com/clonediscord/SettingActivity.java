package discord.clone.com.clonediscord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lee Jia Le on 5/17/2019.
 */

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.user_setting);
        setTitle("User Setting");
    }
}
