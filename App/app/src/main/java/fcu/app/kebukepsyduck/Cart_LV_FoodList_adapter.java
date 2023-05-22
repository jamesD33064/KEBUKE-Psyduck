package fcu.app.kebukepsyduck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Cart_LV_FoodList_adapter extends BaseAdapter {
    private Context context;
    private List<FoodItem> listFoods;

    public Cart_LV_FoodList_adapter(Context context, List<FoodItem> listFoods) {
        this.context = context;
        this.listFoods = listFoods;
    }

    @Override
    public int getCount() {
        return listFoods.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.cart_fooditem_layout, viewGroup, false);
        }
        FoodItem food = listFoods.get(i);

        ImageView iv = view.findViewById(R.id.iv_foodimg);
        iv.setImageResource(food.getImgID());

        TextView iv_foodname = view.findViewById(R.id.tv_foodname);
        iv_foodname.setText(food.getFoodName());

        TextView tv_foodprice = view.findViewById(R.id.tv_foodprice);
        tv_foodprice.setText("$ " + food.getFoodPrice());

        return view;
    }
}
