package com.onlineshop.AdminCheckingOrders;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.onlineshop.Model.Orders;
import com.onlineshop.R;

public class AdminNewOrdersActivity extends AppCompatActivity {
    private RecyclerView orderList;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_orders);
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders");
        orderList = findViewById(R.id.orders_list);
        orderList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Orders> options = new FirebaseRecyclerOptions.Builder<Orders>()
                .setQuery(ordersRef, Orders.class)
                .build();
        FirebaseRecyclerAdapter<Orders, AdminOrdersViewHolder> adapter = new FirebaseRecyclerAdapter<Orders, AdminOrdersViewHolder>(options) {
            @NonNull
            @Override
            public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
                return new AdminOrdersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull AdminOrdersViewHolder holder, final int position, @NonNull final Orders model) {
                holder.name.setText("Name: " + model.getName());
                holder.phone.setText("Phone Number: " + model.getPhone());
                holder.dateTime.setText("Order at: " + model.getDate() + "   -   " + model.getTime());
                holder.city.setText("City: " + model.getCity());
                holder.address.setText("Shipping address: " + model.getAddress());
                holder.showOrders.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userID = getRef(position).getKey();
                        Intent intent = new Intent(AdminNewOrdersActivity.this, AdminUserProductActivity.class);
                        intent.putExtra("uid", userID);
                        startActivity(intent);
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CharSequence options[] = new CharSequence[]{
                                "Yes",
                                "No"

                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(AdminNewOrdersActivity.this);
                        builder.setTitle("Have your shipped this order");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == 0) {
                                    String userID = getRef(position).getKey();
                                    RemoveOrder(userID);
                                } else {
                                    finish();
                                }
                            }
                        });
                        builder.show();
                    }
                });
            }
        };
        orderList.setAdapter(adapter);
        adapter.startListening();
    }

    private void RemoveOrder(String userID) {
        ordersRef.child(userID).removeValue();

    }

    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone, dateTime, city, address;
        private Button showOrders;

        public AdminOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            showOrders = itemView.findViewById(R.id.order_products);
            name = itemView.findViewById(R.id.your_name);
            phone = itemView.findViewById(R.id.your_phone_number);
            dateTime = itemView.findViewById(R.id.date_time);
            city = itemView.findViewById(R.id.your_city);
            address = itemView.findViewById(R.id.your_address);

        }
    }
}

