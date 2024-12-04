package com.example.sign_in_up.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sign_in_up.databinding.ItemContainerUserBinding;
import com.example.sign_in_up.listeners.UserListener;
import com.example.sign_in_up.models.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder>{

    private final List<User> users;
    private final UserListener userListener;

    /**
     * Constructor
     *
     * @param users        the list of users to display
     * @param userListener the listener to handle user click events
     */
    public UsersAdapter(List<User> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }


    /**
     * Creates a new UserViewHolder to represent a user item.
     *
     * @param parent   the parent ViewGroup
     * @param viewType the view type of the new View
     * @return a UserViewHolder
     */
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemContainerUserBinding itemContainerUserBinding = ItemContainerUserBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new UserViewHolder(itemContainerUserBinding);
    }

    /**
     * Binds user data to the given UserViewHolder.
     *
     * @param holder   the ViewHolder to bind data to
     * @param position the position of the user in the list
     */
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setUserData(users.get(position));

    }

    /**
     * Returns the total number of users in the list.
     *
     * @return the size of the user list
     */
    @Override
    public int getItemCount() {
        return users.size();
    }

    /**
     * ViewHolder class for holding and binding user data.
     */
    class UserViewHolder extends RecyclerView.ViewHolder{
        ItemContainerUserBinding binding;


        /**
         * Constructs a new UserViewHolder.
         *
         * @param itemContainerUserBinding the binding for the user item layout
         */
        public UserViewHolder(ItemContainerUserBinding itemContainerUserBinding) {
            super(itemContainerUserBinding.getRoot());

            binding = itemContainerUserBinding;
        }

        /**
         * Binds the data of a User to the views.
         *
         * @param user the user whose data is to be displayed
         */
        void setUserData(User user){
            binding.textName.setText(user.name);
            binding.textEmail.setText(user.email);
            binding.imageProfile.setImageBitmap(getUserImage(user.image));

            binding.getRoot().setOnClickListener(y -> userListener.onUserClicked(user));
        }
    }

    /**
     * Decodes a Base64-encoded image string into a Bitmap.
     *
     * @param encodedImage the Base64-encoded image string
     * @return a Bitmap representing the decoded image
     */
    private Bitmap getUserImage(String encodedImage){
        byte[] bytes = Base64.decode(encodedImage,Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

}
