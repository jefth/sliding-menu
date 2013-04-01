package com.slidingmenu.lib.app;

import android.os.Bundle;
import android.view.View;

import com.slidingmenu.lib.SlidingMenu;

public abstract class SlidingMenuActivity extends SlidingFragmentActivity {

	protected SlidingMenu slidingMenu;
	protected View contentView;
	protected View sidebarLeftView;
	protected View sidebarRightView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		slidingMenu  = getSlidingMenu();
		
		onConfigSlidingMenu(slidingMenu);
		
		contentView = onCreateContentView();
		sidebarLeftView = onCreateLeftView();
		sidebarRightView = onCreateRightView();
		
		setContentView(contentView);
		setBehindContentView(sidebarLeftView);
		if (sidebarRightView != null){
			slidingMenu.setSecondaryMenu(sidebarRightView);
		}else{
			slidingMenu.setMode(SlidingMenu.LEFT);
		}
	}
	
	// configuration of sliding menu
	public abstract void onConfigSlidingMenu(SlidingMenu slidingMenu);
	
	// create Main content view
	public abstract View onCreateContentView();
	
	// create Left side view
	public abstract View onCreateLeftView();
	
	// create Right side view
	public View onCreateRightView(){
		return null;
	}

}
