package ly.generalassemb.drewmahrt.shoppinglistdetailview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistdetailview.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity
implements ShoppingListAdapter.OnItemSelectListener,
        DetailFragment.OnItemCheckedListener{

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.detail_fragment_container)==null){
            mTwoPane = false;
        }else
            mTwoPane = true;

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        //Setup the RecyclerView
        RecyclerView shoppingListRecyclerView = (RecyclerView) findViewById(R.id.shopping_list_recyclerview);

        ShoppingSQLiteOpenHelper db = ShoppingSQLiteOpenHelper.getInstance(this);
        List<ShoppingItem> shoppingList = db.getShoppingList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        shoppingListRecyclerView.setLayoutManager(linearLayoutManager);
        shoppingListRecyclerView.setAdapter(new ShoppingListAdapter(shoppingList, this));

    }

    @Override
    public void onItemSelect(int id) {
        if (mTwoPane){
            DetailFragment detailFragment = DetailFragment.newInstance(id);

            getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container,detailFragment).commit();
        }else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.ITEM_ID_KEY, id);
            startActivity(intent);
        }
    }

    @Override
    public void OnItemChecked(int id, boolean isDone) {

    }
}
