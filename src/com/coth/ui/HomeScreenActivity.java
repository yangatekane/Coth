package com.coth.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import com.coth.adapters.ScriptureBasicDtoAdapter;
import com.coth.ui.fragments.HomeFragmentTab;
import com.coth.ui.fragments.MenuFragmentTab;
import com.coth.webservice.CAsyncTask;
import com.coth.webservice.objects.ScriptureBasicDto;
import com.coth.webservice.objects.enums.CothServiceException;
import com.coth.webservice.objects.results.ScriptureDataResult;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends CActivity {
    /**
     * Called when the activity is first created.
     */
    ListView list;
    ScriptureBasicDtoAdapter scriptureAdapter;
    ActionBar actionBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab home = actionBar.newTab().setIcon(R.drawable.gd_action_bar_home);
        ActionBar.Tab menu = actionBar.newTab().setIcon(R.drawable.actionbar_menu);
        Fragment menuFragmentTab = new MenuFragmentTab();
        Fragment homeFragmentTab = new HomeFragmentTab();
        home.setTabListener(new ActionBarTabsListener(homeFragmentTab));
        menu.setTabListener(new ActionBarTabsListener(menuFragmentTab));
        actionBar.addTab(home);
        actionBar.addTab(menu);
        //test data
        ScriptureBasicDto me = new ScriptureBasicDto();
        me.description = "The fear of the Lord is the Beginning(first) of ALL wisdom....!";
        me.first_name = "Bongani";
        me.last_name = "Msibi";
        me.imgAvatar = R.drawable.pastor;
        me.IsOnline = true;
        me.IsBusy = false;
        me.role = "pastor";
        me.title = "Pastor";
        ScriptureBasicDto me1 = new ScriptureBasicDto();
        me1.description = "And Jesus said ,He that heareth you heareth me; and he that despiseth you despiseth me; and he that despiseth me despiseth him that sent me. Therefore beloved love and receive the Man of God (Pastors) that God has entrusted for your lives. In doing so you receive also Jesus and the Father (Father God).I receive and appreciates Luke 10: 16";
        me1.first_name = "MV";
        me1.last_name = "Lephoko";
        me1.imgAvatar = R.drawable.bishop;
        me1.IsOnline = false;
        me1.IsBusy = true;
        me1.role = "bishop";
        me1.title = "Bishop";
        ScriptureBasicDto me2 = new ScriptureBasicDto();
        me2.description = "COTH CPT thats where our hearts and spirits are this weekend. Weekend filled with anointing, power, love, fun and change. CPT will never be the same again. People around CPT must not miss this powerful life changing experience. Youth of Honor and Vision your life will be Bigger , Brighter and Better Be@ CPOA...18:00pm tonight!";
        me2.first_name = "Bongani";
        me2.last_name = "Msibi";
        me2.imgAvatar = R.drawable.pastor;
        me2.IsOnline = true;
        me2.IsBusy = false;
        me2.role = "pastor";
        me2.title = "Pastor";
        ScriptureBasicDto me3 = new ScriptureBasicDto();
        me3.description = "And Jesus said ,He that heareth you heareth me; and he that despiseth you despiseth me; and he that despiseth me despiseth him that sent me. Therefore beloved love and receive the Man of God (Pastors) that God has entrusted for your lives. In doing so you receive also Jesus and the Father (Father God).I receive and appreciates Luke 10: 16";
        me3.first_name = "Aneziwe";
        me3.last_name = "Maganya";
        me3.imgAvatar = R.drawable.anez;
        me3.IsOnline = true;
        me3.IsBusy = false;
        me3.role = "member";
        me3.title = "Ms";
        ScriptureBasicDto me4 = new ScriptureBasicDto();
        me4.description = "And Jesus said ,He that heareth you heareth me; and he that despiseth you despiseth me; and he that despiseth me despiseth him that sent me. Therefore beloved love and receive the Man of God (Pastors) that God has entrusted for your lives. In doing so you receive also Jesus and the Father (Father God).I receive and appreciates Luke 10: 16";
        me4.first_name = "Zuzile";
        me4.last_name = "Mabuza";
        me4.imgAvatar = R.drawable.mabuza;
        me4.IsOnline = false;
        me4.IsBusy = false;
        me4.role = "member";
        me4.title = "Ms";
        ScriptureBasicDto me5 = new ScriptureBasicDto();
        me5.description = "And Jesus said ,He that heareth you heareth me; and he that despiseth you despiseth me; and he that despiseth me despiseth him that sent me. Therefore beloved love and receive the Man of God (Pastors) that God has entrusted for your lives. In doing so you receive also Jesus and the Father (Father God).I receive and appreciates Luke 10: 16";
        me5.first_name = "Yanga";
        me5.last_name = "Tekane";
        me5.imgAvatar = R.drawable.ta;
        me5.IsOnline = false;
        me5.IsBusy = false;
        me5.role = "member";
        me5.title = "Mr";
        ScriptureBasicDto me6 = new ScriptureBasicDto();
        me6.description = "And Jesus said ,He that heareth you heareth me; and he that despiseth you despiseth me; and he that despiseth me despiseth him that sent me. Therefore beloved love and receive the Man of God (Pastors) that God has entrusted for your lives. In doing so you receive also Jesus and the Father (Father God).I receive and appreciates Luke 10: 16";
        me6.first_name = "Yanga";
        me6.last_name = "Tekane";
        me6.imgAvatar = R.drawable.ta;
        me6.IsOnline = false;
        me6.IsBusy = false;
        me6.role = "member";
        me6.title = "Mr";

        //set list header
        list = (ListView) findViewById(R.id.homeScreenActivity_lvExperts);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View header = inflater.inflate(R.layout.home_screen_list_header, null);
        //View separator =inflater.inflate(R.layout.list_seperator,null);
        list.addHeaderView(header);
        //list.addView(separator);

        List<ScriptureBasicDto> Scriptures =  new ArrayList<ScriptureBasicDto>();
        Scriptures.add(me);
        Scriptures.add(me1);
        Scriptures.add(me2);
        Scriptures.add(me3);
        Scriptures.add(me4);
        Scriptures.add(me5);
        Scriptures.add(me6);
        scriptureAdapter = new ScriptureBasicDtoAdapter(HomeScreenActivity.this, Scriptures);
        list.setAdapter(scriptureAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        //loadExperts();
    }
    public void loadExperts(){
        //ok, here we get a list of the experts
        new CAsyncTask<Void, Void, ScriptureDataResult>() {

            @Override
            protected ScriptureDataResult doClientBackground(Void... params) throws Exception {
                ScriptureDataResult results = getCws().GetList((context));
                return results;
            }

            @Override
            protected void onClientPostExecute(ScriptureDataResult result) throws Exception {
                if (result.isSuccess()) {
                    scriptureAdapter.setScripture(result.Scriptures);
                    scriptureAdapter.notifyDataSetChanged();
                } else {
                    throw new CothServiceException(result.ServiceOperationOutcome);
                }
            }

            @Override
            protected void onClientPreExecute() {
            }

        }.setContext(this).execute();
    }
}
