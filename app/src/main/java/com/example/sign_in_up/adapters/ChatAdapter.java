package com.example.sign_in_up.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sign_in_up.databinding.ItemContainerRecievedMessageBinding;
import com.example.sign_in_up.databinding.ItemContainerSentMessageBinding;
import com.example.sign_in_up.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Bitmap receiverProfileImage;

    private final List<ChatMessage> chatMessages;

    private final String sendId;


    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;

    /**
     * Constructs a new ChatAdapter.
     *
     * @param chatMessages       the list of chat messages to display
     * @param receiverProfileImage the profile image of the receiver
     * @param sendId             the unique ID of the sender
     */
    public ChatAdapter( List<ChatMessage> chatMessages,Bitmap receiverProfileImage, String sendId) {
        this.chatMessages = chatMessages;
        this.receiverProfileImage = receiverProfileImage;
        this.sendId = sendId;
    }

    /**
     * Creates the appropriate ViewHolder based on the view type.
     *
     * @param parent   the parent ViewGroup
     * @param viewType the type of the view
     * @return a new ViewHolder for the given view type
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_SENT){
            return new SentMessageViewHolder(ItemContainerSentMessageBinding
                    .inflate(LayoutInflater.from(parent.getContext()),parent,false));
        } else {
            return new ReceiverMessageViewHolder(ItemContainerRecievedMessageBinding
                    .inflate(LayoutInflater.from(parent.getContext()),parent,false));
        }
    }

    /**
     * Binds data to the appropriate ViewHolder based on the position.
     *
     * @param holder   the ViewHolder to bind data to
     * @param position the position of the item in the list
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_SENT){
            ((SentMessageViewHolder)holder).setData(chatMessages.get(position));
        } else {
            ((ReceiverMessageViewHolder)holder)
                    .setData(chatMessages.get(position),receiverProfileImage);
        }
    }

    /**
     * Returns the total number of chat messages.
     *
     * @return the size of the chat message list
     */
    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    /**
     * Determines the view type for a given position.
     *
     * @param position the position of the item
     * @return VIEW_TYPE_SENT if the message is sent by the sender, otherwise VIEW_TYPE_RECEIVED
     */
    @Override
    public int getItemViewType(int position){
        if(chatMessages.get(position).senderId.equals(sendId)){
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    /**
     * ViewHolder for displaying sent messages.
     */
    static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        private final ItemContainerSentMessageBinding binding;

        /**
         * Constructs a new {@code SentMessageViewHolder}.
         *
         * @param itemContainerSentMessageBinding the binding for the sent message item layout
         */
        public SentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        /**
         * Binds the data of a sent message to the views.
         *
         * @param chatMessage the chat message to display
         */
        void setData(ChatMessage chatMessage) {
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
        }
    }

    /**
     * ViewHolder for displaying received messages.
     */
    static class ReceiverMessageViewHolder extends RecyclerView.ViewHolder {
        private final ItemContainerRecievedMessageBinding binding;

        /**
         * Constructs a new {@code ReceiverMessageViewHolder}.
         *
         * @param itemContainerRecievedMessageBinding the binding for the received message item layout
         */
        public ReceiverMessageViewHolder(ItemContainerRecievedMessageBinding itemContainerRecievedMessageBinding) {
            super(itemContainerRecievedMessageBinding.getRoot());
            binding = itemContainerRecievedMessageBinding;
        }

        /**
         * Binds the data of a received message to the views.
         *
         * @param chatMessage        the chat message to display
         * @param receiverProfileImage the profile image of the receiver
         */
        void setData(ChatMessage chatMessage, Bitmap receiverProfileImage) {
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
            binding.imageProfile.setImageBitmap(receiverProfileImage);
        }
    }
}
