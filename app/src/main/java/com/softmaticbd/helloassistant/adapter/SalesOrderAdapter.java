package com.softmaticbd.helloassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softmaticbd.helloassistant.R;
import com.softmaticbd.helloassistant.model.SalesOrderModel;
import com.softmaticbd.helloassistant.service.ClickListener;
import com.softmaticbd.helloassistant.viewholder.SalesOrderViewHolder;

import java.util.List;

public class SalesOrderAdapter extends RecyclerView.Adapter<SalesOrderViewHolder> {
    private Context context;
    private List<SalesOrderModel> orderList ;
    private SalesOrderModel salesOrder;
    private final ClickListener listener;
    private int ordQty = 0;

    public SalesOrderAdapter(Context context, List<SalesOrderModel> orderList, ClickListener listener) {
        this.context = context;
        this.orderList = orderList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SalesOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.sales_order_item_list,parent,false);
//        return new SalesOrderViewHolder(view);
        return new SalesOrderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sales_order_list, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final SalesOrderViewHolder holder, int position) {
        salesOrder = orderList.get(position);
        holder.tvOrdName.setText("Name : " + salesOrder.getName());
        holder.tvOrdCode.setText("Code : " + salesOrder.getCode());
        holder.tvOrdPrice.setText("Price : " + salesOrder.getPrice());
        holder.tvOrdSize.setText("Size : " + salesOrder.getSize());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
