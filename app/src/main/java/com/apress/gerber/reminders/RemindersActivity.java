package com.apress.gerber.reminders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RemindersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        ListView mListView = (ListView)findViewById(R.id.reminders_list_view);
        // The arrayAdapter is the controller in our
        // model-view-controller relationship. (controller)
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                // context
                this,
                // layout (viewI
                R.layout.reminders_row,
                // row (view)
                R.id.row_text,
                // data (model) with bogus data to test our listview
                new String[]{"first record", "second record", "third record"}
        );

        mListView.setAdapter(arrayAdapter);
    }
    // Remainder of the class listing amitted for brevity

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_reminders,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new:
                //create new Reminder
                Log.d(getLocalClassName(), "create new Reminder");
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return false;
        }
    }


}
