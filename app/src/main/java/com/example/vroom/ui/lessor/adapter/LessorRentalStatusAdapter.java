package com.example.vroom.ui.lessor.adapter;

import static com.example.vroom.R.drawable.perodua_bezza;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.ui.lessor.model.MyRentalStatusData;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LessorRentalStatusAdapter extends RecyclerView.Adapter<LessorRentalStatusAdapter.DesignViewHolder> {
    ArrayList<MyRentalStatusData> myRentalStatusData=new ArrayList<>();

    public LessorRentalStatusAdapter(ArrayList<MyRentalStatusData> myRentalStatusData) {
        this.myRentalStatusData=myRentalStatusData;
    }

    @NonNull
    @Override
    public LessorRentalStatusAdapter.DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_lessor_status,parent,false);
        return new LessorRentalStatusAdapter.DesignViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull LessorRentalStatusAdapter.DesignViewHolder holder, int position) {
        MyRentalStatusData currentVehicle= myRentalStatusData.get(position);

        Picasso.get().load("https://vroom.lepak.xyz/storage/picture/vehicle/"+currentVehicle.getPlatno()+".png").into(holder.iv_vehicle, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                holder.iv_vehicle.setImageResource(R.drawable.perodua_bezza);
            }
        });

        holder.tv_rental.setText(currentVehicle.getStatus());
        switch (currentVehicle.getStatus()){
            case "pending":
                holder.cl_rentalprogress.setVisibility(View.GONE);
                holder.btn_viewmessage.setVisibility(View.GONE);
                holder.tv_rental.setTextColor(Color.parseColor("#FFDC5D"));
                holder.iv_bar.setBackgroundColor(Color.parseColor("#FFDC5D"));
                break;

            case "accepted":
                holder.tv_datepayment.setText(currentVehicle.getDatepayment());
                holder.tv_pick.setText(currentVehicle.getDatepickup());
                holder.tv_return.setText(currentVehicle.getDatereturn());

                holder.ll_rentalactions.setVisibility(View.GONE);
                holder.btn_viewmessage.setVisibility(View.GONE);
                holder.tv_rental.setTextColor(Color.parseColor("#67E405"));
                holder.iv_bar.setBackgroundColor(Color.parseColor("#67E405"));

                if (currentVehicle.getProgress().equals("paid")){
                    holder.iv_progresspaid.setBackgroundResource(R.drawable.ic_baseline_done_24);
                    holder.tv_progresspaid.setText("Payment Is Done");
                }
                else if (currentVehicle.getProgress().equals("taken")){
                    holder.iv_progresspaid.setBackgroundResource(R.drawable.ic_baseline_done_24);
                    holder.iv_progresspick.setBackgroundResource(R.drawable.ic_baseline_done_24);
                    holder.tv_progresspaid.setText("Payment Is Done");
                    holder.tv_progresspick.setText("Car Already Been Picked Up");
                }
                else{
                    holder.iv_progresspaid.setBackgroundResource(R.drawable.ic_baseline_done_24);
                    holder.iv_progresspick.setBackgroundResource(R.drawable.ic_baseline_done_24);
                    holder.iv_progressreturn.setBackgroundResource(R.drawable.ic_baseline_done_24);
                    holder.tv_progresspaid.setText("Payment Is Done");
                    holder.tv_progresspick.setText("Car Already Been Picked Up");
                    holder.tv_progressreturn.setText("Car Has Benn Returned");
                }
                break;

            case "rejected":
                holder.ll_rentalactions.setVisibility(View.GONE);
                holder.cl_rentalprogress.setVisibility(View.GONE);
                holder.tv_rental.setTextColor(Color.parseColor("#E40505"));
                holder.iv_bar.setBackgroundColor(Color.parseColor("#E40505"));
                break;
        }

        holder.tv_lessesname.setText(currentVehicle.getLessesname());
        holder.tv_total.setText("Total Payment(RM): "+currentVehicle.getTotal());
        holder.tv_payment.setText("Payment Method: "+currentVehicle.getPaymenttype());
        holder.tv_date.setText("Date From To: "+currentVehicle.getDatepickup());
        holder.tv_location.setText("Pick Up Location: "+currentVehicle.getLocation());
        holder.btn_viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.btn_viewmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.tv_brand.setText(currentVehicle.getBrand());
        holder.tv_price.setText(currentVehicle.getPrice());
        holder.btn_passenger.setText(currentVehicle.getPassanger());
        holder.btn_door.setText(currentVehicle.getDoor());
        holder.btn_luggage.setText(currentVehicle.getLuggage());
        holder.btn_gas.setText(currentVehicle.getGas());

        holder.tv_rentallayout.setOnClickListener(new View.OnClickListener() {
            String detailexpand="close";
            @Override
            public void onClick(View v) {
                switch (detailexpand){
                    case "close":
                        holder.ll_rentalprogress.setVisibility(View.GONE);
                        detailexpand="open";
                        break;
                    case "open":
                        holder.ll_rentalprogress.setVisibility(View.VISIBLE);
                        detailexpand="close";
                        break;
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return myRentalStatusData.size();
    }


    public static class DesignViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_vehicle,iv_progresspaid,iv_progresspick,iv_progressreturn,iv_bar;
        TextView tv_brand,tv_price,tv_rental,tv_lessesname,
                tv_total,tv_payment,tv_date,tv_location,
                tv_datepayment,tv_pick,tv_return,tv_rentallayout,
                tv_progresspaid, tv_progresspick, tv_progressreturn;
        AppCompatButton btn_book,btn_reject;
        Button btn_passenger,btn_door,btn_luggage,btn_gas,btn_viewdetails,btn_viewmessage;
        LinearLayoutCompat cl_rentalprogress,ll_rentalprogress;
        LinearLayout ll_rentalactions;
        public DesignViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_bar=itemView.findViewById(R.id.iv_bar);
            tv_rental=itemView.findViewById(R.id.tv_rental);
            tv_lessesname=itemView.findViewById(R.id.tv_lessesname);
            tv_total=itemView.findViewById(R.id.tv_total);
            tv_payment=itemView.findViewById(R.id.tv_payment);
            tv_date=itemView.findViewById(R.id.tv_date);
            tv_location=itemView.findViewById(R.id.tv_location);
            btn_viewdetails=itemView.findViewById(R.id.btn_viewdetails);
            btn_viewmessage=itemView.findViewById(R.id.btn_viewmessage);

            tv_brand=itemView.findViewById(R.id.tv_brand);
            tv_price=itemView.findViewById(R.id.tv_price);
            iv_vehicle=itemView.findViewById(R.id.iv_vehicle);
            btn_passenger=itemView.findViewById(R.id.btn_passenger);
            btn_door=itemView.findViewById(R.id.btn_door);
            btn_luggage=itemView.findViewById(R.id.btn_luggage);
            btn_gas=itemView.findViewById(R.id.btn_gas);

            tv_datepayment=itemView.findViewById(R.id.tv_datepayment);
            tv_pick=itemView.findViewById(R.id.tv_pick);
            tv_return=itemView.findViewById(R.id.tv_return);
            cl_rentalprogress=itemView.findViewById(R.id.cl_rentalprogress);
            ll_rentalprogress=itemView.findViewById(R.id.ll_rentalprogress);
            tv_rentallayout=itemView.findViewById(R.id.tv_rentallayout);
            ll_rentalactions=itemView.findViewById(R.id.ll_rentalactions);
            btn_reject=itemView.findViewById(R.id.btn_reject);
            btn_book=itemView.findViewById(R.id.btn_book);
            iv_progresspaid=itemView.findViewById(R.id.iv_progresspaid);
            iv_progresspick=itemView.findViewById(R.id.iv_progresspick);
            iv_progressreturn=itemView.findViewById(R.id.iv_progressreturn);

            tv_progresspaid =itemView.findViewById(R.id.tv_progresspaid);
            tv_progresspick=itemView.findViewById(R.id.tv_progresspick);
            tv_progressreturn=itemView.findViewById(R.id.tv_progressreturn);
        }
    }
}