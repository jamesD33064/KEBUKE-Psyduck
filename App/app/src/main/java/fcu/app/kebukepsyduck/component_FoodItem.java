package fcu.app.kebukepsyduck;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class component_FoodItem extends LinearLayout{

    private ImageView img_component_FoodItem;
    private TextView name_component_FoodItem;
    private TextView price_component_FoodItem;


    public component_FoodItem(Context context) {
        super(context);
        init(context);
    }

    public component_FoodItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.component_FoodItem);
        int count = typedArray.getIndexCount();

        try{
            for (int i=0 ; i<count ; i++){
                int attr = typedArray.getIndex(i);
                if (attr == R.styleable.component_FoodItem_imgSrc){
                    setProductImage(typedArray.getResourceId(attr, R.drawable.breakfast_eggcake));
                } else if (attr == R.styleable.component_FoodItem_textName){
                    setProductName(typedArray.getString(attr));
                } else if (attr == R.styleable.component_FoodItem_textPrice){
                    setProductPrice(typedArray.getString(attr));
                }
            }
        }finally {
            typedArray.recycle();
        }
    }

    public component_FoodItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.component_fooditem, this);

        img_component_FoodItem = findViewById(R.id.img_component_FoodItem);
        name_component_FoodItem = findViewById(R.id.name_component_FoodItem);
        price_component_FoodItem = findViewById(R.id.price_component_FoodItem);

    }

    public void setProductImage(int imgId) {
        img_component_FoodItem.setImageResource(imgId);
    }

    public void setProductName(String name) {
        name_component_FoodItem.setText(name);
    }

    public void setProductPrice(String price) {
        price_component_FoodItem.setText(price);
    }


}
