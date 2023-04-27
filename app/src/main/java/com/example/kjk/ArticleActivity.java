package com.example.kjk;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleActivity extends AppCompatActivity {

    private ImageView articleImage;
    private TextView articleTitle;
    private TextView articleAuthor;
    private TextView articleDate;
    private TextView articleContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);

// Get references to the views
        articleImage = findViewById(R.id.article_image);
        articleTitle = findViewById(R.id.article_title);
        articleContent = findViewById(R.id.article_content);

// Set the values for the views
        articleImage.setImageResource(R.drawable.article_img);
        articleTitle.setText("Article on Junk Mapper");
        articleContent.setText("As we become more aware of the impact our actions have on the environment, recycling has become an essential part of our daily lives. However, it can be confusing to know which items can be recycled and how to properly dispose of them. In this article, we'll go over some common household items and how to recycle them. ♻️\n" +
                "\n" +
                "\uD83E\uDDF4 Plastic Bottles\n" +
                "Most plastic bottles can be recycled, but it's important to make sure they're clean and free of any liquid or food residue. Remove the bottle cap and place it in the recycling bin separately. If the bottle is labeled with a recycling symbol, check to see if your local recycling program accepts that type of plastic.\n" +
                "\n" +
                "\uD83D\uDCDC Paper Products\n" +
                "Paper products such as newspapers, magazines, and cardboard boxes can be recycled. Be sure to remove any plastic or metal components, such as plastic windowpanes in envelopes or metal clasps on folders, before recycling. Shredded paper should be put in a separate bag or bin to prevent it from getting caught in the recycling machinery.\n" +
                "\n" +
                "\uD83E\uDD6B Aluminum Cans\n" +
                "Aluminum cans are highly recyclable and can be turned into new cans or other products. Rinse the can out and remove the pull tab, if applicable, before recycling. Check with your local recycling program to see if they accept aluminum foil and other aluminum products.\n" +
                "\n" +
                "\uD83C\uDF76 Glass Bottles and Jars\n" +
                "Glass bottles and jars can be recycled, but they need to be clean and free of any food or liquid residue. Remove any lids or caps and place them in the recycling bin separately. Broken glass should not be placed in the recycling bin, but can be taken to a recycling center.\n" +
                "\n" +
                "\uD83D\uDCBB Electronics\n" +
                "Electronics such as cell phones, computers, and televisions contain hazardous materials and should not be placed in the regular recycling bin. Instead, take them to a designated electronics recycling center where they can be properly disposed of. ♻️\uD83D\uDD0C\n" +
                "\n" +
                "\n" +
                "By following these simple guidelines, you can help reduce waste and protect the environment. Be sure to check with your local recycling program for specific guidelines and instructions on how to properly dispose of recyclable items in your area. Together, we can make a difference in creating a cleaner, more sustainable future. \uD83C\uDF0D\uD83C\uDF31");
    }

}