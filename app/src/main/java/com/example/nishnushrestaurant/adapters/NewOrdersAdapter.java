package com.example.nishnushrestaurant.adapters;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nishnushrestaurant.R;
import com.example.nishnushrestaurant.helpClasses.Order;
import com.example.nishnushrestaurant.helpClasses.WayOfPayment;
import com.example.nishnushrestaurant.helpClasses.enums.OrderStatus;

import java.util.List;


public class NewOrdersAdapter extends RecyclerView.Adapter<NewOrdersAdapter.NewOrdersViewHolder> {


    Context context;
    List<Order> orderList;
    AnimationDrawable frameAnimation;
    ValueAnimator colorAnim;

    public NewOrdersAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;

    }


    @NonNull
    @Override
    public NewOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new NewOrdersViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NewOrdersViewHolder holder, int position) {


        holder.orderNumberTextView.setText("מספר הזמנה: " + orderList.get(position).getOrderNumber());
        holder.customerNameTextView.setText(orderList.get(position).getCostumerName());
        holder.hourOfOrderTextView.setText(orderList.get(position).getTime());
        holder.dateOfOrderTextView.setText(orderList.get(position).getDate());
        holder.orderCostTextView.setText(String.valueOf(orderList.get(position).getSumOfOrder()));



        holder.costumerNameFinishTextView.setText(orderList.get(position).getCostumerName());
        holder.hourOfFinishTextView.setText(orderList.get(position).getTime());
        holder.dateOfFinishTextView.setText(orderList.get(position).getDate());
//        holder.watchFinishOrderTextView.setText();


        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < orderList.get(position).getWayOfPayments().size(); i++) {

            if (orderList.get(position).getWayOfPayments().get(i).getWayOfPaymentEnum() == WayOfPayment.WayOfPaymentEnum.CASH) {

                stringBuilder.append("מזומן");
                stringBuilder.append(" ").append(" ₪ ");
                stringBuilder.append(orderList.get(position).getWayOfPayments().get(i).getAmountToPay());

            } else {
                stringBuilder.append("אשראי");
                stringBuilder.append(" ").append(" ₪ ");
                stringBuilder.append(orderList.get(position).getWayOfPayments().get(i).getAmountToPay());
                stringBuilder.append("(").append(orderList.get(position).getWayOfPayments().get(i).getCreditCard().toString());
            }


            stringBuilder.append(" | ");

        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        holder.wayOfPayingDetailsTextView.setText(stringBuilder);

        holder.addressTextView.setText(orderList.get(position).getAddressToDeliver().fullMyAddress());

        if (orderList.get(position).getNoteForDelivery() != null && !orderList.get(position).getNoteForDelivery().isEmpty()) {
            holder.noteForDeliverTextView.setText("הערות: " + orderList.get(position).getNoteForDelivery());
        }



        holder.orderDishesListView.setAdapter(new ClassificationAdapter(context, orderList.get(position).getOrder()));



       OrderStatus orderStatus = orderList.get(position).getOrderStatus();

        if (orderStatus == OrderStatus.NONE || orderStatus == OrderStatus.SEND){

            updateAcceptedStatusOrder(holder);

        }else if (orderStatus == OrderStatus.ACCEPTED){

            updatePaidOrderStatus(holder);

        }else if (orderStatus == OrderStatus.PAID){

            updateInProgressOrderStatus(holder);

        }else if (orderStatus == OrderStatus.IN_PROGRESS){

            updateFinishOrderUI(holder);

        }



        //FIRST
        holder.acceptedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frameAnimation.stop();
                colorAnim.cancel();

                holder.acceptedImageView.setBackgroundResource(R.drawable.green_background);
                holder.acceptedBtn.setTextColor(Color.WHITE);
                holder.acceptedBtn.setEnabled(false);
                holder.paidBtn.setEnabled(true);

                orderList.get(position).setOrderStatus(OrderStatus.ACCEPTED);
                updatePaidOrderStatus(holder);

                Log.i("ACCEPTED", "CLICKED");

            }
        });




        //SECOND
        holder.paidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frameAnimation.stop();
                colorAnim.cancel();

                holder.paidImageView.setBackgroundResource(R.drawable.green_background);
                holder.paidBtn.setTextColor(Color.WHITE);
                holder.paidBtn.setEnabled(false);
                holder.inProgressBtn.setEnabled(true);

                orderList.get(position).setOrderStatus(OrderStatus.PAID);
                updateInProgressOrderStatus(holder);

                Log.i("PAID", "CLICKED");

            }
        });




        //THIRD
        holder.inProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frameAnimation.stop();
                colorAnim.cancel();

                holder.inProgressBtn.setTextColor(Color.WHITE);
                holder.inProgressImageView.setBackgroundResource(R.drawable.green_background);
                holder.inProgressBtn.setEnabled(false);

                orderList.get(position).setOrderStatus(OrderStatus.IN_PROGRESS);
                updateFinishOrderUI(holder);

                Log.i("ACCEPTED", "IN PROGRESS");
                
