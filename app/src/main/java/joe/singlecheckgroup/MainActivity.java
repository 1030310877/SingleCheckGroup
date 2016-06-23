package joe.singlecheckgroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import joe.library.SingleCheckGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SingleCheckGroup group = (SingleCheckGroup) findViewById(R.id.group);
        group.setOnItemClickListener(new SingleCheckGroup.onGroupItemViewClick() {
            @Override
            public void onItemClick(View v) {

            }
        });
    }
}
