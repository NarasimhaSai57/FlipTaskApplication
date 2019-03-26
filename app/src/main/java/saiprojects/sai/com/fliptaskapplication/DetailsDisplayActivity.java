package saiprojects.sai.com.fliptaskapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsDisplayActivity extends AppCompatActivity {

    String name,image,character,gameseries;
    ImageView img_display;
    TextView tv_title,tv_character,tv_gameseries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_display);

        img_display = findViewById(R.id.img_display);
        tv_gameseries = findViewById(R.id.tv_gameseries);
        tv_title = findViewById(R.id.tv_title);
        tv_character = findViewById(R.id.tv_character);

        if (getIntent() != null)
        {
            name = getIntent().getExtras().getString("title");
            image = getIntent().getExtras().getString("image");
            character = getIntent().getExtras().getString("character");
            gameseries = getIntent().getExtras().getString("gameseries");


            if(name!=null && !name.equalsIgnoreCase("")){
                tv_title.setText("Title  :  "+name);
            }
            if(character!=null && !character.equalsIgnoreCase("")){
                tv_character.setText("Character  :  "+character);
            }
            if(gameseries!=null && !gameseries.equalsIgnoreCase("")){
                tv_gameseries.setText("Game Series  :  "+gameseries);
            }
            if(image!=null && !image.equalsIgnoreCase("")){
                Picasso.get().load(image).fit().centerInside().into(img_display);
            }
        }

    }
}
