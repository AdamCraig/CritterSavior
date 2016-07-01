package com.epicodus.supremeinvention;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;

        import butterknife.Bind;
        import butterknife.ButterKnife;

public class MyPreferencesActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.favoritesButton) Button mFavoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_preferences);
        ButterKnife.bind(this);
        mFavoritesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mFavoritesButton) {
            Intent intent = new Intent(MyPreferencesActivity.this, MyFavoritesActivity.class);
            startActivity(intent);
        }
    }
}
