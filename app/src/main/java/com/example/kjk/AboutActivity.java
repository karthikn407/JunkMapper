package com.example.kjk;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        TextView appName = findViewById(R.id.app_name);
        appName.setText("About Junk Mapper");

        TextView appDescription = findViewById(R.id.app_description);
        appDescription.setText("This app helps you recycle effectively and learn more about the environment. Here are some of the features:");

        TextView objectIdentification = findViewById(R.id.object_identification);
        objectIdentification.setText("- Object Identification: Take a picture of an item and the app will tell you if it's recyclable or not.");

        TextView recyclingGuidelines = findViewById(R.id.recycling_guidelines);
        recyclingGuidelines.setText("- Recycling Guidelines: Learn how to recycle different materials and reduce your environmental impact.");

        TextView nearbyRecyclingAreaFinder = findViewById(R.id.nearby_recycling_area_finder);
        nearbyRecyclingAreaFinder.setText("- Nearby Recycling Area Finder: Find recycling centers near you and get directions to them.");

        TextView articles = findViewById(R.id.articles);
        articles.setText("- Articles: Read about the latest news and trends in recycling and sustainability.");

        TextView socialMediaSharing = findViewById(R.id.social_media_sharing);
        socialMediaSharing.setText("- Sharing Achievements on Social Media: Share your recycling achievements with your friends and inspire them to recycle more.");

        TextView wasteDisposalGame = findViewById(R.id.waste_disposal_game);
        wasteDisposalGame.setText("- Fun Waste Disposal Game: Play a game to learn how to dispose of waste properly");
    }

}
