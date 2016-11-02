package ly.generalassemb.drewmahrt.shoppinglistdetailview;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;


public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mItemId;

    private OnItemCheckedListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(int param1) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItemId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = (TextView) view.findViewById(R.id.detail_name);
        TextView description = (TextView) view.findViewById(R.id.detail_description);
        TextView price = (TextView) view.findViewById(R.id.detail_price);
        TextView category = (TextView) view.findViewById(R.id.detail_category);

        ShoppingItem selectedItem = ShoppingSQLiteOpenHelper.getInstance(getContext()).getShoppingItemById(mItemId);

        // Populate the TextViews
        name.setText(selectedItem.getName());
        description.setText(selectedItem.getDescription());
        category.setText(selectedItem.getType());

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        double priceValue = Double.valueOf(selectedItem.getPrice());
        price.setText(currencyFormat.format(priceValue));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemCheckedListener) {
            mListener = (OnItemCheckedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemSelectListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnItemCheckedListener{
        void OnItemChecked(int id, boolean isDone);
    }
}
