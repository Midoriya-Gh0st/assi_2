package ie.ul.assi_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Script;
import android.text.SpannableString;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1beta1.FirestoreGrpc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);

        EditText tit = findViewById(R.id.title);
        SpannableString s = new SpannableString("Title");
        tit.setHint(s);

        EditText aut = findViewById(R.id.author);
        s = new SpannableString("Author");
        aut.setHint(s);

        EditText cot = findViewById(R.id.content);
        s = new SpannableString("Content");
        cot.setHint(s);
    }

    public void onClickPost(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference contents = db.collection("contents");

        EditText titleText = findViewById(R.id.title);
        EditText authorText = findViewById(R.id.author);
        EditText contentText = findViewById(R.id.content);

        String titleTxt = titleText.getText().toString();
        String authorTxt = authorText.getText().toString();
        String contentTxt = contentText.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
        String timeTxt = sdf.format(new Date());
        String timeName = System.currentTimeMillis() + " ";

        Map<String, Object> content = new HashMap<>();
        content.put("title", titleTxt);
        content.put("author", authorTxt);
        content.put("content", contentTxt);
        content.put("time", timeTxt);
        contents.document(timeName).set(content);

        Intent intent = new Intent(this, reading.class);
        startActivity(intent);
    }
}
