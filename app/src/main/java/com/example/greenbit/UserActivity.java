package com.example.greenbit;

import java.util.ArrayList;

import com.example.adapter.AdapterRecyclerArticle.RecyclerViewListener;
import com.example.controller.HomeFragment;
import com.example.fragment.ArticleListFragment;
import com.example.fragment.CardExpandFragment;
import com.example.object.ArticleItem;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.TransactionTooLargeException;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity implements RecyclerViewListener {

	private DrawerLayout mDrawerLayout;
	private NavigationView mNavigationView;
	private Toolbar mToolbar;
	private ActionBarDrawerToggle mDrawerToggle;
	private OnNavigationItemSelectedListener mItemListener;
	private SharedPreferences mSharedPreferences;
	private Fragment fragment = null;
	private ActionBar mActionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		initialization();

		DisplayView(R.id.home);
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.frameLayout, fragment).commit();

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(UserActivity.this, mDrawerLayout, R.string.activity_user, R.string.activity_user){

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				// TODO Auto-generated method stub
				super.onDrawerSlide(drawerView, 0);
			}

			@Override
			public void onDrawerStateChanged(int newState) {
				// TODO Auto-generated method stub
				super.onDrawerStateChanged(newState);
			}

		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerToggle.syncState();

		mNavigationView.setNavigationItemSelectedListener(OnItemClickEvent(mItemListener));
	}

	private void initialization(){
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		mNavigationView = (NavigationView) findViewById(R.id.navigationView);
		mSharedPreferences = UserActivity.this.getSharedPreferences("UserPrefs", Context.MODE_APPEND);
	}

	private OnNavigationItemSelectedListener OnItemClickEvent(OnNavigationItemSelectedListener listener){
		listener = new OnNavigationItemSelectedListener() {

			@Override
			public boolean onNavigationItemSelected(MenuItem mItem) {
				// TODO Auto-generated method stub
				if(mItem.isChecked())
					mItem.setChecked(false);
				else
					mItem.setChecked(true);

				mDrawerLayout.closeDrawers();

				DisplayView(mItem.getItemId());

				if(fragment != null){
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.replace(R.id.frameLayout, fragment).commit();
					return true;
				}
				return false;
			}
		};

		return listener;
	}

	private void DisplayView(int resource){
		switch (resource) {
			case R.id.home:
				fragment = new HomeFragment();
				break;

			case R.id.article:
				fragment = new ArticleListFragment();
				break;

			case R.id.logout:
				Intent intent = new Intent(UserActivity.this, MainActivity.class);
				SharedPreferences.Editor mEditor = mSharedPreferences.edit();
				mEditor.putString("login", null);
				mEditor.commit();
				startActivity(intent);
				break;

			default:
				break;
		}
	}

	/* private void InflateCustomActionBar(){
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.actionbar, null);
		mActionBar.setCustomView(view);
		mActionBar.setDisplayShowCustomEnabled(true);
	} */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void getCardView(View v, ArrayList<ArticleItem> list, int pos) {
		// TODO Auto-generated method stub
		System.out.println("View: "+v+"Pos: "+pos+"ArrayList: "+list);
		ArticleItem item = list.get(pos);
		CardExpandFragment mCardFragment = new CardExpandFragment(item.getTitle(), item.getContent(), item.getCreated(), item.getCreator());
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.frameLayout, mCardFragment).commit();
	}
}
