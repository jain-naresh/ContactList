package com.example.contact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapterViewHolder>  {
    private Context mContext = null;
    private final View mEmptyView;
    private ArrayList<UserModel> mContactList;


    public ContactListAdapter(Context context, View emptyView, ArrayList<UserModel> list) {
        mContext = context;
        mEmptyView = emptyView;
        mContactList = list;
    }

    @Override
    public ContactListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent instanceof RecyclerView) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
            ContactListAdapterViewHolder holder = new ContactListAdapterViewHolder(view);
            findAndCacheViews(holder);
            return holder;
        }
        return null;
    }


    @Override
    public long getItemId(int position) {
        mContactList.get(position);
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mContactList == null) {
            return 0;
        }
        return mContactList.size();
    }

    public void updateList(ArrayList<UserModel> newList) {
        if (newList != null) {
            mContactList.clear();
            mContactList = null;
        }
        mContactList = newList;
        if (mContactList != null) {
            notifyDataSetChanged();
        }
        mEmptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    public void updateListView() {
        notifyDataSetChanged();
        mEmptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }


   private void findAndCacheViews(ContactListAdapterViewHolder viewHolder) {
        viewHolder.itemView.findViewById(R.id.primary_action_view).setOnClickListener(mActionListener);
        View actionView = viewHolder.itemView.findViewById(R.id.secondary_action_icon);
        actionView.setOnClickListener(mActionListener);

    }

    @Override
    public void onBindViewHolder(ContactListAdapterViewHolder holder, int position) {

        mContactList.get(position);

        UserModel userModel = mContactList.get(position);
        String contactName = userModel.getName();
        String id = userModel.getId();

        holder.mNameView.setText(contactName);
        holder.mDeleteView.setOnClickListener(mActionListener);
        holder.mDeleteView.setTag(userModel);

    }

    private final View.OnClickListener mActionListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            if (viewId == R.id.deletebutton) {

                UserModel userModel = (UserModel) view.getTag();
                mContactList.remove(userModel);
                updateListView();
                AppPreferences.putBoolean(userModel.getId(),true,mContext);

            }
        }
    };

}