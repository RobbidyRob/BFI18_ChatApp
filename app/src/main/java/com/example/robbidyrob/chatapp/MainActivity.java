package com.example.robbidyrob.chatapp;

import android.database.DataSetObservable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    private ChatArrayAdapter adp;
    private ListView list;
    private EditText chatText;
    private Button send;
    Intent intent;
    private boolean side = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.main);
        send = (Button) findViewById(R.id.btn);
        list = (ListView) findViewById(R.id.listview);
        adp = new ChatArrayAdapter(getApplicationContext(), R.layout.chat);
        list.setAdapter(adp);
        chatText = (EditText) findViewById(R.id.chat_text);
        chatText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            });
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    sendChatMessage();
                }
            });
            list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            list.setAdapter(adp);
            adp.registerDataSetObserver(new DataSetObserver() {
                public void OnChanged(){
                    super.onChanged();
                    list.setSelection(adp.getCount() -1);
                }
            });
            }
            private boolean sendChatMessage(){
                adp.add(new ChatMessage(side, chatText.getText().toString()));
                chatText.setText("");
                side = !side;
                return true;
            }
        )
    }
}