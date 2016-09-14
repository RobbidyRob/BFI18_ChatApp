package com.example.robbidyrob.chatapp;

import android.content.Context;

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage>{
    private TextView chatText;
    private List<ChatMessage> MessageList = new ArrayList<ChatMessage>();
    private LinearLayout layout;
    public ChatArrayAdapter(Context context, int textViewResourceId)
    {
        super(context, textViewResourceId);
        public void add(ChatMessage object)
        {
            MessageList.add(object);
            super.add(object);
        };

        public int getCount()
        {
            return this.MessageList.size();
        };

        public ChatMessage getItem(int index)
        {
            return this.MessageList.get(index);
        };

        public View getView(int position, View ConvertView, ViewGroup parent)
        {
            View v = ConvertView;
            if(v==null)
            {
                LayoutInflater inflater = (LayoutInflater)
                this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.chat, parent, false);
            }

            layout = (LinearLayout)v.findViewById(R.id.Message1);
            ChatMessage messageobj = getItem(position);
            chatText = (TextView)v.findViewById(R.id.SingleMessage);
            chatText.setText(messageobj.message);
            chatText.setBackgroundResource(messageobj.left ? R.drawable.bubble :R.drawable.bubble);
            layout.setGravity(messageobj.left?Gravity.LEFT:Gravity.RIGHT);
            return v;
        }
    }
}