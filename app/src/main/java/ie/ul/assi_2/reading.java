package ie.ul.assi_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class reading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("contents")
                .orderBy("time", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String article = "";
                        if(task.isSuccessful()) {
                            int i = 0;
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                String title = document.get("title").toString();
                                String author = document.get("author").toString();
                                String content = document.get("content").toString();
                                article = article + title + "\t - by: " + author + "\n" + content + "\n\n";
                                i++;
                                if(i >= 5) {
                                    break;
                                }
                            }
                            TextView textView = findViewById(R.id.article);
                            textView.setText(article);
                        }
                    }
                });
        //roll
        TextView textView = (TextView)findViewById(R.id.article);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public void onClickJump(View view){ //jump to the post page
        Intent intent = new Intent(this, post.class);
        startActivity(intent);
    }
}
