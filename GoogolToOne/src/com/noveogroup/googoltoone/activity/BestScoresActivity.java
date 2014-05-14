package com.noveogroup.googoltoone.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.*;
import com.noveogroup.googoltoone.database.ContentDescriptor;
import com.noveogroup.googoltoone.R;

// TODO: implement without support library yet
@SuppressLint("NewApi")
public class BestScoresActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private SimpleCursorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_scores);

        ListView listView = (ListView) findViewById(R.id.list);

        LoaderManager loaderManager = getLoaderManager();

        // TODO: NAMEs for IDs
        String[] colomns = new String[]{
                "b.name",
                ContentDescriptor.GamesWithPlayersNames.Cols.PLAYER1_SCORE,
                ContentDescriptor.GamesWithPlayersNames.Cols.PLAYER2_SCORE,
                "c.name"}; //TODO
        /*String[] colomns = new String[]{
                ContentDescriptor.Players.Cols.NAME,
                ContentDescriptor.Players.Cols.ID};*/
        int[] toFields = new int[]{
                R.id.item_player_one_name,
                R.id.item_player_one_score,
                R.id.item_player_two_score,
                R.id.item_player_two_name};

        adapter = new SimpleCursorAdapter(this, R.layout.best_scores_list_item, null, colomns, toFields, 0);
        listView.setAdapter(adapter);

        loaderManager.initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        //return new CursorLoader(BestScoresActivity.this, ContentDescriptor.GamesWithPlayersNames.TABLE_URI, null, null, null, null);
        return new CursorLoader(BestScoresActivity.this, ContentDescriptor.GamesWithPlayersNames.TABLE_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        if (adapter != null && cursor != null) {
            adapter.changeCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        if (adapter != null) {
            adapter.changeCursor(null);
        }
    }
}