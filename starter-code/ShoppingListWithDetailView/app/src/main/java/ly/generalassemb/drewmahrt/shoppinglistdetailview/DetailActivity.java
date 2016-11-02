package ly.generalassemb.drewmahrt.shoppinglistdetailview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity implements DetailFragment.OnItemCheckedListener
{
    public static final String ITEM_ID_KEY = "itemIdKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get ID of selected item
        int selectedItemId = getIntent().getIntExtra(ITEM_ID_KEY, -1);

        // If we don't have a valid ID, no reason to continue
        if (selectedItemId == -1) {
            Log.d("DetailActivity", "onCreate: No ID passed on the intent!");
            finish();
        }
        DetailFragment detailFragment = DetailFragment.newInstance(selectedItemId);
        getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container,detailFragment).commit();
    }

    @Override
    public void OnItemChecked(int id, boolean isDone) {

    }
}
