package com.softmaticbd.helloassistant.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.softmaticbd.helloassistant.R;
import com.softmaticbd.helloassistant.service.ClickListener;

import java.lang.ref.WeakReference;

public class SalesOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imgProduct,ivOrdInc,ivOrdDec;
    public TextView tvOrdName,tvOrdCode,tvOrdSize,tvOrdPrice,tvOrdQty;
    private WeakReference<ClickListener> listenerRef;
    private int qty;
    private boolean isInValidQty = false;

    public SalesOrderViewHolder(@NonNull View itemView, ClickListener listener) {
        super(itemView);
        listenerRef = new WeakReference<>(listener);
        tvOrdCode = itemView.findViewById(R.id.tvOrdCode);
        tvOrdName = itemView.findViewById(R.id.tvOrdName);
        tvOrdSize = itemView.findViewById(R.id.tvOrdSize);
        tvOrdPrice = itemView.findViewById(R.id.tvOrdPrice);
        tvOrdQty = itemView.findViewById(R.id.tvOrdQty);
        ivOrdInc = itemView.findViewById(R.id.orIncrementQty);
        ivOrdInc.setOnClickListener(this);
        ivOrdDec = itemView.findViewById(R.id.orDecrementQty);
        ivOrdDec.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == ivOrdDec.getId()){
            qty = qty - 1;
            if (qty <=0){
                Toast.makeText(view.getContext(),"Stock Quantity Can't Less than 1",Toast.LENGTH_LONG).show();
                isInValidQty = true;
                tvOrdQty.setText(String.valueOf(qty = 1));
            }
            tvOrdQty.setText(String.valueOf(qty));
        }
        if (view.getId() == ivOrdInc.getId()) {
            qty = qty + 1;
            tvOrdQty.setText(String.valueOf(qty));
        }
        listenerRef.get().onPositionClicked(getAdapterPosition());
    }
}
