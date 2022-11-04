package com.example.menuexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ActionMode myActionMode;
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView content = findViewById(R.id.tvContent);
        //registerForContextMenu(content);
        content.setOnLongClickListener(new View.OnLongClickListener() {
            // Called when the user long-clicks on someView
            @Override
            public boolean onLongClick(View view) {
                if (myActionMode != null) return false;

                myActionMode = MainActivity.this.startActionMode(actionModeCallback);
                view.setSelected(true);
                return true;
            }
        });

        btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnClick);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(MainActivity.this, "You clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                // Show popup menu
                popupMenu.show();
            }
        });
    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.contextMenuCopy:
                    Toast.makeText(MainActivity.this, "Copy this content", Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    return true;
                case R.id.contextMenuPaste:
                    Toast.makeText(MainActivity.this, "Paste content", Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    return true;
                case R.id.contextMenuShare:
                    Toast.makeText(MainActivity.this, "Share this content", Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            myActionMode = null;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuFavorite:
                showActivity(FavoriteActivity.class);
                return true;
            case R.id.menuSetting:
                showActivity(SettingsActivity.class);
                return true;
            case R.id.menuAbout:
                showActivity(AboutActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contextMenuCopy:
                Toast.makeText(this, "Copy this content", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contextMenuPaste:
                Toast.makeText(this, "Paste content", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contextMenuShare:
                Toast.makeText(this, "Share this content", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}