//                orderList.remove(position);

            }
        });

    }



    private void updateFinishOrderUI(@NonNull NewOrdersViewHolder holder) {
        holder.finishOrderFrameLayout.setForeground(ContextCompat.getDrawable(context, R.drawable.finished_order_background));
        holder.finishOrderLinearLayout.setVisibility(View.VISIBLE);
        holder.parentConstrainLayout.setBackground(ContextCompat.getDrawable(context, android.R.color.transparent));
    }



    private void updateInProgressOrderStatus(@NonNull NewOrdersViewHolder holder) {
        holder.inProgressImageView.setBackgroundResource(R.drawable.btn_change_color_animation);
        frameAnimation = (AnimationDrawable) holder.inProgressImageView.getBackground();
        colorAnim = animationBackground(holder, 3);
        frameAnimation.start();
        colorAnim.start();
    }



    private void updatePaidOrderStatus(@NonNull NewOrdersViewHolder holder) {
        colorAnim = animationBackground(holder, 2);
        holder.paidImageView.setBackgroundResource(R.drawable.btn_change_color_animation);
        frameAnimation = (AnimationDrawable) holder.paidImageView.getBackground();
        frameAnimation.start();
        colorAnim.start();
    }



    private void updateAcceptedStatusOrder(@NonNull NewOrdersViewHolder holder) {
        holder.acceptedImageView.setBackgroundResource(R.drawable.btn_change_color_animation);
        frameAnimation = (AnimationDrawable) holder.acceptedImageView.getBackground();
        colorAnim = animationBackground(holder, 1);
        frameAnimation.start();
        colorAnim.start();
    }





    public ValueAnimator animationBackground(NewOrdersViewHolder holder, int which) {

        ValueAnimator colorAnim = new ValueAnimator();

        switch (which) {

            case 1:

                colorAnim = ObjectAnimator.ofInt(holder.acceptedBtn, "textColor", ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.text_grey));
                break;


            case 2:
                colorAnim = ObjectAnimator.ofInt(holder.paidBtn, "textColor", ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.text_grey));
                break;


            case 3:

                colorAnim = ObjectAnimator.ofInt(holder.inProgressBtn, "textColor", ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.text_grey));
                break;


        }


        colorAnim.setDuration(250);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);

        return colorAnim;
    }



    @Override
    public int getItemCount() {
        return orderList.size();
    }


    public class NewOrdersViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout parentConstrainLayout;
        FrameLayout finishOrderFrameLayout;
        LinearLayout finishOrderLinearLayout;
        TextView costumerNameFinishTextView, hourOfFinishTextView, dateOfFinishTextView, watchFinishOrderTextView;
        TextView orderNumberTextView, customerNameTextView, hourOfOrderTextView, dateOfOrderTextView, orderCostTextView, wayOfPayingDetailsTextView, addressTextView, noteForDeliverTextView;
        ListView orderDishesListView;
        AppCompatButton acceptedBtn, paidBtn, inProgressBtn;

        ImageView acceptedImageView, paidImageView, inProgressImageView;

        public NewOrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            orderNumberTextView = itemView.findViewById(R.id.order_num_text_view_order_item);
            customerNameTextView = itemView.findViewById(R.id.costumer_name_text_view_order_item);
            hourOfOrderTextView = itemView.findViewById(R.id.hour_of_order_text_view_order_item);
            dateOfOrderTextView = itemView.findViewById(R.id.date_of_order_text_view_order_item);
            orderCostTextView = itemView.findViewById(R.id.order_cost_text_view_order_item);
            wayOfPayingDetailsTextView = itemView.findViewById(R.id.way_of_payment_details_text_view_order_item);
            addressTextView = itemView.findViewById(R.id.address_order_text_view_order_item);
            noteForDeliverTextView = itemView.findViewById(R.id.note_for_deliver_text_view_order_item);

            orderDishesListView = itemView.findViewById(R.id.order_dishes_list_list_view_order_item);

            acceptedBtn = itemView.findViewById(R.id.order_accepted_btn_order_item);
            paidBtn = itemView.findViewById(R.id.order_paid_btn_order_item);
            inProgressBtn = itemView.findViewById(R.id.order_on_progress_btn_order_item);

            acceptedImageView = itemView.findViewById(R.id.order_accepted_image_view_order_item);
            paidImageView = itemView.findViewById(R.id.order_paid_image_view_order_item);
            inProgressImageView = itemView.findViewById(R.id.order_on_progress_image_view_order_item);

            parentConstrainLayout = itemView.findViewById(R.id.constrain_layout_parent_order_item);
            finishOrderFrameLayout = itemView.findViewById(R.id.finished_order_constrain_layout_order_item);
            finishOrderLinearLayout = itemView.findViewById(R.id.finished_order_linear_layout_msg_area_order_item);


            costumerNameFinishTextView = itemView.findViewById(R.id.costumer_name_finished_order_text_view_order_item);
            hourOfFinishTextView = itemView.findViewById(R.id.hour_of_order_finish_text_view_order_item);
            dateOfFinishTextView = itemView.findViewById(R.id.date_of_order_finish_text_view_order_item);
            watchFinishOrderTextView = itemView.findViewById(R.id.watch_finish_order_text_view_order_item);

        }
    }
}
