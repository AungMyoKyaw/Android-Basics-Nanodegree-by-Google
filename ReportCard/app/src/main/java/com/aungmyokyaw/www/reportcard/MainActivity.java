package com.aungmyokyaw.www.reportcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageView proc = (ImageView) findViewById(R.id.profilePic);
        proc.setImageResource(R.drawable.proc_pic);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText("ABA");

//        ArrayList<student> students = getStudent();
        ArrayList<subject> subjects = getSubjects();

        subjectAdapter adapter = new subjectAdapter(this,subjects);

        ListView listView = (ListView) findViewById(R.id.subject);
        listView.setAdapter(adapter);

    }

    private ArrayList<student> getStudent(){
        ArrayList<student> students = new ArrayList<student>();

        students.add(new student("ABA",R.drawable.proc_pic,100,100,100,100,100,100));

        return students;
    }

    private ArrayList<subject> getSubjects(){
        ArrayList<subject> subjects = new ArrayList<subject>();

        subjects.add(new subject("Burmese",100));
        subjects.add(new subject("English",100));
        subjects.add(new subject("Maths",100));
        subjects.add(new subject("Chemistry",100));
        subjects.add(new subject("Physics",100));
        subjects.add(new subject("Bio",100));

        return subjects;
    }
}
