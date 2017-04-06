package com.example.contact;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactListAdapterViewHolder extends RecyclerView.ViewHolder {
    public final TextView mNameView;
    public final ImageView mDeleteView;

    public ContactListAdapterViewHolder(View itemView) {
        super(itemView);
        mNameView = (TextView) itemView.findViewById(R.id.contact_name);
        mDeleteView = (ImageView) itemView.findViewById(R.id.deletebutton);

    }
}
