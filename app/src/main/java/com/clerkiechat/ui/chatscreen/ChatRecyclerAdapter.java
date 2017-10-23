package com.clerkiechat.ui.chatscreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.clerkiechat.R;
import com.clerkiechat.model.MessageData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pranav Bhoraskar
 */

public class ChatRecyclerAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private String userId;
    private Context context;
    private List<MessageData> listOfMessages = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {
        if (userId.equals(listOfMessages.get(position).getUserId())) {
            return VIEW_TYPE_MESSAGE_SENT;
        }
        else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sent_message_row, parent, false);
            return new SendViewHolder(view);
        }
        else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.received_message_row, parent, false);
            return new ReceiveViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SendViewHolder) holder)
                        .bindData(listOfMessages.get(holder.getAdapterPosition()));
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceiveViewHolder) holder)
                        .bindData(listOfMessages.get(holder.getAdapterPosition()));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listOfMessages != null ? listOfMessages.size() : 0;
    }

    void setMessagesList(Context context, String userId, List<MessageData> listOfMessages) {
        this.context = context;
        this.userId = userId;
        this.listOfMessages = listOfMessages;
    }

    class SendViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sent_photoMessage)
        ImageView photoMessage;
        @BindView(R.id.messageText)
        TextView messageText;
        @BindView(R.id.nameText)
        TextView nameText;
        @BindView(R.id.sent_image_timestamp)
        TextView sentImageTimestamp;
        @BindView(R.id.sent_image_linear_layout)
        LinearLayout sentImageLinearLayout;
        @BindView(R.id.sent_text_timestamp)
        TextView sentTextTimestamp;
        @BindView(R.id.text_linear_layout)
        LinearLayout textLinearLayout;

        SendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(final MessageData messageData) {
            boolean isPhoto = messageData.getPhotoUrl() != null;

            if (isPhoto) {
                textLinearLayout.setVisibility(View.GONE);
                sentImageLinearLayout.setVisibility(View.VISIBLE);
                sentImageTimestamp.setText(messageData.getTimeStamp());
                photoMessage.setVisibility(View.VISIBLE);
                Glide.with(context).load(messageData.getPhotoUrl())
                        .centerCrop()
                        .placeholder(R.mipmap.no_image)
                        .into(photoMessage);

                photoMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, PhotoViewActivity.class);
                        intent.putExtra("image_url", messageData.getPhotoUrl());
                        context.startActivity(intent);
                    }
                });
            }

            else {
                sentImageLinearLayout.setVisibility(View.GONE);
                textLinearLayout.setVisibility(View.VISIBLE);
                messageText.setText(messageData.getMessageBody());
                sentTextTimestamp.setText(messageData.getTimeStamp());
            }
            nameText.setText(R.string.text_sender_you);
        }
    }

    class ReceiveViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.receivePhotoMessage)
        ImageView photoMessage;
        @BindView(R.id.receiveMessageText)
        TextView messageText;
        @BindView(R.id.receive_text_timestamp)
        TextView receiveTimestamp;
        @BindView(R.id.text_receive_linear_layout)
        LinearLayout textReceiveLinearLayout;
        @BindView(R.id.receive_image_timestamp)
        TextView receiveImageTimestamp;
        @BindView(R.id.receive_image_linear_layout)
        LinearLayout receiveImageLinearLayout;
        @BindView(R.id.nameText)
        TextView nameText;

        ReceiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(final MessageData messageData) {
            boolean isPhoto = messageData.getPhotoUrl() != null;

            if (isPhoto) {
                textReceiveLinearLayout.setVisibility(View.GONE);
                receiveImageLinearLayout.setVisibility(View.VISIBLE);
                photoMessage.setVisibility(View.VISIBLE);
                receiveImageTimestamp.setVisibility(View.VISIBLE);
                receiveImageTimestamp.setText(messageData.getTimeStamp());
                Glide.with(context).load(messageData.getPhotoUrl())
                        .centerCrop()
                        .placeholder(R.mipmap.no_image)
                        .into(photoMessage);

                photoMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, PhotoViewActivity.class);
                        intent.putExtra("image_url", messageData.getPhotoUrl());
                        context.startActivity(intent);
                    }
                });
            }

            else {
                receiveImageLinearLayout.setVisibility(View.GONE);
                textReceiveLinearLayout.setVisibility(View.VISIBLE);
                receiveTimestamp.setText(messageData.getTimeStamp());
                messageText.setText(messageData.getMessageBody());
            }
            nameText.setText(messageData.getSenderName());
        }
    }
}